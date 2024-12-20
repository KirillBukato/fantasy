package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.TournamentDTO;
import by.bsu.fantasy.model.Tournament;
import by.bsu.fantasy.service.TournamentService;
import by.bsu.fantasy.util.AuthPolicy;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.util.SetAuthPolicy;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @SetAuthPolicy(policy = AuthPolicy.BASIC_USER)
    @GetMapping("/tournaments")
    public List<TournamentDTO> getTournaments() {
        return tournamentService.getTournaments()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }

    @SetAuthPolicy(policy = AuthPolicy.BASIC_USER)
    @GetMapping("/tournament/{id}")
    public TournamentDTO getTournament(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                tournamentService.getTournament(id)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PostMapping("/tournament")
    public TournamentDTO addTournament(@RequestBody Tournament tournament) {
        return DtoMappingUtil.convert(
                tournamentService.addTournament(tournament)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PutMapping("/tournament/{id}")
    public TournamentDTO updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return DtoMappingUtil.convert(
                tournamentService.updateTournament(id, tournament)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @DeleteMapping("/tournament/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }
}
