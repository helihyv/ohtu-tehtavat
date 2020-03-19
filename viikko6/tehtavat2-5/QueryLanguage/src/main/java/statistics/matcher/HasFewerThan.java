
package statistics.matcher;

import statistics.Player;


public class HasFewerThan implements Matcher {

    private Matcher matcher; 
    public HasFewerThan(int value, String fieldname) {
        
        matcher = new Not(new HasAtLeast(value, fieldname));
    }
    
    

    @Override
    public boolean matches(Player p) {
    
        return matcher.matches(p);
    }
    
    
}
