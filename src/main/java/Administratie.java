public class Administratie {

    private Administratie() {

    }

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public static double berekenGemiddeldAantal(int[] aantal) {
        int sum = 0;
        for(int nummer : aantal) {
            sum += nummer;
        }
        return (double) (sum / aantal.length);
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */

    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double sum = 0;
        for(double nummer : omzet) {
            sum += nummer;
        }
        return sum / omzet.length;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */
    public static double[] berekenDagOmzet(double[] omzet) {
        int DAYS_IN_WEEK = 7;
        double[] temp = new double[DAYS_IN_WEEK];
        for(int dag = 0; dag < DAYS_IN_WEEK; dag++) {
            int week = 0;
            int i;
            while ((i = dag+ DAYS_IN_WEEK *week) < omzet.length) {
                temp[dag] += omzet[i];
                week++;
            }
        }
        return temp;
    }
}


