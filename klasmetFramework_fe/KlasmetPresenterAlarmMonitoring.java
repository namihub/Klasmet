/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import emeruxclient.KlasmetPresenterMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nami
 */
public class KlasmetPresenterAlarmMonitoring extends KlasmetPresenter implements Initializable {

    @FXML
    TableView<KlasmetAlarmMonitoringAlarm> alarmTable;

    final static ObservableList<KlasmetAlarmMonitoringAlarm> ALARM_LIST = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ALARM_LIST.add(new KlasmetAlarmMonitoringAlarm("1", "occuranceTime", "acktime", "cleartime", "connector", "desc", "severity", "parent", false, "cat", "xid"));
        TableColumn colAid = new TableColumn("Alarm ID");
        colAid.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("alarmId"));
        TableColumn colOccuranceTime = new TableColumn("Occurance Time");
        colOccuranceTime.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("occuranceTime"));
        TableColumn colAckTime = new TableColumn("Ack Time");
        colAckTime.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("ackTime"));
        TableColumn colClearanceTime = new TableColumn("Clearance Time");
        colClearanceTime.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("clearanceTime"));
        TableColumn colConnector = new TableColumn("Connector");
        colConnector.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("connectorName"));
        TableColumn colParent = new TableColumn("ParentAID");
        colParent.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("parentAlarmId"));
        TableColumn colDescription = new TableColumn("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("description"));
        TableColumn colSeverity = new TableColumn("Severity");
        colSeverity.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("severity"));
        TableColumn colHasAction = new TableColumn("HasAction");
        colHasAction.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("hasAction"));
        TableColumn colCategory = new TableColumn("Category");
        colCategory.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("category"));
        TableColumn colXID = new TableColumn("XID");
        colXID.setCellValueFactory(new PropertyValueFactory<KlasmetAlarmMonitoringAlarm, String>("xId"));

        alarmTable.setItems(ALARM_LIST);
        alarmTable.getColumns().addAll(colAid, colOccuranceTime, colSeverity, colDescription, colClearanceTime, colAckTime, colConnector, colParent, colHasAction, colCategory, colXID);
        colXID.setVisible(false);
        alarmTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        alarmTable.setRowFactory(tv -> {
            TableRow<KlasmetAlarmMonitoringAlarm> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    KlasmetAlarmMonitoringAlarm rowData = row.getItem();
                    if (rowData.getAckTime().equals("")) {//not acked yet
                        KlasmetMessagingAlarmAckMsg ackMsg = new KlasmetMessagingAlarmAckMsg(rowData.getAlarmId(), rowData.getConnectorName(), "2000-01-01 00:00:00", rowData.getXId());
                        ackAlarmByClient(ackMsg);
                    }

                }
//                if (event.getButton().equals(MouseButton.SECONDARY) && (!row.isEmpty())) {
//                    KlasmetAlarmMonitoringAlarm rowData = row.getItem();
//                    System.out.println(rowData.getAlarmId());
//                    ALARM_LIST.add(new KlasmetAlarmMonitoringAlarm("1", "occuranceTime", "acktime", "cleartime", "connector", "desc", "severity", "parent", false, "cat", "xid"));
//                    //ALARM_LIST.clear();
//                }
            });
            return row;
        });

//        ////set right click popup menu on jT_alarm --BEGIN
//        JPopupMenu popupMenu = new JPopupMenu();
//        final JMenuItem menuItemAck = new JMenuItem("Ack selected alarms");
//        popupMenu.add(menuItemAck);
//        alarmTable.setComponentPopupMenu(popupMenu);
//
//        ActionListener menuListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                if (event.getActionCommand().equals(menuItemAck.getActionCommand())) {//send ack message to server for all selected alarms in view
//                    if (jT_alarm.getSelectedRows().length > 0) {
//                        for (int i = 0; i < jT_alarm.getSelectedRows().length; i++) {
//
//                            //System.out.println(jT_alarm.getSelectedRows()[i]);
//                            //System.out.println(jT_alarm.getModel().getValueAt(jT_alarm.getSelectedRows()[i], 9));
//                            String alarmId = jT_alarm.getModel().getValueAt(jT_alarm.getSelectedRows()[i], 1).toString();
//                            String connectorName = jT_alarm.getModel().getValueAt(jT_alarm.getSelectedRows()[i], 7).toString();
//                            String xId = jT_alarm.getModel().getValueAt(jT_alarm.getSelectedRows()[i], 9).toString();
//                            KlasmetMessagingAlarmAckMsg ackMsg = new KlasmetMessagingAlarmAckMsg(alarmId, connectorName, "2000-01-01 00:00:00", xId);
//                            KlasmetAlarmMonitoring.ackAlarmByClient(ackMsg);
//
//                        }
//                    }
//                }
//            }
//        };
//        menuItemAck.addActionListener(menuListener);
//        ////set right click popup menu on jT_alarm --END
    }

    public static void truncateAlarms() {
        ALARM_LIST.clear();
    }

    /**
     *
     * @param alarmMessage
     */
    public static void newAlarm(KlasmetMessagingAlarmMsg alarmMessage) {
        //"*", "Alarm ID", "Severity", "Description", "Occurance Time", "Clearance Time", "Ack Time", "Connector", "Category", "XID"
        try {
            String alarmId = alarmMessage.getAlarmId();
            String category = alarmMessage.getCategory();
            String connector = alarmMessage.getConnectorName();
            String description = alarmMessage.getDescription();
            String occuranceTime = alarmMessage.getOccuranceTime();
            String ackTime = alarmMessage.getAckTime();
            String parentAlarmId = alarmMessage.getParentAlarmId();
            String severity = alarmMessage.getSeverity();
            boolean hasAction = alarmMessage.getHasAction();
            String xId = alarmMessage.getXId();

            if (occuranceTime.contains("2000-01-01")) {
                occuranceTime = "";
            }
            if (ackTime.contains("2000-01-01")) {
                ackTime = "";
            }

            KlasmetAlarmMonitoringAlarm klasmetAlarm = new KlasmetAlarmMonitoringAlarm(alarmId, occuranceTime, ackTime, null, connector, description, severity, parentAlarmId, hasAction, category, xId);
            if (!findAlarm(xId)) {
                ALARM_LIST.add(klasmetAlarm);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static boolean findAlarm(String xId) {
        for (KlasmetAlarmMonitoringAlarm nextAlarm : ALARM_LIST) {
            if (nextAlarm.getXId().equals(xId)) {
                return true;
            }
        }
        return false;
    }

    public static void clearAlarm(KlasmetMessagingAlarmClearMsg alarmClearMessage) {//server to client
        String clearanceTime = alarmClearMessage.getClearanceTime();
        String xId = alarmClearMessage.getXId();
        //////////updating alarmList
        int index = 0;
        for (KlasmetAlarmMonitoringAlarm nextAlarm : ALARM_LIST) {
            if (nextAlarm.getXId().equals(xId)) {//this is the alarm
                if (!(nextAlarm.getAckTime()).equals("")) {//this alarm is acked so should be removed
                    //remove from alarmList
                    ALARM_LIST.remove(nextAlarm);
                } else {
                    //this alarm is not acked just update alarmList with cancelation time
                    nextAlarm.setClearanceTime(clearanceTime);
                    ALARM_LIST.set(index, nextAlarm);
                }
                break;
            }
            index++;
        }
//        //////////updating alarmList
//        Iterator alarmListItr = ALARM_LIST.iterator();
//        while (alarmListItr.hasNext()) {
//            KlasmetAlarmMonitoringAlarm nextAlarm = (KlasmetAlarmMonitoringAlarm) alarmListItr.next();
//            if (nextAlarm.getXId().equals(xId)) {//this is the alarm
//                if (!(nextAlarm.getAckTime()).equals("")) {//this alarm is acked so should be removed
//                    //remove from alarmList
//                    alarmListItr.remove();
//                } else {
//                    //this alarm is not acked just update alarmList with cancelation time
//                    alarmListItr.remove();
//                    nextAlarm.setClearanceTime(clearanceTime);
//                    ALARM_LIST.add(nextAlarm);
//                }
//                break;
//            }
//        }
    }

    public static void ackAlarmByClient(KlasmetMessagingAlarmAckMsg alarmAckMessage) {//client to server
        try {
            //send ack MESSAGE to server
            KlasmetInitializer.klasmetTcpStreamer.write(alarmAckMessage);

        } catch (IOException | NullPointerException ex) {
        }
    }

    public static void ackAlarm(KlasmetMessagingAlarmAckMsg alarmAckMessage) {//server to client
        String ackTime = alarmAckMessage.getAckTime();
        String xId = alarmAckMessage.getXId();

        ///////////////////////// updating alarmList
        int index = 0;
        for (KlasmetAlarmMonitoringAlarm nextAlarm : ALARM_LIST) {

            if (nextAlarm.getXId().equals(xId)) {//this is the alarm
                if (!(nextAlarm.getClearanceTime()).equals("")) {//this alarm is cleared so should be removed
                    //remove from alarmList
                    ALARM_LIST.remove(nextAlarm);
                } else {
                    //this alarm is not cleared just update alarmList with ack time
                    nextAlarm.setAckTime(ackTime);
                    ALARM_LIST.set(index, nextAlarm);
                }
                break;
            }
            index++;
        }
//        ///////////////////////// updating alarmList
//        Iterator alarmListItr = ALARM_LIST.iterator();
//        while (alarmListItr.hasNext()) {
//            KlasmetAlarmMonitoringAlarm nextAlarm = (KlasmetAlarmMonitoringAlarm) alarmListItr.next();
//            if (nextAlarm.getXId().equals(xId)) {//this is the alarm
//                if (!(nextAlarm.getClearanceTime()).equals("")) {//this alarm is cleared so should be removed
//                    //remove from alarmList
//                    alarmListItr.remove();
//                } else {
//                    //this alarm is not cleared just update alarmList with ack time
//                    alarmListItr.remove();
//                    nextAlarm.setAckTime(ackTime);
//                    ALARM_LIST.add(nextAlarm);
//                }
//                break;
//            }
//        }

    }

    @Override
    public void klasmetDestroyer() {
        KlasmetPresenterMain.isAlarmDashboardFormVisible = false;
    }
}
