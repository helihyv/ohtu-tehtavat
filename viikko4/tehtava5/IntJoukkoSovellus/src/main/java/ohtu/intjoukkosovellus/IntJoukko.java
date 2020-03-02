
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (!kuuluu(luku)) {
            ljono[alkioidenLkm++] = luku;

            if (alkioidenLkm == ljono.length) {
                suurennaTaulukkoa();
            }
            return true;
        }
        return false;
    }
    
    private void suurennaTaulukkoa() {
        int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(ljono, uusiTaulukko);
        ljono = uusiTaulukko;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
            return false;
    }

    public boolean poista(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
 
                for (int j = i; j < alkioidenLkm - 1; j++) {
                    ljono[j] = ljono[j + 1];
                }
                alkioidenLkm--;
                return true;
            }
            
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {

        String tulostus = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tulostus += ljono[i];
            if (i < alkioidenLkm -1) {
                tulostus += ", ";
            }
        }
        tulostus += "}";
        return tulostus;

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] joukkoATauluna = joukkoA.toIntArray();
        int[] joukkoBTauluna = joukkoB.toIntArray();
        for (int i = 0; i < joukkoATauluna.length; i++) {
            uusiJoukko.lisaa(joukkoATauluna[i]);
        }
        for (int i = 0; i < joukkoBTauluna.length; i++) {
            uusiJoukko.lisaa(joukkoBTauluna[i]);
        }
        return uusiJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] joukkoATauluna = joukkoA.toIntArray();
        for (int i = 0; i < joukkoATauluna.length; i++) {
                if (joukkoB.kuuluu(joukkoATauluna[i])) {
                    uusiJoukko.lisaa(joukkoATauluna[i]);
                }
            }        
        return uusiJoukko;

    }
    
    public static IntJoukko erotus ( IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] joukkoATauluna = joukkoA.toIntArray();
        for (int i = 0; i < joukkoATauluna.length; i++) {
            if (!joukkoB.kuuluu(joukkoATauluna[i])) {
                uusiJoukko.lisaa(joukkoATauluna[i]);
            }
        }
        return uusiJoukko;
    }
        
}
