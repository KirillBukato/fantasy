package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.PlayerIncomeDTO;
import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.service.PlayerIncomeService;
import by.bsu.fantasy.util.AuthPolicy;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.util.SetAuthPolicy;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerIncomeController {

    private final PlayerIncomeService playerIncomeService;

    public PlayerIncomeController(PlayerIncomeService playerIncomeService) {
        this.playerIncomeService = playerIncomeService;
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @GetMapping("/playerIncomes")
    public List<PlayerIncomeDTO> getPlayerIncomes() {
        return playerIncomeService.getPlayerIncomes()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @GetMapping("/playerIncome/{id}")
    public PlayerIncomeDTO getPlayerIncome(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                playerIncomeService.getPlayerIncome(id)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PostMapping("/playerIncome")
    public PlayerIncomeDTO addPlayerIncome(@RequestBody PlayerIncome playerIncome) {
        return DtoMappingUtil.convert(
                playerIncomeService.addPlayerIncome(playerIncome)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PutMapping("/playerIncome/{id}")
    public PlayerIncomeDTO updatePlayerIncome(@PathVariable Long id, @RequestBody PlayerIncome playerIncome) {
        return DtoMappingUtil.convert(
                playerIncomeService.updatePlayerIncome(id, playerIncome)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @DeleteMapping("/playerIncome/{id}")
    public void deletePlayerIncome(@PathVariable Long id) {
        playerIncomeService.deletePlayerIncome(id);
    }

}
