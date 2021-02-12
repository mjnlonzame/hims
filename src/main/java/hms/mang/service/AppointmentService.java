package hms.mang.service;

import hms.mang.model.Appointment;
import hms.mang.model.Checkup;

import java.util.List;

public interface AppointmentService {
    Appointment save(Appointment appointment);

    List<Appointment> getAllByPatientId(Long patientId);

    List<Appointment> getAllByDoctortId(Long doctorId);
}
