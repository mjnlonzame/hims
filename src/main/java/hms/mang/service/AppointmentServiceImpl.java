package hms.mang.service;

import hms.mang.model.Appointment;
import hms.mang.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllByPatientId(Long patientId) {
        return StreamSupport.stream(appointmentRepository.findAllByPatientId(patientId).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getAllByDoctortId(Long doctorId) {
        return StreamSupport.stream(appointmentRepository.findAllByDoctorId(doctorId).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> getAll() {
        return StreamSupport.stream(appointmentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
