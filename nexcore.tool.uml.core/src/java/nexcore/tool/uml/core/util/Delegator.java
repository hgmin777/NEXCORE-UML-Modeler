/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.core.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설 명 : Delegator</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class Delegator {
    /** DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE_IMPLEMENTING_EXACTLY_METHOD */
    private static final String DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE_IMPLEMENTING_EXACTLY_METHOD = "DelegateTemplate must be constructed  with an interface implementing exactly method!";

    /** DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE_IMPLEMENTING_ONLY_ONE_METHOD */
    private static final String DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE_IMPLEMENTING_ONLY_ONE_METHOD = "DelegateTemplate must be constructed  with an interface implementing only one method!";

    /** DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE */
    private static final String DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE = "DelegateTemplate must be constructed with an interface";

    /** NO_SUITABLE_METHOD_FOUND */
    private static final String NO_SUITABLE_METHOD_FOUND = "No suitable method found";

    /** REQUESTED_METHOD_IS_NOT_PUBLIC */
    private static final String REQUESTED_METHOD_IS_NOT_PUBLIC = "Requested method is not public";

    /** REQUESTED_METHOD_RETURNS_WRONG_TYPE */
    private static final String REQUESTED_METHOD_RETURNS_WRONG_TYPE = "Requested method returns wrong type";

    /** Dummy Array for operations */
    public static final Method[] EMPTY_METHOD_ARRAY = {};

    /** Dummy Array for objects */
    public static final Object[] EMPTY_OBJECT_ARRAY = {};

    /** Dummy Array for delegator */
    public static final Delegator[] EMPTY_ARRAY = {};

    /** Singleton of delegate */
    public static final Delegator RUNNABLE_DELEGATE = new Delegator(Runnable.class);

    /**
     * Convenience method to make a runnable delegate
     * 
     * @param item
     *            non-null target object
     * @param methodName
     *            non-null name of a method of type void ()
     * @return non-null Runnable proxy
     */
    public static Runnable buildRunnable(Object item, String methodName) {
        return ((Runnable) RUNNABLE_DELEGATE.build(item, methodName));
    }

    /**
     * Convenience method to make a runnable delegate
     * 
     * @param item
     *            non-null target class
     * @param methodName
     *            non-null name of a method of type void ()
     * @return non-null Runnable proxy
     */
    @SuppressWarnings("unchecked")
    public static Runnable buildRunnable(Class item, String methodName) {
        return ((Runnable) RUNNABLE_DELEGATE.build(item, methodName));
    }

    /** to be assigned interface as delegate */
    @SuppressWarnings("unchecked")
    private final Class theInterface; // may be null

    /** return classes */
    @SuppressWarnings("unchecked")
    private final Class theReturn;

    /** arguments to be used in delegate */
    @SuppressWarnings("unchecked")
    private final Class[] theArgument;

    /**
     * @param params
     *            non-null array of arguments
     * @param retClass
     *            possibly null return class null says do not care
     */
    @SuppressWarnings("unchecked")
    public Delegator(Class[] params, Class retClass) {
        theInterface = null;
        theReturn = retClass;
        theArgument = params;
    }

    /**
     * @param TheInterface
     *            an non-null interface with EXACTLY one method
     */
    @SuppressWarnings("unchecked")
    public Delegator(Class TheInterface) {
        theInterface = TheInterface;
        Method met = findMethod(TheInterface);
        theReturn = met.getReturnType();
        theArgument = met.getParameterTypes();
    }

    /**
     * accessor for return class
     */
    @SuppressWarnings("unchecked")
    public Class getReturn() {
        return theReturn;
    }

    /**
     * accessor for argument classes
     */
    @SuppressWarnings("unchecked")
    public Class[] getArguments() {
        return theArgument;
    }

    /**
     * 
     * 
     * @return Class
     */
    @SuppressWarnings("unchecked")
    public Class getInterface() {
        return theInterface;
    }

    /**
     * 
     * @param target
     *            non-null class with a bindable static method
     * @param MethodName
     *            name of the static method
     * @return non-null IDelegate if getInterface() is non-null it will be a
     *         dynamic prozy implementing that interface
     */
    @SuppressWarnings("unchecked")
    public IDelegate build(Class target, String MethodName) {
        Class myInterface = getInterface();
        DelegateProxy theDelegate = new DelegateProxy(null, target, MethodName, this);
        if (myInterface != null) {
            Class[] interfaces = { myInterface, IDelegate.class };
            IDelegate ret = (IDelegate) java.lang.reflect.Proxy.newProxyInstance(target.getClassLoader(),
                interfaces,
                theDelegate);
            return (ret);

        }
        return ((IDelegate) theDelegate);
    }

    /**
     * 
     * @param target
     *            non-null target with a bindable method
     * @param MethodName
     *            name of the method
     * @return non-null IDelegate if getInterface() is non-null it will be a
     *         dynamic prozy implementing that interface
     */
    @SuppressWarnings("unchecked")
    public IDelegate build(Object target, String MethodName) {
        Class myInterface = getInterface();
        DelegateProxy theDelegate = new DelegateProxy(target, target.getClass(), MethodName, this);
        if (myInterface != null) { // build a dynamic proxy
            Class[] interfaces = { myInterface, IDelegate.class };
            IDelegate ret = (IDelegate) java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),
                interfaces,
                theDelegate);
            return (ret);

        }
        if (!(theDelegate instanceof IDelegate))
            throw new ClassCastException();
        return ((IDelegate) theDelegate);
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
     * <li>설 명 : DelegateProxy</li>
     * <li>작성일 : 2009. 12. 3.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    protected class DelegateProxy implements IDelegate, InvocationHandler {
        /** MUST_BE_OF_CLASS */
        private static final String MUST_BE_OF_CLASS = " must be of class ";

        /** ARGUMENT */
        private static final String ARGUMENT = "Argument ";

        /** ARGUMENTS */
        private static final String ARGUMENTS = "arguments";

        /** DELEGATE_REQUIRED */
        private static final String DELEGATE_REQUIRED = "Delegate required ";

        /** BAD_DELGATE_STATE */
        private static final String BAD_DELGATE_STATE = "Bad Delgate State";

        /** theMethod */
        private final Method theMethod;

        /** theTarget */
        private final Object theTarget;

        /**
         * constructor supplying a Class passing in types not template
         * 
         * @param target
         *            possibly null target if null the method must be static
         * @param target
         *            non-null class implementing a suitable method
         * @param target
         *            non-null object implementing a suitable static method
         * @param MethodName
         *            nun-null name of a public static method in target
         * @param template
         *            non-null template with the required arguemts and return
         */
        @SuppressWarnings("unchecked")
        protected DelegateProxy(Object target, Class targetClass, String MethodName, Delegator template) {
            theTarget = target;
            Method meth = findSuitableMethod(targetClass, MethodName, template);
            theMethod = meth;
        }

        /**
         * convenience call to handle case of no arguments
         * 
         * @return whatever is returned
         */
        public Object invoke() throws IllegalArgumentException, DelegateInvokeException {
            return (invoke(EMPTY_OBJECT_ARRAY));
        }

        /**
         * convenience call to handle case of one argument
         * 
         * @param arg
         *            some argument
         * @return whatever is returned
         */
        public Object invoke(Object arg) throws IllegalArgumentException, DelegateInvokeException {
            Object[] args = { arg };
            return (invoke(args));
        }

        /**
         * convenience call to handle case of two argument
         * 
         * @param arg1
         *            some argument
         * @param arg2
         *            some argument
         * @return whatever is returned
         */
        public Object invoke(Object arg1, Object arg2) throws IllegalArgumentException, DelegateInvokeException {
            Object[] args = { arg1, arg2 };
            return (invoke(args));
        }

        /**
         * method required by InvocationHandler so we can build dynamic Proxys
         * 
         * @param proxy
         *            object for which we are a proxy (ignored)
         * @param method
         *            method to call (ignored)
         * @param args
         *            arguments to pass
         * @return whatever is returned primitive types are wrapped
         */
        public Object invoke(Object proxy, Method method, Object[] args) {
            return (invoke(args));
        }

        /**
         * basic call to method
         * 
         * @param args
         *            method arguments
         * @return whatever is returned
         */
        public Object invoke(Object[] args) throws IllegalArgumentException, DelegateInvokeException {
            // validateArgs(args);
            try {
                Object ret = getMethod().invoke(getTarget(), args);
                return (ret);
            } catch (IllegalAccessException ex1) {
                throw new IllegalStateException(BAD_DELGATE_STATE + ex1.getMessage()); // should
                                                                                       // not
                                                                                       // happen
            } catch (InvocationTargetException ex1) {
                throw new Delegator.DelegateInvokeException(ex1.getCause());
            }
        }

        /**
         * if uncommented in invoke this code will throw an IllegalArgument call
         * if arguments are of the wrong type
         */
        @SuppressWarnings("unchecked")
        protected void validateArgs(Object[] args) throws IllegalArgumentException {
            Class[] myArg = getArguments();
            if (args.length != myArg.length)
                throw new IllegalArgumentException(DELEGATE_REQUIRED + myArg.length + ARGUMENTS);
            for (int i = 0; i < args.length; i++) {
                if (!myArg[i].isInstance(args[i]))
                    throw new IllegalArgumentException(ARGUMENT + i + MUST_BE_OF_CLASS + myArg[i].getName());
            }
        }

        /**
         * accessor for the method
         */
        public Method getMethod() {
            return theMethod;
        }

        /**
         * accessor for the target
         */
        public Object getTarget() {
            return theTarget;
        }

    }// end class DelegateProxy

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
     * <li>설 명 : DelegateInvokeException</li>
     * <li>작성일 : 2009. 12. 3.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    @SuppressWarnings("serial")
    public static class DelegateInvokeException extends RuntimeException {
        public DelegateInvokeException(Throwable cause) {
            super(cause);
        }
    }

    // ===================================================================
    // static utility methods in this section identify the
    // method in verious targets
    // ===================================================================

    /**
     * utility method to test suitability
     */
    @SuppressWarnings("unchecked")
    protected static boolean isSuitableMethod(Method testMethod, Class[] args, Class retClass) {
        Class[] methodArgs = testMethod.getParameterTypes();
        Class arg;
        for (int i = 0; i < methodArgs.length; i++) {
            arg = methodArgs[i];
            if (!arg.isAssignableFrom(args[i]))
                return (false);
        }
        // This is the only
        isValidReturn(testMethod, retClass);
        return (true);
    }

    /**
     * utility method to get candidate methods to search
     */
    @SuppressWarnings("unchecked")
    protected static Method[] getCandidateMethods(Class targetClass, String MethodName, int nargs) {
        Method[] possibilities = targetClass.getMethods();
        List holder = new ArrayList();
        Method possibility;

        for (int i = 0; i < possibilities.length; i++) {
            possibility = possibilities[i];
            if (possibility.getName().equals(MethodName) && possibility.getParameterTypes().length == nargs
                && Modifier.isPublic(possibility.getModifiers()))
                holder.add(possibility);
        }
        return ((Method[]) holder.toArray(EMPTY_METHOD_ARRAY));
    }

    /**
     * utility method to test return
     */
    @SuppressWarnings("unchecked")
    protected static boolean isValidReturn(Method test, Class retClass) {
        if (retClass == null)
            return (true); // we do not care
        if (test.getReturnType() == retClass)
            return (true);
        if (retClass.isAssignableFrom(test.getReturnType()))
            return (true);
        return (false);
    }

    /**
     * Utility method to locate a proper Method object
     */
    @SuppressWarnings("unchecked")
    protected static Method findSuitableMethod(Class targetClass, String MethodName, Delegator templ) {
        Class[] args = templ.getArguments();
        Class retClass = templ.getReturn();
        // perfect match
        try {
            Method ret = targetClass.getMethod(MethodName, args);
            if (!isValidReturn(ret, retClass))
                throw new IllegalArgumentException(REQUESTED_METHOD_RETURNS_WRONG_TYPE);
            if (!Modifier.isPublic(ret.getModifiers()))
                throw new IllegalArgumentException(REQUESTED_METHOD_IS_NOT_PUBLIC);
            return (ret);
        } catch (Exception ex) {} // on to try2
        Method[] possibilities = getCandidateMethods(targetClass, MethodName, args.length);
        for (int i = 0; i < possibilities.length; i++) {
            Method possibility = possibilities[i];
            if (isSuitableMethod(possibility, args, retClass))
                return (possibility);
        }
        throw new IllegalArgumentException(NO_SUITABLE_METHOD_FOUND);
    }

    /**
     * utility code to find the one suitable method in the passed in interface.
     */
    @SuppressWarnings("unchecked")
    protected static Method findMethod(Class TheInterface) {
        if (!TheInterface.isInterface())
            throw new IllegalArgumentException(DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE);
        Method[] methods = TheInterface.getMethods();
        Method ret = null;
        Method test;
        for (int i = 0; i < methods.length; i++) {
            test = methods[i];
            if (Modifier.isAbstract(test.getModifiers())) {
                if (ret != null)
                    throw new IllegalArgumentException(DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE_IMPLEMENTING_ONLY_ONE_METHOD);
                ret = test;
            }
        }
        if (ret == null)
            throw new IllegalArgumentException(DELEGATE_TEMPLATE_MUST_BE_CONSTRUCTED_WITH_AN_INTERFACE_IMPLEMENTING_EXACTLY_METHOD);
        return (ret);
    }

}
