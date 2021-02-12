package hms.mang.repository;

import hms.mang.model.Appointment;
import hms.mang.model.Checkup;
import hms.mang.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    public Iterable<Appointment> findAllByPatientId(Long patientId);
    public Iterable<Appointment> findAllByDoctorId(Long doctorId);
}
