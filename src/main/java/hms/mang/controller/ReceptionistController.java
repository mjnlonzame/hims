package hms.mang.controller;

import hms.mang.model.Receptionist;
import hms.mang.service.ReceptionistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/receptionist", produces = "application/json")
@CrossOrigin(origins = "*")
public class ReceptionistController {
    private final ReceptionistService receptionistService;

    public ReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Receptionist> createReceptionist(@RequestBody Receptionist receptionist) {


        Optional<Receptionist> optionalReceptionist = receptionistService.getByName(receptionist.getName());
        if (optionalReceptionist.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(receptionistService.save(receptionist), HttpStatus.CREATED);
    }
}
