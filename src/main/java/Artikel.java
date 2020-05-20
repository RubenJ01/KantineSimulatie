public class Artikel {

    private String naam;
    private int prijs;

    public Artikel(String naam, int prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    public Artikel() {

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public String toString() {
        return getNaam() + "/n" + getPrijs();
    }
}
