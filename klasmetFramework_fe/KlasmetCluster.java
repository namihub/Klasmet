package klasmetFramework_fe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author nami
 */
public final class KlasmetCluster {

    private static final ArrayList<KlasmetMessagingCommandRequest> COMMAND_REQUEST_LIST = new ArrayList<>();
    private static final ArrayList<KlasmetMessagingFiletransferRequest> FILETRANSFER_REQUEST_LIST = new ArrayList<>();
    private static final ArrayList<KlasmetMessagingPerformanceRequest> PERFORMANCE_REQUEST_LIST = new ArrayList<>();
    private static final ArrayList<KlasmetMessagingReportRequest> REPORT_REQUEST_LIST = new ArrayList<>();

    public static void collector(KlasmetMessagingReportRequest request) throws IOException, NullPointerException, ServerNotReachableException {
        if (!KlasmetInitializer.connectedToServer) {
            throw new ServerNotReachableException("Server is not reachable");
        }
        Iterator requestListItr = REPORT_REQUEST_LIST.iterator();
        KlasmetMessagingReportRequestMsg reportRequest = request.getReportRequest();
        reportRequest.setClientSessionId(KlasmetInitializer.sessionId);
        reportRequest.setUuid(UUID.randomUUID().toString());
        while (requestListItr.hasNext()) {
            KlasmetMessagingReportRequest nextRequest = (KlasmetMessagingReportRequest) requestListItr.next();
            KlasmetMessagingReportRequestMsg nextReportRequest = nextRequest.getReportRequest();
            if (reportRequest.getServerPresenterClass().equals(nextReportRequest.getServerPresenterClass()) && reportRequest.getServerPresenterMethod().equals(nextReportRequest.getServerPresenterMethod())) {
                if (reportRequest.getParameters().equals(nextReportRequest.getParameters())) {
                    //KlasmetInitializer.klasmetTcpStreamer.write(reportRequest); //this is disabled on 2020Jan5
                    //do nothing and exit method.
                    return;
                } else {
                    requestListItr.remove();
                    REPORT_REQUEST_LIST.add(request);
                    KlasmetInitializer.klasmetTcpStreamer.write(reportRequest);
                    return; //this is added on 2020Jan5
                }
            }
        }
        REPORT_REQUEST_LIST.add(request);
        KlasmetInitializer.klasmetTcpStreamer.write(reportRequest);
    }

    protected static  void reflector(KlasmetMessagingReportResponseMsg response) {
        Iterator requestListItr = REPORT_REQUEST_LIST.iterator();
        while (requestListItr.hasNext()) {
            KlasmetMessagingReportRequest nextRequest = (KlasmetMessagingReportRequest) requestListItr.next();
            if (response.getUuid().equals(nextRequest.getReportRequest().getUuid())) {
                try {
                    requestListItr.remove();
                    int reportFileSize = response.getReportFileSize();
                    String reportFileName = response.getReportFileName();
                    KlasmetMessagingFiletransferRequestMsg reportFleTransferRequestMsg = new KlasmetMessagingFiletransferRequestMsg(KlasmetMessagingKey.REPORT_FILE_DOWNLOAD, KlasmetMessagingKey.EMERUX_SERVER, KlasmetInitializer.sessionId, reportFileName + "," + String.valueOf(reportFileSize));
                    KlasmetMessagingFiletransferRequest reportFleTransferRequest = new KlasmetMessagingFiletransferRequest(reportFleTransferRequestMsg, nextRequest.getPresenterClassName(), nextRequest.getPresenterMethodName());
                    collector(reportFleTransferRequest);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetCluster.KlasmetMessagingReportResponseMsg module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }
                break;
            }
        }
    }

    public static  void collector(KlasmetMessagingPerformanceRequest request) throws IOException, NullPointerException, ServerNotReachableException {
        if (!KlasmetInitializer.connectedToServer) {
            throw new ServerNotReachableException("Server is not reachable");
        }
        Iterator requestListItr = PERFORMANCE_REQUEST_LIST.iterator();
        KlasmetMessagingPerformanceRequestMsg performanceRequest = request.getPerformanceRequest();
        performanceRequest.setClientSessionId(KlasmetInitializer.sessionId);

        performanceRequest.setUuid(UUID.randomUUID().toString());
        while (requestListItr.hasNext()) {

            KlasmetMessagingPerformanceRequest nextRequest = (KlasmetMessagingPerformanceRequest) requestListItr.next();
            KlasmetMessagingPerformanceRequestMsg nextPerformanceRequest = nextRequest.getPerformanceRequest();
            if (performanceRequest.getServerPresenterClass().equals(nextPerformanceRequest.getServerPresenterClass()) && performanceRequest.getServerPresenterMethod().equals(nextPerformanceRequest.getServerPresenterMethod())) {
                if (performanceRequest.getParameters().equals(nextPerformanceRequest.getParameters())) {
                    //KlasmetInitializer.klasmetTcpStreamer.write(performanceRequest); //this is disabled on 2020Jan5
                    //do nothing and exit method.
                    return;
                } else {
                    requestListItr.remove();
                    PERFORMANCE_REQUEST_LIST.add(request);
                    KlasmetInitializer.klasmetTcpStreamer.write(performanceRequest);
                    return; //this is added on 2020Jan5
                }
            }
        }
        PERFORMANCE_REQUEST_LIST.add(request);
        KlasmetInitializer.klasmetTcpStreamer.write(performanceRequest);
    }

    protected static  void reflector(KlasmetMessagingPerformanceResponseMsg response) {
        Iterator requestListItr = PERFORMANCE_REQUEST_LIST.iterator();
        while (requestListItr.hasNext()) {
            KlasmetMessagingPerformanceRequest nextRequest = (KlasmetMessagingPerformanceRequest) requestListItr.next();
            if (response.getUuid().equals(nextRequest.getPerformanceRequest().getUuid())) {
                String presenterClassName = nextRequest.getPresenterClassName();
                String presenterMethodName = nextRequest.getPresenterMethodName();
                String performanceData = response.getData();
                requestListItr.remove();
                KlasmetPresenterManager.reflector(presenterClassName, presenterMethodName, performanceData);
                break;
            }
        }
    }

    public static  void collector(KlasmetMessagingCommandRequest request) throws IOException, NullPointerException, ServerNotReachableException {
        if (!KlasmetInitializer.connectedToServer) {
            throw new ServerNotReachableException("Server is not reachable");
        }
        Iterator requestListItr = COMMAND_REQUEST_LIST.iterator();
        KlasmetMessagingCommandRequestMsg commandRequest = request.getCommandRequest();
        commandRequest.setClientSessionId(KlasmetInitializer.sessionId);
        while (requestListItr.hasNext()) {
            KlasmetMessagingCommandRequest nextRequest = (KlasmetMessagingCommandRequest) requestListItr.next();
            KlasmetMessagingCommandRequestMsg nextCommandRequest = nextRequest.getCommandRequest();
            if (commandRequest.getConnectorName().equals(nextCommandRequest.getConnectorName()) && commandRequest.getAgent().equals(nextCommandRequest.getAgent())) {
                if (commandRequest.getAgentParameters().equals(nextCommandRequest.getAgentParameters())) {
                    //KlasmetInitializer.klasmetTcpStreamer.write(performanceRequest); //this is disabled on 2020Jan5
                    //do nothing and exit method.
                    return;
                } else {
                    requestListItr.remove();
                    COMMAND_REQUEST_LIST.add(request);
                    KlasmetInitializer.klasmetTcpStreamer.write(commandRequest);
                    return; //this is added on 2020Jan5
                }
            }
        }
        COMMAND_REQUEST_LIST.add(request);
        KlasmetInitializer.klasmetTcpStreamer.write(commandRequest);
    }

    protected static  void reflector(KlasmetMessagingCommandResponseMsg response) {
        Iterator requestListItr = COMMAND_REQUEST_LIST.iterator();
        while (requestListItr.hasNext()) {
            KlasmetMessagingCommandRequest nextRequest = (KlasmetMessagingCommandRequest) requestListItr.next();
            if (response.getConnectorName().equals(nextRequest.getCommandRequest().getConnectorName()) && response.getAgent().equals(nextRequest.getCommandRequest().getAgent())) {
                String presenterClassName = nextRequest.getPresenterClassName();
                String presenterMethodName = nextRequest.getPresenterMethodName();
                String commandResponseParameters = response.getMessage();
                requestListItr.remove();
                KlasmetPresenterManager.reflector(presenterClassName, presenterMethodName, commandResponseParameters);
                break;
            }
        }
    }

    public static  void collector(KlasmetMessagingFiletransferRequest request) throws IOException, NullPointerException, ServerNotReachableException {
        if (!KlasmetInitializer.connectedToServer) {
            throw new ServerNotReachableException("Server is not reachable");
        }
        Iterator requestListItr = FILETRANSFER_REQUEST_LIST.iterator();
        KlasmetMessagingFiletransferRequestMsg ftpRequestequest = request.getFiletransferRequest();
        ftpRequestequest.setClientSessionId(KlasmetInitializer.sessionId);
        while (requestListItr.hasNext()) {
            KlasmetMessagingFiletransferRequest nextRequest = (KlasmetMessagingFiletransferRequest) requestListItr.next();
            KlasmetMessagingFiletransferRequestMsg nextFtpRequest = nextRequest.getFiletransferRequest();
            if (ftpRequestequest.getConnectorName().equals(nextFtpRequest.getConnectorName()) && ftpRequestequest.getAgent().equals(nextFtpRequest.getAgent())) {
                if (ftpRequestequest.getAgentParameters().equals(nextFtpRequest.getAgentParameters())) {
                    //KlasmetInitializer.klasmetTcpStreamer.write(ftpRequestequest);
                    return;
                } else {
                    requestListItr.remove();
                    FILETRANSFER_REQUEST_LIST.add(request);
                    KlasmetInitializer.klasmetTcpStreamer.write(ftpRequestequest);
                    return;//this is added on 2020Jan5
                }
            }
        }
        FILETRANSFER_REQUEST_LIST.add(request);
        KlasmetInitializer.klasmetTcpStreamer.write(ftpRequestequest);
    }

    public static  void reflector(KlasmetMessagingFiletransferResponseMsg response) {
        Iterator requestListItr = FILETRANSFER_REQUEST_LIST.iterator();
        while (requestListItr.hasNext()) {
            KlasmetMessagingFiletransferRequest nextRequest = (KlasmetMessagingFiletransferRequest) requestListItr.next();
            if (response.getConnectorName().equals(nextRequest.getFiletransferRequest().getConnectorName()) && response.getAgent().equals(nextRequest.getFiletransferRequest().getAgent())) {
                String presenterClassName = nextRequest.getPresenterClassName();
                String presenterMethodName = nextRequest.getPresenterMethodName();
                String filetransferResponseFileName = response.getFileName();
                //int filetransferResponseFileSize = response.getFileSize();
                requestListItr.remove();
                KlasmetPresenterManager.reflector(presenterClassName, presenterMethodName, filetransferResponseFileName);
                break;
            }
        }
    }

    public static  void collector(KlasmetMessagingClientversionRequest request) throws IOException, NullPointerException, ServerNotReachableException {
        if (!KlasmetInitializer.connectedToServer) {
            throw new ServerNotReachableException("Server is not reachable");
        }
        KlasmetMessagingClientversionRequestMsg ClientversionRequest = request.getClientversionRequest();
        ClientversionRequest.setClientSessionId(KlasmetInitializer.sessionId);
        KlasmetInitializer.klasmetTcpStreamer.write(ClientversionRequest);
    }

    protected static  void reflector(KlasmetMessagingClientversionResponseMsg response) {
        String presenterClassName = "";
        String presenterMethodName = "";
        if (response.getConnectorName() != null && response.getConnectorName().equals(KlasmetMessagingKey.EMERUX_SERVER)) {
            presenterClassName = "KlasmetPresenterSystemClientVersion";
            presenterMethodName = "clientversion";
        }
        KlasmetPresenterManager.reflector(presenterClassName, presenterMethodName, response.getMessage());
    }

    public static  void collector(KlasmetMessagingSystemstatRequest request) throws IOException, NullPointerException, ServerNotReachableException {
        if (!KlasmetInitializer.connectedToServer) {
            throw new ServerNotReachableException("Server is not reachable");
        }
        KlasmetMessagingSystemstatRequestMsg systemstatRequest = request.getSystemstatRequest();
        systemstatRequest.setClientSessionId(KlasmetInitializer.sessionId);
        KlasmetInitializer.klasmetTcpStreamer.write(systemstatRequest);
    }

    protected static  void reflector(KlasmetMessagingSystemstatResponseMsg response) {
        String presenterClassName = "";
        String presenterMethodName = "";
        if (response.getConnectorName() != null && response.getConnectorName().equals(KlasmetMessagingKey.EMERUX_SERVER)) {
            presenterClassName = "KlasmetPresenterSystemStat";
            presenterMethodName = "serverStat";
        } else if (response.getConnectorName() != null && !response.getConnectorName().equals(KlasmetMessagingKey.EMERUX_SERVER)) {
            presenterClassName = "KlasmetPresenterSystemStat";
            presenterMethodName = "connectorStat";
        }
        KlasmetPresenterManager.reflector(presenterClassName, presenterMethodName, response.getMessage());
    }

}
