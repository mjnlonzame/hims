package hms.mang.controller;

import hms.mang.model.Appointment;
import hms.mang.model.Doctor;
import hms.mang.model.Patient;
import hms.mang.service.DoctorService;
import hms.mang.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/doctor", produces = "application/json")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {

        Optional<Doctor> optFoundDoctor = doctorService.getByName(doctor.getName());
        if (optFoundDoctor.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(doctorService.save(doctor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable("id") Long id) {
        Optional<Doctor> optDoctor = doctorService.getById(id);
        if (optDoctor.isPresent()) {
            return new ResponseEntity<>(optDoctor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Doctor>> getAll() {
        List<Doctor> doctors = doctorService.getAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Doctor> patchDoctor(@PathVariable("id") Long id, @RequestBody Doctor patchDoctor) {
        Optional<Doctor> optionalDoctor = doctorService.getById(id);
        if(optionalDoctor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Doctor doctor = optionalDoctor.get();
        if(patchDoctor.getSpecialization() != null) {
            doctor.setSpecialization(patchDoctor.getSpecialization());
        }

        if(patchDoctor.getName() != null) {
            doctor.setName(patchDoctor.getName());
        }

        return new ResponseEntity<>(doctorService.save(doctor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") Long id) {
        try {
            doctorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
