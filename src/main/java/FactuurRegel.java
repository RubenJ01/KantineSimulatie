import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Factuur factuur;

    private Artikel artikel;

    public FactuurRegel() {
    }

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    public Artikel getArtikel(){
        return Artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        // method body omitted
    }
}
