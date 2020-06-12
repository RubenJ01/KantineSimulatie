import javax.print.Doc;
import java.util.Iterator;

public class Kassa {

    private double totaalPrijs;
    private int aantalArtikelen;


    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {

    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();
        double totaalePrijs;
        if(klant.getKlant() instanceof KantineMedewerker || klant.getKlant() instanceof Docent) {
            if(!klant.getKlant().heeftMaximum()) {
                totaalePrijs = klant.getTotaalPrijs() * (1 - klant.getKlant().geefKortingsPercentage());
            } else {
                double totaleKorting = klant.getTotaalPrijs() - (klant.getTotaalPrijs() * (1 - klant.getKlant().geefKortingsPercentage()));
                if (totaleKorting > klant.getKlant().geefMaximum()) {
                    totaalePrijs = klant.getTotaalPrijs() - klant.getKlant().geefMaximum();
                } else {
                    totaalePrijs = klant.getTotaalPrijs() * (1 - klant.getKlant().geefKortingsPercentage());
                }
            }
        } else {
            totaalePrijs = klant.getTotaalPrijs();
        }
        try {
            betaalwijze.betaal(totaalePrijs);
            totaalPrijs += klant.getTotaalPrijs();
            aantalArtikelen += klant.getAantalArtikelen();
        } catch (TeWeinigGeldException e) {
            e.printStackTrace();
            System.out.println("Betaling mislukt!");
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return totaalPrijs;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        totaalPrijs = 0;
        aantalArtikelen = 0;
    }

    public void berekenAantalArtikelenEnPrijs(Dienblad dienblad)
    {
        Iterator<Artikel> it = dienblad.lopenDoorArtikelen();
        while(it.hasNext())
        {
           totaalPrijs += it.next().getPrijs();
           aantalArtikelen++;
        }
    }
}
