package PatientRegistration;


import DB.dbConnection;
import Patient.PatientLogIn;
import PatientView.PatientViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class PatientRegistrationController implements Initializable {

    //Instance of patientLogin
    PatientLogIn patientLogIn= new PatientLogIn();

    //Database connection
    private dbConnection db;

    //TextFields
    @FXML
    private TextField medicalNbrTF;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField  lastNameTF;
    @FXML
    private TextField sexTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField birthdateTF;
    @FXML
    private TextField registrationTF;
    @FXML
    private Button registerBtn;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        this.db = new dbConnection();
    }

    @FXML
    private void createPatient(ActionEvent event) {
        try{
            if(!this.patientLogIn.isLogin(this.medicalNbrTF.getText()) && !this.medicalNbrTF.getText().isEmpty()){
                String sqlCreate = "INSERT INTO Patient (Med_nbr, Fname, Lname, Sex, Adress, Phone, birthdate, Registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try{
                    Connection conn = dbConnection.getConnection();
                    PreparedStatement stmt= conn.prepareStatement(sqlCreate);
                    stmt.setString(1,this.medicalNbrTF.getText());
                    stmt.setString(2,this.firstNameTF.getText());
                    stmt.setString(3,this.lastNameTF.getText());
                    stmt.setString(4,this.sexTF.getText());
                    stmt.setString(5,this.addressTF.getText());
                    stmt.setString(6,this.phoneTF.getText());
                    stmt.setString(7,this.birthdateTF.getText());
                    stmt.setString(8,this.registrationTF.getText());

                    stmt.execute();
                    stmt.close();
                    conn.close();
                    login();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Medical number already exist or is empty");
                alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong");
            alert.showAndWait();
        }
    }

    public void login() {
        try{
            if(this.patientLogIn.isLogin(this.medicalNbrTF.getText())){
                Stage stage = (Stage)this.registerBtn.getScene().getWindow();
                stage.close();
                try{
                    Stage patientview = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Pane root = (Pane)loader.load(getClass().getResource("/PatientView/PatientView.FXML").openStream());
                    PatientViewController patientViewController = (PatientViewController)loader.getController();
                    patientViewController.medNbr(this.medicalNbrTF.getText());

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
}
