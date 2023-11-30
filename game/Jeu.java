package game;

import env3d.Env;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Jeu {
    private Env env; // Environnement 3D
    private Room room; // Salle du jeu
    private Profil profil; // Profil du joueur
    private Tux tux; // Personnage du joueur
    private ArrayList<Letter> lettres; // Liste des lettres dans le jeu
    private Dico dico; // Dictionnaire de mots
    

    // Constructeur du jeu
    public Jeu() throws SAXException, IOException, ParserConfigurationException {
        env = new Env(); // Crée un nouvel environnement
        room = new Room(); // Instancie une salle
        tux = new Tux(env, room); // Instancie le personnage Tux
        dico = new Dico("src/xml/xml"); // Instancie le dictionnaire

        // Réglage de la caméra
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        profil = new Profil(); // Instancie un profil par défaut
    }

    // Méthode pour exécuter le jeu
    public void execute() {
        joue(new Partie());
        env.exit();
    }
    protected ArrayList<Letter> getLettres() {
    return lettres;
    }

    // Méthode principale du jeu
    public void joue(Partie partie) {
        env.setRoom(room); // Régle la salle dans l'environnement
        lettres = new ArrayList<Letter>();
        String mot = dico.getMotDepuisListeNiveaux(2);

        // Création et ajout des lettres aléatoires dans la salle
        for (int i = 0; i < mot.length(); i++) {
            double x = Math.random() * 100;
            double z = Math.random() * 100;
            Letter l = new Letter(env, room, mot.charAt(i), x, z);
            lettres.add(l);
        }

        for (Letter l : lettres) {
            env.addObject(l); // Ajoute chaque lettre à l'environnement
        }

        démarrePartie(partie); // Initialise la partie

        Boolean finished = false;
        env.addObject(tux); // Ajoute le personnage Tux à l'environnement

        while (!finished) {
            if    ( (env.getKey() == 1) || lettres.isEmpty() )  {
                finished = true; // Sort de la boucle si la touche Escape est pressée
            }

            // Sauvegarde la position actuelle de Tux
            double tuxXBeforeMove = tux.getX();
            double tuxZBeforeMove = tux.getZ();

            // Déplace Tux
            tux.déplace();

            // Vérifie les collisions après le déplacement
            boolean collisionDetected = false;
            for (int i = 0; i < lettres.size(); i++) {
                Letter letter = lettres.get(i);
                if (collision(letter)) {
                    collisionDetected = true;

                    // Vérifie si la collision est avec la première lettre (à l'index 0)
                    if (i == 0) {
                        // Supprime la première lettre de l'ArrayList et de l'environnement
                        lettres.remove(i);
                        env.removeObject(letter);
                    }

                    break; // Sort de la boucle si une collision est détectée
                }
            }

            // Si une collision est détectée, rétablit la position de Tux
            if (collisionDetected) {
                tux.setX(tuxXBeforeMove);
                tux.setZ(tuxZBeforeMove);
            }

            appliqueRegles(partie); // Applique les règles de la partie

            env.advanceOneFrame(); // Fait avancer le moteur de jeu (mise à jour de l'affichage, des événements, etc.)
        }

        terminePartie(partie); // Termine la partie
    }

    // Méthode appelée au démarrage d'une nouvelle partie
    protected void démarrePartie(Partie partie) {
        // Logique supplémentaire
    }

    // Méthode pour appliquer les règles du jeu
    protected void appliqueRegles(Partie partie) {
        for (Letter letter : lettres) {
            if (collision(letter)) {
                // Logique de gestion des collisions ici
            }
        }
        // Autres règles du jeu peuvent être ajoutées ici
    }

    // Méthode appelée à la fin de la partie
    protected void terminePartie(Partie partie) {
        // Logique supplémentaire à la fin de la partie
    }

    // Méthode pour calculer la distance entre Tux et une lettre
    protected double distance(Letter letter) {
        double tuxX = tux.getX();
        double tuxZ = tux.getZ();
        double letterX = letter.getX();
        double letterZ = letter.getZ();

        return Math.sqrt(Math.pow(tuxX - letterX, 2) + Math.pow(tuxZ - letterZ, 2));
    }

    // Méthode pour vérifier s'il y a une collision entre Tux et une lettre
    protected boolean collision(Letter letter) {
        double distance = distance(letter);

        return distance < tux.getScale() + letter.getScale();
    }
}
