public class Student extends Persoon {

    private int studentnummer;
    private String studierichting;

    public Student(int bsn, String voornaam, String achternaam, Datum datum, char geslacht, int studentnummer, String studierichting) {
        super(bsn, voornaam, achternaam, datum, geslacht);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }

    public Student() {

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
