package hms.mang.service;

import hms.mang.model.Checkup;
import hms.mang.repository.CheckupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CheckupServiceImpl implements CheckupService {
    private final CheckupRepository checkupRepository;

    public CheckupServiceImpl(CheckupRepository checkupRepository) {
        this.checkupRepository = checkupRepository;
    }

    @Override
    public List<Checkup> getAllByPatientId(Long patientId) {
        return StreamSupport.stream(checkupRepository.findAllByPatientId(patientId).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Checkup save(Checkup checkup) {
        return checkupRepository.save(checkup);
    }
}
