package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.PickDTO;
import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.service.PickPlayerService;
import by.bsu.fantasy.service.PickService;
import by.bsu.fantasy.service.PickTeamService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PickDTO> getPicks() {
        return pickService.getPicks()
                .stream()
                .map(DtoMappingUtil::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/pick/{id}")
    public PickDTO getPickById(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                pickService.getPickById(id)
        );
    }

    @PostMapping("/pick")
    public PickDTO createPick(@RequestBody Pick pick) {
        pick.setBalance(1000.0);
        return DtoMappingUtil.convert(
                pickService.createPick(pick)
        );
    }

    @PutMapping("/pick/{id}")
    public PickDTO updatePick(@PathVariable Long id, @RequestBody Pick pick) {
        return DtoMappingUtil.convert(
                pickService.updatePick(id, pick)
        );
    }

    @DeleteMapping("/pick/{id}")
    public void deletePick(@PathVariable Long id) {
        pickService.deletePick(id);
    }

    @PutMapping("/pick/{id}/addPlayer/{playerId}")
    public PickDTO addPlayer(@PathVariable Long id, @PathVariable Long playerId) {
        return DtoMappingUtil.convert(
                pickPlayerService.linkPlayerToPick(id, playerId)
        );
    }

    @PutMapping("/pick/{id}/addTeam/{teamId}")
    public PickDTO addTeam(@PathVariable Long id, @PathVariable Long teamId) {
        return DtoMappingUtil.convert(
                pickTeamService.linkTeamToPick(id, teamId)
        );
    }

    @DeleteMapping("/pick/{id}/removePlayer/{playerId}")
    public PickDTO removePlayer(@PathVariable Long id, @PathVariable Long playerId) {
        return DtoMappingUtil.convert(
                pickPlayerService.unlinkPlayerFromPick(id, playerId)
        );
    }

    @DeleteMapping("/pick/{id}/removeTeam/{teamId}")
    public PickDTO removeTeam(@PathVariable Long id, @PathVariable Long teamId) {
        return DtoMappingUtil.convert(
                pickTeamService.unlinkTeamFromPick(id, teamId)
        );
    }

}