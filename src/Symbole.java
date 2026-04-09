public class Symbole {
    private String nom;
    private String couleur;

    public Symbole(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public String getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return nom + " (" + couleur + ")";
    }
}
