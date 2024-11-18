package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.TeamIncomeNotFoundException;
import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.repository.TeamIncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamIncomeService {
    private final TeamIncomeRepository teamIncomeRepository;

    public TeamIncomeService(TeamIncomeRepository teamIncomeRepository) {
        this.teamIncomeRepository = teamIncomeRepository;
    }

    public List<TeamIncome> getTeamIncomes() {
        return teamIncomeRepository.findAll();
    }

    public TeamIncome getTeamIncome(Long id) {
        return teamIncomeRepository
                .findById(id)
                .orElseThrow(() -> new TeamIncomeNotFoundException(id));
    }

    public TeamIncome addTeamIncome(TeamIncome teamIncome) {
        return teamIncomeRepository.save(teamIncome);
    }

    public TeamIncome updateTeamIncome(Long id, TeamIncome teamIncome) {
        teamIncome.setId(id);
        return teamIncomeRepository.save(teamIncome);
    }

    public void deleteTeamIncome(Long id) {
        teamIncomeRepository.deleteById(id);
    }
}
