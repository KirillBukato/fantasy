package by.bsu.fantasy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.model.AuthRecord;
import by.bsu.fantasy.service.AuthRecordService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthRecordController {
    private final AuthRecordService authRecordService;

    @GetMapping("/auth/all")
    private List<AuthRecord> getAll() {
        return authRecordService.getRecords();
    }
}
