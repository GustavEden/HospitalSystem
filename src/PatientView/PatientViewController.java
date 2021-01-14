package PatientView;

import DB.dbConnection;
import PatientRegistration.PatientData;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class PatientViewController implements Initializable {

    @FXML
    private TextField  firstNameCL;
    @FXML
    private TextField  lastNameCL;
    @FXML
    private TextField sexCL;
    @FXML
    private TextField adressCL;
    @FXML
    private TextField phoneCL;
    @FXML
    private TextField birthdateCL;
    @FXML
    private Label medicalNbrCL;
    @FXML
    private Label registrationCL;

    @FXML
    private Button loadDataBtn;

    private dbConnection db;

    private ObservableList <PatientData> data;

    private String sql = "SELECT * FROM PATIENT WHERE Med_nbr =? ";


    @Override
    public void initialize (URL url, ResourceBundle rb){
        this.db = new dbConnection();

    }
    @FXML
    private void loadPatientData(ActionEvent event){
        try{
            Connection conn = dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(sql);

            this.phoneCL.setText(rs1.getString(1));
            this.medicalNbrCL.setText(rs1.getString(2));
            this.firstNameCL.setText(rs1.getString(3));
            this.lastNameCL.setText(rs1.getString(4));
            this.sexCL.setText(rs1.getString(5));
            this.adressCL.setText(rs1.getString(6));
            this.birthdateCL.setText(rs1.getString(7));
            this.registrationCL.setText(rs1.getString(8));
        }catch (Exception e ){
            e.printStackTrace();
        }

/*
        this.medicalNbrCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("medicalNbr"));
        this.firstNameCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("firstName"));
        this.lastNameCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("lastName"));
        this.sexCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("sex"));
        this.adressCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("adress"));
        this.phoneCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("phone"));
        this.birthdateCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("birthdate"));
        this.registrationCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("registrationDate"));



 */



    }




    @FXML
    private void changeInfo(ActionEvent event){
        String sqlChange = " UPDATE Patient SET Fname= ?, Lname=?, Sex=?, Adress=?,Phone =?, birthdate=? WHERE Med_nbr=?";

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sqlChange);
            //kanske inte funkar
           // stmt.setString(1,this.patientTable.getItems(firstNameCL.g));
            stmt.execute();
            System.out.println(stmt.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
