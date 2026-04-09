public class Carte {
    private Hauteur hauteur;
    private Symbole symbole;

    public Carte(Hauteur hauteur, Symbole symbole) {
        this.hauteur = hauteur;
        this.symbole = symbole;
    }

    public Hauteur getHauteur() {
        return hauteur;
    }

    public Symbole getSymbole() {
        return symbole;
    }

    @Override
    public String toString() {
        return hauteur + " de " + symbole.getNom();
    }
}
