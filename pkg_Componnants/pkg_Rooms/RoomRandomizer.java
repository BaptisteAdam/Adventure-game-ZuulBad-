package pkg_Componnants.pkg_Rooms;

import java.util.ArrayList;

/**
 * Permet de selectionner une Room aleatoirement dans une ArrayList de Room.
 *
 * @author Baptiste ADAM
 * @version v2 - 30/05/2017
 */
public class RoomRandomizer
{
   private ArrayList<Room> aAllRooms;
   private Integer aIndiceRoom = null;
   
   /**
    * Constructeur, cree un nouveau RoomRandomizer.
    * @param pAllRooms Une ArrayList de Room
    */
   public RoomRandomizer( final ArrayList<Room> pAllRooms )
   {
       this.aAllRooms = pAllRooms;
   }
   
   /**
    * Permet de set IndiceRoom sur null ou 0.
    * @param pB Un booleen : true met IndiceRoom a 0 (desactive le random)
    */
   public void setIndiceRoom(final boolean pB)
   {
       if(pB)
           this.aIndiceRoom = 0;
       else
           this.aIndiceRoom = null;
   }
   
   /**
    * Renvois une Room aleatoirement.
    * @return Une Room de l'ArrayList
    */
   public Room getRandomRoom()
   {
       if(this.aIndiceRoom == null)
       {
           int aNbRand = (int)Math.floor(Math.random()*this.aAllRooms.size());
           return this.aAllRooms.get(aNbRand);
       }
        else
           return this.aAllRooms.get(this.aIndiceRoom);
   }
    
}
