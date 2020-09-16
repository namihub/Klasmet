package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingCommandResponseMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_COMMAND_RESPONSE;
    private String agent;
    private String message;

    public KlasmetMessagingCommandResponseMsg(String agent, String connectorName, String message) {
        if (agent == null) {
            this.agent = "";
        } else {
            this.agent = agent;
        }
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
    public static String toJsonString(KlasmetMessagingCommandResponseMsg commandResponse) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AGENT, commandResponse.getAgent());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, commandResponse.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.MESSAGE, commandResponse.getMessage());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingCommandResponseMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String agent = requestParsedJSONObj.get(KlasmetMessagingKey.AGENT).toString();
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String message = requestParsedJSONObj.get(KlasmetMessagingKey.MESSAGE).toString();
        return (new KlasmetMessagingCommandResponseMsg(agent, connectorName, message));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
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
     * @return the AGENT
     */
    public String getAgent() {
        return agent;
    }

    /**
     * @param agent the AGENT to set
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }
}
