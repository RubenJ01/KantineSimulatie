public class Persoon {

    private int bsn;
    private String voornaam;
    private String achternaam;
    private Datum datum;
    private char geslacht;

    public Persoon(int bsn, String voornaam, String achternaam, Datum datum, char geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.datum = datum;
        this.geslacht = geslacht;
    }

    public Persoon() {
        this.datum = new Datum(0, 0,0);
        this.geslacht = 'm';
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }

    /**
     * De setter voor datum.
     * @param dag
     * @param maand
     * @param jaar
     * Roept de constructor aan van de zelfgemaakte klasse: Datum.
     */
    public void setDatum(int dag, int maand, int jaar) {
        this.datum = new Datum(dag, maand, jaar);
    }

    public int getBsn() {
        return bsn;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getDatum() {
        return datum.getDatumAsString(datum);
    }

    public String getGeslacht() {
        switch (this.geslacht) {
            case 'm':
                return "Man";
            case 'v':
                return "Vrouw";
            default:
                return "Onbekend";
        }
    }

    public String toString() {
        return getAchternaam() + "/n" + getDatum() + "/n" + getGeslacht() + "/n" +  getVoornaam() + "/n" + getBsn();
    }
}
