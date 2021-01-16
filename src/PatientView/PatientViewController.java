package PatientView;

import DB.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
    private TextField medicalNbrCL;
    @FXML
    private TextField registrationCL;


                                                //@FXML
                                                //private Button loadDataBtn;


    //Database connection
    private dbConnection db;
                                                //private ObservableList <PatientData> data;

    // DoctorTable variables
    private ObservableList <DoctorData> doctorData;
    @FXML
    private TableView<DoctorData> doctorTable;
    @FXML
    private TableColumn <DoctorData, String> emp_idCl;
    @FXML
    private TableColumn <DoctorData, String> priceCL;
    @FXML
    private TableColumn <DoctorData, String> specializationCl;
    @FXML
    private TextField doctorSearchTF;

    // SchemaTable variables
    private ObservableList <SchemaData> schemaData;
    @FXML
    private TableView<SchemaData> schemaTable;
    @FXML
    private TableColumn <DoctorData, String> DateCl;
    @FXML
    private TableColumn <DoctorData, String> Emp_idCl;
    @FXML
    private TableColumn <DoctorData, String> Schedule_idCl;
    @FXML
    private TableColumn <DoctorData, String> AvailabilityCl;

    //Querys
    private String patientQuery;
    private String doctorsListQuery;
    private String schemaQuery;

    //Current ptients medical number
    private String medical_number;

    public void medNbr(String nbr){
        medical_number = nbr;
        patientQuery = "SELECT * FROM PATIENT WHERE Med_nbr =" + nbr;
        try{
            Connection conn = dbConnection.getConnection();
            //this.data= FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(patientQuery);

            this.phoneCL.setText(rs1.getString(1));
            this.medicalNbrCL.setText(rs1.getString(2));
            this.firstNameCL.setText(rs1.getString(3));
            this.lastNameCL.setText(rs1.getString(4));
            this.sexCL.setText(rs1.getString(5));
            this.adressCL.setText(rs1.getString(6));
            this.birthdateCL.setText(rs1.getString(7));
            this.registrationCL.setText(rs1.getString(8));
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        loadDoctors();
    }

    public void loadDoctors(){
        doctorsListQuery = "SELECT Doctor.Emp_id, Cost.Price, Cost.Specialization FROM Doctor JOIN Cost ON Doctor.Specialization = Cost.Specialization";
        try{
            Connection conn = dbConnection.getConnection();
            this.doctorData = FXCollections.observableArrayList();
            ResultSet result = conn.createStatement().executeQuery(doctorsListQuery);

            while(result.next()){
                this.doctorData.add(new DoctorData(result.getString(1),result.getString(2),result.getString(3)));
            }
            conn.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
        this.emp_idCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("emp_id"));
        this.priceCL.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("price"));
        this.specializationCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("specialization"));

        this.doctorTable.setItems(null);
        this.doctorTable.setItems(this.doctorData);

        showSelectedDoctorsScheme();
    }

    public void searchDoctors(){
        String spec = "";
        if(!this.doctorSearchTF.getText().equals("")){
            spec =  "WHERE Cost.Specialization = \"" + this.doctorSearchTF.getText() + "\"";
        }
        doctorsListQuery = "SELECT Doctor.Emp_id, Cost.Price, Cost.Specialization FROM Doctor JOIN Cost ON Doctor.Specialization = Cost.Specialization " + spec;
        try{
            Connection conn = dbConnection.getConnection();
            this.doctorData = FXCollections.observableArrayList();
            ResultSet result = conn.createStatement().executeQuery(doctorsListQuery);

            while(result.next()){
                this.doctorData.add(new DoctorData(result.getString(1),result.getString(2),result.getString(3)));
            }
            conn.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
        this.emp_idCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("emp_id"));
        this.priceCL.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("price"));
        this.specializationCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("specialization"));

        this.doctorTable.setItems(null);
        this.doctorTable.setItems(this.doctorData);
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
            stmt.setString(1,this.firstNameCL.getText());
            stmt.setString(2,this.lastNameCL.getText());
            stmt.setString(3,this.sexCL.getText());
            stmt.setString(4,this.adressCL.getText());
            stmt.setString(5,this.phoneCL.getText());
            stmt.setString(6,this.birthdateCL.getText());

            stmt.execute();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void showSelectedDoctorsScheme(){
        doctorTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DoctorData dd = doctorTable.getItems().get(doctorTable.getSelectionModel().getSelectedIndex());
                System.out.println(dd.getEmp_id());

                schemaQuery = "SELECT * FROM Schema Where Emp_id = " + dd.getEmp_id();
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
                DateCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("date"));
                Emp_idCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("emp_id"));
                Schedule_idCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("schedule_id"));
                AvailabilityCl.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("availability"));


                schemaTable.setItems(null);
                schemaTable.setItems(schemaData);
            }
        });
    }
}
