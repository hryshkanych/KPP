package ViewModel;

import Models.Bank;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.security.InvalidParameterException;
import java.util.concurrent.locks.ReentrantLock;

public class BankViewModel {
    private final Bank bank;
    private final ReentrantLock rlock;

    public BankViewModel(Bank bank)
    {
        this.bank = bank;
        this.rlock = new ReentrantLock();
    }

    public void withdrawCash(int amount, Label textField) {
        if(amount < 0) {
            throw new InvalidParameterException("The parameter 'amount' can't be less that zero!");
        }

        rlock.lock();
        try {
            final var cash = bank.getTotalCash();

            Platform.runLater(() -> {
                textField.setText("Cash in bank before withdraw: " + cash + "\n");
                textField.setText(textField.getText() + "Amount of withdraw: " + amount + "\n");
            });

            if (cash <= 0) {
                Platform.runLater(() -> {
                    textField.setText("Bank is empty!");
                });
                System.exit(0);
            }

            bank.withdrawCash(amount);
            final var nextCash = bank.getTotalCash();
            Platform.runLater(() -> {
                textField.setText(textField.getText() + "Cash in bank after withdraw: " + nextCash + "\n");
            });
        }
        finally {
            rlock.unlock();
        }
    }

    public void depositCash(int amount, Label textField) {
        if(amount < 0) {
            throw new InvalidParameterException("The parameter 'amount' can't be less that zero!");
        }

        rlock.lock();
        try {
            final var cash = bank.getTotalCash();

            Platform.runLater(() -> {
                textField.setText("Cash in bank before deposit: " + cash + "\n");
                textField.setText(textField.getText() + "Amount of deposit: " + amount + "\n");
            });

            bank.depositCash(amount);

            final var  nextCash = bank.getTotalCash();

            Platform.runLater(() -> {
                textField.setText(textField.getText() + "Cash in bank after deposit: " + nextCash + "\n");
            });
        }
        finally {
            rlock.unlock();
        }
    }
}
