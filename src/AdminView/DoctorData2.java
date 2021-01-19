package AdminView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorData2 {

    private final StringProperty emp_id;
    private final StringProperty fName;

    public DoctorData2(String emp_id, String fName){
        this.emp_id= new SimpleStringProperty(emp_id);
        this.fName= new SimpleStringProperty(fName);
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
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

}


