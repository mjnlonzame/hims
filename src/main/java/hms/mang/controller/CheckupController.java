package hms.mang.controller;

import hms.mang.model.Checkup;
import hms.mang.model.Doctor;
import hms.mang.model.Patient;
import hms.mang.service.CheckupService;
import hms.mang.service.DoctorService;
import hms.mang.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/checkup", produces = "application/json")
@CrossOrigin(origins = "*")
public class CheckupController {
    private final CheckupService checkupService;
    private final DoctorService doctorService;
    private final PatientService patientService;


    @Autowired
    public CheckupController(CheckupService checkupService, DoctorService doctorService, PatientService patientService) {
        this.checkupService = checkupService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping(value = "/{patientId}/{doctorId}", consumes = "application/json")
    public ResponseEntity<Checkup> createCheckup(@RequestBody Checkup checkup, @PathVariable("patientId") Long patientId, @PathVariable("doctorId") Long doctorId) {
        Optional<Patient>  optionalPatient = patientService.getById(patientId);

        if (optionalPatient.isPresent()) {
            checkup.setPatient(optionalPatient.get());
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<Doctor> optDoctor = doctorService.getById(doctorId);
        if (optDoctor.isPresent()) {
            checkup.setDoctor(optDoctor.get());
        }
        return new ResponseEntity<>(checkupService.save(checkup), HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Checkup>> getAllByPatientId(@PathVariable("patientId") Long patientId) {
        List<Checkup> checkups = checkupService.getAllByPatientId(patientId);
        return new ResponseEntity<>(checkups, HttpStatus.OK);
    }





}
