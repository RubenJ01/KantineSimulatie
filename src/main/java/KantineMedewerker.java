public class KantineMedewerker extends Persoon {

    private int medewerkersnummer;
    private boolean magAanKassaStaan;

    public KantineMedewerker(int bsn, String voornaam, String achternaam, Datum datum, char geslacht, int medewerkersnummer, boolean magAanKassaStaan) {
        super(bsn, voornaam, achternaam, datum, geslacht);
        this.medewerkersnummer = medewerkersnummer;
        this.magAanKassaStaan = magAanKassaStaan;
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
}

