package MVC_simple;

/**
 *
 * @author vadimzubchenko
 */
import javax.swing.JOptionPane;

/**
 * Tämä luokka on MVC-mallin View (V) eli näyttö. Näyttö on tässä luokassa
 * toteutettu pääosin JOptionPane-avulla.
 *
 */
public class Naytto {

    // tieto kontrollerista, jolle näyttö lähettää pyynnöt
    private Kontrolleri kontrolleri; //mutta tässä luokassa sitä ei luoda 
    // Tä on kiinostava: kontrolleri-olio on luotu 
    // Kontrolleri-luokan sisällä main-metodissa.

    // metodi luo näytön, joka näytetään aluksi käyttäjälle
    public void aloitusNaytto() {
        String valintaStr;      // käyttäjän valinta merkkijonona
        int valinta;            // valinta numerona

        // näytetään käyttäjälle päävalikko
        valintaStr = JOptionPane.showInputDialog(null,
                "Valitse toiminto (1-4) seuraavista: \n"
                + "1: Näytä pankkkitilin saldo \n"
                + "2: otto \n"
                + "3: pano \n"
                + "4: lopetus \n"
                + "Mitä haluat tehdä?");
        /* käytetään try-catch syöttämäsi arvon tarkistamiseksi. 
         Näin ohjelma ei katke, jos käyttäjä söyttää luku väärin*/
        try {
            // muutetaan käyttäjän vastaus numeroksi
            valinta = Integer.parseInt(valintaStr);
            // siirrytään käyttäjän haluamaan toimintoon
            switch (valinta) {
                case 1:
                    saldo();
                    break;
                case 2:
                    otto();
                    break;
                case 3:
                    pano();
                    break;
                case 4:
                    lopetus();
                    break;
                default:
                    // näytetään valikko uudestaan, jos ei kunnollinen valinta
                    aloitusNaytto();
            }
            
        } catch (Exception e) {
            naytaViesti("Upps! Antamasi syöte oli virheellinen. Syötä luku 1-4.");
            aloitusNaytto();
        }
        
    }

    // näytön toimintoja
    public void naytaViesti(String viesti) {
        // Parametrina näytettävä viesti
        JOptionPane.showMessageDialog(null, viesti);
    }
    
    public void saldo() {
        // pyydetään kontrolleria hoitamaan toiminto, 
        // saadaan takaisin haluttu vastaus
        double saldo = kontrolleri.saldo();
        JOptionPane.showMessageDialog(null, "Tilin saldo: " + saldo);
        // siirrytään takaisin aloitusnäyttöön
        aloitusNaytto();
    }
    
    public void otto() {
        // pyydetään käyttäjältä lisätietoa ennen kontrollerin kutsumista
        String kateistaStr
                = JOptionPane.showInputDialog(null, "Paljonko haluat nostaa?");
        // kontrolleri vaatii parametrina double, muutetaan se 'lennossa'.
        kontrolleri.otto(Double.parseDouble(kateistaStr));
    }
    
    public void pano() {
        // pyydetään käyttäjältä lisätietoa ennen kontrollerin kutsumista
        String uuttaRahaa
                = JOptionPane.showInputDialog(null, "Paljonko talletetaan?");
        // kontrolleri vaatii parametrina double, muutetaan se 'lennossa'.
        kontrolleri.talletus(Double.parseDouble(uuttaRahaa));
    }
    
    public void lopetus() {
        System.exit(0);
    }
    
    public void rekisteroiOhjain(Kontrolleri ohjain) {
        // näyttö saa tiedon kontrollerista, jolle se välittää pyyntöjä
        this.kontrolleri = ohjain;
    }
    
}
