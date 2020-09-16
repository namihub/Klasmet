package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingSystemstatRequestMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_SYSTEMSTAT_REQUEST;
    private String clientSessionId;

    public KlasmetMessagingSystemstatRequestMsg(String clientSessionId) {

        if (clientSessionId == null) {
            this.clientSessionId = "";
        } else {
            this.clientSessionId = clientSessionId;
        }
    }

    public KlasmetMessagingSystemstatRequestMsg() {
        
        this.clientSessionId = "";
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingSystemstatRequestMsg commandRequestObj) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.CLIENT_SESSION_ID, commandRequestObj.getClientSessionId());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingSystemstatRequestMsg toObject(String JSONCommand) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONCommand);
        String clientSessionId = requestParsedJSONObj.get(KlasmetMessagingKey.CLIENT_SESSION_ID).toString();
        return (new KlasmetMessagingSystemstatRequestMsg(clientSessionId));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return KEY;
    }

    /**
     * @return the getClientSessionId
     */
    public String getClientSessionId() {
        return clientSessionId;
    }

    /**
     * @param clientSessionId the clientSessionId to set
     */
    public void setClientSessionId(String clientSessionId) {
        this.clientSessionId = clientSessionId;
    }
}
