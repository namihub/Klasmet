package klasmetFramework_be;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingAlarmSyncMsg extends KlasmetMessagingMessenger {

    private static final String key = KlasmetMessagingKey.KEY_ALARM_SYNC;
    private ArrayList<String> alarmIdList = new ArrayList<>();

    public KlasmetMessagingAlarmSyncMsg(ArrayList alarmIdList, String connectorName) {
        this.alarmIdList = alarmIdList;
        if (connectorName == null) {
            super.setConnectorName("");
        } else {
            super.setConnectorName(connectorName);
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingAlarmSyncMsg klasmetAlarmMessage) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        String alarmIdListString = "";
        Iterator alarmIdListItr = klasmetAlarmMessage.getAlarmIdList().iterator();
        while (alarmIdListItr.hasNext()) {
            alarmIdListString += alarmIdListItr.next().toString();
            alarmIdListString += ",";
        }
        String alarmIdListStringFinal = alarmIdListString.substring(0, alarmIdListString.lastIndexOf(","));
        JSONObjCommand.put(KlasmetMessagingKey.ALARM_ID_LIST, alarmIdListStringFinal);
        JSONObjCommand.put(KlasmetMessagingKey.CONNECTOR_NAME, klasmetAlarmMessage.getConnectorName());
        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingAlarmSyncMsg toObject(String JSONResponse) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONResponse);
        String connectorName = requestParsedJSONObj.get(KlasmetMessagingKey.CONNECTOR_NAME).toString();
        ArrayList<String> alarmIdList;
        String[] idList = requestParsedJSONObj.get(KlasmetMessagingKey.ALARM_ID_LIST).toString().split(",");
        alarmIdList = new ArrayList<>(Arrays.asList(idList));
        return (new KlasmetMessagingAlarmSyncMsg(alarmIdList, connectorName));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return key;
    }

    /**
     * @return the alarmIdList
     */
    public ArrayList<String> getAlarmIdList() {
        return alarmIdList;
    }

    /**
     * @param alarmIdList the alarmIdList to set
     */
    public void setAlarmIdList(ArrayList<String> alarmIdList) {
        this.alarmIdList = alarmIdList;
    }

}
