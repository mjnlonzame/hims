package hms.mang.service;

import hms.mang.model.Receptionist;
import hms.mang.repository.ReceptionistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    private final ReceptionistRepository receptionistRepositoryt;

    public ReceptionistServiceImpl(ReceptionistRepository receptionistRepositoryt) {
        this.receptionistRepositoryt = receptionistRepositoryt;
    }

    @Override
    public Optional<Receptionist> getByName(String name) {
        return receptionistRepositoryt.findByName(name);
    }

    @Override
    public Receptionist save(Receptionist receptionist) {
        return receptionistRepositoryt.save(receptionist);
    }
}
