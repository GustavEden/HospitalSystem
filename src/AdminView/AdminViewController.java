package AdminView;

import DB.dbConnection;
import Patient.PatientLogIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    //Database connection
    private dbConnection db;

    private PatientLogIn patientLogIn;

    //FXML
    @FXML
    private TextField checkEmpId;


    @Override
    public void initialize (URL url, ResourceBundle rb){
        this.db = new dbConnection();
    }

    @FXML
    private void createDoctor(ActionEvent event) {
        try{
            if(!this.patientLogIn.checkEmpId(this.checkEmpId.getText()) && !this.checkEmpId.getText().isEmpty()){
                String sqlCreate = "INSERT INTO Patient (Med_nbr, Fname, Lname, Sex, Adress, Phone, birthdate, Registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try{
                    Connection conn = dbConnection.getConnection();
                    PreparedStatement stmt= conn.prepareStatement(sqlCreate);
                    stmt.setString(1,this.checkEmpId.getText());
                   

                    stmt.execute();
                    stmt.close();
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Emp_id already exist or is empty");
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
    
    
}
