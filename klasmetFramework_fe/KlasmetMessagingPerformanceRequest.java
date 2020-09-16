/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

/**
 *
 * @author nami
 */
final public class KlasmetMessagingPerformanceRequest {

    private KlasmetMessagingPerformanceRequestMsg performanceRequest;
    private String presenterClassName;
    private String presenterMethodName;

    /**
     *
     * @param performanceRequest
     * @param presenterClassName
     * @param presenterMethodName
     */
    public KlasmetMessagingPerformanceRequest(KlasmetMessagingPerformanceRequestMsg performanceRequest, String presenterClassName, String presenterMethodName) {
        this.performanceRequest = performanceRequest;
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
     * @return the commandRequest
     */
    public KlasmetMessagingPerformanceRequestMsg getPerformanceRequest() {
        return performanceRequest;
    }

    /**
     * @param performanceRequest the commandRequest to set
     */
    public void setCommandRequest(KlasmetMessagingPerformanceRequestMsg performanceRequest) {
        this.performanceRequest = performanceRequest;
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
