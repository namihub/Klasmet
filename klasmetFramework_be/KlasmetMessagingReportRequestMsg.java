package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingReportRequestMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_REPORT_REQ;
    private String serverPresenterClass;
    private String serverPresenterMethod;
    private String clientSessionId;
    private String parameters;
    private String uuid;

    public KlasmetMessagingReportRequestMsg(String uuid, String serverPresenterClass, String serverPresenterMethod, String clientSessionId, String parameters) {
        if (uuid == null) {
            this.uuid = "";
        } else {
            this.uuid = uuid;
        }
        if (serverPresenterClass == null) {
            this.serverPresenterClass = "";
        } else {
            this.serverPresenterClass = serverPresenterClass;
        }
        if (serverPresenterMethod == null) {
            this.serverPresenterMethod = "";
        } else {
            this.serverPresenterMethod = serverPresenterMethod;
        }
        if (clientSessionId == null) {
            this.clientSessionId = "";
        } else {
            this.clientSessionId = clientSessionId;
        }
        if (parameters == null) {
            this.parameters = "";
        } else {
            this.parameters = parameters;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingReportRequestMsg reportRequestObj) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.REPORT_SERVER_PRESENTER_CLASS, reportRequestObj.getServerPresenterClass());
        JSONObjCommand.put(KlasmetMessagingKey.CLIENT_SESSION_ID, reportRequestObj.getClientSessionId());
        JSONObjCommand.put(KlasmetMessagingKey.REPORT_SERVER_PRESENTER_METHOD, reportRequestObj.getServerPresenterMethod());
        JSONObjCommand.put(KlasmetMessagingKey.REPORT_PARAMETERS, reportRequestObj.getParameters());
        JSONObjCommand.put(KlasmetMessagingKey.UUID, reportRequestObj.getUuid());

        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingReportRequestMsg toObject(String JSONCommand) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONCommand);
        String serverPresenterClass = requestParsedJSONObj.get(KlasmetMessagingKey.REPORT_SERVER_PRESENTER_CLASS).toString();
        String serverPresenterMethod = requestParsedJSONObj.get(KlasmetMessagingKey.REPORT_SERVER_PRESENTER_METHOD).toString();
        String clientSessionId = requestParsedJSONObj.get(KlasmetMessagingKey.CLIENT_SESSION_ID).toString();
        String parameters = requestParsedJSONObj.get(KlasmetMessagingKey.REPORT_PARAMETERS).toString();
        String uuid = requestParsedJSONObj.get(KlasmetMessagingKey.UUID).toString();
        return (new KlasmetMessagingReportRequestMsg(uuid, serverPresenterClass, serverPresenterMethod, clientSessionId, parameters));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return KEY;
    }

    /**
     * @return the parameters
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * @param agentParameters the parameters to set
     */
    public void setParameters(String agentParameters) {
        this.parameters = agentParameters;
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
     * @return the serverPresenterMethod
     */
    public String getServerPresenterMethod() {
        return serverPresenterMethod;
    }

    /**
     * @param serverPresenterMethod the serverPresenterMethod to set
     */
    public void setServerPresenterMethod(String serverPresenterMethod) {
        this.serverPresenterMethod = serverPresenterMethod;
    }

    /**
     * @return the serverPresenterClass
     */
    public String getServerPresenterClass() {
        return serverPresenterClass;
    }

    /**
     * @param serverPresenterClass the serverPresenterClass to set
     */
    public void setServerPresenterClass(String serverPresenterClass) {
        this.serverPresenterClass = serverPresenterClass;
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
}
