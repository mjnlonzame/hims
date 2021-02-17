package hms.mang.service;

import hms.mang.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment save(Appointment appointment);

    List<Appointment> getAllByPatientId(Long patientId);

    List<Appointment> getAllByDoctortId(Long doctorId);

    Optional<Appointment> findById(Long id);

    List<Appointment> getAll();
}
