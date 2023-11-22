/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;

/**
 *
 * @author belguitr
 */
public class Jeu {
    private Env env ;
    private Room room;
    private Profil profil ;
    private Tux tux;
    private ArrayList<Letter> lettres;
    private Dico dico;
    
    public Jeu(){
        
            // Crée un nouvel environnement
            env = new Env();

            // Instancie une Room
            room = new Room();
            
            tux = new Tux(env,room);

            // Instancie une Dico
            dico = new Dico("/random/folder");
            
            String[] mots1 = new String[] {"mot", "fin", "but", "lait", "ami"};
            String[] mots2 = new String[] {"forme", "texte", "jambon", "pomme", "titre"};
            String[] mots3 = new String[] {"vanille", "fichier", "argument", "unique", "ouvrier"};
            String[] mots4 = new String[] {"information", "philosophe", "correspondre", "travaille", "expression"};
            String[] mots5 = new String[] {"impitoyable", "obligatoirement", "reconnaissance", "realisateur", "adolescence"};
            
            for (String s : mots1) {
                dico.ajouteMotADico(1, s);
            }
            
            for (String s : mots2) {
                dico.ajouteMotADico(2, s);
            }
            
            for (String s : mots3) {
                dico.ajouteMotADico(3, s);
            }
            
            for (String s : mots4) {
                dico.ajouteMotADico(4, s);
            }
            
            for (String s : mots5) {
                dico.ajouteMotADico(5, s);
            }

            // Règle la camera
            env.setCameraXYZ(50, 60, 175);
            env.setCameraPitch(-20);

            // Désactive les contrôles par défaut
            env.setDefaultControl(false);

            // Instancie un profil par défaut
            profil = new Profil();
            
    }
    
    public void execute() {

        // pour l'instant, nous nous contentons d'appeler la méthode joue comme cela
        // et nous créons une partie vide, juste pour que cela fonctionne
        joue(new Partie());
         
        // Détruit l'environnement et provoque la sortie du programme 
        env.exit();
    }
    
         
         
    public void joue(Partie partie) {
 
        // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(room);
 
        // Instancie un Tux
        //tux = //?!!?;
        //env.addObject(//?!!?;);

        // Instancie des Lettres
        lettres = new ArrayList<Letter>();
        String mot = dico.getMotDepuisListeNiveaux(1);
        
        for (int i = 0; i < mot.length(); i++) {
            double x = Math.random() * 100;
            double z = Math.random() * 100;
            Letter l = new Letter(env, room, mot.charAt(i), x, z);
            lettres.add(l);
        }

        for (Letter l : lettres) {
            env.addObject(l);
        }
         
        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);
         
        // Boucle de jeu
        Boolean finished;
        finished = false;
        env.addObject(tux);
        
        while (!finished) {
 
            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }
 
            // Contrôles des déplacements de Tux (gauche, droite, ...)
            // ... (sera complété plus tard) ...
            tux.déplace();
 
            // Ici, on applique les regles
            appliqueRegles(partie);
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
 
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);
 
    }    
    protected void démarrePartie(Partie partie){
    }
    protected void appliqueRegles(Partie partie){
    }
    protected void terminePartie(Partie partie){
    }
    
    
    
    
}
