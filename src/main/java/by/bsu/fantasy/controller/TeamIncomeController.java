package by.bsu.fantasy.controller;

import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.service.TeamIncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamIncomeController {

    private final TeamIncomeService teamIncomeService;

    public TeamIncomeController(TeamIncomeService teamIncomeService) {
        this.teamIncomeService = teamIncomeService;
    }

    @GetMapping("/teamIncomes")
    public List<TeamIncome> getTeamIncomes() {
        return teamIncomeService.getTeamIncomes();
    }

    @GetMapping("/teamIncome/{id}")
    public TeamIncome getTeamIncome(@PathVariable Long id) {
        return teamIncomeService.getTeamIncome(id);
    }

    @PostMapping("/teamIncomes")
    public TeamIncome addTeamIncome(@RequestBody TeamIncome teamIncome) {
        return teamIncomeService.addTeamIncome(teamIncome);
    }

    @PutMapping("/teamIncomes/{id}")
    public TeamIncome updateTeamIncome(@PathVariable Long id, @RequestBody TeamIncome teamIncome) {
        return teamIncomeService.updateTeamIncome(id, teamIncome);
    }

    @DeleteMapping("/teamIncomes/{id}")
    public void deleteTeamIncome(@PathVariable Long id) {
        teamIncomeService.deleteTeamIncome(id);
    }
}
