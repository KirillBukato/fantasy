package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.TeamNotFoundException;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getTeams() {
        return teamRepository
                .findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team team) {
        team.setId(id);
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
