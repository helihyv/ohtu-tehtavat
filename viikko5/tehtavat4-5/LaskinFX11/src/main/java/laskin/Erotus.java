
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Erotus extends Komento{
        public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
       int syote = getSyote();
       sovellus.miinus(syote);
       tuloskentta.setText(Integer.toString( sovellus.tulos()));
       undo.setDisable(false);
        asetaNollauksenDisable();
        asetaKentat();       
           
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenSyote);
        tuloskentta.setText(Integer.toString(sovellus.tulos()));
        undo.setDisable(true);
        asetaNollauksenDisable();
        asetaKentat();        
    }
    
}
