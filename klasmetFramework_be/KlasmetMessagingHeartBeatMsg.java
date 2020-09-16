/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingHeartBeatMsg extends KlasmetMessagingMessenger {

    private String message;
    private static final String key = KlasmetMessagingKey.KEY_HEARTBEAT;

    public KlasmetMessagingHeartBeatMsg(String message) {
        if (message == null) {
            this.message = "";
        } else {
            this.message = message;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingHeartBeatMsg heartBeatMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.HEARTBEAT_MESSAGE, heartBeatMessage.getMessage());
        return JSONObjCommand.toJSONString();
    }
    
    public static KlasmetMessagingHeartBeatMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String message = requestParsedJSONObj.get(KlasmetMessagingKey.HEARTBEAT_MESSAGE).toString();
        return (new KlasmetMessagingHeartBeatMsg(message));
    }

    /**
     * @return the MESSAGE
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the MESSAGE to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

}
