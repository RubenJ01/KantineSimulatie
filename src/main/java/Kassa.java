import javax.persistence.EntityManager;
import javax.print.Doc;
import java.time.LocalDate;
import java.util.Iterator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Kassa {

    private double totaalPrijs;
    private int aantalArtikelen;
    private javax.persistence.EntityManager manager;

    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij, javax.persistence.EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        Factuur factuur = new Factuur(klant, LocalDate.now().plusDays(KantineSimulatie_2.huidige_datum));
        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();
        Persoon persoon = klant.getKlant();
        double totaalePrijs;
        if (persoon instanceof KortingskaartHouder) {
            final KortingskaartHouder kortingskaartHouder = (KortingskaartHouder) persoon;
            if (!kortingskaartHouder.heeftMaximum()) {
                totaalePrijs = klant.getTotaalPrijs() * (1 - kortingskaartHouder.geefKortingsPercentage());
            } else {
                double totaleKorting = klant.getTotaalPrijs() - (klant.getTotaalPrijs() * (1 - kortingskaartHouder.geefKortingsPercentage()));
                if (totaleKorting > kortingskaartHouder.geefMaximum()) {
                    totaalePrijs = klant.getTotaalPrijs() - kortingskaartHouder.geefMaximum();
                } else {
                    totaalePrijs = klant.getTotaalPrijs() * (1 - kortingskaartHouder.geefKortingsPercentage());
                }
            }
        } else {
            totaalePrijs = klant.getTotaalPrijs();
        }
        EntityTransaction transaction;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            betaalwijze.betaal(totaalePrijs);
            totaalPrijs += totaalePrijs;
            aantalArtikelen += klant.getAantalArtikelen();
            manager.persist(factuur);
            transaction.commit();
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

    public void berekenAantalArtikelenEnPrijs(Dienblad dienblad) {
        Iterator<Artikel> it = dienblad.lopenDoorArtikelen();
        while (it.hasNext()) {
            totaalPrijs += it.next().getPrijs();
            aantalArtikelen++;
        }
    }
}
