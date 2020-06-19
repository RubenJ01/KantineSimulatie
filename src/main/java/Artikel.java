public class Artikel {

    private String naam;
    private int prijs;
    private int korting;

    public Artikel(String naam, int prijs, int korting) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = korting;
    }

    public Artikel(String naam, int prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.korting = 0;
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

    public int getKorting() {
        return korting;
    }

    public void setKorting(int korting) {
        this.korting = korting;
    }

}
