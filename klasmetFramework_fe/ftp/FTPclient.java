package klasmetFramework_fe.ftp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import klasmetFramework_fe.KlasmetCluster;
import klasmetFramework_fe.KlasmetInitializer;
import klasmetFramework_fe.KlasmetMessagingFiletransferResponseMsg;

/**
 *
 * @author nami
 */
public class FTPclient {

    private Socket socket;

    public FTPclient(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {

        }
    }

    public void getFile(KlasmetMessagingFiletransferResponseMsg filetransferResponseResponse, String serverSideFileNameToDownload, String localSidePathToSaveFile, int fileSize) {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileOutputStream fos = new FileOutputStream(localSidePathToSaveFile + serverSideFileNameToDownload);
            int chunkSize = 1024;
            dos.writeUTF(serverSideFileNameToDownload + "(fileSize)" + fileSize);
            byte[] bufferChunk = new byte[chunkSize];
            int remaining = fileSize;
            int read = 0;
            while ((read = dis.read(bufferChunk, 0, Math.min(chunkSize, remaining))) > 0) {
                remaining -= read;
                fos.write(bufferChunk, 0, read);
            }
            dis.close();
            fos.close();
            KlasmetCluster.reflector(filetransferResponseResponse);
        } catch (Exception e) {
            KlasmetInitializer.klasmetLogger.writeLog(1, "FTPclient.getFile()", e.toString());
        }
    }
}
