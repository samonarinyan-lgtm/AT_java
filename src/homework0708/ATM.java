package homework0708;

public class ATM {

    private Card card;

    private ATMState state;

    private int pinAttempts;


    public ATM() {
        state = ATMState.IDLE;
    }



    public void insertCard(Card card) {

        if(state == ATMState.IDLE ||
                state == ATMState.CARD_EJECTED) {

            this.card = card;
            state = ATMState.CARD_INSERTED;
            pinAttempts = 0;

            System.out.println("Card inserted");
        }
    }



    public void enterPin(int pin) {


        if(card == null) {
            System.out.println("No card");
            return;
        }


        if(state == ATMState.CARD_INSERTED ||
                state == ATMState.PIN_VERIFICATION) {


            if(card.checkPin(pin)) {

                state = ATMState.AUTHENTICATED;
                pinAttempts = 0;

                System.out.println("PIN correct");

            } else {


                pinAttempts++;


                if(pinAttempts == 3) {

                    state = ATMState.BLOCKED;

                    card.block();

                    System.out.println("Card blocked");

                } else {

                    state = ATMState.PIN_VERIFICATION;

                    System.out.println("Wrong PIN");
                }
            }
        }
    }


    public WithdrawalType checkAmount(int amount) {


        if(amount < 100) {

            return WithdrawalType.TOO_LOW;
        }


        if(amount > 20000) {

            return WithdrawalType.TOO_HIGH;
        }


        if(amount % 100 != 0) {

            return WithdrawalType.NOT_MULTIPLE_OF_100;
        }


        return WithdrawalType.VALID;
    }



    public WithdrawalResult withdraw(int amount) {


        if(state != ATMState.AUTHENTICATED) {

            return WithdrawalResult.DENIED_ACCOUNT_BLOCKED;
        }

        if(card.getStatus() == AccountStatus.BLOCKED) {

            return WithdrawalResult.DENIED_ACCOUNT_BLOCKED;
        }


        if(checkAmount(amount) != WithdrawalType.VALID) {

            return WithdrawalResult.DENIED_INVALID_AMOUNT;
        }

        if(card.getBalance() < amount) {

            return WithdrawalResult.DENIED_INSUFFICIENT_BALANCE;
        }

        if(card.getDailyWithdrawn() + amount > 50000) {

            return WithdrawalResult.DENIED_LIMIT_EXCEEDED;
        }



        state = ATMState.TRANSACTION_IN_PROGRESS;
        card.withdraw(amount);
        state = ATMState.AUTHENTICATED;
        return WithdrawalResult.APPROVED;
    }



    public void ejectCard() {

        if(state == ATMState.AUTHENTICATED ||
                state == ATMState.CARD_INSERTED ||
                state == ATMState.BLOCKED) {


            card = null;

            state = ATMState.CARD_EJECTED;

            System.out.println("Card ejected");
        }
    }

    public void reset() {


        if(state == ATMState.CARD_EJECTED) {

            state = ATMState.IDLE;
        }
    }

    public ATMState getState() {
        return state;
    }
}