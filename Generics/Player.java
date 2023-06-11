package Generics;

public class Player {
    private String name;
    private String id;
    private String position; // Batsman, Bowler, Fielder, WicketKeeper
    private String no_of_matches;

    Player() {}

    public Player(String name, String id, String position, String no_of_matches) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.no_of_matches = no_of_matches;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", no_of_matches='" + no_of_matches + '\'' +
                '}';
    }
}
