package PatientRegistration;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientData {
    private final StringProperty medicalNbr;


    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty sex;
    private final StringProperty adress;
    private final StringProperty phone;
    private final StringProperty birthdate;
    private final StringProperty registrationDate;
    private final StringProperty totalCost;

    public PatientData(String medicalNbr, String firstName, String lastName, String sex, String adress, String phone, String birthdate, String registrationDate,String totalCost){
        this.medicalNbr= new SimpleStringProperty(medicalNbr);
        this.firstName= new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.sex= new SimpleStringProperty(sex);
        this.adress= new SimpleStringProperty(adress);
        this.phone= new SimpleStringProperty(phone);
        this.birthdate= new SimpleStringProperty(birthdate);
        this.registrationDate= new SimpleStringProperty(registrationDate);
        this.totalCost= new SimpleStringProperty(totalCost);

    }
    public String getMedicalNbr() {
        return medicalNbr.get();
    }

    public StringProperty medicalNbrProperty() {
        return medicalNbr;
    }

    public void setMedicalNbr(String medicalNbr) {
        this.medicalNbr.set(medicalNbr);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getAdress() {
        return adress.get();
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public String getRegistrationDate() {
        return registrationDate.get();
    }

    public StringProperty registrationDateProperty() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate.set(registrationDate);
    }

    public String getTotalCost() {
        return totalCost.get();
    }

    public StringProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost.set(totalCost);
    }
}
