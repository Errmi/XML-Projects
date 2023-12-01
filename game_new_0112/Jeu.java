/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.Env;
import org.lwjgl.input.Keyboard;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author gladen
 */
public abstract class Jeu {

    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }

    private final Env env;              // Environnement 3D
    private Tux tux;                    // Personnage du joueur
    private final Room mainRoom;        // Salle du jeu
    private final Room menuRoom;
    // FROM NEW CODE (I replaced it by the line below)???: private Letter letter;
    private ArrayList<Letter> lettres;  // Liste des lettres dans le jeu
    private Profil profil;              // Profil du joueur
    private final Dico dico;            // Dictionnaire de mots
    protected EnvTextMap menuText;      //text (affichage des texte du jeu)
    protected Boolean finished = false;
    
    
    
    public Jeu() throws SAXException, IOException, ParserConfigurationException {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        mainRoom = new Room();

        // Instancie une autre Room pour les menus
        menuRoom = new Room();
        menuRoom.setTextureEast("textures/black.png");
        menuRoom.setTextureWest("textures/black.png");
        menuRoom.setTextureNorth("textures/black.png");
        menuRoom.setTextureBottom("textures/black.png");

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil = new Profil();
        
        // Instancie le Dictionnaire
        dico = new Dico("src/xml/xml");

        // instancie le menuText
        menuText = new EnvTextMap(env);
        
        // Textes affichés à l'écran
        menuText.addText("Voulez vous ?", "Question", 200, 300);
        menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
        menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
        menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
        menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);
        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
        menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
        menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
        menuText.addText("3. Sortir du jeu ?", "Principal3", 250, 240);
        menuText.addText("Choisissez le niveau (1-5): ", "Niveau", 200, 300);
        menuText.addText("Choisissez une partie existante : ", "Partie", 200, 300);
    }
    
    // DO WE USE THIS???
    protected ArrayList<Letter> getLettres() {
        return lettres;
    }
    
    public Env getEnv() {
        return env;
    }
    
    

    /**
     * Gère le menu principal
     *
     */
    public void execute() {
        
        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        this.env.setDisplayStr("Au revoir !", 300, 30);
        
        // ???Commence une nouveau partie - NOT SUPPOSED TO BE HERE ANYMORE but in menuJeu()
        //joue(new Partie());
        
        env.exit();
    }


    // fourni
    private String getNomJoueur() {
        String nomJoueur = "";
        menuText.getText("NomJoueur").display();
        nomJoueur = menuText.getText("NomJoueur").lire(true);
        menuText.getText("NomJoueur").clean();
        return nomJoueur;
    }
    
    // Ask for, read and return niveau chosen by user
    private int getNiveau() {
        String niveauString = "";
        int niveau = 0;
        int validNiveau = 0;
        menuText.getText("Niveau").display();
        niveauString = menuText.getText("Niveau").lire(true);
        menuText.getText("Niveau").clean();
        
        niveau = Integer.parseInt(niveauString);
        
        if (niveau < 6 && niveau > 0)
            validNiveau = niveau;
            
        

        /*switch (niveauString) {
            case "1":
                niveau = 1;
                break;
            case "2":
                niveau = 2;
                break;
            case "3":
                niveau = 3;
                break;
            case "4":
                niveau = 4;
                break;
            case "5":
                niveau = 5;
                break;
            default:
                break;
            
        }*/
        return validNiveau;
    }
    

    
    // fourni, à compléter
    private MENU_VAL menuJeu() {

        MENU_VAL playTheGame;
        MENU_VAL choix = MENU_VAL.MENU_CONTINUE; // Pour choix du niveau
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);
            // affiche menu
            menuText.getText("Question").display();
            menuText.getText("Jeu1").display();
            menuText.getText("Jeu2").display();
            menuText.getText("Jeu3").display();
            menuText.getText("Jeu4").display();
            
            // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
            int touche = 0;
            while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4)) {
                touche = env.getKey();
                env.advanceOneFrame();
            }

            // nettoie l'environnement du texte
            menuText.getText("Question").clean();
            menuText.getText("Jeu1").clean();
            menuText.getText("Jeu2").clean();
            menuText.getText("Jeu3").clean();
            menuText.getText("Jeu4").clean();

            // restaure la room du jeu
            env.setRoom(mainRoom);

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                    // .......... dico.******
                    String motNouveau = "";
                    int niveau = getNiveau();
                    if (niveau != 0) {
                        motNouveau = dico.getMotDepuisListeNiveaux(niveau);
                        
                        // crée un nouvelle partie
                        partie = new Partie("2018-09-7", motNouveau, niveau); //???
                        
                        
                        
                        menuText.getText("Jeu4").clean();
                        
                        lettres = new ArrayList<Letter>();
                        
                        // Création et ajout des lettres aléatoires dans la salle
                        for (int i = 0; i < motNouveau.length(); i++) {
                            double x = Math.random() * 100;
                            double z = Math.random() * 100;
                            Letter l = new Letter(env, mainRoom, motNouveau.charAt(i), x, z);
                            lettres.add(l);
                        }

                        for (Letter l : lettres) {
                            env.addObject(l); // Ajoute chaque lettre à l'environnement
                        }
                                                
                        // joue
                        joue(partie);
                        
                        // enregistre la partie dans le profil --> enregistre le profil
                        profil.enregistrePartie(partie);
                        
                        playTheGame = MENU_VAL.MENU_JOUE;
                    } else {
                        playTheGame = MENU_VAL.MENU_SORTIE;
                    }
                    
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
                case Keyboard.KEY_2: // charge une partie existante
                    if (profil.getPartiePasFinie() != null) {
                        partie = profil.getPartiePasFinie();
                        joue(partie);
                    } else {
                        playTheGame = MENU_VAL.MENU_CONTINUE;
                    }
                    //partie = new Partie("2018-09-7", "test", 1); //XXXXXXXXX ???
                    // Recupère le mot de la partie existante - HVORFOR???
                    // .......... 
                    //String motExistant = partie.getMot();
                    // joue
                    //joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    //profil.enregistrePartie(partie);
                    //playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 3 : Sortie de ce jeu
                // -----------------------------------------                
                case Keyboard.KEY_3:
                    playTheGame = MENU_VAL.MENU_CONTINUE;
                    break;

                // -----------------------------------------
                // Touche 4 : Quitter le jeu
                // -----------------------------------------                
                case Keyboard.KEY_4:
                    playTheGame = MENU_VAL.MENU_SORTIE;
            }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        return playTheGame;
    }

    private MENU_VAL menuPrincipal() {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;

        // restaure la room du menu
        env.setRoom(menuRoom);

        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("Principal3").display();
               
        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                // demande le nom du joueur existant
                nomJoueur = getNomJoueur();
                // charge le profil de ce joueur si possible
                if (profil.charge(nomJoueur)) {
                    choix = menuJeu();
                } else {
                    choix = MENU_VAL.MENU_SORTIE;//CONTINUE;
                }
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                // crée un profil avec le nom d'un nouveau joueur
                profil = new Profil(nomJoueur);
                choix = menuJeu();
                break;

            // -------------------------------------
            // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_3:
                choix = MENU_VAL.MENU_SORTIE;
        }
        return choix;
    }

    public void joue(Partie partie) {

        // Instancie un Tux
        tux = new Tux(env, mainRoom);
        env.addObject(tux);

        //letter = new Letter('a', 10, 10); ?? DELETE?
        
        //String mot = dico.getMotDepuisListeNiveaux(2);
       
        
        
        // env.addObject(letter); - REPLACED BY THE LINES ABOVE ???
        


                        

        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);

        // Boucle de jeu
        //Boolean finished;
        //finished = false;
        
        // Ajoute le personnage Tux à l'environnement
        env.addObject(tux);
        
        
        while (!finished) {

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if ((env.getKey() == 1) || lettres.isEmpty()) {
                finished = true; // Sort de la boucle si la touche Escape est pressée
            }
            
            // Sauvegarde la position actuelle de Tux
            double tuxXBeforeMove = tux.getX();
            double tuxZBeforeMove = tux.getZ();

            // Contrôles des déplacements de Tux (gauche, droite, ...)
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

            // Ici, on applique les regles
            appliqueRegles(partie);

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }
    
    // Méthode appelée au démarrage d'une nouvelle partie
    protected abstract void démarrePartie(Partie partie);

    // Méthode pour appliquer les règles du jeu
    protected abstract boolean appliqueRegles(Partie partie);

    // Méthode appelée à la fin de la partie
    protected abstract void terminePartie(Partie partie);
    
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
