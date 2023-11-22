/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import game.Dico;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author engesvis
 */
public class TestDico {
    
    public static void main(String[] args) throws SAXException, IOException {

        /*// Test self iniated words
        Dico dico = new Dico("/random/folder");
        
        String[] mots1 = new String[] {"mot", "fin", "but", "lait", "ami"};
        String[] mots2 = new String[] {"forme", "texte", "jambon", "pomme", "titre"};
        //String[] mots3 = new String[] {"vanille", "fichier", "argument", "unique", "ouvrier"};
        String[] mots4 = new String[] {"information", "philosophe", "correspondre", "travaille", "expression"};
        String[] mots5 = new String[] {"impitoyable", "obligatoirement", "reconnaissance", "realisateur", "adolescence"};
        
        for (String s : mots1) {
            dico.ajouteMotADico(1, s);
        }
        
        for (String s : mots2) {
            dico.ajouteMotADico(2, s);
        }
        
        for (String s : mots4) {
            dico.ajouteMotADico(4, s);
        }
        
        for (String s : mots5) {
            dico.ajouteMotADico(5, s);
        }
        
        System.out.println("Mot niveau 1: " + dico.getMotDepuisListeNiveaux(1));
        System.out.println("Mot niveau 1: " + dico.getMotDepuisListeNiveaux(1));
        System.out.println("Mot niveau 1: " + dico.getMotDepuisListeNiveaux(1));
        System.out.println("Mot niveau 2: " + dico.getMotDepuisListeNiveaux(2));
        System.out.println("Mot niveau 3 (vide - should print 'tux'): " + dico.getMotDepuisListeNiveaux(3));
        System.out.println("Mot niveau 4: " + dico.getMotDepuisListeNiveaux(4));
        System.out.println("Mot niveau 5: " + dico.getMotDepuisListeNiveaux(5));
        */
        
        // Test DOM parser - read dictrionnary from xml file
        Dico dico = new Dico("src/xml/xml/");
        
        System.out.println("Mot niveau 1: " + dico.getMotDepuisListeNiveaux(1));
        System.out.println("Mot niveau 1: " + dico.getMotDepuisListeNiveaux(1));
        System.out.println("Mot niveau 1: " + dico.getMotDepuisListeNiveaux(1));
        System.out.println("Mot niveau 2: " + dico.getMotDepuisListeNiveaux(2));
        System.out.println("Mot niveau 3: " + dico.getMotDepuisListeNiveaux(3));
        System.out.println("Mot niveau 4: " + dico.getMotDepuisListeNiveaux(4));
        System.out.println("Mot niveau 5: " + dico.getMotDepuisListeNiveaux(5));
        
    }
}
