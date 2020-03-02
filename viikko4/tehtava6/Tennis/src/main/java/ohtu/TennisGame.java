package ohtu;

public class TennisGame {
    
    private final static int SCORE_NONE = 0;
    private final static int SCORE_15 = 1;
    private final static int SCORE_30 = 2;
    private final static int SCORE_40 = 3;
    private final static int MINIMUM_LEAD_TO_WIN = 2;
    private final static int SCORE_ADDITION = 1;
    
    private int player1Score = SCORE_NONE;
    private int player2Score = SCORE_NONE;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            player1Score += SCORE_ADDITION;
        else
            player2Score += SCORE_ADDITION;
    }

    public String getScore() {

        if (player1Score==player2Score) {
            return getScoreWhenPlayersHaveEqualScores();
        }
        
        if (player1Score > SCORE_40 || player2Score > SCORE_40) {
            return getScoreWhenPlayerScoresAreNotEqualAndAScoreIsOver40();
        }

        return getSingleScoreWhenScoresAreNeitherEqualNorOverForty(player1Score) 
                + "-" 
                + getSingleScoreWhenScoresAreNeitherEqualNorOverForty(player2Score);
   
    }
    
    private String getScoreWhenPlayersHaveEqualScores () {
        switch (player1Score)
                    {
            case SCORE_NONE:
                    return "Love-All";
            case SCORE_15:
                    return "Fifteen-All";
            case SCORE_30:
                    return "Thirty-All";
            case SCORE_40:
                    return"Forty-All";
            default:
                    return "Deuce";

            }
         
    }
    
    private String getSingleScoreWhenScoresAreNeitherEqualNorOverForty(int score) {
        switch(score)
                {
                    case SCORE_NONE:
                        return "Love";
                    case SCORE_15:
                        return "Fifteen";
                    case SCORE_30:
                        return "Thirty";
                    default:
                        return "Forty";
                        
                }
    }
    
    private String getScoreWhenPlayerScoresAreNotEqualAndAScoreIsOver40() {
        
        if (player1Score > player2Score) {
            if (player1Score - player2Score >= MINIMUM_LEAD_TO_WIN) {
                 return "Win for player1";
            }
                return "Advantage player1";
            }

        if (player2Score - player1Score >= MINIMUM_LEAD_TO_WIN) {
            return "Win for player2";    
        } 

        return "Advantage player2";            
    }
}