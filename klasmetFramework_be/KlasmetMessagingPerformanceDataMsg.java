package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingPerformanceDataMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_PERFORMANCE_DATA;
    private String agent;
    private String data;
    private String time;
    private String serverSideLoaderClassName;
    private String serverSideLoaderMethodName;

    public KlasmetMessagingPerformanceDataMsg(String agent, String data, String time, String connectorName, String serverSideLoaderClassName, String serverSideLoaderMethodName) {

        if (agent == null) {
            this.agent = "";
        } else {
            this.agent = agent;
        }
        if (data == null) {
            this.data = "";
        } else {
            this.data = data;
        }
        if (time == null) {
            this.time = "";
        } else {
            this.time = time;
        }
        if (serverSideLoaderClassName == null) {
            this.serverSideLoaderClassName = "";
        } else {
            this.serverSideLoaderClassName = serverSideLoaderClassName;
        }
        if (serverSideLoaderMethodName == null) {
            this.serverSideLoaderMethodName = "";
        } else {
            this.serverSideLoaderMethodName = serverSideLoaderMethodName;
        }
        if (connectorName == null) {
            super.setConnectorName("");
        } else {
            super.setConnectorName(connectorName);
        }

    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingPerformanceDataMsg klasmetPerformanceMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.AGENT, klasmetPerformanceMessage.getAgent());
        JSONObjCommand.put(KlasmetMessagingKey.PERFORMANCE_DATA, klasmetPerformanceMessage.getData());
        JSONObjCommand.put(KlasmetMessagingKey.PERFORMANCE_TIME, klasmetPerformanceMessage.getTime());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, klasmetPerformanceMessage.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.PERFORMANCE_LOADER_CLASS, klasmetPerformanceMessage.getServerSideLoaderClassName());
        JSONObjCommand.put(KlasmetMessagingKey.PERFORMANCE_LOADER_METHOD, klasmetPerformanceMessage.getServerSideLoaderMethodName());

        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingPerformanceDataMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String agent = requestParsedJSONObj.get(KlasmetMessagingKey.AGENT).toString();
        String data = requestParsedJSONObj.get(KlasmetMessagingKey.PERFORMANCE_DATA).toString();
        String time = requestParsedJSONObj.get(KlasmetMessagingKey.PERFORMANCE_TIME).toString();
        String serverSideLoaderClassName = requestParsedJSONObj.get(KlasmetMessagingKey.PERFORMANCE_LOADER_CLASS).toString();
        String serverSideLoaderMethodName = requestParsedJSONObj.get(KlasmetMessagingKey.PERFORMANCE_LOADER_METHOD).toString();

        return (new KlasmetMessagingPerformanceDataMsg(agent, data, time, connectorName,serverSideLoaderClassName,serverSideLoaderMethodName));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

    /**
     * @return the agent
     */
    public String getAgent() {
        return agent;
    }

    /**
     * @param agent the agent to set
     */
    public void setAgent(String agent) {
        this.agent = agent;
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

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the serverSideLoaderClassName
     */
    public String getServerSideLoaderClassName() {
        return serverSideLoaderClassName;
    }

    /**
     * @param serverSideLoaderClassName the serverSideLoaderClassName to set
     */
    public void setServerSideLoaderClassName(String serverSideLoaderClassName) {
        this.serverSideLoaderClassName = serverSideLoaderClassName;
    }

    /**
     * @return the serverSideLoaderMethodName
     */
    public String getServerSideLoaderMethodName() {
        return serverSideLoaderMethodName;
    }

    /**
     * @param serverSideLoaderMethodName the serverSideLoaderMethodName to set
     */
    public void setServerSideLoaderMethodName(String serverSideLoaderMethodName) {
        this.serverSideLoaderMethodName = serverSideLoaderMethodName;
    }

}
