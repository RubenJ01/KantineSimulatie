public class Student extends Persoon {

    private int studentnummer;
    private String studierichting;

    public Student(int bsn, String voornaam, String achternaam, Datum datum, char geslacht, int studentnummer, String studierichting, Betaalwijze betaalwijze) {
        super(bsn, voornaam, achternaam, datum, geslacht, betaalwijze);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }

    public Student() {
        this(15, "Ruben", "Eekhof", new Datum(10, 9, 2001), 'm', 1, "hbo", new Contant());
    }

    public int getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }
}
