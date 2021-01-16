package PatientView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SchemaData {

    private final StringProperty date;
    private final StringProperty emp_id;
    private final StringProperty schedule_id;
    private final StringProperty availability;

    public SchemaData(String date, String emp_id, String schedule_id, String availability){
        this.date = new SimpleStringProperty(date);
        this.emp_id= new SimpleStringProperty(emp_id);
        this.schedule_id = new SimpleStringProperty(schedule_id);
        this.availability = new SimpleStringProperty(availability);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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

    public String getSchedule_id() {
        return schedule_id.get();
    }

    public StringProperty schedule_idProperty() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id.set(schedule_id);
    }

    public String getAvailability() {
        return availability.get();
    }

    public StringProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability.set(availability);
    }
}
