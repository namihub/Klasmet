package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
final class KlasmetMessagingAlarmMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_ALARM_TRAP;
    private String alarmId;
    private String description;
    private String severity;
    private String parentAlarmId;
    private String occuranceTime;
    private boolean hasAction;
    private String category;
    private String xId;
    private String ackTime;

    protected KlasmetMessagingAlarmMsg(String category, String alarmId, String description, String severity, String parentAlarmId, boolean hasAction, String connectorName, String occuranceTime, String ackTime, String xId) {

        if (category == null) {
            this.category = "";
        } else {
            this.category = category;
        }
        if (alarmId == null) {
            this.alarmId = "";
        } else {
            this.alarmId = alarmId;
        }
        if (xId == null) {
            this.xId = "";
        } else {
            this.xId = xId;
        }
        if (connectorName == null) {
            super.setConnectorName("");
        } else {
            super.setConnectorName(connectorName);
        }
        if (description == null) {
            this.description = "";
        } else {
            this.description = description;
        }
        if (severity == null) {
            this.severity = "";
        } else {
            this.severity = severity;
        }
        if (parentAlarmId == null) {
            this.parentAlarmId = "";
        } else {
            this.parentAlarmId = parentAlarmId;
        }
        if (occuranceTime == null) {
            this.occuranceTime = "";
        } else {
            this.occuranceTime = occuranceTime;
        }
        if (ackTime == null) {
            this.ackTime = "";
        } else {
            this.ackTime = ackTime;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingAlarmMsg klasmetAlarmMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_CATEGORY, klasmetAlarmMessage.getCategory());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_ID, klasmetAlarmMessage.getAlarmId());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_DESCRIPTION, klasmetAlarmMessage.getDescription());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_PARENT_ALARM_ID, klasmetAlarmMessage.getParentAlarmId());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_SEVERITY, klasmetAlarmMessage.getSeverity());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_HAS_ACTION, klasmetAlarmMessage.getHasAction());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, klasmetAlarmMessage.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_OCCURANCE_TIME, klasmetAlarmMessage.getOccuranceTime());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_ACK_TIME, klasmetAlarmMessage.getAckTime());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_X_ID, klasmetAlarmMessage.getXId());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingAlarmMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        boolean hasAction = (Boolean) requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_HAS_ACTION);
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String alarmId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_ID).toString();
        String alarmCategory = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_CATEGORY).toString();
        String description = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_DESCRIPTION).toString();
        String parentAlarmId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_PARENT_ALARM_ID).toString();
        String severity = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_SEVERITY).toString();
        String occuranceTime = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_OCCURANCE_TIME).toString();
        String ackTime = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_ACK_TIME).toString();
        String xId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_X_ID).toString();

        return (new KlasmetMessagingAlarmMsg(alarmCategory, alarmId, description, severity, parentAlarmId, hasAction, connectorName, occuranceTime, ackTime, xId));
    }

    /**
     * @return the KEY
     */
    protected static String getKey() {
        return key;
    }

    /**
     * @return the alarmId
     */
    protected String getAlarmId() {
        return alarmId;
    }

    /**
     * @param alarmId the alarmId to set
     */
    protected void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    /**
     * @return the description
     */
    protected String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the severity
     */
    protected String getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    protected void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * @return the parentAlarmId
     */
    protected String getParentAlarmId() {
        return parentAlarmId;
    }

    /**
     * @param parentAlarmId the parentAlarmId to set
     */
    protected void setParentAlarmId(String parentAlarmId) {
        this.parentAlarmId = parentAlarmId;
    }

    /**
     * @return the hasAction
     */
    protected boolean getHasAction() {
        return hasAction;
    }

    /**
     * @param hasAction the hasAction to set
     */
    protected void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
    }

    /**
     * @return the occuranceTime
     */
    protected String getOccuranceTime() {
        return occuranceTime;
    }

    /**
     * @param occuranceTime the occuranceTime to set
     */
    protected void setOccuranceTime(String occuranceTime) {
        this.occuranceTime = occuranceTime;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the xId
     */
    public String getXId() {
        return xId;
    }

    /**
     * @param xId the xId to set
     */
    public void setXId(String xId) {
        this.xId = xId;
    }

    /**
     * @return the ackTime
     */
    public String getAckTime() {
        return ackTime;
    }

    /**
     * @param ackTime the ackTime to set
     */
    public void setAckTime(String ackTime) {
        this.ackTime = ackTime;
    }

}
