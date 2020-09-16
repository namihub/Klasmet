package klasmetFramework_be;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public final class KlasmetMessagingReportResponseMsg extends KlasmetMessagingMessenger {

    private static final String KEY = KlasmetMessagingKey.KEY_REPORT_RES;
    private String clientSessionId;
    private String reportFileUrl;
    private String reportFileName;
    private int reportFileSize;
    private String uuid;

    public KlasmetMessagingReportResponseMsg(String uuid, String reportFileUrl,String reportFileName, int reportFileSize, String clientSessionId) {
        if (uuid == null) {
            this.uuid = "";
        } else {
            this.uuid = uuid;
        }
        if (reportFileName == null) {
            this.reportFileName = "";
        } else {
            this.reportFileName = reportFileName;
        }
        if (reportFileUrl == null) {
            this.reportFileUrl = "";
        } else {
            this.reportFileUrl = reportFileUrl;
        }
        this.reportFileSize = reportFileSize;
        if (clientSessionId == null) {
            this.clientSessionId = "";
        } else {
            this.clientSessionId = clientSessionId;
        }
    }

    @SuppressWarnings("unchecked")
    public static String toJsonString(KlasmetMessagingReportResponseMsg reportResponseObj) {
        JSONObject JSONObjCommand = new JSONObject();
        JSONObjCommand.put(KlasmetMessagingKey.KEY, getKey());
        JSONObjCommand.put(KlasmetMessagingKey.CLIENT_SESSION_ID, reportResponseObj.getClientSessionId());
        JSONObjCommand.put(KlasmetMessagingKey.REPORT_FILE_URL, reportResponseObj.getReportFileUrl());
        JSONObjCommand.put(KlasmetMessagingKey.REPORT_FILE_NAME, reportResponseObj.getReportFileName());
        JSONObjCommand.put(KlasmetMessagingKey.REPORT_FILE_SIZE, reportResponseObj.getReportFileSize());
        JSONObjCommand.put(KlasmetMessagingKey.UUID, reportResponseObj.getUuid());

        return JSONObjCommand.toJSONString();
    }

    public static KlasmetMessagingReportResponseMsg toObject(String JSONCommand) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(JSONCommand);
        String reportFileUrl = requestParsedJSONObj.get(KlasmetMessagingKey.REPORT_FILE_URL).toString();
        String reportFileName = requestParsedJSONObj.get(KlasmetMessagingKey.REPORT_FILE_NAME).toString();
        int reportFileSize = Integer.valueOf(requestParsedJSONObj.get(KlasmetMessagingKey.REPORT_FILE_SIZE).toString());
        String clientSessionId = requestParsedJSONObj.get(KlasmetMessagingKey.CLIENT_SESSION_ID).toString();
        String uuid = requestParsedJSONObj.get(KlasmetMessagingKey.UUID).toString();
        return (new KlasmetMessagingReportResponseMsg(uuid, reportFileUrl,reportFileName, reportFileSize, clientSessionId));
    }

    /**
     * @return the KEY
     */
    public static String getKey() {
        return KEY;
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

    /**
     * @return the reportFileUrl
     */
    public String getReportFileUrl() {
        return reportFileUrl;
    }

    /**
     * @param reportFileUrl the reportFileUrl to set
     */
    public void setReportFileUrl(String reportFileUrl) {
        this.reportFileUrl = reportFileUrl;
    }

    /**
     * @return the reportFileSize
     */
    public int getReportFileSize() {
        return reportFileSize;
    }

    /**
     * @param reportFileSize the fileSize to set
     */
    public void setReportFileSize(int reportFileSize) {
        this.reportFileSize = reportFileSize;
    }

    /**
     * @return the reportFileName
     */
    public String getReportFileName() {
        return reportFileName;
    }

    /**
     * @param reportFileName the reportFileName to set
     */
    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }
}
