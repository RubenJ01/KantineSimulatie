public class Docent extends Persoon {

    private String afkorting;
    private String afdeling;

    public Docent(int bsn, String voornaam, String achternaam, Datum datum, char geslacht, String afkorting, String afdeling) {
        super(bsn, voornaam, achternaam, datum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    public Docent() {

    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }
}
