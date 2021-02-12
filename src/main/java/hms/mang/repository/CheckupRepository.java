package hms.mang.repository;

import hms.mang.model.Checkup;
import org.springframework.data.repository.CrudRepository;

public interface CheckupRepository extends CrudRepository<Checkup, Long>  {
    public Iterable<Checkup> findAllByPatientId(Long patientId);
}
