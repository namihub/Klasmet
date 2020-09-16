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
final public class KlasmetMessagingSystemstatRequest {

    private KlasmetMessagingSystemstatRequestMsg systemstatRequest;

    /**
     *
     * @param systemstatRequest
  
     */
    public KlasmetMessagingSystemstatRequest(KlasmetMessagingSystemstatRequestMsg systemstatRequest) {
        this.systemstatRequest = systemstatRequest;
        
    }

    /**
     * @return the systemstatRequest
     */
    public KlasmetMessagingSystemstatRequestMsg getSystemstatRequest() {
        return systemstatRequest;
    }

    /**
     * @param systemstatRequest the commandRequest to set
     */
    public void setSystemstatRequest(KlasmetMessagingSystemstatRequestMsg systemstatRequest) {
        this.systemstatRequest = systemstatRequest;
    }

   

}
