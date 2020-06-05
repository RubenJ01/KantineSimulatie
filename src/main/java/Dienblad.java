import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */

    public Dienblad() {
        artikelen = new Stack<Artikel>();
    }

    public Dienblad(Persoon klant) {
        this.klant = klant;
    }

    public Persoon getKlant() {
        return this.klant;
    }

    public void setKlant(Persoon klant_) {
        this.klant = klant_;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return artikelen.size();
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        double totaalprijs = 0;
        for (Artikel a : artikelen) {
            totaalprijs += a.getPrijs();
        }
        return totaalprijs;
    }

    public Iterator<Artikel> lopenDoorArtikelen()
    {
        return artikelen.iterator();
    }
}

