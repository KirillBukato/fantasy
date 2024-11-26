package by.bsu.fantasy.controller;

import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.service.PickPlayerService;
import by.bsu.fantasy.service.PickService;
import by.bsu.fantasy.service.PickTeamService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PickController {
    private final PickService pickService;
    private final PickPlayerService pickPlayerService;
    private final PickTeamService pickTeamService;

    public PickController(PickService pickService, PickPlayerService pickPlayerService, PickTeamService pickTeamService) {
        this.pickService = pickService;
        this.pickPlayerService = pickPlayerService;
        this.pickTeamService = pickTeamService;
    }

    @GetMapping("/picks")
    public List<Pick> getPicks() {
        return pickService.getPicks();
    }

    @GetMapping("/pick/{id}")
    public Pick getPickById(@PathVariable Long id) {
        return pickService.getPickById(id);
    }

    @PostMapping("/pick")
    public Pick createPick(@RequestBody Pick pick) {
        return pickService.createPick(pick);
    }

    @PutMapping("/pick/{id}")
    public Pick updatePick(@PathVariable Long id, @RequestBody Pick pick) {
        return pickService.updatePick(id, pick);
    }

    @DeleteMapping("/pick/{id}")
    public void deletePick(@PathVariable Long id) {
        pickService.deletePick(id);
    }

    @PutMapping("/pick/{id}/addPlayer/{playerId}")
    public Pick addPlayer(@PathVariable Long id, @PathVariable Long playerId) {
        return pickPlayerService.linkPlayerToPick(id, playerId);
    }

    @PutMapping("/pick/{id}/addTeam/{teamId}")
    public Pick addTeam(@PathVariable Long id, @PathVariable Long teamId) {
        return pickTeamService.linkTeamToPick(id, teamId);
    }
}