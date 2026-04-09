import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class Tas {
    private LinkedList<Carte> cartes;

    public Tas() {
        cartes = new LinkedList<>();
    }

    public boolean estVide() {
        return cartes.isEmpty();
    }

    public void ajouterCarte(Carte c) {
        cartes.addLast(c);
    }

    public Carte retirerCarte() {
        return cartes.pollFirst();
    }

    public void melanger() {
        Collections.shuffle(cartes);
    }

    public int taille() {
        return cartes.size();
    }

    public void ajouterTas(Tas autreTas) {
        cartes.addAll(autreTas.cartes);
    }

    public List<Carte> getCartes() {
        return cartes;
    }
}
