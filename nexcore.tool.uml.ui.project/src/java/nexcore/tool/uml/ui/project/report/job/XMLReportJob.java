/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.report.job;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.project.report.control.IJobMonitor;
import nexcore.tool.uml.ui.project.report.control.ReportHandler;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.job</li>
 * <li>설  명 : XMLReportJob</li>
 * <li>작성일 : 2010. 10. 12.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class XMLReportJob extends Job implements IJobMonitor {

    /** 진행상태 모니터 */
    protected IProgressMonitor monitor = null;

    /** 보고서 생성 처리기 Action에서 등록됨 */
    protected ReportHandler reportHandler = null;

    /**
     * @return the reportHandler
     */
    public ReportHandler getReportHandler() {
        return reportHandler;
    }

    /**
     * @param reportHandler
     *            the reportHandler to set
     */
    public void setReportHandler(ReportHandler reportHandler) {
        this.reportHandler = reportHandler;
    }

    /**
     * @param name
     */
    public XMLReportJob() {
        super(UMLMessage.LABEL_NEXCORE_UML_MODELER);
    }

    /**
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        this.monitor = monitor;
        try {
            reportHandler.setJobMonitor(this);
            reportHandler.createReport();
        } catch (Exception error) {
            Log.error(error.getStackTrace());
            return Status.CANCEL_STATUS;
        }
        return Status.OK_STATUS;
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.IJobMonitor#endTask()
     */
    public void endTask() {
        if (null == this.monitor) {
            return;
        }
        this.monitor.done();
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.IJobMonitor#isCanceled()
     */
    public boolean isCanceled() {
        if (null == this.monitor) {
            return true;
        }
        return this.monitor.isCanceled();
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.IJobMonitor#setMonitor(java.lang.String)
     */
    public void setMonitor(String message) {
        setMonitor(message, 1);
    }

    /**
     * setMonitor
     *  
     * @param message
     * @param worked void
     */
    public void setMonitor(String message, int worked) {
        if (null == this.monitor) {
            return;
        }
        this.monitor.subTask(message);
        this.monitor.worked(worked);
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.IJobMonitor#startTask(java.lang.String,
     *      int)
     */
    public void startTask(String message, int totalWork) {
        if (null == this.monitor) {
            return;
        }
        this.monitor.beginTask(message, totalWork);
    }

}
