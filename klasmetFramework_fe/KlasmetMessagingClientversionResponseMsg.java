package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingClientversionResponseMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_CLIENTVERSION_RESPONSE;
    private String message;

    public KlasmetMessagingClientversionResponseMsg(String connectorName, String message) {

        if (connectorName == null) {
            super.setConnectorName("");
        } else {
            super.setConnectorName(connectorName);
        }
        if (message == null) {
            this.message = "";
        } else {
            this.message = message;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingClientversionResponseMsg clientversionResponse) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, clientversionResponse.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.MESSAGE, clientversionResponse.getMessage());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingClientversionResponseMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String message = requestParsedJSONObj.get(KlasmetMessagingKey.MESSAGE).toString();
        return (new KlasmetMessagingClientversionResponseMsg(connectorName, message));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return KEY;
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

}
