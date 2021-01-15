package PatientView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorData {

    private final StringProperty emp_id;
    private final StringProperty price;
    private final StringProperty specialization;

    public DoctorData(String emp_id, String price, String specialization){
        this.emp_id= new SimpleStringProperty(emp_id);
        this.price= new SimpleStringProperty(price);
        this.specialization = new SimpleStringProperty(specialization);
    }
}
