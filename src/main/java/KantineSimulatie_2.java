import java.util.*;

import javax.persistence.Query;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class KantineSimulatie_2 {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal dagen
    public static final int DAGEN = 7;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[]{"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    public static int huidige_datum = 0;

    /**
     * Constructor
     */
    public KantineSimulatie_2() {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        kantine = new Kantine(manager);
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte de lengte die van de array
     * @param min    minimale waarde
     * @param max    maximale waarde
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }
        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min minimale waarde inclusief
     * @param max maximale waarde inclusief
     * @return Een random getal
     */

    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen de indexen die corresponderen met de variabele artikelnamen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];
        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];
        }
        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen het aantal dagen dat deze simulatie duurt
     */

    public void simuleer(int dagen) {
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        // for lus voor dagen
        for (int dag = 0; dag < dagen; dag++) {

            kantine.setDagAanbieding(artikelnamen[getRandomValue(0, artikelnamen.length - 1)]);
            huidige_datum = (dag + 1);

            // laat de personen maar komen...
            int randomValue = getRandomValue(1, 100);
            for (int j = 0; j < randomValue; j++) {

                // maak persoon en dienblad aan, koppel ze
                int getal = getRandomValue(1, 100);
                Persoon persoon;
                if (getal == 1) {
                    persoon = new KantineMedewerker();
                } else if (getal > 1 && getal < 90) {
                    persoon = new Student();
                } else {
                    persoon = new Docent();
                }
                Dienblad dienblad = new Dienblad(persoon);
                // System.out.println(persoon.toString());
                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON, MAX_ARTIKELEN_PER_PERSOON);

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN - 1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);

            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();

            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            Kassa kassa = kantine.getKassa();
            System.out.println("Dag: " + (dag + 1));
            System.out.println("Aantal personen in kantine: " + randomValue);
            System.out.println("Aantal artikelen dat de kassa heeft gepasseerd: " + kassa.aantalArtikelen());
            System.out.println("Aantal geld dat in de kassa zit: " + kassa.hoeveelheidGeldInKassa());

            // reset de kassa voor de volgende dag
            kassa.resetKassa();
        }

        System.out.println("Query's.");
        System.out.println("Totale omzet en toegepaste korting:");
        for (Object[] a : totaleOmzetToegepasteKorting()) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("Gemiddelde omzet: ");
        System.out.println(gemiddeldeOmzet());
        System.out.println("Top 3 facturen: ");
        for (Long a : topDrieFacturen()) {
            System.out.println(a);
        }
        System.out.println("Totalen en toegepaste korting per artikel: ");
        for (Object[] a : totalenToegepasteKortingPerArtikel()) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("Totalen en toegepaste korting per artikel per dag: ");
        for (Object[] a : totalenToegepasteKortingPerArtikelPerDag()) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("Top 3 meest populaire artikelen: ");
        for (Object[] a : topDrieMeestPopulaireArtikelen()) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("Top 3 artikelen meeste omzet: ");
        for (Object[] a : topDrieArtiklenMeesteOmzet()) {
            System.out.println(Arrays.toString(a));
        }
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    public List<Object[]> totaleOmzetToegepasteKorting() {
        Query query = manager.createQuery("SELECT SUM(totaal), SUM(korting) FROM Factuur");
        return query.getResultList();
    }

    public Double gemiddeldeOmzet() {
        Query query = manager.createQuery("SELECT AVG(totaal) FROM Factuur");
        return (Double) query.getSingleResult();
    }

    public List<Long> topDrieFacturen() {
        Query query = manager.createQuery("SELECT id FROM Factuur ORDER BY (totaal-korting) DESC");
        query.setMaxResults(3);
        return query.getResultList();
    }

    public List<Object[]> totalenToegepasteKortingPerArtikel() {
        Query query = manager.createQuery("SELECT artikel.naam, sum(artikel.prijs), sum(artikel.korting) FROM FactuurRegel group by artikel.naam");
        return query.getResultList();
    }

    public List<Object[]> totalenToegepasteKortingPerArtikelPerDag() {
        Query query = manager.createQuery("SELECT f.datum, fr.artikel.naam, sum(fr.artikel.prijs), SUM(fr.artikel.korting) FROM FactuurRegel fr join fr.factuur f GROUP BY fr.artikel.naam, f.datum");
        return query.getResultList();
    }

    public List<Object[]> topDrieMeestPopulaireArtikelen() {
        Query query = manager.createQuery("SELECT artikel.naam, COUNT(artikel.naam)  FROM FactuurRegel GROUP BY artikel.naam ORDER BY COUNT(artikel.naam) DESC");
        query.setMaxResults(3);
        return query.getResultList();
    }

    public List<Object[]> topDrieArtiklenMeesteOmzet() {
        Query query = manager.createQuery("SELECT artikel.naam, sum(artikel.prijs) FROM FactuurRegel group by artikel.naam order by sum(artikel.prijs) desc");
        query.setMaxResults(3);
        return query.getResultList();
    }


    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        kantineSimulatie.simuleer(dagen);
    }


}
