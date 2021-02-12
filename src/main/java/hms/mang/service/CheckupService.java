package hms.mang.service;

import hms.mang.model.Checkup;

import java.util.List;

public interface CheckupService {
    List<Checkup> getAllByPatientId(Long patientId);

    Checkup save(Checkup checkup);
}
