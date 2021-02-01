package pkg_Componnants.pkg_Entities;

import pkg_Componnants.pkg_Rooms.Room;
import pkg_Componnants.pkg_Items.Item;
/**
 * Write a description of class MovingCharacter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MovingCharacter extends NPC
{
    private Room aCurrentRoom;
    
    /**
     * Constructeur, cree un nouveau MovingCharacter.
     * @param pNom Une String, Le nom du NPC
     * @param pImage Une String, L'image du NPC
     * @param pDialogue Un tableau de String, Les phrases que prononce le NPC
     * @param pRace Une String, La race du NPC
     * @param pJob Une String, La classe du NPC
     * @param pItem L'item que le NPC porte
     * @param pRoom La Room dans laquelle il est cree
     */
    public MovingCharacter(final String pNom, final String pImage,
                           final String[] pDialogue,
                           final String pRace, final String pJob, 
                           final Item pItem, final Room pRoom)
    {
        super(pNom, pImage, pDialogue, pRace, pJob, pItem);
        this.aCurrentRoom = pRoom;
    }
    
    /**
     * Permet de deplacer le MovingCharacter.
     * @param pDirection Une String, la direction dans laquelle il se deplace
     * @return Un boolean true s'il s'est deplace, false sinon
     */
    public boolean move(final String pDirection)
    {
       Room vNextRoom = this.aCurrentRoom.getExit(pDirection);
        if (vNextRoom == null)
           return false;
       else 
       {
           this.aCurrentRoom = vNextRoom;
           return true;
       }
    }
    
    /**
     * retourne la CurrentRoom du NPC
     * @return Une Room
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }
}
