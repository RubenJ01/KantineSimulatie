public class Docent extends Persoon implements KortingskaartHouder {

    private String afkorting;
    private String afdeling;
    private double kortingsPercentage;
    private boolean heeftMaximum;
    private double maximum;

    public Docent(int bsn, String voornaam, String achternaam, Datum datum, char geslacht, String afkorting, String afdeling) {
        super(bsn, voornaam, achternaam, datum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
        this.kortingsPercentage = 0.25;
        this.heeftMaximum = true;
        this.maximum = 1;
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
