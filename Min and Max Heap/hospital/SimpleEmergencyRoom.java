package hospital;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        Patient highestPriority = patients.get(0);
        for (Patient patient: patients) {
            if ((Integer)patient.getPriority() < (Integer)highestPriority.getPriority()) {
                highestPriority = patient;
            }
        }

        for (Patient patient: patients) {
            if ((Integer)patient.getPriority() == (Integer)highestPriority.getPriority()) {
                patients.remove(patient);
                break;
            }
        }

        return highestPriority;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
