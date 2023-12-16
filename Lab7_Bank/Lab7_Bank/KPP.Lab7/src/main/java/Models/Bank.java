package Models;

import java.security.InvalidParameterException;

public class Bank {
    private Integer totalCash;
    private final Object lock;

    public Bank(int cash) {
        this.totalCash = cash;
        this.lock = new Object();
    }

    public int withdrawCash(int amount) {
        if(amount < 0) {
            throw new InvalidParameterException("The parameter 'amount' can't be less that zero!");
        }

        synchronized (this.lock) {
            if(totalCash > amount) {
                totalCash -= amount;
                return totalCash;
            } else {
                return -1;
            }
        }
    }

    public int depositCash(int amount) {
        if(amount < 0) {
            throw new InvalidParameterException("The parameter 'amount' can't be less that zero!");
        }
        synchronized (this.lock) {
            totalCash += amount;
            return totalCash;
        }
    }

    public int getTotalCash() {
        synchronized (this.lock) {
            System.out.println(totalCash);
            return totalCash;
        }
    }
}
