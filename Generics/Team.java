package Generics;

import java.util.ArrayList;

public class Team <T extends Player> {  // extends Player is an Upperbound, so we could use only Player and its subclass
    private String teamName;
    private ArrayList <T> teamMembers;

    public Team(String teamName) {
        this.teamName = teamName;
        teamMembers = new ArrayList<>();
    }

    public void addPlayer(T player) {
        teamMembers.add(player);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", teamMembers=" + teamMembers +
                '}';
    }
}
