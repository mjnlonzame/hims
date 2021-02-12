package hms.mang.service;

import hms.mang.model.Receptionist;

import java.util.Optional;

public interface ReceptionistService {
    Optional<Receptionist> getByName(String name);

    Receptionist save(Receptionist receptionist);
}
