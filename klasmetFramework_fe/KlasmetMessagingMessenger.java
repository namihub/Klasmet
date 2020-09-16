/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nami
 */
public abstract class KlasmetMessagingMessenger {

    public static final ArrayList<KlasmetMessagingCommandRequestMsg> COMMAND_REQUEST_LIST = new ArrayList<>();
    public static final ArrayList<KlasmetMessagingFiletransferRequestMsg> FILETRANSFER_REQUEST_LIST = new ArrayList<>();
    public static final ArrayList<KlasmetMessagingPerformanceRequestMsg> PERFORMANCE_REQUEST_LIST = new ArrayList<>();

    private String connectorName;

    public static  String getKey(String message) throws ParseException {
        JSONParser requestParser = new JSONParser();
        JSONObject requestParsedJSONObj = (JSONObject) requestParser.parse(message);
        return (requestParsedJSONObj.get(KlasmetMessagingKey.KEY).toString());
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
     * Note: add all type of requests list to this method
     *
     * @param _connectorName
     */
    public static  void flushRequestsByConnector(String _connectorName) {
        Iterator commandRequestListItr = COMMAND_REQUEST_LIST.iterator();
        while (commandRequestListItr.hasNext()) {
            KlasmetMessagingCommandRequestMsg nextCommandRequest = (KlasmetMessagingCommandRequestMsg) commandRequestListItr.next();
            if (nextCommandRequest.getConnectorName().equals(_connectorName)) {
                commandRequestListItr.remove();
            }
        }
        Iterator filetransferRequestListItr = FILETRANSFER_REQUEST_LIST.iterator();
        while (filetransferRequestListItr.hasNext()) {
            KlasmetMessagingFiletransferRequestMsg nextFiletransferRequestListItr = (KlasmetMessagingFiletransferRequestMsg) filetransferRequestListItr.next();
            if (nextFiletransferRequestListItr.getConnectorName().equals(_connectorName)) {
                filetransferRequestListItr.remove();
            }
        }
    }

    /**
     * Note: add all type of requests list to this method
     *
     * @param clientSessionId
     */
    public static  void flushRequestsByClient(String clientSessionId) {
        Iterator commandRequestListItr = COMMAND_REQUEST_LIST.iterator();
        while (commandRequestListItr.hasNext()) {
            KlasmetMessagingCommandRequestMsg nextCommandRequest = (KlasmetMessagingCommandRequestMsg) commandRequestListItr.next();
            if (nextCommandRequest.getClientSessionId().equals(clientSessionId)) {
                commandRequestListItr.remove();
            }
        }
        Iterator filetransferRequestListItr = FILETRANSFER_REQUEST_LIST.iterator();
        while (filetransferRequestListItr.hasNext()) {
            KlasmetMessagingFiletransferRequestMsg nextFiletransferRequestListItr = (KlasmetMessagingFiletransferRequestMsg) filetransferRequestListItr.next();
            if (nextFiletransferRequestListItr.getClientSessionId().equals(clientSessionId)) {
                filetransferRequestListItr.remove();
            }
        }
        Iterator performanceRequestListItr = PERFORMANCE_REQUEST_LIST.iterator();
        while (performanceRequestListItr.hasNext()) {
            KlasmetMessagingPerformanceRequestMsg nextperformanceRequestListItr = (KlasmetMessagingPerformanceRequestMsg) performanceRequestListItr.next();
            if (nextperformanceRequestListItr.getClientSessionId() != null && nextperformanceRequestListItr.getClientSessionId().equals(clientSessionId)) {
                performanceRequestListItr.remove();
            }
        }
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param commandResponse can be any subclass of KlasmetMessenger class.
     * @return
     */
    public static  KlasmetMessagingCommandRequestMsg fetchRequest(KlasmetMessagingCommandResponseMsg commandResponse) {
        for (KlasmetMessagingCommandRequestMsg nextCommandRequest : COMMAND_REQUEST_LIST) {
            if (nextCommandRequest.getAgent().equals(commandResponse.getAgent()) && nextCommandRequest.getConnectorName().equals(commandResponse.getConnectorName())) {
                return nextCommandRequest;
            }
        }
        return null;
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param filetransferResponse can be any subclass of KlasmetMessenger class.
     * @return
     */
    public static  KlasmetMessagingFiletransferRequestMsg fetchRequest(KlasmetMessagingFiletransferResponseMsg filetransferResponse) {
        for (KlasmetMessagingFiletransferRequestMsg nextFiletransferRequest : FILETRANSFER_REQUEST_LIST) {
            if (nextFiletransferRequest.getAgent().equals(filetransferResponse.getAgent()) && nextFiletransferRequest.getConnectorName().equals(filetransferResponse.getConnectorName())) {
                return nextFiletransferRequest;
            }
        }
        return null;
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param commandRequest can be any subclass of KlasmetMessenger class.
     */
    public static  void removeRequest(KlasmetMessagingCommandRequestMsg commandRequest) {
        Iterator commandRequestListItr = COMMAND_REQUEST_LIST.iterator();
        while (commandRequestListItr.hasNext()) {
            KlasmetMessagingCommandRequestMsg nextCommandRequest = (KlasmetMessagingCommandRequestMsg) commandRequestListItr.next();
            if (nextCommandRequest.getAgent().equals(commandRequest.getAgent()) && nextCommandRequest.getConnectorName().equals(commandRequest.getConnectorName())) {
                commandRequestListItr.remove();
            }
        }
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param performanceResponse can be any subclass of KlasmetMessenger class.
     * @return
     */
    public static  KlasmetMessagingPerformanceRequestMsg fetchRequest(KlasmetMessagingPerformanceResponseMsg performanceResponse) {
        for (KlasmetMessagingPerformanceRequestMsg nextPerformanceRequest : PERFORMANCE_REQUEST_LIST) {
            if (nextPerformanceRequest.getUuid().equals(performanceResponse.getUuid())) {
                return nextPerformanceRequest;
            }
        }
        return null;
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param commandRequest can be any subclass of KlasmetMessenger class.
     * @return true if command list does not all ready contains this request
     */
    public static  boolean addRequest(KlasmetMessagingCommandRequestMsg commandRequest) {
        boolean connectorFound = false;
        Iterator commandRequestListItr = COMMAND_REQUEST_LIST.iterator();
        KlasmetMessagingCommandRequestMsg nextCommandRequest;
        while (commandRequestListItr.hasNext()) {
            nextCommandRequest = (KlasmetMessagingCommandRequestMsg) commandRequestListItr.next();
            if (nextCommandRequest.getConnectorName().equals(commandRequest.getConnectorName())) {
                connectorFound = true;
                if (nextCommandRequest.getClientSessionId().equals(commandRequest.getClientSessionId())) {
                    if (nextCommandRequest.getAgent().equals(commandRequest.getAgent())) {
                        if (nextCommandRequest.getAgentParameters().equals(commandRequest.getAgentParameters())) {
                            return false;
                        } else {
                            commandRequestListItr.remove();
                            COMMAND_REQUEST_LIST.add(commandRequest);
                            return true;
                        }
                    } else {
                        COMMAND_REQUEST_LIST.add(commandRequest);
                        return true;
                    }
                } else if (nextCommandRequest.getAgent().equals(commandRequest.getAgent())) {
                    if (nextCommandRequest.getAgentParameters().equals(commandRequest.getAgentParameters())) {
                        COMMAND_REQUEST_LIST.add(commandRequest);
                        return false;
                    } else {
                        COMMAND_REQUEST_LIST.add(commandRequest);
                        return true;
                    }
                }
            }
        }
        if (!connectorFound) {
            COMMAND_REQUEST_LIST.add(commandRequest);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param filetransferRequest can be any subclass of KlasmetMessenger class.
     */
    public static  void removeRequest(KlasmetMessagingFiletransferRequestMsg filetransferRequest) {
        Iterator filetransferRequestListItr = FILETRANSFER_REQUEST_LIST.iterator();
        while (filetransferRequestListItr.hasNext()) {
            KlasmetMessagingFiletransferRequestMsg nextFiletransferRequest = (KlasmetMessagingFiletransferRequestMsg) filetransferRequestListItr.next();
            if (nextFiletransferRequest.getAgent().equals(filetransferRequest.getAgent()) && nextFiletransferRequest.getConnectorName().equals(filetransferRequest.getConnectorName())) {
                filetransferRequestListItr.remove();
            }
        }
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param filetransferRequest can be any subclass of KlasmetMessenger class.
     * @return true if command list does not all ready contains this request
     */
    public static  boolean addRequest(KlasmetMessagingFiletransferRequestMsg filetransferRequest) {
        boolean connectorFound = false;
        Iterator commandRequestListItr = FILETRANSFER_REQUEST_LIST.iterator();
        KlasmetMessagingFiletransferRequestMsg nextFiletransferRequest;
        while (commandRequestListItr.hasNext()) {
            nextFiletransferRequest = (KlasmetMessagingFiletransferRequestMsg) commandRequestListItr.next();
            if (nextFiletransferRequest.getConnectorName().equals(filetransferRequest.getConnectorName())) {
                connectorFound = true;
                if (nextFiletransferRequest.getClientSessionId().equals(filetransferRequest.getClientSessionId())) {
                    if (nextFiletransferRequest.getAgent().equals(filetransferRequest.getAgent())) {
                        if (nextFiletransferRequest.getAgentParameters().equals(filetransferRequest.getAgentParameters())) {
                            return false;
                        } else {
                            commandRequestListItr.remove();
                            FILETRANSFER_REQUEST_LIST.add(filetransferRequest);
                            return true;
                        }
                    } else {
                        FILETRANSFER_REQUEST_LIST.add(filetransferRequest);
                        return true;
                    }
                } else if (nextFiletransferRequest.getAgent().equals(filetransferRequest.getAgent())) {
                    if (nextFiletransferRequest.getAgentParameters().equals(filetransferRequest.getAgentParameters())) {
                        FILETRANSFER_REQUEST_LIST.add(filetransferRequest);
                        return false;
                    } else {
                        FILETRANSFER_REQUEST_LIST.add(filetransferRequest);
                        return true;
                    }
                }
            }
        }
        if (!connectorFound) {
            FILETRANSFER_REQUEST_LIST.add(filetransferRequest);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param performanceRequest can be any subclass of KlasmetMessenger class.
     * @return true if command list does not all ready contains this request
     */
    public static  boolean addRequest(KlasmetMessagingPerformanceRequestMsg performanceRequest) {
        Iterator requestListItr = PERFORMANCE_REQUEST_LIST.iterator();
        while (requestListItr.hasNext()) {
            KlasmetMessagingPerformanceRequestMsg nextRequest = (KlasmetMessagingPerformanceRequestMsg) requestListItr.next();
            if (nextRequest.getClientSessionId().equals(performanceRequest.getClientSessionId())
                    && nextRequest.getServerPresenterClass().equals(performanceRequest.getServerPresenterClass())
                    && nextRequest.getServerPresenterMethod().equals(performanceRequest.getServerPresenterMethod())) {
                requestListItr.remove();
            }
        }
        PERFORMANCE_REQUEST_LIST.add(performanceRequest);
        return true;
    }

    /**
     * Note: Override this method for all types of messages.
     *
     * @param performanceRequest can be any subclass of KlasmetMessenger class.
     */
    public static  void removeRequest(KlasmetMessagingPerformanceRequestMsg performanceRequest) {
        Iterator performanceRequestListItr = PERFORMANCE_REQUEST_LIST.iterator();
        while (performanceRequestListItr.hasNext()) {
            KlasmetMessagingPerformanceRequestMsg nextPerformanceRequest = (KlasmetMessagingPerformanceRequestMsg) performanceRequestListItr.next();
            if (nextPerformanceRequest.getUuid().equals(performanceRequest.getUuid())) {
                performanceRequestListItr.remove();
            }
        }
    }

}
