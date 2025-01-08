package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.PickNotFoundException;
import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.PickRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PickService {
    private final PickRepository pickRepository;

    public List<Pick> getPicks() {
        return pickRepository
                .findAll();
    }

    public Pick getPickById(Long id) {
        return pickRepository
                .findById(id)
                .orElseThrow(() -> new PickNotFoundException(id));
    }

    public Pick createPick(Pick pick) {
        return pickRepository
                .save(pick);
    }

    public Pick createNewPick(User user) {
        Pick pick = new Pick();
        pick.setBalance(1000.);
        pick.setPoints(0);
        pick.setUser(user);
        Pick savedPick = pickRepository.save(pick);
        user.getPicks().add(savedPick);
        return savedPick;
    }

    public Pick updatePick(Long id, Pick pick) {
        pick.setId(id);
        return pickRepository.save(pick);
    }

    public void deletePick(Long id) {
        pickRepository.deleteById(id);
    }

}
