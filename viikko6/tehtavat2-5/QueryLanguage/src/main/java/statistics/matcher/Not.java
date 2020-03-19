
package statistics.matcher;

import statistics.Player;

public class Not implements Matcher{

    private Matcher notMatcher;
    
    public Not(Matcher matcher) {
        this.notMatcher = matcher;
    }

    
    @Override
    public boolean matches(Player p) {
       return !notMatcher.matches(p);
    
    } 
}
