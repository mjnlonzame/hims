package hms.mang.service;

import hms.mang.model.Doctor;
import hms.mang.repository.AppointmentRepository;
import hms.mang.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return StreamSupport.stream(doctorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<Doctor> getByName(String name) {
        return doctorRepository.findByName(name);
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return doctorRepository.findById(id);
    }
}
