package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.PickNotFoundException;
import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.PickRepository;

import by.bsu.fantasy.repository.PlayerRepository;
import by.bsu.fantasy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickService {
    private final PickRepository pickRepository;
    private final UserRepository userRepository;

    public PickService(PickRepository pickRepository, UserRepository userRepository) {
        this.pickRepository = pickRepository;
        this.userRepository = userRepository;
    }

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
        pick.setUser(user);
        user.getPicks().add(pick);
        userRepository.save(user);
        return pickRepository
                .save(pick);
    }

    public Pick updatePick(Long id, Pick pick) {
        pick.setId(id);
        return pickRepository.save(pick);
    }

    public void deletePick(Long id) {
        pickRepository.deleteById(id);
    }

    public List<PlayerIncome> getPickPlayerIncomes(Long id) {
        return getPickById(id)
                .getPlayers()
                .stream()
                .flatMap(player -> player.getIncomes().stream())
                .toList();
    }

    public List<TeamIncome> getPickTeamIncomes(Long id) {
        return getPickById(id)
                .getTeams()
                .stream()
                .flatMap(team -> team.getIncomes().stream())
                .toList();
    }

    public Integer getPickEstimatedIncome(Long id) {
        Integer playerSum = getPickPlayerIncomes(id)
                .stream()
                .mapToInt(PlayerIncome::getAmount)
                .reduce(0, Integer::sum);
        Integer teamSum = getPickTeamIncomes(id)
                .stream()
                .mapToInt(TeamIncome::getAmount)
                .reduce(0, Integer::sum);
        return playerSum + teamSum;
    }
}
