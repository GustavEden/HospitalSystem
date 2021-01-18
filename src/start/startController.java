package start;

import AdminView.AdminViewController;
import Patient.PatientLogInController;
import DoctorView.DoctorViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class startController {

    @FXML
    private Button patientBtn;
    @FXML
    private Button doctorBtn;
    @FXML
    private Button adminBtn;

    public void startPatient() {
        Stage stage = (Stage)this.patientBtn.getScene().getWindow();
        stage.close();
        try{
            Stage patientview = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/Patient/PatientLogin.FXML").openStream());
            PatientLogInController patientLoginController = (PatientLogInController)loader.getController();

            Scene scene = new Scene(root);
            patientview.setScene(scene);
            patientview.setTitle("Patient View");
            patientview.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startDoctor() {
        Stage stage = (Stage)this.doctorBtn.getScene().getWindow();
        stage.close();
        try{
            Stage patientview = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/DoctorView/DoctorView.FXML").openStream());
            DoctorViewController doctorViewController = (DoctorViewController)loader.getController();

            Scene scene = new Scene(root);
            patientview.setScene(scene);
            patientview.setTitle("Doctor View");
            patientview.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startAdmin() {
        Stage stage = (Stage)this.adminBtn.getScene().getWindow();
        stage.close();
        try{
            Stage patientview = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/AdminView/AdminView.FXML").openStream());
            AdminViewController adminViewController = (AdminViewController)loader.getController();

            Scene scene = new Scene(root);
            patientview.setScene(scene);
            patientview.setTitle("Admin View");
            patientview.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
