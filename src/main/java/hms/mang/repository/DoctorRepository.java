package hms.mang.repository;

import hms.mang.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository  extends CrudRepository<Doctor, Long> {
    public Optional<Doctor> findByName(String name);
}
