public class Contant extends Betaalwijze {

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if(tebetalen <= saldo) {
            saldo -= tebetalen;
        }
        throw new TeWeinigGeldException();
    }

}
