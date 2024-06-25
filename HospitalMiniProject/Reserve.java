public class Reserve {
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Reserve(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }
}
