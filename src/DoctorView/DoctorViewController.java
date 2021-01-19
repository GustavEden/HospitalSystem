package DoctorView;

import AdminView.JournalData;
import DB.dbConnection;
import Patient.PatientLogIn;
import PatientRegistration.PatientData;
import PatientView.DoctorData;
import PatientView.SchemaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class DoctorViewController implements Initializable {
    //Database connection
    private dbConnection db;

    @FXML
    private TextField empIdTF;
    //appointments
    @FXML
    private TableView<SchemaData> appointmentTable;
    @FXML
    private TableColumn<SchemaData,String> dateCL;
    @FXML
    private TableColumn <SchemaData,String> empId_CL;
    @FXML
    private TableColumn <SchemaData,String> scheduleIdCL;
    @FXML
    private TableColumn <SchemaData,String> availabilityCL;

    private ObservableList<SchemaData> schemaData;
    //PatientData
    @FXML
    private TableColumn<PatientData,String> fNameCL;
    @FXML
    private TableColumn <PatientData,String> lNameCL;
    @FXML
    private TableColumn <PatientData,String>sexCL;
    @FXML
    private TableColumn <PatientData,String>addressCL;
    @FXML
    private TableColumn <PatientData,String>phoneCL;
    @FXML
    private TableColumn <PatientData,String> birthdayCL;
    @FXML
    private TableColumn <PatientData,String>medIDCL;
    @FXML
    private TableColumn <PatientData,String>registrationCL;
    @FXML
    private TableColumn <PatientData,String>totalDebtCL;
    @FXML
    private TableView patientTable;

    private ObservableList<PatientData> patientData;
    private PatientLogIn patientLogIn = new PatientLogIn();

    //journal
    private ObservableList<JournalData> journalData;
    @FXML
    private TableView <JournalData> journalTable;
    @FXML
    private TableColumn<JournalData,String> diagnosCL;
    @FXML
    private TableColumn<JournalData,String> descriptionCL;
    @FXML
    private TableColumn<JournalData,String> medicineCL;
    @FXML
    private TableColumn<JournalData,String> journalIdCL;
    @FXML
    private TableColumn<JournalData,String> medNbrCL;
    @FXML
    private TableColumn<JournalData,String> empIdCL;
    @FXML
    private TableColumn<JournalData,String> journalDateCL;

    public void initialize(URL url, ResourceBundle rb){
        this.db = new dbConnection();
    }
    @FXML
    private void checkEmpID(ActionEvent event){
        try {
            if(patientLogIn.checkEmpId(empIdTF.getText())){
                loadPatient();
                loadAppointments(empIdTF.getText());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Emp_id already exist or is empty");
                alert.showAndWait();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void loadPatient(){
        String patientQuery = "SELECT * FROM PATIENT ";
        try{
            Connection conn = dbConnection.getConnection();
            this.patientData= FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(patientQuery);
            while(rs1.next()){
                this.patientData.add(new PatientData(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),
                        rs1.getString(6),rs1.getString(1),rs1.getString(7),rs1.getString(8),rs1.getString(9)));
            }
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        this.medIDCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("medicalNbr"));
        this.phoneCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("phone"));
        this.fNameCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("firstName"));
        this.lNameCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("lastName"));
        this.sexCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("sex"));
        this.addressCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("adress"));
        this.birthdayCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("birthdate"));
        this.registrationCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("registrationDate"));
        this.totalDebtCL.setCellValueFactory(new PropertyValueFactory<PatientData, String>("totalCost"));

        this.patientTable.setItems(null);
        this.patientTable.setItems(this.patientData);



    }
    public void loadAppointments(String empID){

       String schemaQuery = "SELECT * FROM Schema Where Emp_id = " + empID;
        try{
            Connection conn = dbConnection.getConnection();
            schemaData = FXCollections.observableArrayList();
            ResultSet result = conn.createStatement().executeQuery(schemaQuery);

            while(result.next()){
                schemaData.add(new SchemaData(result.getString(1),result.getString(2),result.getString(3),result.getString(4)));
            }
            conn.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
        dateCL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("date"));
        empId_CL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("emp_id"));
        scheduleIdCL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("schedule_id"));
        availabilityCL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("availability"));


        appointmentTable.setItems(null);
        appointmentTable.setItems(schemaData);



    }

}
