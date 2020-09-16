package klasmetFramework_fe;

import java.io.*;
import java.net.*;

/**
 *
 * @author nami
 */
final class KlasmetTcpStreamer {

    private InputStream inbound = null;
    private DataOutputStream outbound = null;
    private final String serverIp;
    private final int serverPort;
    private Socket clientSocket;

    protected KlasmetTcpStreamer(String server_ip, int server_port) {
        this.serverIp = server_ip;
        this.serverPort = server_port;
    }

    protected BufferedReader init() throws IOException {
        InetAddress ip = InetAddress.getByName(this.serverIp);
        clientSocket = new Socket();
        clientSocket.setSoLinger(true, 0);
        SocketAddress socketAddress = new InetSocketAddress(ip, this.serverPort);
        clientSocket.connect(socketAddress, 10000);
        //clientSocket = new Socket(ip, this.serverPort);
        //clientSocket.setSoLinger(true, 0);
        inbound = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inbound));
        outbound = new DataOutputStream(clientSocket.getOutputStream());
        return bufferedReader;
    }

    protected void destroy() {
        try {
            this.clientSocket.getInputStream().close();
        } catch (IOException ex) {
        }
        try {
            this.clientSocket.getOutputStream().close();
        } catch (IOException ex) {
        }
        try {
            this.clientSocket.close();
            this.clientSocket = null;
        } catch (IOException ex) {
        }
    }

    protected synchronized boolean write(KlasmetMessagingReportRequestMsg reportRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingReportRequestMsg.toJsonString(reportRequest) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingPerformanceRequestMsg performanceRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingPerformanceRequestMsg.toJsonString(performanceRequest) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingCommandRequestMsg commandRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingCommandRequestMsg.toJsonString(commandRequest) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingSystemstatRequestMsg systemstatRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingSystemstatRequestMsg.toJsonString(systemstatRequest) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingClientversionRequestMsg clientversionRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingClientversionRequestMsg.toJsonString(clientversionRequest) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingCommandResponseMsg commandResponse) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingCommandResponseMsg.toJsonString(commandResponse) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingHeartBeatMsg heartBeat) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingHeartBeatMsg.toJsonString(heartBeat) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingSessionIdAckMsg klasmetSessionIdAckMessage) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingSessionIdAckMsg.toJsonString(klasmetSessionIdAckMessage) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingLoginInfoResponseMsg loginResponse) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingLoginInfoResponseMsg.toJsonString(loginResponse) + "\n");
        outbound.flush();
        return true;
    }

    protected synchronized boolean write(KlasmetMessagingAlarmAckMsg alarmAckMessage) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingAlarmAckMsg.toJsonString(alarmAckMessage) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingFiletransferRequestMsg filetransferRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingFiletransferRequestMsg.toJsonString(filetransferRequest) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingFiletransferResponseMsg filetransferResponse) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingFiletransferResponseMsg.toJsonString(filetransferResponse) + "\n");
        outbound.flush();
        return true;
    }
}
