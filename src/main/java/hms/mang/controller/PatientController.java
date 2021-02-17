package hms.mang.controller;

    import hms.mang.model.Patient;
import hms.mang.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.dao.EmptyResultDataAccessException;
    import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/patient", produces = "application/json")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {

        Optional<Patient> optFoundPatient = patientService.getByName(patient.getName());
        if (optFoundPatient.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable("id") Long id) {
        Optional<Patient> optPatient = patientService.getById(id);
        if (optPatient.isPresent()) {
            return new ResponseEntity<>(optPatient.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAll() {
        List<Patient> patients = patientService.getAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> patchPatient(@PathVariable("id") Long id, @RequestBody Patient patchPatient) {
        Optional<Patient> optionalPatient = patientService.getById(id);
        if(optionalPatient.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Patient patient = optionalPatient.get();
        if(patchPatient.getAge() != 0) {
            patient.setAge(patchPatient.getAge());
        }

        if(patchPatient.getName() != null) {
            patient.setName(patchPatient.getName());
        }

        if(patchPatient.getAddress() != null) {
            patient.setAddress(patchPatient.getAddress());
        }

        if(patchPatient.getGender() != null) {
            patient.setGender(patchPatient.getGender());
        }

        return new ResponseEntity<>(patientService.save(patient), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id) {
        try {
            patientService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
