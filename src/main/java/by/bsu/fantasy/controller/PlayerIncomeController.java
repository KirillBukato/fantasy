package by.bsu.fantasy.controller;

import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.service.PlayerIncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerIncomeController {

    private final PlayerIncomeService playerIncomeService;

    public PlayerIncomeController(PlayerIncomeService playerIncomeService) {
        this.playerIncomeService = playerIncomeService;
    }

    @GetMapping("/playerIncomes")
    public List<PlayerIncome> getPlayerIncomes() {
        return playerIncomeService.getPlayerIncomes();
    }

    @GetMapping("/playerIncome/{id}")
    public PlayerIncome getPlayerIncome(@PathVariable Long id) {
        return playerIncomeService.getPlayerIncome(id);
    }

    @PostMapping("/playerIncomes")
    public PlayerIncome addPlayerIncome(@RequestBody PlayerIncome playerIncome) {
        return playerIncomeService.addPlayerIncome(playerIncome);
    }

    @PutMapping("/playerIncomes/{id}")
    public PlayerIncome updatePlayerIncome(@PathVariable Long id, @RequestBody PlayerIncome playerIncome) {
        return playerIncomeService.updatePlayerIncome(id, playerIncome);
    }

    @DeleteMapping("/playerIncomes/{id}")
    public void deletePlayerIncome(@PathVariable Long id) {
        playerIncomeService.deletePlayerIncome(id);
    }

}
