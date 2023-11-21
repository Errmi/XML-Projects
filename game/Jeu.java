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
    public Jeu(){
        
            // Crée un nouvel environnement
            env = new Env();
           


            // Instancie une Room
            room = new Room();
            
            tux = new Tux(env,room);
            

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
