package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.User;

public class UserNotEnoughMoneyException extends RuntimeException {
    public UserNotEnoughMoneyException(User user, Double requiredMoney) {
        super(
                "User " + user.getName() +
                        " doesn't have enough money. Money required: " + requiredMoney +
                        ", current money: " + user.getBalance()
        );
    }
}
