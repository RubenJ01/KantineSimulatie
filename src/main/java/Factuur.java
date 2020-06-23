

    import javax.persistence.*;
    import java.time.LocalDate;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;

    @Entity
    @Table(name = "factuur")

    public class Factuur implements Serializable {

        @Id
        @GeneratedValue
        private Long id;

        @Column(name="datum", nullable=false)
        private LocalDate datum;

        @Column(name="korting", nullable=false)
        private double korting;

        @Column(name="totaal", nullable=false)
        private double totaal;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinTable(name = "factuur_factuurregel", joinColumns = @JoinColumn(name = "factuur_id"), inverseJoinColumns = @JoinColumn(name = "factuurregel_id"))
        private List<FactuurRegel> regels;

        public Factuur() {
            totaal = 0;
            korting = 0;
        }

        public Factuur(Dienblad klant, LocalDate datum) {
            this();
            this.datum = datum;
            regels = new ArrayList<>();

            verwerkBestelling(klant);
        }

        /**
         * Verwerk artikelen en pas kortingen toe.
         * <p>
         * Zet het totaal te betalen bedrag en het
         * totaal aan ontvangen kortingen.
         *
         * @param klant
         */
        private void verwerkBestelling(Dienblad klant) {
            Iterator<Artikel> artikelIterator = klant.lopenDoorArtikelen();
            Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();
            Persoon persoon = klant.getKlant();
            double totaalePrijs;
            double totaaleKorting;
            if(persoon instanceof KortingskaartHouder) {
                final KortingskaartHouder kortingskaartHouder = (KortingskaartHouder) persoon;
                if(!kortingskaartHouder.heeftMaximum()) {
                    totaalePrijs = klant.getTotaalPrijs() * (1 - kortingskaartHouder.geefKortingsPercentage());
                    totaaleKorting = klant.getTotaalPrijs()-totaalePrijs;
                } else {
                    totaaleKorting = klant.getTotaalPrijs() - (klant.getTotaalPrijs() * (1 - kortingskaartHouder.geefKortingsPercentage()));
                    if (totaaleKorting > kortingskaartHouder.geefMaximum()) {
                        totaalePrijs = klant.getTotaalPrijs() - kortingskaartHouder.geefMaximum();
                        totaaleKorting = 1;
                    } else {
                        totaalePrijs = klant.getTotaalPrijs() * (1 - kortingskaartHouder.geefKortingsPercentage());
                    }
                }
            } else {
                totaalePrijs = klant.getTotaalPrijs();
                totaaleKorting = 0;
            }



            korting += totaaleKorting;
            totaal += totaalePrijs;
            while (artikelIterator.hasNext()) {
                Artikel a = artikelIterator.next();
                totaalePrijs += a.getPrijs();
                totaaleKorting += a.getKorting();

                regels.add(new FactuurRegel(this, a));
            }


        }

        /**
         * @return het totaalbedrag
         */
        public double getTotaal() {
            return totaal;
        }

        /**
         * @return de toegepaste korting
         */
        public double getKorting() {
            return korting;
        }

        /**
         * @return een printbaar bonnetje
         */
        public String toString() {
            StringBuilder bon = new StringBuilder();
            for (FactuurRegel fr : regels) {
                bon.append(fr.getArtikel().toString()).append("\n");

            }
            return (bon + "Factuur nr: " +id+ "\n Datum: " + datum + "\n Korting: " + korting + "\n Totaalbedrag: " + totaal);
        }
    }

