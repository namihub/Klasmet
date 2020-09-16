package klasmetFramework_be;

import java.io.*;
import java.net.*;

/**
 *
 * @author nami
 */
public final class KlasmetTcpStreamer {

    private InputStream inbound = null;
    private DataOutputStream outbound = null;
    private final String serverIp;
    private final int serverPort;
    private Socket clientSocket;

    public KlasmetTcpStreamer(String server_ip, int server_port) {
        this.serverIp = server_ip;
        this.serverPort = server_port;
    }

    public BufferedReader init() throws IOException {
        InetAddress ip = InetAddress.getByName(this.serverIp);
        clientSocket = new Socket(ip, this.serverPort);
        clientSocket.setSoLinger(true, 0);
        inbound = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inbound));
        outbound = new DataOutputStream(clientSocket.getOutputStream());
        return bufferedReader;
    }

    public void destroy() {
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

    public synchronized boolean write(KlasmetMessagingCommandRequestMsg commandRequest) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingCommandRequestMsg.toJsonString(commandRequest) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingCommandResponseMsg commandResponse) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingCommandResponseMsg.toJsonString(commandResponse) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingHeartBeatMsg heartBeat) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingHeartBeatMsg.toJsonString(heartBeat) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingSessionIdAckMsg sessionIdAckMessage) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingSessionIdAckMsg.toJsonString(sessionIdAckMessage) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingLoginInfoResponseMsg loginResponse) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingLoginInfoResponseMsg.toJsonString(loginResponse) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingAlarmSyncMsg alarmSyncMessage) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingAlarmSyncMsg.toJsonString(alarmSyncMessage) + "\n");
        outbound.flush();
        return true;
    }

    public synchronized boolean write(KlasmetMessagingAlarmMsg alarmMessage) throws IOException, NullPointerException {
        outbound.flush();
        outbound.writeBytes(KlasmetMessagingAlarmMsg.toJsonString(alarmMessage) + "\n");
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
