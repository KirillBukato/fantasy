package by.bsu.fantasy.controller;

import by.bsu.fantasy.exceptions.TeamNotFoundException;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/teams/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamRepository
                .findById(id)
                .map(t -> {
                    t.setName(team.getName());
                    t.setPrice(t.getPrice());
                    t.setPoints(t.getPoints());
                    return teamRepository.save(t);
                })
                .orElseGet(
                        () -> teamRepository.save(team)
                );
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamRepository.deleteById(id);
    }
}
