package hms.mang.controller;

import hms.mang.model.Appointment;
import hms.mang.model.Checkup;
import hms.mang.model.Doctor;
import hms.mang.model.Patient;
import hms.mang.service.AppointmentService;
import hms.mang.service.DoctorService;
import hms.mang.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/appointment", produces = "application/json")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping(value = "/{patientId}/{doctorId}", consumes = "application/json")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment, @PathVariable("patientId") Long patientId, @PathVariable("doctorId") Long doctorId) {
        Optional<Patient>  optionalPatient = patientService.getById(patientId);

        if (optionalPatient.isPresent()) {
            appointment.setPatient(optionalPatient.get());
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<Doctor> optDoctor = doctorService.getById(doctorId);
        if (optDoctor.isPresent()) {
            appointment.setDoctor(optDoctor.get());
        }

        appointment.setPatient(optionalPatient.get());
        return new ResponseEntity<>(appointmentService.save(appointment), HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAllByPatientId(@PathVariable("patientId") Long patientId) {
        List<Appointment> appointments = appointmentService.getAllByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAllByDoctorId(@PathVariable("doctorId") Long doctorId) {
        List<Appointment> appointments = appointmentService.getAllByDoctortId(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }




}
