package hms.mang.repository;

import hms.mang.model.Receptionist;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReceptionistRepository extends CrudRepository<Receptionist, Long>  {
    public Optional<Receptionist> findByName(String name);
}
