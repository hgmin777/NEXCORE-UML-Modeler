/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.too.uml.core.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nexcore.tool.uml.core.util.Delegator;
import nexcore.tool.uml.core.util.IDelegate;

/**
 * com.lordjoe.csharp.TestDelegate2
 * 
 * @author Steve Lewis smlewis@lordjoe.com
 */
public class TestDelegate extends TestCase {
    /**
     * ITER_COUNT
     */
    public static final int ITER_COUNT = 10 * 1000 * 1000;

    /**
     * common interface - note No implementing class i.e. Class1 implements this
     */
    public static interface IStringDisplay {
        public void doDisplay(String s, String s1, String s2);
    }

    /**
     * TestDelegate
     * @param name
     */
    public TestDelegate(String name) {
        super(name);
    }

    /**
     * This section tests that DelegateTemplate fails when passed an unsuitable
     * interface
     */
    public static interface IBadStringDisplay {
        public void doDisplay(String s);

        public void doDisplay2(String s);
    }

    public static interface IBadStringDisplay2 {
    }

    /**
     * testDelegateBuild
     *   void
     */
    public void testDelegateBuild() {
        try {
            new Delegator(IBadStringDisplay.class);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new Delegator(IBadStringDisplay2.class);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * code to test calls to delegate
     */
    public void testDelegate() throws Exception {
        Delegator myDelegate = new Delegator(IStringDisplay.class);
        Class1 obj1 = new Class1();
        Class2 obj2 = new Class2();
        IStringDisplay[] items = new IStringDisplay[3];
        items[0] = (IStringDisplay) myDelegate.build(obj1, "show");
        items[1] = (IStringDisplay) myDelegate.build(obj2, "display");
        items[2] = (IStringDisplay) myDelegate.build(Class3.class, "staticDisplay");

        for (int i = 0; i < items.length; i++) {
            IStringDisplay item = items[i];
            item.doDisplay("test", "KKK", "JJJ");
        }
        // timingTest(items,obj1,obj2,ITER_COUNT);
    }

    /**
     * code to test calls to delegate
     */
    @SuppressWarnings("unchecked")
    public void testDelegateInvoke() throws Exception {
        Class[] params = { String.class };
        Delegator myDelegate = new Delegator(params, Void.TYPE);
        Class1 obj1 = new Class1();
        Class2 obj2 = new Class2();
        IDelegate[] items = new IDelegate[4];
        items[0] = myDelegate.build(obj1, "show");
        items[1] = myDelegate.build(obj2, "display");
        items[2] = myDelegate.build(Class3.class, "staticDisplay");

        for (int i = 0; i < items.length; i++) {
            IDelegate item = items[i];
            item.invoke("test");
        }
    }

    /**
     * Test of timing - note set iteration large i.e. 1000000 for resaonbale
     * results
     */
    // public void timingTest(IStringDisplay[] items,Class1 obj1,Class2 obj2,int
    // iterations)
    // {
    // // Warm up hotspot
    // for(int k = 0; k < 100; k++) {
    // for(int i = 0; i < items.length; i++) {
    // IStringDisplay item = items[i];
    // item.doDisplay("test");
    // }
    // obj1.show("test");
    // obj2.display("test");
    // Class3.staticDisplay("test");
    // }
    // long start = System.currentTimeMillis();
    // for(int j = 0; j < iterations; j++) {
    // for(int i = 0; i < items.length; i++) {
    // IStringDisplay item = items[i];
    // item.doDisplay("test");
    // }
    // }
    // long end = System.currentTimeMillis();
    // double delegateTime = (end - start) / 1000;
    // double perIteration = 1000 * 1000 * delegateTime / iterations;
    //
    // start = System.currentTimeMillis();
    // for(int j = 0; j < iterations; j++) {
    // obj1.show("test");
    // obj2.display("test");
    // Class3.staticDisplay("test");
    // }
    // end = System.currentTimeMillis();
    // double directTime = (end - start) / 1000;
    // double perCallIteration = 1000 * 1000 * directTime / iterations;
    //
    // System.out.println("Ran " + iterations + " iterations ");
    // System.out.println("Delegate Test took " + delegateTime + "sec");
    // System.out.println("per iteration " + perIteration + "microsec");
    // System.out.println("Direct Calls took " + directTime + "sec");
    // System.out.println("per iteration " + perCallIteration + "microsec");
    //
    //
    // }

    public static Test suite() {
        return new TestSuite(TestDelegate.class);
    }

    /**
     * main
     *  
     * @param args void
     */
    public static void main(String[] args) {
        String[] RealArgs = { "com.lordjoe.csharp.TestDelegate3" };
        // junit.swingui.TestRunner.main(RealArgs);
        junit.textui.TestRunner.main(RealArgs);
    }

}

/**
 * some classes we need to call method body might printout fir vicibility or
 * increment a variable for timing test (to prevent optimizing out)
 */
class Class1 {
    /**
     * count
     */
    int count;

    /**
     * show
     *  
     * @param s
     * @param s1
     * @param ss void
     */
    public void show(String s, String s1, String ss) {
        count++; // System.out.println(s);
    }

    /**
     * show1
     *   void
     */
    public void show1() {
        count++; // System.out.println(s);
    }
}

class Class2 {
    /**
     * count
     */
    int count;

    /**
     * display
     *  
     * @param s
     * @param s1
     * @param ss void
     */
    public void display(String s, String s1, String ss) {
        count++;// System.out.println(s);
    }
}

/**
 * here the methos is static
 */
class Class3 { // allows static method as well
    /**
     * count
     */
    static int count;

    /**
     * staticDisplay
     *  
     * @param s
     * @param s1
     * @param ss void
     */
    public static void staticDisplay(String s, String s1, String ss) {
        count++; // System.out.println(s);
    }
}
