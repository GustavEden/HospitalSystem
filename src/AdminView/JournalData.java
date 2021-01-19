package AdminView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JournalData {
    private final StringProperty diagnos;
    private final StringProperty description;
    private final StringProperty medicine;
    private final StringProperty journalId;
    private final StringProperty medNbr;
    private final StringProperty empId;
    private final StringProperty journalDate;

    public JournalData(String diagnos,String description,String medicine,String journalId,String medNbr, String empId,String journalDate){
        this.diagnos= new SimpleStringProperty(diagnos);
        this.description= new SimpleStringProperty(description);
        this.medicine= new SimpleStringProperty(medicine);
        this.journalId= new SimpleStringProperty(journalId);
        this.medNbr= new SimpleStringProperty(medNbr);
        this.empId= new SimpleStringProperty(empId);
        this.journalDate= new SimpleStringProperty(journalDate);


    }

    public String getDiagnos() {
        return diagnos.get();
    }

    public StringProperty diagnosProperty() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos.set(diagnos);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getMedicine() {
        return medicine.get();
    }

    public StringProperty medicineProperty() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine.set(medicine);
    }

    public String getJournalId() {
        return journalId.get();
    }

    public StringProperty journalIdProperty() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId.set(journalId);
    }

    public String getMedNbr() {
        return medNbr.get();
    }

    public StringProperty medNbrProperty() {
        return medNbr;
    }

    public void setMedNbr(String medNbr) {
        this.medNbr.set(medNbr);
    }

    public String getEmpId() {
        return empId.get();
    }

    public StringProperty empIdProperty() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId.set(empId);
    }

    public String getJournalDate() {
        return journalDate.get();
    }

    public StringProperty journalDateProperty() {
        return journalDate;
    }

    public void setJournalDate(String journalDate) {
        this.journalDate.set(journalDate);
    }
}
