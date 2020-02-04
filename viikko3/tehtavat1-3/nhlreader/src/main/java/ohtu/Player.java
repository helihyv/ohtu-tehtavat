
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private int assists;
    private int goals;
    private String team;
    private String nationality;
   
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }
    
    @Override
    public String toString() {
        return String.format("%1$-20s\t%2$s \t%3$3d + %4$3d = %5$3d", 
                name, 
                team, 
                goals, 
                assists, 
                goals + assists
        );

    }

    @Override
    public int compareTo(Player t) {
        return (this.goals + this.assists) - (t.getGoals() + t.getAssists());
    }
      
}
