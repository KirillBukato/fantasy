package by.bsu.fantasy.controller;

import by.bsu.fantasy.exceptions.TeamNotFoundException;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return teamRepository
                .findAll();
    }

    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }
}
