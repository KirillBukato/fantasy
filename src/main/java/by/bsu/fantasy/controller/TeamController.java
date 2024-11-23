package by.bsu.fantasy.controller;

import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @GetMapping("/team/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
