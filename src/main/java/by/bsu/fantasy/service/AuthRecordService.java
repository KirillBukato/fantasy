package by.bsu.fantasy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.bsu.fantasy.model.AuthRecord;
import by.bsu.fantasy.repository.AuthRecordRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthRecordService {
    private final AuthRecordRepository authRecordRepository;

    public void createRecord(String login, String passw, String role) {
        AuthRecord newRecord = new AuthRecord();
        newRecord.setUsername(login);
        newRecord.setPassword(passw);
        newRecord.setRole(role);
        authRecordRepository.save(newRecord);
    }

    public AuthRecord getRecordByUsername(String id) {
        return authRecordRepository
                .findByUsername(id)
                .orElseThrow(() -> new RuntimeException(id));
    }

    public List<AuthRecord> getRecords() {
        return authRecordRepository
                .findAll();
    }
}
