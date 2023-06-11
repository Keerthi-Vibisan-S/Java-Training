package Generics;

public class Main {
    public static void main(String [] args) {
        BasketBallPlayer lebronJames = new BasketBallPlayer("Lebron James", "13", "Defence", "80");
        BasketBallPlayer jordan = new BasketBallPlayer("Curry", "23", "Mid Post", "100+");
        BasketBallPlayer harden = new BasketBallPlayer("James Harden", "17", "Offence", "70");

        CricketPlayer dhoni = new CricketPlayer("Dhoni", "7", "Wicket keeper", "100+");
        CricketPlayer jadeja = new CricketPlayer("Jadeja", "8", "Bowler", "60+");
        CricketPlayer virat = new CricketPlayer("Kholi", "18", "Batsman", "100+");

        Team <BasketBallPlayer> lakers = new Team<>("Lakers");
        lakers.addPlayer(lebronJames);
        lakers.addPlayer(jordan);
        lakers.addPlayer(harden);

        /**Type-safety - so we cannot add a cricket player in a basketball team and this reduces code
         for creating separate class for each team **/
        // lakers.addPlayer(dhoni); ---> Error Type Safety

        Team <CricketPlayer> india = new Team<>("India");
        india.addPlayer(dhoni);
        india.addPlayer(jadeja);
        india.addPlayer(virat);

        System.out.println(lakers);
        System.out.println("---- ------ ----- ------");
        System.out.println(india);
    }
}
