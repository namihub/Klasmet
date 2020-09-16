package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingFiletransferRequestMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_FILETRANSFER_REQUEST;
    private String agent;
    private String clientSessionId;
    private String agentParameters;

    public KlasmetMessagingFiletransferRequestMsg(String agent, String connectorName, String clientSessionId, String agentParameters) {
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
        if (clientSessionId == null) {
            this.clientSessionId = "";
        } else {
            this.clientSessionId = clientSessionId;
        }
        if (agentParameters == null) {
            this.agentParameters = "";
        } else {
            this.agentParameters = agentParameters;
        }
    }

    public KlasmetMessagingFiletransferRequestMsg(String agent, String connectorName, String agentParameters) {
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
        if (agentParameters == null) {
            this.agentParameters = "";
        } else {
            this.agentParameters = agentParameters;
        }
        this.clientSessionId = "";
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingFiletransferRequestMsg fileTransferRequestObj) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AGENT, fileTransferRequestObj.getAgent());
        JSONObjCommand.put(KlasmetMessagingKey.CLIENT_SESSION_ID, fileTransferRequestObj.getClientSessionId());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, fileTransferRequestObj.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.AGENT_PARAMETERS, fileTransferRequestObj.getAgentParameters());

        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingFiletransferRequestMsg toObject(String JSONCommand) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONCommand);
        String agent = requestParsedJSONObj.get(KlasmetMessagingKey.AGENT).toString();
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String clientSessionId = requestParsedJSONObj.get(KlasmetMessagingKey.CLIENT_SESSION_ID).toString();
        String agentParameters = requestParsedJSONObj.get(KlasmetMessagingKey.AGENT_PARAMETERS).toString();
        return (new KlasmetMessagingFiletransferRequestMsg(agent, connectorName, clientSessionId, agentParameters));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return KEY;
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

    /**
     * @return the parameters
     */
    public String getAgentParameters() {
        return agentParameters;
    }

    /**
     * @param agentParameters the parameters to set
     */
    public void setAgentParameters(String agentParameters) {
        this.agentParameters = agentParameters;
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
