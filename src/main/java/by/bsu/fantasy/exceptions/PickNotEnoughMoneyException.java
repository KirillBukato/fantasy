package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Pick;

public class PickNotEnoughMoneyException extends RuntimeException {
    public PickNotEnoughMoneyException(Pick pick, Double requiredMoney) {
        super(
                "Pick " + pick.getName() +
                        " doesn't have enough money. Money required: " + requiredMoney +
                        ", current money: " + pick.getBalance()
        );
    }
}
