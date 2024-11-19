package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.TournamentNotFoundException;
import by.bsu.fantasy.model.Tournament;
import by.bsu.fantasy.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }


    public List<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournament(Long id) {
        return tournamentRepository
                .findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Long id, Tournament tournament) {
        tournament.setId(id);
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
