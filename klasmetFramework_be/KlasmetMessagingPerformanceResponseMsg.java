package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingPerformanceResponseMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_PERFORMANCE_RES;
    private String clientSessionId;
    private String data;
    private String uuid;

    public KlasmetMessagingPerformanceResponseMsg(String uuid, String data, String clientSessionId) {
        if (uuid == null) {
            this.uuid = "";
        } else {
            this.uuid = uuid;
        }
        if (data == null) {
            this.data = "";
        } else {
            this.data = data;
        }
        if (clientSessionId == null) {
            this.clientSessionId = "";
        } else {
            this.clientSessionId = clientSessionId;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingPerformanceResponseMsg performanceResponseObj) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.CLIENT_SESSION_ID, performanceResponseObj.getClientSessionId());
        JSONObjCommand.put(KlasmetMessagingKey.PERFORMANCE_DATA, performanceResponseObj.getData());
        JSONObjCommand.put(KlasmetMessagingKey.UUID, performanceResponseObj.getUuid());

        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingPerformanceResponseMsg toObject(String JSONCommand) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONCommand);
        String data = requestParsedJSONObj.get(KlasmetMessagingKey.PERFORMANCE_DATA).toString();
        String clientSessionId = requestParsedJSONObj.get(KlasmetMessagingKey.CLIENT_SESSION_ID).toString();
        String uuid = requestParsedJSONObj.get(KlasmetMessagingKey.UUID).toString();
        return (new KlasmetMessagingPerformanceResponseMsg(uuid, data, clientSessionId));
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

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
}
