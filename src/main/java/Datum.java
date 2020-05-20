import java.util.HashMap;

public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Constructor
	 */
	public Datum(int dag_, int maand_, int jaar_) {
		this();
		if(Datum.bestaatDatum(dag_, maand_, jaar_)) {
			this.dag = dag_;
			this.maand = maand_;
			this.jaar = jaar_;
		}
	}

	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	/**
	 * Checkt of een datum wel echt bestaat.
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return true als de datum bestaat, false indien niet.
	 */
	public static boolean bestaatDatum(int dag, int maand, int jaar) {
		boolean schrikkel = false;
		// de maanden en het aantal dagen in een hashmap zetten
		HashMap<Integer, Integer> maandLengte = new HashMap<Integer, Integer>();
		maandLengte.put(1, 31);
		maandLengte.put(2, 28);
		maandLengte.put(3, 31);
		maandLengte.put(4, 30);
		maandLengte.put(5, 31);
		maandLengte.put(6, 30);
		maandLengte.put(7, 31);
		maandLengte.put(8, 31);
		maandLengte.put(9, 30);
		maandLengte.put(10, 31);
		maandLengte.put(11, 30);
		maandLengte.put(12, 31);
		if (jaar % 4 == 0) {
			if (jaar % 100 == 0) {
				if (jaar % 400 == 0) {
					schrikkel = true;
				}
			} else
				schrikkel = true;
		}
		// indien het jaar een schrikkeljaar is, verander februarie naar 29 dagen
		if (schrikkel) {
			maandLengte.put(2, 29);
		}
		if ((dag >= 1) && (maand > 0 && maand < 13) && (jaar > 1899 && jaar < 2101)) {
			return dag <= maandLengte.get(maand);
		}
		return false;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString(Datum datum) {
		if (datum.dag == 0 && datum.maand == 0 && datum.jaar == 0) {
		    return "Onbekend";
        }
		return "";
	}

	public int getDag() {
		return dag;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

	public int getMaand() {
		return maand;
	}

	public void setMaand(int maand) {
		this.maand = maand;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
}
