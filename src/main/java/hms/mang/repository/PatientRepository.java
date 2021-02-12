package hms.mang.repository;

import hms.mang.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    public Optional<Patient> findByName(String name);
}
