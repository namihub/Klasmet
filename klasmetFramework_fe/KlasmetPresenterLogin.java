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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nami
 */
public class KlasmetPresenterLogin extends KlasmetPresenter implements Initializable {

    @FXML
    private Label klasmetLoginMessage;
    @FXML
    private TextField klasmetLoginUsername;
    @FXML
    private PasswordField klasmetLoginPassword;
    @FXML
    private CheckBox klasmetLoginSavelogin;
    @FXML
    private Button klasmetLoginLogin;
    @FXML
    private Button klasmetLoginClose;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (KlasmetInitializer.connectedToServer) {
            getKlasmetLoginMessage().setText("Already connected to server");
            getKlasmetLoginUsername().setDisable(true);
            getKlasmetLoginPassword().setDisable(true);
            getKlasmetLoginLogin().setDisable(true);
        }
        try {//read saved login info from credential file
            String RunningDirectory = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            RunningDirectory = RunningDirectory.substring(0, RunningDirectory.substring(0, RunningDirectory.length() - 1).lastIndexOf("/"));
            String credentialFile = RunningDirectory + "/config/" + "credential";
            String credential = KlasmetCipher.decrypt(credentialFile);
            String[] credentialArray = credential.split(" ");
            getKlasmetLoginUsername().setText(credentialArray[0]);
            getKlasmetLoginPassword().setText(credentialArray[1]);
            //System.out.println(credential);
        } catch (Exception e) {
        }
    }

    @FXML
    public void handleButtonActionLogin(ActionEvent evt) throws IOException {

        getKlasmetLoginUsername().setDisable(true);
        getKlasmetLoginPassword().setDisable(true);
        getKlasmetLoginClose().setDisable(true);
        if (KlasmetInitializer.connectedToServer) {
            getKlasmetLoginMessage().setText("Already connected to server");
            getKlasmetLoginUsername().setDisable(true);
            getKlasmetLoginPassword().setDisable(true);
            getKlasmetLoginLogin().setDisable(true);
            getKlasmetLoginClose().setDisable(false);
        } else {
            if (!KlasmetInitializer.connectionTryLock) {
                //KlasmetInitializer.tryToConnectToserver = true;
                final String userName = getKlasmetLoginUsername().getText();
                final String userPass = String.valueOf(getKlasmetLoginPassword().getText());
                if (userName.equals("") || userPass.equals("") || userName.contains("'") || userPass.contains("'") || userName.contains("\"") || userPass.contains("\"") || userName.contains(" ") || userPass.contains(" ")) {
                    getKlasmetLoginMessage().setText("Invalid username and/or password");
                    getKlasmetLoginUsername().setDisable(false);
                    getKlasmetLoginPassword().setDisable(false);
                    getKlasmetLoginClose().setDisable(false);
                } else {
                    try {//write save login info on credential file
                        if (getKlasmetLoginSavelogin().isSelected()) {
                            String RunningDirectory = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
                            RunningDirectory = RunningDirectory.substring(0, RunningDirectory.substring(0, RunningDirectory.length() - 1).lastIndexOf("/"));
                            String credentialFile = RunningDirectory + "/config/" + "credential";
                            KlasmetCipher.encrypt(userName + " " + userPass, credentialFile);
                        }
                    } catch (Exception e) {
                    }
                    KlasmetInitializer.tryToConnectToserver = true;
                    getKlasmetLoginMessage().setText("Initializing Klasmet Framework...");
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            KlasmetInitializer.init(userName, userPass);
                        }
                    });
                }
            } else {
                getKlasmetLoginMessage().setText("Connection retry is locked. please retry later");
            }
        }
    }

    @FXML
    public void handleButtonActionCancel(ActionEvent evt) throws IOException {
        //KlasmetMainFormController.isLoginFormVisible = false;
        //KlasmetInitializer.tryToConnectToserver = false;
        Stage stage = (Stage) getKlasmetLoginClose().getScene().getWindow();
        stage.close();
    }

    @Override
    public void klasmetDestroyer() {
        KlasmetPresenterMain.isLoginFormVisible = false;
        KlasmetInitializer.tryToConnectToserver = false;
    }

    /**
     * @return the klasmetLoginMessage
     */
    public Label getKlasmetLoginMessage() {
        return klasmetLoginMessage;
    }

    /**
     * @param klasmetLoginMessage the klasmetLoginMessage to set
     */
    public void setKlasmetLoginMessage(Label klasmetLoginMessage) {
        this.klasmetLoginMessage = klasmetLoginMessage;
    }

    /**
     * @return the klasmetLoginUsername
     */
    public TextField getKlasmetLoginUsername() {
        return klasmetLoginUsername;
    }

    /**
     * @param klasmetLoginUsername the klasmetLoginUsername to set
     */
    public void setKlasmetLoginUsername(TextField klasmetLoginUsername) {
        this.klasmetLoginUsername = klasmetLoginUsername;
    }

    /**
     * @return the klasmetLoginPassword
     */
    public PasswordField getKlasmetLoginPassword() {
        return klasmetLoginPassword;
    }

    /**
     * @param klasmetLoginPassword the klasmetLoginPassword to set
     */
    public void setKlasmetLoginPassword(PasswordField klasmetLoginPassword) {
        this.klasmetLoginPassword = klasmetLoginPassword;
    }

    /**
     * @return the klasmetLoginSavelogin
     */
    public CheckBox getKlasmetLoginSavelogin() {
        return klasmetLoginSavelogin;
    }

    /**
     * @param klasmetLoginSavelogin the klasmetLoginSavelogin to set
     */
    public void setKlasmetLoginSavelogin(CheckBox klasmetLoginSavelogin) {
        this.klasmetLoginSavelogin = klasmetLoginSavelogin;
    }

    /**
     * @return the klasmetLoginLogin
     */
    public Button getKlasmetLoginLogin() {
        return klasmetLoginLogin;
    }

    /**
     * @param klasmetLoginLogin the klasmetLoginLogin to set
     */
    public void setKlasmetLoginLogin(Button klasmetLoginLogin) {
        this.klasmetLoginLogin = klasmetLoginLogin;
    }

    /**
     * @return the klasmetLoginClose
     */
    public Button getKlasmetLoginClose() {
        return klasmetLoginClose;
    }

    /**
     * @param klasmetLoginClose the klasmetLoginClose to set
     */
    public void setKlasmetLoginClose(Button klasmetLoginClose) {
        this.klasmetLoginClose = klasmetLoginClose;
    }

}
