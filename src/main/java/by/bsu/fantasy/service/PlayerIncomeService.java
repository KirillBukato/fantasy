package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.PlayerIncomeNotFoundException;
import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.repository.PlayerIncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerIncomeService {

    private final PlayerIncomeRepository playerIncomeRepository;

    public PlayerIncomeService(PlayerIncomeRepository playerIncomeRepository) {
        this.playerIncomeRepository = playerIncomeRepository;
    }

    public List<PlayerIncome> getPlayerIncomes() {
        return playerIncomeRepository.findAll();
    }

    public PlayerIncome getPlayerIncome(Long id) {
        return playerIncomeRepository
                .findById(id)
                .orElseThrow(() -> new PlayerIncomeNotFoundException(id));
    }

    public PlayerIncome addPlayerIncome(PlayerIncome playerIncome) {
        return playerIncomeRepository.save(playerIncome);
    }

    public PlayerIncome updatePlayerIncome(Long id, PlayerIncome playerIncome) {
        playerIncome.setId(id);
        return playerIncomeRepository.save(playerIncome);
    }

    public void deletePlayerIncome(Long id) {
        playerIncomeRepository.deleteById(id);
    }

}
