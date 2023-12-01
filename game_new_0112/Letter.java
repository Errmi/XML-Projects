/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.Env;
import env3d.advanced.EnvNode;

/**
 *
 * @author engesvis
 */
public class Letter extends EnvNode {
    private char letter;
    private Room room;
    private Env env;
    
    
    public Letter(Env env, Room room, char l, double x, double z) {
        this.room = room;
        this.env = env;
        letter = l;
        
        String letterTexture = "models/letter/" + letter + ".png";
        
        setScale(4.0);
        setX(x);
        setY(4.0);
        setZ(z);
        //setTexture(letterTexture);
        setTexture(letterTexture);
        setModel("models/letter/cube.obj");
    }
}