package klasmetFramework_fe;

import static emeruxclient.EmeruxClient.KLASMET_MAIN_STAGE;
import klasmetFramework_fe.ftp.FTPclient;
import java.io.BufferedReader;
import java.io.IOException;
import static emeruxclient.KlasmetPresenterMain.isLoginFormVisible;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.parser.ParseException;

//import view.MainForm;
/**
 *
 * @author nami
 */
public final class KlasmetInitializer {

    // BEGIN global variables
    protected static boolean connectionTryLock = false; //if true means at the moment one process is trying to connect
    protected static boolean tryToConnectToserver = false;
    protected static boolean connectedToServer = false;
    public static KlasmetLogWriter klasmetLogger;
    public static KlasmetConfiguration klasmetConfiguration;
    protected static KlasmetTcpStreamer klasmetTcpStreamer;
    protected static final int CONNECTION_RETRY_TIME_MILISECOND = 5000;
    protected static String sessionId;
    private static String groupname;
    static int retry = 0;

    //  END global variables
    protected static void init(final String username, final String password) {
// BEGIN starters
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    klasmetLogger = new KlasmetLogWriter();
                } catch (InterruptedException e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("could not start log server!");
                        }
                    });

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    klasmetConfiguration = new KlasmetConfiguration();
                    if (!klasmetConfiguration.readConfiguration()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("could not read config file!");
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    klasmetLogger.writeLog(1, "Main.main() e", e.toString());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //int retry = 0;
                    retry = 0;
                    while (retry < 3) {
                        //clear alarm memory and view --begin
                        KlasmetPresenterAlarmMonitoring.truncateAlarms();
                        //clear alarm memory and view --end
                        if (!tryToConnectToserver) {
                            connectionTryLock = false;
                            break;
                        }
                        connectionTryLock = true;
                        Thread.sleep(CONNECTION_RETRY_TIME_MILISECOND);
                        if (!tryToConnectToserver) {
                            connectionTryLock = false;
                            break;
                        }
                        klasmetTcpStreamer = new KlasmetTcpStreamer(klasmetConfiguration.getServer_ip(), klasmetConfiguration.getServer_tcp_port());
                        BufferedReader bufferedReader = null;

                        retry++;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Connecting to server (try #" + retry + " of 3)");
                            }
                        });
                        try {
                            bufferedReader = klasmetTcpStreamer.init();
                        } catch (IOException ex) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText(ex.toString());
                                }
                            });

                            //TODO: handle this exception
                        }
                        try {
                            String line;
                            if (bufferedReader != null) {
                                while ((line = bufferedReader.readLine()) != null) {
                                    //System.out.println(line);
                                    retry = 0;
                                    String key;
                                    key = KlasmetMessagingMessenger.getKey(line);
                                    switch (key) {
                                        case KlasmetMessagingKey.KEY_LOGIN_INFO_REQUEST: {
                                            KlasmetMessagingLoginInfoResponseMsg loginResponse = new KlasmetMessagingLoginInfoResponseMsg(username, password);
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Sending login information");
                                                }
                                            });
                                            klasmetTcpStreamer.write(loginResponse);
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_AUTHORIZATION_RESULT: {
                                            KlasmetMessagingAuthorizationMsg authorizationResult = KlasmetMessagingAuthorizationMsg.toObject(line);
                                            boolean authorized = authorizationResult.isAuthorized();
                                            if (authorized) {//we are logged in and authorized by sessionId
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Connection established");
                                                    }
                                                });

                                                //KlasmetMainForm.klasmetMenu_connectionStatus.setText("Connection Status: Connected");
                                                //KLASMET_MAIN_FORM_CONTROLLER.getConnectionStatus().setFill(Color.GREEN);
                                                KlasmetPresenterManager.klasmetPresenterMain.getConnectionStatus().setFill(Color.GREEN);
                                                tryToConnectToserver = false;
                                                connectedToServer = true;
                                                KlasmetHeartBeater heartBeater = new KlasmetHeartBeater(klasmetTcpStreamer, sessionId);
                                                heartBeater.startHearBeating(klasmetConfiguration.getHeartBeat());
                                                //check for client update
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Checking for update...");
                                                    }
                                                });

                                                KlasmetMessagingClientversionRequestMsg clientversionReq = new KlasmetMessagingClientversionRequestMsg();
                                                KlasmetMessagingClientversionRequest request = new KlasmetMessagingClientversionRequest(clientversionReq);
                                                try {
                                                    KlasmetCluster.collector(request);
                                                } catch (IOException | NullPointerException ex) {
                                                    Platform.runLater(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                                            alert.setTitle("KLASMET Error");
                                                            alert.setHeaderText(null);
                                                            alert.setResizable(true);
                                                            alert.setContentText("Collector error:\n" + ex.toString());
                                                            alert.showAndWait();
                                                        }
                                                    });
                                                    Platform.runLater(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                                                        }
                                                    });

                                                } catch (ServerNotReachableException ex) {
                                                    Platform.runLater(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                                                        }
                                                    });

                                                    Platform.runLater(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                                            alert.setTitle("KLASMET Error");
                                                            alert.setHeaderText(null);
                                                            alert.setResizable(true);
                                                            alert.setContentText("Server is not reachable");
                                                            alert.showAndWait();
                                                        }
                                                    });
                                                }
                                            } else {//User is not Authorized to access to Agent
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                                        alert.setTitle("KLASMET Error");
                                                        alert.setHeaderText(null);
                                                        alert.setResizable(true);
                                                        alert.setContentText("Permission denied");
                                                        alert.showAndWait();
                                                    }
                                                });
                                            }
                                            //btnClose.setEnabled(true);
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_AUTHENTICATION_RESULT: {
                                            KlasmetMessagingAuthenticationMsg authenticationResult = KlasmetMessagingAuthenticationMsg.toObject(line);
                                            boolean autenticated = authenticationResult.isAuthenticated();
                                            if (autenticated) {//login information accepted
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Client authenticated!");
                                                    }
                                                });

                                            } else {
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Authentication Failed!");
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginUsername().setDisable(false);
                                                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginPassword().setDisable(false);
                                                        KlasmetPresenterManager.klasmetPresenterMain.getConnectionStatus().setFill(Color.RED);
                                                    }
                                                });
                                                connectedToServer = false;
                                                tryToConnectToserver = false;
                                                klasmetTcpStreamer.destroy();
                                            }
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_SESSION_ID: {
                                            KlasmetMessagingSessionIdMsg klasmetSessionIdMessage = KlasmetMessagingSessionIdMsg.toObject(line);
                                            sessionId = klasmetSessionIdMessage.getSessionId();
                                            groupname = klasmetSessionIdMessage.getGroupname();
                                            //implement menu authorization beased on group name
                                            //1=file, 2:klasmet , 3:Performance
                                            switch (klasmetFramework_fe.KlasmetInitializer.getGroupname()) {
                                                case "Operation":
                                                    //Klasmet menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuKlasmet().setDisable(false);
                                                    //Performance menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuPerformance().setDisable(false);
                                                    //Reports menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuReports().setDisable(false);
                                                    //Operations menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuOperations().setDisable(false);
                                                    break;
                                                case "FO":
                                                    //Klasmet menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuKlasmet().setDisable(false);
                                                    //Performance menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuPerformance().setDisable(false);
                                                    //Reports menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuReports().setDisable(false);
                                                    //Operations menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuOperations().setDisable(false);
                                                    break;
                                                case "CustomerTechnical":
                                                    //Klasmet menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuKlasmet().setDisable(true);
                                                    //Performance menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuPerformance().setDisable(false);
                                                    //Reports menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuReports().setDisable(false);
                                                    break;
                                                case "CustomerMarketing":
                                                    //Klasmet menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuKlasmet().setDisable(true);
                                                    //Performance menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuPerformance().setDisable(false);
                                                    //Reports menu
                                                    KlasmetPresenterManager.klasmetPresenterMain.getMenuReports().setDisable(false);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            KlasmetMessagingSessionIdAckMsg klasmetSessionIdAckMessage = new KlasmetMessagingSessionIdAckMsg(sessionId);
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Sending authorization request");
                                                }
                                            });

                                            klasmetTcpStreamer.write(klasmetSessionIdAckMessage);
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_COMMAND_RESPONSE: {
                                            KlasmetMessagingCommandResponseMsg commandResponse = KlasmetMessagingCommandResponseMsg.toObject(line);
                                            KlasmetCluster.reflector(commandResponse);
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_PERFORMANCE_RES: {
                                            KlasmetMessagingPerformanceResponseMsg performanceResponse = KlasmetMessagingPerformanceResponseMsg.toObject(line);
                                            KlasmetCluster.reflector(performanceResponse);
                                            break;
                                        }

                                        case KlasmetMessagingKey.KEY_REPORT_RES: {
                                            KlasmetMessagingReportResponseMsg reportResponse = KlasmetMessagingReportResponseMsg.toObject(line);
                                            KlasmetCluster.reflector(reportResponse);
                                            break;
                                        }

                                        case KlasmetMessagingKey.KEY_SYSTEMSTAT_RESPONSE: {
                                            KlasmetMessagingSystemstatResponseMsg systemstatResponse = KlasmetMessagingSystemstatResponseMsg.toObject(line);
                                            KlasmetCluster.reflector(systemstatResponse);
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_CLIENTVERSION_RESPONSE: {
                                            KlasmetMessagingClientversionResponseMsg clientversionResponse = KlasmetMessagingClientversionResponseMsg.toObject(line);
                                            KlasmetCluster.reflector(clientversionResponse);
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_FILETRANSFER_RESPONSE: {
                                            KlasmetMessagingFiletransferResponseMsg filetransferResponseResponse = KlasmetMessagingFiletransferResponseMsg.toObject(line);
                                            if (filetransferResponseResponse.getAgent().equals(KlasmetMessagingKey.CLIENT_NEWVERSION_DOWNLOAD) && filetransferResponseResponse.getConnectorName().equals(KlasmetMessagingKey.EMERUX_SERVER)) {
                                                //This is a client latest version download response
                                                if (filetransferResponseResponse.getFileSize() == -1) {
                                                    filetransferResponseResponse.setFileName("-1," + filetransferResponseResponse.getFileName());
                                                    KlasmetCluster.reflector(filetransferResponseResponse);
                                                } else {
                                                    FTPclient ftpClient = new FTPclient(klasmetConfiguration.getServer_ip(), klasmetConfiguration.getServer_ftp_port());
                                                    ftpClient.getFile(filetransferResponseResponse, filetransferResponseResponse.getFileName(), klasmetConfiguration.getRunningDirectory(), filetransferResponseResponse.getFileSize());
                                                }
                                            } else if (filetransferResponseResponse.getAgent().equals(KlasmetMessagingKey.REPORT_FILE_DOWNLOAD) && filetransferResponseResponse.getConnectorName().equals(KlasmetMessagingKey.EMERUX_SERVER)) {
                                                //This is a REPORT FILE download response
                                                if (filetransferResponseResponse.getFileSize() == -1) {
                                                    filetransferResponseResponse.setFileName("-1," + filetransferResponseResponse.getFileName());
                                                    KlasmetCluster.reflector(filetransferResponseResponse);
                                                } else {
                                                    FTPclient ftpClient = new FTPclient(klasmetConfiguration.getServer_ip(), klasmetConfiguration.getServer_ftp_port());
                                                    ftpClient.getFile(filetransferResponseResponse, filetransferResponseResponse.getFileName(), klasmetConfiguration.getPathToSaveDownloadedFiles(), filetransferResponseResponse.getFileSize());
                                                }
                                            } else {//This is a normap FILE download response
                                                if (filetransferResponseResponse.getFileSize() == -1) {
                                                    filetransferResponseResponse.setFileName("-1," + filetransferResponseResponse.getFileName());
                                                    KlasmetCluster.reflector(filetransferResponseResponse);
                                                } else {
                                                    FTPclient ftpClient = new FTPclient(klasmetConfiguration.getServer_ip(), klasmetConfiguration.getServer_ftp_port());
                                                    ftpClient.getFile(filetransferResponseResponse, filetransferResponseResponse.getFileName(), klasmetConfiguration.getPathToSaveDownloadedFiles(), filetransferResponseResponse.getFileSize());
                                                }
                                            }
                                            break;
                                        }
                                        case KlasmetMessagingKey.KEY_ALARM_TRAP: {
                                            KlasmetMessagingAlarmMsg klasmetAlarmMessage = KlasmetMessagingAlarmMsg.toObject(line);
                                            KlasmetPresenterAlarmMonitoring.newAlarm(klasmetAlarmMessage);
                                            break;

                                        }
                                        case KlasmetMessagingKey.KEY_ALARM_CLEAR: {
                                            KlasmetMessagingAlarmClearMsg klasmetAlarmClearMessage = KlasmetMessagingAlarmClearMsg.toObject(line);
                                            KlasmetPresenterAlarmMonitoring.clearAlarm(klasmetAlarmClearMessage);
                                            break;

                                        }
                                        case KlasmetMessagingKey.KEY_ALARM_ACK: {
                                            KlasmetMessagingAlarmAckMsg klasmetAlarmAckMessage = KlasmetMessagingAlarmAckMsg.toObject(line);
                                            KlasmetPresenterAlarmMonitoring.ackAlarm(klasmetAlarmAckMessage);
                                            break;

                                        }
                                    }
                                }
                            }
                        } catch (IOException | ParseException e) {
                            KlasmetPresenterManager.klasmetPresenterMain.getConnectionStatus().setFill(Color.RED);
                            connectedToServer = false;
                            connectionTryLock = false;
                            tryToConnectToserver = true;
                            if (!isLoginFormVisible) {
                                isLoginFormVisible = true;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/klasmetFramework_fe/klasmetView/KlasmetLoginForm.fxml"));
                                            Parent root = loader.load();
                                            KlasmetPresenterLogin klasmetPresenterLogin = loader.getController();
                                            Scene scene = new Scene(root);
                                            Stage stage = new Stage();
                                            try {
                                                klasmetPresenterLogin.klasmetPresenterConstructor(stage, klasmetPresenterLogin, "KlasmetPresenterLogin");
                                                stage.setScene(scene);
                                                stage.initModality(Modality.WINDOW_MODAL);
                                                stage.initOwner(KLASMET_MAIN_STAGE);
                                                stage.initStyle(StageStyle.UNDECORATED);
                                                //stage.setTitle("KLASMET Login");
                                                stage.show();
                                            } catch (PresenterAlreadyRunningException ex) {
                                            }

                                        } catch (IOException ex) {
                                        }
                                    }
                                });

                            }
                        }
                    }
                    connectionTryLock = false;
                    if (retry == 3) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Could not reach the server after 3 tries");
                                ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                                ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginUsername().setDisable(false);
                                ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginPassword().setDisable(false);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        }).start();
// END starters
    }

    /**
     * @return the groupname
     */
    public static String getGroupname() {
        return groupname;
    }

    /**
     * @param aGroupname the groupname to set
     */
    public static void setGroupname(String aGroupname) {
        groupname = aGroupname;
    }

}
