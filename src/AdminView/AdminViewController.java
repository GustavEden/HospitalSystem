package AdminView;

import DB.dbConnection;
import Patient.PatientLogIn;
import PatientRegistration.PatientData;
import PatientView.SchemaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    //Database connection
    private dbConnection db;

    //PatientLogin instance
    private PatientLogIn patientLogIn = new PatientLogIn();

    //Add Doctor
    @FXML
    private TextField empIdTF;
    @FXML
    private TextField fullNameTF;
    @FXML
    private TextField specTF;
    @FXML
    private TextField phoneTF;

    //Doctor Table
    private ObservableList<DoctorData2> doctorData;
    @FXML
    private TableView<DoctorData2> doctorTable;
    @FXML
    private TableColumn<DoctorData2, String> emp_idCL;
    @FXML
    private TableColumn<DoctorData2, String> doctorCL;

    //Current DoctorID
    private String currentDrID;

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

    //PatientData
    private ObservableList<PatientData> patientData;
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

    //appointments
    private ObservableList <SchemaData> schemaData;
    @FXML
    private TableView <SchemaData> appointmentTable;
    @FXML
    private TableColumn <SchemaData,String> dateCL;
    @FXML
    private TableColumn <SchemaData,String> time_CL;
    @FXML
    private TableColumn <SchemaData,String> empId_CL;
    @FXML
    private TableColumn <SchemaData,String> scheduleIdCL;
    @FXML
    private TableColumn <SchemaData,String> bookingTimeCL;

    //Add Price
    @FXML
    private TextField specializationTF;
    @FXML
    private TextField priceTF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.db = new dbConnection();
        loadDoctors();
        selectDoctor();
        loadPatient();
        showSelectedPatientJournal();
        showDoctorsSchema();
    }

    @FXML
    private void addPrice(ActionEvent event) {
        String sqlCreate = "INSERT INTO Cost (Specialization, Price) VALUES (?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlCreate);
            stmt.setString(1, this.specializationTF.getText());
            stmt.setString(2, this.priceTF.getText());

            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.specializationTF.clear();
            this.priceTF.clear();
        }
    }

    @FXML
    private void createDoctor(ActionEvent event) {
        try {
            if (!this.patientLogIn.checkEmpId(this.empIdTF.getText()) && !this.empIdTF.getText().isEmpty()) {
                String sqlCreate = "INSERT INTO Doctor (Emp_id, Fname, Phone, Specialization) VALUES (?, ?, ?, ?)";
                try {
                    Connection conn = dbConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sqlCreate);
                    stmt.setString(1, this.empIdTF.getText());
                    stmt.setString(2, this.fullNameTF.getText());
                    stmt.setString(3, this.phoneTF.getText());
                    stmt.setString(4, this.specTF.getText());



                    stmt.execute();
                    stmt.close();
                    conn.close();

                    createSchema(empIdTF.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Emp_id already exist or is empty");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong");
            alert.showAndWait();
            e.printStackTrace();
        }
        loadDoctors();
    }

    private void createSchema(String emp_id) {
        LocalDate ld = LocalDate.now();
        ld = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        for (int i = 0; i < 5; i++) {
            String time = "09.00";
            for (int j = 0; j < 4; j++) {

                String sqlCreate = "INSERT INTO Schema (Date, Emp_id, Availability, Time) VALUES (?, ?, ?, ?)";
                try {
                    Connection conn = dbConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sqlCreate);
                    stmt.setString(1, String.valueOf(ld));
                    stmt.setString(2, emp_id);
                    stmt.setString(3, "True");
                    stmt.setString(4, time);

                    stmt.execute();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                switch (time) {
                    case "09.00":
                        time = "09.30";
                        break;
                    case "09.30":
                        time = "10.00";
                        break;
                    case "10.00":
                        time = "10.30";
                        break;
                }
            }
            ld  = ld.plusDays(1);
        }
    }

    private void selectDoctor() {
        doctorTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DoctorData2 dd = doctorTable.getItems().get(doctorTable.getSelectionModel().getSelectedIndex());
                currentDrID = dd.getEmp_id();
            }
        });
    }

    private void showDoctorsSchema() {
        doctorTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DoctorData2 dd = doctorTable.getItems().get(doctorTable.getSelectionModel().getSelectedIndex());

                String schemaQuery = "SELECT * FROM Schema Where Emp_id = " + dd.getEmp_id() + " AND Availability = \"False\"";
                try{
                    Connection conn = dbConnection.getConnection();
                    schemaData = FXCollections.observableArrayList();
                    ResultSet result = conn.createStatement().executeQuery(schemaQuery);

                    while(result.next()){
                        if(getCurrentWeek(result.getString(1))){
                            schemaData.add(new SchemaData(result.getString(1),result.getString(2),
                                    result.getString(3),result.getString(4),result.getString(5),result.getString(6)));
                        }
                    }
                    conn.close();
                }catch (Exception e ){

                }
                dateCL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("date"));
                time_CL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("time"));
                empId_CL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("emp_id"));
                scheduleIdCL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("schedule_id"));
                bookingTimeCL.setCellValueFactory(new PropertyValueFactory<SchemaData, String>("bookedTime"));

                appointmentTable.setItems(null);
                appointmentTable.setItems(schemaData);
            }
        });
    }

    private Boolean getCurrentWeek(String s) {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int thisWeek = date.get(weekFields.weekOfWeekBasedYear());

        LocalDate d = LocalDate.parse(s);
        int now = d.get(weekFields.weekOfWeekBasedYear());

        if(thisWeek <= now){
            return true;
        }else {
            return false;
        }
    }

    @FXML
    private void deleteDoctor(ActionEvent event){
        String sqlDelete = "DELETE FROM Doctor WHERE Emp_id=?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, this.currentDrID);

            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadDoctors();
    }


    public void loadDoctors(){
        String doctorsListQuery = "SELECT Doctor.Emp_id, Doctor.Fname FROM Doctor ";
        try{
            Connection conn = dbConnection.getConnection();
            this.doctorData = FXCollections.observableArrayList();
            ResultSet result = conn.createStatement().executeQuery(doctorsListQuery);

            while(result.next()){
                this.doctorData.add(new DoctorData2(result.getString(1),result.getString(2)));
            }
            conn.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
        this.emp_idCL.setCellValueFactory(new PropertyValueFactory<DoctorData2, String>("emp_id"));
        this.doctorCL.setCellValueFactory(new PropertyValueFactory<DoctorData2, String>("fName"));

        this.doctorTable.setItems(null);
        this.doctorTable.setItems(this.doctorData);
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

    private void showSelectedPatientJournal(){
        patientTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PatientData jd = (PatientData) patientTable.getItems().get(patientTable.getSelectionModel().getSelectedIndex());

                String journalQuery = "SELECT * FROM Journal Where Med_nbr = " + jd.getMedicalNbr();
                try{
                    Connection conn = dbConnection.getConnection();
                    journalData = FXCollections.observableArrayList();
                    ResultSet result = conn.createStatement().executeQuery(journalQuery);

                    while(result.next()){
                        journalData.add(new JournalData(result.getString(1),result.getString(2),result.getString(3),result.getString(4),
                                result.getString(5),result.getString(6),result.getString(7)));
                    }
                    conn.close();
                }catch (Exception e ){
                    e.printStackTrace();
                }
                diagnosCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("diagnos"));
                descriptionCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("description"));
                medicineCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("medicine"));
                journalIdCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("journalId"));
                medNbrCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("medNbr"));
                empIdCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("empId"));
                journalDateCL.setCellValueFactory(new PropertyValueFactory<JournalData, String>("journalDate"));


                journalTable.setItems(null);
                journalTable.setItems(journalData);
            }
        });
    }
}
    
    

