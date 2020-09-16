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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author nami
 */
public class KlasmetPresenterSystemStat extends KlasmetPresenter implements Initializable {

    @FXML
    private TextArea ta_server;
    @FXML
    private TextArea ta_connectors;
    @FXML
    private Button btn_refresh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void klasmetDestroyer() {
        KlasmetPresenterMain.isSystemstatFormVisible = false;
        KlasmetInitializer.tryToConnectToserver = false;
    }

    @FXML
    public void handleButtonActionRefresh(ActionEvent evt) throws IOException {
        ta_server.setText("System status check request sent to server. waiting for response ...\n");
        ta_connectors.setText("System status check request sent to all connectors.\n");
        KlasmetMessagingSystemstatRequestMsg systemStatReq = new KlasmetMessagingSystemstatRequestMsg();
        KlasmetMessagingSystemstatRequest request = new KlasmetMessagingSystemstatRequest(systemStatReq);
        try {
            KlasmetCluster.collector(request);
        } catch (IOException | NullPointerException ex) {
            //System.out.println(ex.toString());
        } catch (ServerNotReachableException ex) {
            //JOptionPane.showMessageDialog(KlasmetSystemstatForm.this, "Server is not reachable", "Error Message", JOptionPane.ERROR_MESSAGE);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("KLASMET Error");
                    alert.setHeaderText(null);
                    alert.setResizable(true);
                    alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.KlasmetPresenterSystemStat module:\n" + ex.toString());
                    alert.showAndWait();
                }
            });
        }
    }

    public void serverStat(String message) {
        ta_server.setText(message);
    }

    public void connectorStat(String message) {
        //synchronized (ta_connectors) {
        ta_connectors.appendText(message);
        //}

    }

}
