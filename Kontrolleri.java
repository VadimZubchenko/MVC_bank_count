package MVC_simple;

import javax.swing.JOptionPane;

/**
 *
 * @author vadimzubchenko
 * 
 * Tämä on sovelluksen Controller (C). Tämä luokka sisältää nyt myös sovelluksen
 * käynnistyksen (main).
 *
 */
    // Tämä luokka sisältää sovelluksen toimintalogiikkaa.    
    // Tuntee MVC-mallin View-tason sekä Model-tason. 
    // View-taso kutsuu Controllerin metodeja, joten sille välitetään
    // viite tähän Controller-olioon.

public class Kontrolleri {       
    // private final Engine engine; // Composition "part of"
    // private Engine engine;     // Aggregation  "has a"

    // kapseloidut ominaisuudet (V, M)
    private Naytto naytto; // heikko koostumissuhde, Aggregation
    private Pankkitili malli;// heikko koostumissuhde, Aggregation
    boolean maksuOk;
    public Kontrolleri() {
        // Nyt kontrollerin (C) konstruktori (eli tämä metodi) 
        // luo muut tarvittavat komponentit ja pyytää sitten 
        // näyttöä esittämään aloitusnäytön.
        
        // luodaan sovelluksen tarvitsemat muut komponentit: M, V.
        malli = new Pankkitili(500);             // M, alkusaldo 500.
        naytto = new Naytto();                  // V
        
        // välitetään näytölle tieto sovelluksen kontrollerista eli
        // tämä komponentti itse (this).
        naytto.rekisteroiOhjain(this); 
        
        // pyydetään näyttöä esittämään aloitusnäyttö
        naytto.aloitusNaytto();
        
                        
    }
    
    // näytöltä tulevia pyyntöjä
    public double saldo() {
        double tilinSaldo = malli.getSaldo();
        return tilinSaldo;
    }
    
    public void otto(double kateisenMaara) {
        // että muuttaa 'Pankitili' saldo  nostamisen jälkeen
        maksuOk = malli.otto(kateisenMaara);
        if (maksuOk) {
            naytto.naytaViesti("Onnistui!");
        } else {
// ei nyt jaksa..       
            naytto.naytaViesti("Ei riittää...");
        }
        naytto.aloitusNaytto(); 
    }
    
    public void talletus(double lisaRaha) {
        malli.talletus(lisaRaha);
        // Ei kerrota nyt käyttäjälle toiminnon onnistumisesta mitään (???)
        // Näyttöä pyydetään siirtymään vain aloitusnäyttöön
        naytto.naytaViesti("Onnistui!");
        naytto.aloitusNaytto();
    }
    
        
    // Koko sovelluksen käynnistäminen
    public static void main(String[] args) {
        // luodaan uusi kontrolleri-olio, joka valmistelee nyt sovelluksen
        new Kontrolleri();
        
    }
}
