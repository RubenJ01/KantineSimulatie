public class KantineMedewerker extends Persoon implements KortingskaartHouder{

    private int medewerkersnummer;
    private boolean magAanKassaStaan;
    private double kortingsPercentage;
    private boolean heeftMaximum;
    private double maximum;

    public KantineMedewerker(int bsn, String voornaam, String achternaam, Datum datum, char geslacht, int medewerkersnummer, boolean magAanKassaStaan) {
        super(bsn, voornaam, achternaam, datum, geslacht);
        this.medewerkersnummer = medewerkersnummer;
        this.magAanKassaStaan = magAanKassaStaan;
        this.kortingsPercentage = 0.35;
        this.heeftMaximum = false;
        this.maximum = 0;
    }

    public KantineMedewerker() {

    }

    public int getMedewerkersnummer() {
        return medewerkersnummer;
    }

    public void setMedewerkersnummer(int medewerkersnummer) {
        this.medewerkersnummer = medewerkersnummer;
    }

    public boolean isMagAanKassaStaan() {
        return magAanKassaStaan;
    }

    public void setMagAanKassaStaan(boolean magAanKassaStaan) {
        this.magAanKassaStaan = magAanKassaStaan;
    }

    @Override
    public double geefKortingsPercentage() {
        return this.kortingsPercentage;
    }

    @Override
    public boolean heeftMaximum() {
        return this.heeftMaximum;
    }

    @Override
    public double geefMaximum() {
        return this.maximum;
    }
}

