package klasmetFramework_fe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
final class KlasmetMessagingAlarmClearMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_ALARM_CLEAR;
    private String alarmId;
    private String clearanceTime;
    private String xId;

    protected KlasmetMessagingAlarmClearMsg(String alarmId, String connectorName, String clearanceTime, String xId) {

        if (alarmId == null) {
            this.alarmId = "";
        } else {
            this.alarmId = alarmId;
        }
        if (connectorName == null) {
            super.setConnectorName("");
        } else {
            super.setConnectorName(connectorName);
        }
        if (xId == null) {
            this.xId = "";
        } else {
            this.xId = xId;
        }

        if (clearanceTime == null) {
            this.clearanceTime = "";
        } else {
            this.clearanceTime = clearanceTime;
        }
    }

    @SuppressWarnings("unchecked")
    protected static String toJsonString(KlasmetMessagingAlarmClearMsg klasmetAlarmMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_ID, klasmetAlarmMessage.getAlarmId());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, klasmetAlarmMessage.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_CLEARANCE_TIME, klasmetAlarmMessage.getClearanceTime());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_X_ID, klasmetAlarmMessage.getXId());
        return JSONObjCommand.toJSONString();
    }

    protected static KlasmetMessagingAlarmClearMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String alarmId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_ID).toString();
        String clearanceTime = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_CLEARANCE_TIME).toString();
        String xId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_X_ID).toString();
        return (new KlasmetMessagingAlarmClearMsg(alarmId, connectorName, clearanceTime, xId));
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
     * @return the clearanceTime
     */
    protected String getClearanceTime() {
        return clearanceTime;
    }

    /**
     * @param clearanceTime the clearanceTime to set
     */
    protected void setClearanceTime(String clearanceTime) {
        this.clearanceTime = clearanceTime;
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

}
