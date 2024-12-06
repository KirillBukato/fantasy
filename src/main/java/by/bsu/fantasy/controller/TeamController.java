package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.TeamDTO;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.service.TeamService;
import by.bsu.fantasy.util.DtoMappingUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public List<TeamDTO> getTeams() {
        return teamService.getTeams()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }

    @GetMapping("/team/{id}")
    public TeamDTO getTeamById(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                teamService.getTeamById(id)
        );
    }

    @PostMapping("/team")
    public TeamDTO createTeam(@RequestBody Team team) {
        return DtoMappingUtil.convert(
                teamService.createTeam(team)
        );
    }

    @PutMapping("/team/{id}")
    public TeamDTO updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return DtoMappingUtil.convert(
                teamService.updateTeam(id, team)
        );
    }

    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
