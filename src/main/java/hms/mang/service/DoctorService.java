package hms.mang.service;

import hms.mang.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor save(Doctor doctor);

    List<Doctor> getAll();

    Optional<Doctor> getByName(String name);

    Optional<Doctor> getById(Long id);

    void deleteById(Long id);
}
