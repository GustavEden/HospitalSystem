package PatientView;

import DB.dbConnection;
import PatientRegistration.PatientData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;

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
    //@FXML
   // private Table doctorTable;

    @FXML
    private Button loadDataBtn;

    private dbConnection db;

    private ObservableList <PatientData> data;

    private ObservableList <DoctorData> doctorData;

    private TableColumn <DoctorData, String> emp_id;
    private TableColumn <DoctorData, String> price;
    private TableColumn <DoctorData, String> specialization;

    private String patientQuery;
    private String doctorsListQuery;
    private String schemaQuery;

    private String medical_number;

    public void medNbr(String nbr){
        medical_number = nbr;
        patientQuery = "SELECT * FROM PATIENT WHERE Med_nbr =" + nbr;
        try{
            Connection conn = dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(patientQuery);

            this.phoneCL.setText(rs1.getString(1));
            this.medicalNbrCL.setText(rs1.getString(2));
            this.firstNameCL.setText(rs1.getString(3));
            this.lastNameCL.setText(rs1.getString(4));
            this.sexCL.setText(rs1.getString(5));
            this.adressCL.setText(rs1.getString(6));
            this.birthdateCL.setText(rs1.getString(7));
            this.registrationCL.setText(rs1.getString(8));


        }catch (Exception e){
            e.printStackTrace();
        }

       // loadDoctors();
    }

    public void loadDoctors(){
        doctorsListQuery = "SELECT Doctor.Emp_id, Cost.Price, Cost.Specialization FROM Doctor JOIN Cost ON Doctor.Specialization = Cost.Specialization";
        try{
            Connection conn = dbConnection.getConnection();
            this.data= FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(patientQuery);

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
    }


    @Override
    public void initialize (URL url, ResourceBundle rb){
        this.db = new dbConnection();

    }

    @FXML
    private void changeInfo(ActionEvent event){
        String sqlChange = " UPDATE Patient SET Fname= ?, Lname=?, Sex=?, Adress=?,Phone =?, birthdate=? WHERE Med_nbr=" + medical_number;

        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sqlChange);
            //kanske inte funkar
            //stmt.setString(1,this.patientTable.getItems(firstNameCL.g));
            stmt.execute();
            System.out.println(stmt.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
