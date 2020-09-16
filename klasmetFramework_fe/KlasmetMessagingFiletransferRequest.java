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
final public class KlasmetMessagingFiletransferRequest {

    private final KlasmetMessagingFiletransferRequestMsg ftpRequest;
    private String presenterClassName;
    private String presenterMethodName;

    /**
     *
     * @param ftpRequest
     * @param presenterClassName
     * @param presenterMethodName
     */
    public KlasmetMessagingFiletransferRequest(KlasmetMessagingFiletransferRequestMsg ftpRequest, String presenterClassName, String presenterMethodName) {
        this.ftpRequest = ftpRequest;
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
    public KlasmetMessagingFiletransferRequestMsg getFiletransferRequest() {
        return ftpRequest;
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
