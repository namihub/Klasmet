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
final public class KlasmetMessagingClientversionRequest {

    private KlasmetMessagingClientversionRequestMsg clientversionRequest;

    /**
     *
     * @param clientversionRequest
  
     */
    public KlasmetMessagingClientversionRequest(KlasmetMessagingClientversionRequestMsg clientversionRequest) {
        this.clientversionRequest = clientversionRequest;
        
    }

    /**
     * @return the clientversionRequest
     */
    public KlasmetMessagingClientversionRequestMsg getClientversionRequest() {
        return clientversionRequest;
    }

    /**
     * @param clientversionRequest the commandRequest to set
     */
    public void setClientversionRequest(KlasmetMessagingClientversionRequestMsg clientversionRequest) {
        this.clientversionRequest = clientversionRequest;
    }

   

}
