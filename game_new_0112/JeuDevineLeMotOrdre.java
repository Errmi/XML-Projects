package game;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class JeuDevineLeMotOrdre extends Jeu {
    private int nbLettresRestantes;
    private Chronometre chrono;
    private EnvText timerText;
    private EnvText motText;

    public JeuDevineLeMotOrdre() throws SAXException, IOException, ParserConfigurationException {
        super();
        chrono = new Chronometre(15);
        timerText = new EnvText(super.getEnv(), "", 20, 50);
        motText = new EnvText(getEnv(), "", 50, 100);
    }

    // Override the execute method to start the timer before calling the parent execute method
    /*@Override
    public void execute() {
        chrono.start(); // Start the timer
        super.execute(); // Call the parent execute method
        chrono.stop(); // Stop the timer when the game execution is finished
    }

    // Override the joue method to check the timer and update game logic accordingly
    @Override
    public void joue(Partie partie) {
                if (!chrono.remainsTime()) {
                    terminePartie(partie);
        }
        super.joue(partie); // Call the parent joue method
        
    }*/

    // Méthode pour vérifier si Tux a trouvé une lettre
    private boolean tuxTrouveLettre() {
        ArrayList<Letter> lettres = getLettres();
        for (Letter lettre : lettres) {
            if (collision(lettre)) {
                return true;
            }
        }
        return false;
    }

    // Méthode pour obtenir le nombre de lettres restantes
    private int getNBLettersRestantes() {
        // Ajoutez la logique pour calculer le nombre de lettres restantes
        ArrayList<Letter> lettres = getLettres();
        return lettres.size();
    }

    // Méthode pour obtenir le temps écoulé depuis le chronomètre
    private int getTemps() {
        // Ajoutez la logique pour obtenir le temps écoulé depuis le chronomètre
        return (int) chrono.getSeconds();
    }
    
    // Méthode appelée au démarrage d'une nouvelle partie
    protected void démarrePartie(Partie partie) {
        chrono.start();
        nbLettresRestantes = getLettres().size();
    }
    
        // Méthode pour appliquer les règles du jeu
    protected boolean appliqueRegles(Partie partie) {
        boolean res = true;

        if (chrono.remainsTime()) {
            int timeRemaining = chrono.getSeconds();
            String motDisplay = "Trouve ce mot : " + partie.getMot();
            motText.modifyTextAndDisplay(motDisplay); 
            
            if (timeRemaining <= 10) {
                motText.clean();
            }

            if (timeRemaining >= 0) {
                // Met à jour l'affichage du chronomètre
                String timerDisplay = "Time: " + timeRemaining;
                timerText.modifyTextAndDisplay(timerDisplay);  
            }

            if (tuxTrouveLettre()) {
                if (nbLettresRestantes == 0) {
                    chrono.stop();
                    finished = true; // Défini finished à true pour sortir de la boucle du jeu
                }
            }
            
        } else {
            chrono.stop();
            finished = true; // Défini finished à true pour sortir de la boucle du jeu lorsque le temps est écoulé
        }

        return res;
    }

    // Méthode appelée à la fin de la partie
    protected void terminePartie(Partie partie) {
        chrono.stop();
        partie.setTrouve(nbLettresRestantes);
        partie.setTemps(chrono.getSeconds());
        System.out.println("PARTIE TERMINEE");
    }
}
