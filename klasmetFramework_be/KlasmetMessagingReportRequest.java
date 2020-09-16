/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_be;

/**
 *
 * @author nami
 */
final public class KlasmetMessagingReportRequest {

    private KlasmetMessagingReportRequestMsg reportRequest;
    private String presenterClassName;
    private String presenterMethodName;

    /**
     *
     * @param reportRequest
     * @param presenterClassName
     * @param presenterMethodName
     */
    public KlasmetMessagingReportRequest(KlasmetMessagingReportRequestMsg reportRequest, String presenterClassName, String presenterMethodName) {
        this.reportRequest = reportRequest;
        if (presenterClassName == null) {
            this.presenterClassName = "";
        } else {
            this.presenterClassName = presenterClassName;
        }
        if (presenterMethodName == null) {
            this.presenterMethodName = "";
        } else {
            this.presenterMethodName = presenterMethodName;
        }
    }

    /**
     * @return the ReportRequest
     */
    public KlasmetMessagingReportRequestMsg getReportRequest() {
        return reportRequest;
    }

    /**
     * @param reportRequest the commandRequest to set
     */
    public void setReportRequest(KlasmetMessagingReportRequestMsg reportRequest) {
        this.reportRequest = reportRequest;
    }

    /**
     * @return the presenterClassName
     */
    public String getPresenterClassName() {
        return presenterClassName;
    }

    /**
     * @param presenterClassName the presenterClassName to set
     */
    public void setPresenterClassName(String presenterClassName) {
        this.presenterClassName = presenterClassName;
    }

    /**
     * @return the presenterMethodName
     */
    public String getPresenterMethodName() {
        return presenterMethodName;
    }

    /**
     * @param presenterMethodName the presenterMethodName to set
     */
    public void setPresenterMethodName(String presenterMethodName) {
        this.presenterMethodName = presenterMethodName;
    }

}
