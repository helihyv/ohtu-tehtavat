
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento{

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {

        int syote = getSyote();
        sovellus.plus(syote);
        tuloskentta.setText(Integer.toString(sovellus.tulos()));
        edellinenSyote = syote;
        undo.setDisable(false );
        asetaNollauksenDisable();
        asetaKentat();
    }

    @Override
    public void peru() {
        sovellus.miinus(edellinenSyote);
        undo.setDisable (true);
        asetaNollauksenDisable();
        asetaKentat();

    }  
}
   