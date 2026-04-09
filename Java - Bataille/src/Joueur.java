public class Joueur {
    private String nom;
    private Tas tas;

    public Joueur(String nom) {
        this.nom = nom;
        this.tas = new Tas();
    }

    public String getNom() {
        return nom;
    }

    public Tas getTas() {
        return tas;
    }

    public boolean aDesCartes() {
        return !tas.estVide();
    }

    public Carte jouerCarte() {
        return tas.retirerCarte();
    }

    @Override
    public String toString() {
        return nom + " (" + tas.taille() + " cartes)";
    }
}
