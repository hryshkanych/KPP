package Models;

import ViewModel.BankViewModel;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Client extends Thread {
    private final BankViewModel bank;
    private final Label textField;
    private final Label info;
    private boolean stoppedManually = false;
    public Client(BankViewModel bank, Label textField, Label info) {
        this.bank = bank;
        this.textField = textField;
        this.info = info;
    }

    public void withdrawCash(int amount) {
        bank.withdrawCash(amount, textField);
    }

    public void depositCash(int amount) {
        bank.depositCash(amount, textField);
    }

    @Override
    public void run() {
        doSomeWork();
        while (stoppedManually) {
            try {
                Thread.sleep(1000);
                System.out.println("Blocked = " + stoppedManually);
            } catch (InterruptedException e) {
                break;
            }
        }

        var currentThread = Thread.currentThread();
        var pr = currentThread.getPriority();
        var threadName = currentThread.getName();

        Platform.runLater(() ->
                info.setText(threadName + "\n" + "Priority: " + pr + "\n" + "Time of last change: " +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));

        // simulate work
        Random random = new Random();
        int i = random.nextInt(1, 3);  // зняття коштів або внесення
        final int moneyBound = 100, transactionsOffset = 2000;  // тривалість транзакції
        while (true) {
            if (i % 2 == 1) {
                withdrawCash(random.nextInt(moneyBound));
            }

            if (i % 2 == 0) {
                depositCash(random.nextInt(moneyBound));
            }

            try {
                Thread.sleep(transactionsOffset);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            if (i > 25) {
                break;
            }

            i++;
        }
    }

    // simulating work
    private void doSomeWork() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void manuallyStop() {
        stoppedManually = true;
    }

    public void manuallyResume() {
        stoppedManually = false;
    }
}
