package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingFiletransferResponseMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_FILETRANSFER_RESPONSE;
    private String agent;
    private String fileName;
    private int fileSize;

    public KlasmetMessagingFiletransferResponseMsg(String agent, String connectorName, String fileName, int fileSize) {
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
        if (fileName == null) {
            this.fileName = "";
        } else {
            this.fileName = fileName;
        }
        this.fileSize = fileSize;
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingFiletransferResponseMsg response) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AGENT, response.getAgent());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, response.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.FILE_NAME, response.getFileName());
        JSONObjCommand.put(KlasmetMessagingKey.FILE_SIZE, response.getFileSize());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingFiletransferResponseMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String agent = requestParsedJSONObj.get(KlasmetMessagingKey.AGENT).toString();
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String fileName = requestParsedJSONObj.get(KlasmetMessagingKey.FILE_NAME).toString();
        int fileSize = Integer.valueOf(requestParsedJSONObj.get(KlasmetMessagingKey.FILE_SIZE).toString());
        return (new KlasmetMessagingFiletransferResponseMsg(agent, connectorName, fileName, fileSize));
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
    public String getFileName() {
        return fileName;
    }

    /**
     * @param message the MESSAGE to set
     */
    public void setFileName(String message) {
        this.fileName = message;
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

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileSize() {
        return this.fileSize;
    }

}
