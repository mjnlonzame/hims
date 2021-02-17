package hms.mang.service;

import hms.mang.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public Optional<Patient> getByName(String name);

    public Patient save(Patient patient);

    Optional<Patient> getById(Long id);

    List<Patient> getAll();

    void deleteById(Long id);
}
