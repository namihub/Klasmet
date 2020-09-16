/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import org.json.simple.JSONObject;

/**
 *
 * @author nami
 */
final class KlasmetMessagingLoginInfoRequestMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_LOGIN_INFO_REQUEST;

    @SuppressWarnings("unchecked")
    protected static String toJsonString() {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        return JSONObjCommand.toJSONString();
    }
    

    /**
     * @return the KEY
     */
    protected static String getKey() {
        return key;
    }

}
