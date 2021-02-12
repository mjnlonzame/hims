package hms.mang.controller;

import hms.mang.model.Doctor;
import hms.mang.model.Patient;
import hms.mang.service.DoctorService;
import hms.mang.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
