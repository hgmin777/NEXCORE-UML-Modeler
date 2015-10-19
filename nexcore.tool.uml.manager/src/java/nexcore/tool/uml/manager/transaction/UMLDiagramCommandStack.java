/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.commands.CommandStackListener;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.transaction</li>
 * <li>설 명 : UMLDiagramCommandStack</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class UMLDiagramCommandStack extends CommandStack implements org.eclipse.emf.common.command.CommandStackListener {

    /**
     * @see org.eclipse.gef.commands.CommandStack#flush()
     */
    @Override
    public void flush() {
        this.transactionEditingDomain.getCommandStack().flush();
        // super.flush();
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#markSaveLocation()
     */
    @Override
    public void markSaveLocation() {
        // this.transactionEditingDomain.getCommandStack().
        super.markSaveLocation();
    }

    // /** GEF Command or Transactional Command Flag*/
    // private boolean isGEFCommand = false;
    /** commandStackEventListenerList */
    private List<CommandStackEventListener> commandStackEventListenerList = new ArrayList<CommandStackEventListener>();

    /** commandStackListenerList */
    private List<CommandStackListener> commandStackListenerList = new ArrayList<CommandStackListener>();

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
     * <li>서브 업무명 : nexcore.tool.uml.manager.transaction</li>
     * <li>설 명 : EmptyCommand</li>
     * <li>작성일 : 2010. 1. 11.</li>
     * <li>작성자 : 한승일</li>
     * GEF Command Stack과 Transactional Command Stack동기를 위한 Empty스택
     * </ul>
     */
    public class EmptyCommand extends Command {
        /**
         * command
         */
        Command command;

        // /**
        // *
        // */
        // public EmptyCommand(){}
        /**
         * @param command
         */
        public EmptyCommand(Command command) {
            this.command = command;
        }

        /**
         * 
         * 
         * @return Command
         */
        public Command getInnerCommand() {
            return this.command;
        }
    }

    /**
     * 트랜잭션 커맨드 listener set void
     */
    private void set() {
        this.transactionEditingDomain.getCommandStack().addCommandStackListener(this);
    }

    /**
     * 트랜잭션 커맨드 listener unset void
     */
    private void unset() {
        this.transactionEditingDomain.getCommandStack().removeCommandStackListener(this);
    }

    /** transactionEditingDomain */
    protected TransactionalEditingDomain transactionEditingDomain = null;

    /**
     * 
     * 
     * @return TransactionalEditingDomain
     */
    public TransactionalEditingDomain getTransactionEditingDomain() {
        return transactionEditingDomain;
    }

    /**
     * 
     * 
     * @param transactionEditingDomain
     *            void
     */
    public void setTransactionEditingDomain(TransactionalEditingDomain transactionEditingDomain) {
        this.transactionEditingDomain = transactionEditingDomain;
        this.set();
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#addCommandStackEventListener(org.eclipse.gef.commands.CommandStackEventListener)
     */
    @Override
    public void addCommandStackEventListener(CommandStackEventListener listener) {
        if (!commandStackEventListenerList.contains(listener)) {
            commandStackEventListenerList.add(listener);
        }
        super.addCommandStackEventListener(listener);
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#addCommandStackListener(org.eclipse.gef.commands.CommandStackListener)
     */
    @Override
    public void addCommandStackListener(CommandStackListener listener) {
        if (!commandStackListenerList.contains(listener)) {
            commandStackListenerList.add(listener);
        }
        super.addCommandStackListener(listener);
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#canRedo()
     */
    @Override
    public boolean canRedo() {
        return transactionEditingDomain.getCommandStack().canRedo();
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#canUndo()
     */
    @Override
    public boolean canUndo() {
        return transactionEditingDomain.getCommandStack().canUndo();
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#execute(org.eclipse.gef.commands.Command)
     */
    @Override
    public void execute(final Command command) {
        transactionEditingDomain.getCommandStack().execute(new RecordingCommand(transactionEditingDomain) {
            @SuppressWarnings("deprecation")
            @Override
            protected void doExecute() {
                notifyListeners(command, PRE_EXECUTE);
                command.execute();
                executeHandler(command);
                notifyListeners();

                notifyListeners(command, POST_EXECUTE);
                // isGEFCommand = true;
            }
        });
        // super.execute(new EmptyCommand());
    }

    /**
     * execute
     *  
     * @param command
     * @param NO_NOTIFICATIONS void
     */
    public void execute(final Command command, Boolean NO_NOTIFICATIONS) {
        TransactionalCommandStack tstack = (TransactionalCommandStack) transactionEditingDomain.getCommandStack();
        
        try {
            tstack.execute(new RecordingCommand(transactionEditingDomain) {
                @SuppressWarnings("deprecation")
                @Override
                protected void doExecute() {
                    notifyListeners(command, PRE_EXECUTE);
                    command.execute();
                    executeHandler(command);
                    notifyListeners();

                    notifyListeners(command, POST_EXECUTE);
                    // isGEFCommand = true;
                }
            }, Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS, NO_NOTIFICATIONS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * executeHandler
     *  
     * @param command void
     */
    private void executeHandler(Command command) {
        super.execute(new EmptyCommand(command));
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#redo()
     */
    @SuppressWarnings("deprecation")
    @Override
    public void redo() {
        if (!this.canRedo()) {
            return;
        }
        this.unset();

        Command command = this.getRedoCommand();
        notifyListeners(command, PRE_REDO);
        transactionEditingDomain.getCommandStack().redo();
        notifyListeners();
        notifyListeners(command, POST_REDO);
        this.set();
        if (super.canRedo()) {
            super.redo();
        }
        // fireNotifyCommandStackListener();//this.commandStackListener.commandStackChanged(new
        // EventObject(this));
    }

    /**
     * @see org.eclipse.gef.commands.CommandStack#undo()
     */
    @SuppressWarnings("deprecation")
    @Override
    public void undo() {
        if (!this.canUndo()) {
            return;
        }
        this.unset();
        Command command = this.getUndoCommand();
        notifyListeners(command, PRE_UNDO);

        transactionEditingDomain.getCommandStack().undo();
        notifyListeners();
        notifyListeners(command, POST_UNDO);
        this.set();
        if (super.canUndo()) {
            super.undo();
        }
        // fireNotifyCommandStackListener();//this.commandStackListener.commandStackChanged(new
        // EventObject(this));
    }

    /**
     * @see org.eclipse.emf.common.command.CommandStackListener#commandStackChanged(java.util.EventObject)
     */
    @SuppressWarnings("deprecation")
    public void commandStackChanged(EventObject event) {
        // if (!isGEFCommand) {
        // //super.execute(new EmptyCommand());
        // }
        //
        // isGEFCommand = false;
        notifyListeners();

        // fireNotifyCommandStackListener();//this.commandStackListener.commandStackChanged(new
        // EventObject(this));
    }

    /**
     * 
     * void
     */
    @SuppressWarnings("unused")
    private void fireNotifyCommandStackListener() {
        for (Iterator<CommandStackListener> iter = this.commandStackListenerList.iterator(); iter.hasNext();) {
            iter.next().commandStackChanged(new EventObject(this));
        }
    }
    // /**
    // * @see org.eclipse.gef.commands.CommandStack#getRedoCommand()
    // */
    // @Override
    // public Command getRedoCommand() {
    // if(this.canRedo()){
    // return new EmptyCommand();
    // }else{
    // return null;
    // }
    // }
    //    
    // /**
    // * @see org.eclipse.gef.commands.CommandStack#getUndoCommand()
    // */
    // @Override
    // public Command getUndoCommand() {
    // if(this.canUndo()){
    // return new EmptyCommand();
    // }else{
    // return null;
    // }
    // }
    // private void fireNotifyCommandStackEventListener(){
    // this.notifyListeners(command, state)
    // for(Iterator<CommandStackEventListener> iter =
    // this.commandStackEventListenerList.iterator(); iter.hasNext();){
    // iter.next().stackChanged(new CommandStackEvent())
    // }
    // }

}
