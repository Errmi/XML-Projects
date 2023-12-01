/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author belguitr
 */
public class Partie {
    private final String date;
    private final String mot;
    private final int niveau;
    private int trouvé;         // Pourcentage (arrondi) de lettres trouvées
    private int temps;          // Secondes qu'a duré la partie
    
    public Partie(String date, String mot, int niveau) {
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }

    public Partie() {
        date = "2023-12-1";
        mot = "default";
        niveau = 1;
    }
    
    public void setTrouve(int nbLettresRestantes) {
        // Calculer la pourcant
        int pourcent = (nbLettresRestantes / mot.length()) * 100; 
        trouvé = pourcent;
    }
    
    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    public int getniveau() {
        return niveau;
    }
    
    public String getMot() {
        return mot;
    }
    
    public boolean partieFinie() {
        boolean finie = true;
        
        if (trouvé < 100) 
            finie = false;
        
        return finie;
    }
    
    public int getTrouvé() {
        return trouvé;
    }

    @Override
    public String toString(){
        return "";
    }
    
}
