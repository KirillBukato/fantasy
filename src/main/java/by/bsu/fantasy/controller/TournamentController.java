package by.bsu.fantasy.controller;

import by.bsu.fantasy.model.Tournament;
import by.bsu.fantasy.service.TournamentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournaments")
    public List<Tournament> getTournaments() {
        return tournamentService.getTournaments();
    }

    @GetMapping("/tournament/{id}")
    public Tournament getTournament(@PathVariable Long id) {
        return tournamentService.getTournament(id);
    }

    @PostMapping("/tournament")
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentService.addTournament(tournament);
    }

    @PutMapping("/tournament/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    @DeleteMapping("/tournament/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }
}
