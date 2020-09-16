/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import static klasmetFramework_fe.KlasmetInitializer.klasmetConfiguration;

/**
 *
 * @author nami
 */
public class KlasmetPresenterSystemClientVersion {

    public static  void clientversion(String message) {
        if (message.equals(KlasmetClientVersion.selfVersion)) {//client is already the latest Version
            ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("You can now close this window start working with Emerux");
                }
            });

        } else {//download the latest Version
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Downloading latest version..");
                }
            });
            KlasmetMessagingFiletransferRequestMsg ftpReq = new KlasmetMessagingFiletransferRequestMsg(KlasmetMessagingKey.CLIENT_NEWVERSION_DOWNLOAD, KlasmetMessagingKey.EMERUX_SERVER, null);
            KlasmetMessagingFiletransferRequest request = new KlasmetMessagingFiletransferRequest(ftpReq, "KlasmetPresenterSystemClientVersion", "clientDownloadResultHandler");
            try {
                KlasmetCluster.collector(request);
            } catch (IOException | NullPointerException ex) {
            } catch (ServerNotReachableException ex) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Server is not reachable");
                        ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                    }
                });
            }
        }
    }

    public static  void clientDownloadResultHandler(String message) {

        if (message.startsWith("-1,")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("Could not download new version" + message.substring(3) + ". Please check logs on Emerux Server");
                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginClose().setDisable(false);
                    ((KlasmetPresenterLogin) KlasmetPresenterManager.getPresenterModelByPresenterClassName("KlasmetPresenterLogin").getPresenterClassObject()).getKlasmetLoginMessage().setText("New version downloaded");
                }
            });

            //Delete self selfVersion
//            try {
//                File selfClient = new File(klasmetConfiguration.getRunningDirectory() + KlasmetClientVersion.selfVersion + ".jar");
//                if (selfClient.exists()) {
//                    selfClient.delete();
//                }
//            } catch (Exception e) {
//            }
            try {
                String newClientFileName = message;
                String newClient = klasmetConfiguration.getRunningDirectory() + newClientFileName;
                File newClientFile = new File(newClient);
                newClientFile.setExecutable(true);
            } catch (Exception e) {
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Version Upgrade");
                    alert.setHeaderText(null);
                    alert.setResizable(true);
                    alert.setContentText("A new version of Emerux client is downloaded to your system, this instance will be closed automatically.\n Please re-run Emerux client after closing this message.\n New version name is " + message);
                    alert.showAndWait();
                    System.exit(0);
                }
            });

        }
    }

}
