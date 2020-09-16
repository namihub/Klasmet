package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingSessionIdMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_SESSION_ID;
    private String sessionId;
    private String groupname;

    public KlasmetMessagingSessionIdMsg(String sessionId, String groupname) {
        if (sessionId == null) {
            this.sessionId = "";
        } else {
            this.sessionId = sessionId;
        }
        if (groupname == null) {
            this.groupname = "";
        } else {
            this.groupname = groupname;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingSessionIdMsg klasmetSessionIdMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.SESSION_ID, klasmetSessionIdMessage.getSessionId());
        JSONObjCommand.put(KlasmetMessagingKey.GROUP_NAME, klasmetSessionIdMessage.getGroupname());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingSessionIdMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String sessionId = requestParsedJSONObj.get(KlasmetMessagingKey.SESSION_ID).toString();
        String groupname = requestParsedJSONObj.get(KlasmetMessagingKey.GROUP_NAME).toString();
        return (new KlasmetMessagingSessionIdMsg(sessionId,groupname));
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

    /**
     * @return the groupname
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * @param groupname the groupname to set
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

}
