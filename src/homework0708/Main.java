package homework0708;

public class Main {
    public static void main(String[] args) {
        Card card =
                new Card(
                        "1234567890123456",
                        1234,
                        70000
                );


        ATM atm = new ATM();

        atm.insertCard(card);
        atm.enterPin(1234);



        System.out.println(
                atm.withdraw(5000)
        );


        System.out.println(
                atm.withdraw(50)
        );


        System.out.println(
                atm.withdraw(20500)
        );

        atm.ejectCard();
        atm.reset();





        Card card2 =
                new Card(
                        "1111222233334444",
                        5555,
                        30000
                );
        ATM atm2 = new ATM();

        atm2.insertCard(card2);


        atm2.enterPin(1111);
        atm2.enterPin(1111);
        atm2.enterPin(1111);

        System.out.println(
                atm2.getState()
        );

    }
     }