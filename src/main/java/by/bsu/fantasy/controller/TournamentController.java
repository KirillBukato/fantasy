package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.TournamentDTO;
import by.bsu.fantasy.model.Tournament;
import by.bsu.fantasy.service.TournamentService;
import by.bsu.fantasy.util.DtoMappingUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournaments")
    public List<TournamentDTO> getTournaments() {
        return tournamentService.getTournaments()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }

    @GetMapping("/tournament/{id}")
    public TournamentDTO getTournament(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                tournamentService.getTournament(id)
        );
    }

    @PostMapping("/tournament")
    public TournamentDTO addTournament(@RequestBody Tournament tournament) {
        return DtoMappingUtil.convert(
                tournamentService.addTournament(tournament)
        );
    }

    @PutMapping("/tournament/{id}")
    public TournamentDTO updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return DtoMappingUtil.convert(
                tournamentService.updateTournament(id, tournament)
        );
    }

    @DeleteMapping("/tournament/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }
}
