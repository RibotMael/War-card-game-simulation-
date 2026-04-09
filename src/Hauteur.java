public class Hauteur {
    private int rang;
    private String valeur;

    public Hauteur(int rang, String valeur) {
        this.rang = rang;
        this.valeur = valeur;
    }

    public int getRang() {
        return rang;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}
