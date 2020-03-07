
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
        
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    
    protected int edellinenSyote;
        
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();
    public abstract void peru();
    
    protected void asetaNollauksenDisable() {
        if ( sovellus.tulos() == 0) {
            nollaa.setDisable(true);
        } else {
            nollaa.setDisable(false);
        }
    }
    
    protected void asetaKentat() {
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
    protected int getSyote() {
        
        try {
            return Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            return 0;
    }
    }
}
