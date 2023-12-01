/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author belguitr
 */
public class LanceurDeJeu {
    public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException {
        // Declare un Jeu
        Jeu jeu;
        jeu = new JeuDevineLeMotOrdre();
        jeu.execute();
        //Instancie un nouveau jeu
        //?!!?;
        //Execute le jeu
        //?!!?;
    }
}
