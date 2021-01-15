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

    public String getEmp_id() {
        return emp_id.get();
    }

    public StringProperty emp_idProperty() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id.set(emp_id);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getSpecialization() {
        return specialization.get();
    }

    public StringProperty specializationProperty() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization.set(specialization);
    }
}
