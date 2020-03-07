
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class Nollaa extends Komento{
    
private int arvoEnnenNollausta;
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        arvoEnnenNollausta = Integer.parseInt(tuloskentta.getText());
        sovellus.nollaa();
        undo.setDisable(false);
        asetaNollauksenDisable();
        asetaKentat();        
    }

    @Override
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(arvoEnnenNollausta);
        undo.setDisable(true);
        asetaNollauksenDisable();
        asetaKentat();        
    }


}
