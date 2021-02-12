package hms.mang.service;

import hms.mang.model.Patient;
import hms.mang.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientServiceImpl implements  PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> getByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> getAll() {
        return StreamSupport.stream(patientRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
