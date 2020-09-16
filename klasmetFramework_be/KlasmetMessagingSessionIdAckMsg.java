package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingSessionIdAckMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_SESSION_ID_ACK;
    private String sessionId;

    public KlasmetMessagingSessionIdAckMsg(String sessionId) {
        if (sessionId == null) {
            this.sessionId = "";
        } else {
            this.sessionId = sessionId;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingSessionIdAckMsg klasmetSessionIdAckMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.SESSION_ID, klasmetSessionIdAckMessage.getSessionId());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingSessionIdAckMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String sessionId = requestParsedJSONObj.get(KlasmetMessagingKey.SESSION_ID).toString();
        return (new KlasmetMessagingSessionIdAckMsg(sessionId));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
