package homework0708;

public class Card {

    private String number;
    private int pin;
    private int balance;
    private int dailyWithdrawn;
    private AccountStatus status;

    public Card(String number, int pin, int balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
        this.dailyWithdrawn = 0;
        this.status = AccountStatus.ACTIVE;
    }

    public boolean checkPin(int enteredPin) {
        return pin == enteredPin;
    }


    public void withdraw(int amount) {
        balance -= amount;
        dailyWithdrawn += amount;
    }

    public String getNumber(){
        return number;
    }
    public int getBalance() {
        return balance;
    }
    public int getDailyWithdrawn() {
        return dailyWithdrawn;
    }
    public AccountStatus getStatus() {
        return status;
    }

    public void block() {
        status = AccountStatus.BLOCKED;
    }
}