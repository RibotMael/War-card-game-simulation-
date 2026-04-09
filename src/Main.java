import java.util.*;

public class Main {
    private static Carte bataille(Joueur j, Tas pile) {
        if (!j.aDesCartes()) return null;

        // Carte "face cachée"
        Carte hidden = j.jouerCarte();
        pile.ajouterCarte(hidden);

        // Carte "face visible"
        if (!j.aDesCartes()) return null;
        Carte visible = j.jouerCarte();
        pile.ajouterCarte(visible);

        return visible;
    }

    public static void main(String[] args) {
        //Création des symboles
        Symbole pique = new Symbole("Pique", "Noir");
        Symbole trefle = new Symbole("Trèfle", "Noir");
        Symbole coeur = new Symbole("Cœur", "Rouge");
        Symbole carreau = new Symbole("Carreau", "Rouge");
        Symbole[] symboles = {pique, trefle, coeur, carreau};

        //Création des hauteurs
        Hauteur[] hauteurs = {
                new Hauteur(7, "7"), new Hauteur(8, "8"), new Hauteur(9, "9"),
                new Hauteur(10, "10"), new Hauteur(11, "Valet"),
                new Hauteur(12, "Dame"), new Hauteur(13, "Roi"), new Hauteur(14, "As")
        };

        //Création du jeu complet
        Tas paquet = new Tas();
        for (Symbole s : symboles) {
            for (Hauteur h : hauteurs) {
                paquet.ajouterCarte(new Carte(h, s));
            }
        }

        //Mélange
        paquet.melanger();

        //Création des joueurs
        Joueur j1 = new Joueur("Alice");
        Joueur j2 = new Joueur("Bob");

        //Distribution
        boolean tour = true;
        while (!paquet.estVide()) {
            if (tour) j1.getTas().ajouterCarte(paquet.retirerCarte());
            else j2.getTas().ajouterCarte(paquet.retirerCarte());
            tour = !tour;
        }

        //Déroulement du jeu
        int tourCount = 0;
        Tas pile = new Tas();

        //Ensemble des états déjà vus (pour éviter les boucles infinies)
        Set<String> etatsVus = new HashSet<>();

        while (j1.aDesCartes() && j2.aDesCartes()) {
            tourCount++;

            //Sauvegarde de l'état actuel
            String etat = j1.getTas().getCartes().toString() + "|" + j2.getTas().getCartes().toString();
            if (etatsVus.contains(etat)) {
                System.out.println("\n Partie bloquée ! Les coups se répètent indéfiniment.");
                break;
            }
            etatsVus.add(etat);

            System.out.println("\n--- Tour " + tourCount + " ---");
            Carte c1 = j1.jouerCarte();
            Carte c2 = j2.jouerCarte();

            System.out.println(j1.getNom() + " joue " + c1);
            System.out.println(j2.getNom() + " joue " + c2);

            pile.ajouterCarte(c1);
            pile.ajouterCarte(c2);

            if (c1.getHauteur().getRang() > c2.getHauteur().getRang()) {
                System.out.println(j1.getNom() + " remporte la manche !");
                j1.getTas().ajouterTas(pile);
                pile = new Tas();
            } else if (c2.getHauteur().getRang() > c1.getHauteur().getRang()) {
                System.out.println(j2.getNom() + " remporte la manche !");
                j2.getTas().ajouterTas(pile);
                pile = new Tas();
            } else {
                System.out.println("Égalité ! Bataille !");

                boolean batailleEnCours = true;

                while (batailleEnCours) {

                    if (j1.getTas().taille() < 2 || j2.getTas().taille() < 2) {
                        System.out.println("Un joueur n'a plus assez de cartes pour continuer la bataille.");
                        batailleEnCours = false;
                        break;
                    }

                    System.out.println(" → Chaque joueur pose une carte face cachée…");

                    // Joueurs posent carte cachée + visible
                    Carte b1 = bataille(j1, pile);
                    Carte b2 = bataille(j2, pile);

                    System.out.println(j1.getNom() + " révèle " + b1);
                    System.out.println(j2.getNom() + " révèle " + b2);

                    // Si un joueur ne peut plus jouer
                    if (b1 == null || b2 == null) break;

                    if (b1.getHauteur().getRang() > b2.getHauteur().getRang()) {
                        System.out.println(j1.getNom() + " remporte la bataille !");
                        j1.getTas().ajouterTas(pile);
                        pile = new Tas();
                        batailleEnCours = false;
                    }
                    else if (b2.getHauteur().getRang() > b1.getHauteur().getRang()) {
                        System.out.println(j2.getNom() + " remporte la bataille !");
                        j2.getTas().ajouterTas(pile);
                        pile = new Tas();
                        batailleEnCours = false;
                    }
                    else {
                        System.out.println("Nouvelle égalité ! On recommence !");
                    }
                }
            }

            System.out.println(j1);
            System.out.println(j2);
        }

        //Résultat final
        System.out.println("\n===== FIN DE LA PARTIE =====");
        if (j1.getTas().taille() > j2.getTas().taille()) {
            System.out.println(j1.getNom() + " gagne la partie !");
        } else if (j2.getTas().taille() > j1.getTas().taille()) {
            System.out.println(j2.getNom() + " gagne la partie !");
        } else {
            System.out.println("Égalité !");
        }
    }
}
