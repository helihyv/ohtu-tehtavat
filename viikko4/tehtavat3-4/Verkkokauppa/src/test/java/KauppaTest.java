package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;
    
    
    @Before
        public void setUp() {
            pankki = mock(Pankki.class);
            viite = mock(Viitegeneraattori.class);
            when(viite.uusi()).thenReturn(42);
        
            varasto = mock(Varasto.class);
       
            k = new Kauppa(varasto, pankki, viite);
        }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));           

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOiekeillaParametreilla() {

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));         

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   

    }
    
    @Test
    public void kahtaEritTuotettaOstettaessaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Otso", "98765");
        
        verify(pankki).tilisiirto("Otso", 42, "98765", "33333-44455", 15); 
    }
    
    @Test    
    public void kahtaSamaaTuotettaOstettaessaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("Otso", "98765");
        
        verify(pankki).tilisiirto("Otso", 42, "98765", "33333-44455", 10); 
    }
    
    @Test    
    public void kahtaEritTuotettaJoistaToinenOnLoppuOstettaessaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Otso", "98765");
        
        verify(pankki).tilisiirto("Otso", 42, "98765", "33333-44455", 5); 
    }
    
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Otso", "98765");
        
        verify(pankki).tilisiirto("Otso", 42, "98765", "33333-44455", 5);
    }
        
    @Test
    public void jokaiselleMaksutapahtumallePyydetaanUusiTilinumero() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Meri", "56789");
        
        verify(viite, times(1)).uusi();
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Otso", "98765");
        
        verify(viite, times(2)).uusi();
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Alli", "13568");
        
        verify(viite, times(3)).uusi();
        
        

    }

    @Test
    public void poistaKoristaPoistaaTuotteenOstoksesta() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.lisaaKoriin(0);
        k.tilimaksu("Otso", "98765");
        
        verify(pankki).tilisiirto("Otso", 42, "98765", "33333-44455", 5);   
    }
        
}