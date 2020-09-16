package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingAlarmAckMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_ALARM_ACK;
    private String alarmId;
    private String ackTime;
    private String xId;

    public KlasmetMessagingAlarmAckMsg(String alarmId, String connectorName, String ackTime, String xId) {

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

        if (ackTime == null) {
            this.ackTime = "";
        } else {
            this.ackTime = ackTime;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingAlarmAckMsg klasmetAlarmMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_ID, klasmetAlarmMessage.getAlarmId());
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, klasmetAlarmMessage.getConnectorName());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_ACK_TIME, klasmetAlarmMessage.getAckTime());
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_X_ID, klasmetAlarmMessage.getXId());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingAlarmAckMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        String alarmId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_ID).toString();
        String ackTime = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_ACK_TIME).toString();
        String xId = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_X_ID).toString();
        return (new KlasmetMessagingAlarmAckMsg(alarmId, connectorName, ackTime, xId));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

    /**
     * @return the alarmId
     */
    public String getAlarmId() {
        return alarmId;
    }

    /**
     * @param alarmId the alarmId to set
     */
    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
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
