/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

/**
 *
 * @author nami
 */
public final class KlasmetAlarmMonitoringAlarm {

    private String alarmId;
    private String occuranceTime;
    private String ackTime;
    private String clearanceTime;
    private String connectorName;
    private String description;
    private String severity;
    private String parentAlarmId;
    private boolean hasAction;
    private String category;
    private String xId;

    public KlasmetAlarmMonitoringAlarm(String alarmId, String occuranceTime, String ackTime, String clearanceTime, String connectorName, String description, String severity, String parentAlarmId, boolean hasAction, String category, String xId) {
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
        if (ackTime == null) {
            this.ackTime = "";
        } else {
            this.ackTime = ackTime;
        }
        if (clearanceTime == null) {
            this.clearanceTime = "";
        } else {
            this.clearanceTime = clearanceTime;
        }
        if (occuranceTime == null) {
            this.occuranceTime = "";
        } else {
            this.occuranceTime = occuranceTime;
        }
        if (connectorName == null) {
            this.connectorName = "";
        } else {
            this.connectorName = connectorName;
        }
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
     * @return the occuranceTime
     */
    public String getOccuranceTime() {
        return occuranceTime;
    }

    /**
     * @param occuranceTime the occuranceTime to set
     */
    public void setOccuranceTime(String occuranceTime) {
        this.occuranceTime = occuranceTime;
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
     * @return the clearanceTime
     */
    public String getClearanceTime() {
        return clearanceTime;
    }

    /**
     * @param clearanceTime the clearanceTime to set
     */
    public void setClearanceTime(String clearanceTime) {
        this.clearanceTime = clearanceTime;
    }

    /**
     * @return the connectorName
     */
    public String getConnectorName() {
        return connectorName;
    }

    /**
     * @param connectorName the connectorName to set
     */
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * @return the parentKlasmetAlarmMonitoringAlarmId
     */
    public String getParentAlarmId() {
        return parentAlarmId;
    }

    /**
     * @param parentAlarmId the parentKlasmetAlarmMonitoringAlarmId to set
     */
    public void setParentAlarmId(String parentAlarmId) {
        this.parentAlarmId = parentAlarmId;
    }

    /**
     * @return the hasAction
     */
    public boolean isHasAction() {
        return hasAction;
    }

    /**
     * @param hasAction the hasAction to set
     */
    public void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
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

}
