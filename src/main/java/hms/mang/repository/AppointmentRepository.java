package hms.mang.repository;

import hms.mang.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    public Iterable<Appointment> findAllByPatientId(Long patientId);
    public Iterable<Appointment> findAllByDoctorId(Long doctorId);
}
