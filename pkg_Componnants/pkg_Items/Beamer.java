package pkg_Componnants.pkg_Items;

import pkg_Componnants.pkg_Rooms.Room;
/**
 * Un Beamer est un item capable de teleporter le joueur dans la salle dans laquelle il a ete charge auparavant.
 *
 * @author Baptiste Adam
 * @version v1 - 5/23/2017
 */
public class Beamer extends Item
{
    private Room aChargedRoom;
    
    /**
     * constructeur, cree un nouveau beamer (non chargé)
     */
    public Beamer()
    {
     super("A mysterious shining orb is laying among the rubble.", 0.5, "orb");
     this.aChargedRoom =null;
    }
    
    /**
     * permet d'obtenir aCargedRoom, 
     * si elle est null, c'est que le beamer n'est pas charge.
     * @return la Room dans laquelle le beamer a ete charge. 
     */
    public Room getChargedRoom()
    {
        return this.aChargedRoom;
    }
    
    /**
     * permet de modifier la valeur de aChargedRoom
     * @param pRoom La Room dans laquelle le beamer est charge.
     */
    public void setChargedRoom(final Room pRoom)
    {
       this.aChargedRoom = pRoom;   
    }
}
