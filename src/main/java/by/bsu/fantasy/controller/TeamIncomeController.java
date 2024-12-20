package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.TeamIncomeDTO;
import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.service.TeamIncomeService;
import by.bsu.fantasy.util.AuthPolicy;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.util.SetAuthPolicy;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamIncomeController {

    private final TeamIncomeService teamIncomeService;

    public TeamIncomeController(TeamIncomeService teamIncomeService) {
        this.teamIncomeService = teamIncomeService;
    }

    @SetAuthPolicy(policy = AuthPolicy.BASIC_USER)
    @GetMapping("/teamIncomes")
    public List<TeamIncomeDTO> getTeamIncomes() {
        return teamIncomeService.getTeamIncomes()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }
    
    @SetAuthPolicy(policy = AuthPolicy.BASIC_USER)
    @GetMapping("/teamIncome/{id}")
    public TeamIncomeDTO getTeamIncome(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                teamIncomeService.getTeamIncome(id)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PostMapping("/teamIncome")
    public TeamIncomeDTO addTeamIncome(@RequestBody TeamIncome teamIncome) {
        return DtoMappingUtil.convert(
                teamIncomeService.addTeamIncome(teamIncome)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PutMapping("/teamIncome/{id}")
    public TeamIncomeDTO updateTeamIncome(@PathVariable Long id, @RequestBody TeamIncome teamIncome) {
        return DtoMappingUtil.convert(
                teamIncomeService.updateTeamIncome(id, teamIncome)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @DeleteMapping("/teamIncome/{id}")
    public void deleteTeamIncome(@PathVariable Long id) {
        teamIncomeService.deleteTeamIncome(id);
    }
}
