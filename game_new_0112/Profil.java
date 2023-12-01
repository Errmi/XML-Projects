/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author belguitr
 */
public class Profil {
    private String nom;
    private String avatar;          // Might not be used?
    private String anniversaire;    // Might not be used?
    private ArrayList<Partie> parties;
    
    public Profil(String nom) {
        if (nom.length() < 50) { // Input validation for name length
            this.nom = nom; 
        }
    }
    
    public Profil() {}
    
    // Check if the name passed as an argument is this profil's name
    public boolean charge(String nom) {
        boolean match = false;
        
        if (this.nom.equals(nom)) {
            match = true;
        }
        return match;
    }
    
    public void enregistrePartie(Partie partie) {
        parties.add(partie);
    }
    
    public Partie getPartiePasFinie() {
        Partie partiePasFinie = null;
        int i = 0;
        while (parties.size() > i && parties.get(i).partieFinie()) {
            i++;
        }
        
        if (i < parties.size()) {
            partiePasFinie = parties.get(i);
        }
        return partiePasFinie;
    }
}
