/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author engesvis
 */
public class Dico {
    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String cheminFichierDico;
    
    public Dico(String cheminFichierDico) throws SAXException, IOException {
        this.cheminFichierDico = cheminFichierDico;
        
        listeNiveau1 = new ArrayList<String>();
        listeNiveau2 = new ArrayList<String>();
        listeNiveau3 = new ArrayList<String>();
        listeNiveau4 = new ArrayList<String>();
        listeNiveau5 = new ArrayList<String>();

        lireDictionnaireDOM(cheminFichierDico, "dico.xml");
    }
    
    public String getMotDepuisListeNiveaux(int niveau) {
        String mot = "";
        switch (vérifieNiveau(niveau)) {
            case 1:
                mot = getMotDepuisListe(listeNiveau1);
                break;
            case 2:
                mot = getMotDepuisListe(listeNiveau2);
                break;
            case 3:
                mot = getMotDepuisListe(listeNiveau3);
                break;
            case 4:
                mot = getMotDepuisListe(listeNiveau4);
                break;
            case 5:
                mot = getMotDepuisListe(listeNiveau5);
                break;
            default: 
                break;
        }
        return mot;
    }
    
    public void ajouteMotADico(int niveau, String mot) {
        switch (vérifieNiveau(niveau)) {
            case 1:
                listeNiveau1.add(mot);
                break;
            case 2:
                listeNiveau2.add(mot);
                break;
            case 3:
                listeNiveau3.add(mot);
                break;
            case 4:
                listeNiveau4.add(mot);
                break;
            case 5:
                listeNiveau5.add(mot);
                break;
            default: 
                break;
        }
    }
    
    public String getCheminFichierDico() {
        return cheminFichierDico;
    }
    
    private int vérifieNiveau(int niveau) {
        int niv = 0;
        if (niveau > 0 && niveau < 6) {
            niv = niveau;
        }
        return niv;
    }
    
    private String getMotDepuisListe(ArrayList<String> list) {
        String mot = "tux";     // Mot par default
        
        if (!list.isEmpty()) {  // If the list is not empty, get mot from list
            int randomNr = (int) (Math.random() * list.size());     // Random nr between 0-4
            mot = list.get(randomNr);
        } 
            
        return mot;
    }

    private void lireDictionnaireDOM(String path, String filename) throws SAXException, IOException {
        DOMParser parser = new DOMParser();
        parser.parse(path + filename);
        Document doc = parser.getDocument();
        
        NodeList motsNL = doc.getElementsByTagName("tux:mot");
        int nbMots = motsNL.getLength();
        System.out.println(nbMots);
        
        for (int i = 0; i < nbMots; i++) {
            Element currentMot = (Element) motsNL.item(i);
            int niveau = Integer.parseInt(currentMot.getAttribute("niveau"));
            niveau = vérifieNiveau(niveau);
            Node motNd = motsNL.item(i);
            String mot = motNd.getTextContent();
            
            ajouteMotADico(niveau, mot);
        }
    }
    
}
