package Patient;


import PatientRegistration.PatientRegistrationController;
import PatientView.MedicalNbrModel;
import PatientView.PatientViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientLogInController implements Initializable {

    PatientLogIn patientLogIn= new PatientLogIn();

    @FXML
    private TextField medicalNbrLogin;
    @FXML
    private Label dbStatus;
    @FXML
    private Button logIn;
    @FXML
    private Button newPatient;

   // @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    if(this.patientLogIn.isDatabaseConnected()){
        this.dbStatus.setText("Connected");
    }
    else{
        this.dbStatus.setText("Not Connected");
    }
    }



    @FXML
    public void login(ActionEvent event) throws Exception{
        try{
            if(this.patientLogIn.isLogin(this.medicalNbrLogin.getText())){
                Stage stage = (Stage)this.logIn.getScene().getWindow();
                stage.close();
                try{
                    Stage patientview = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Pane root = (Pane)loader.load(getClass().getResource("/PatientView/PatientView.FXML").openStream());
                    PatientViewController patientViewController = (PatientViewController)loader.getController();
                    patientViewController.medNbr(this.medicalNbrLogin.getText());

                    Scene scene = new Scene(root);
                    patientview.setScene(scene);
                    patientview.setTitle("Patient View");
                    patientview.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Medical number doesn't exist");
                alert.showAndWait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void registration(ActionEvent rege) throws Exception{
        try {
            Stage patientRegistration = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/PatientRegistration/PatientRegistration.FXML").openStream());
            PatientRegistrationController patientRegistrationController = (PatientRegistrationController) loader.getController();


            Scene scene = new Scene(root);
            patientRegistration.setScene(scene);
            patientRegistration.setTitle("Patient registration");
            patientRegistration.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
