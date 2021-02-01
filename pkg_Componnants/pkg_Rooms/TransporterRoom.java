package pkg_Componnants.pkg_Rooms;

import java.util.ArrayList;

import pkg_Componnants.Son;
/**
 * TransporterRoom est une Room qui teleporte le Player aleatoirement sur la map
 * lorsqu'on essaye d'en sortir.
 *
 * @author Baptiste ADAM
 * @version v2 - 30/05/2017
 */
public class TransporterRoom extends Room
{
   private RoomRandomizer aRoomRandomizer;
    
   /**
    * Constructeur, cree une nouvelle TransporterRoom
    * @param pDescription La description de la TransporterRoom
    * @param pImage L'adresse de l'image de la TransporterRoom
    * @param pTypeRoom Le type de la Room
    * @param pAllRooms L'arrayList des Rooms ou l'on peux se teleporter
    */
   public TransporterRoom(final String pDescription, final String pImage, final int pTypeRoom, final ArrayList<Room> pAllRooms)
   {
       super(pDescription, pImage, pTypeRoom);
       this.aRoomRandomizer = new RoomRandomizer(pAllRooms);
   }
   
   /**
    * Retourne une Room aléatoirement parmis toutes celles disponibles.
    * @param pDirection La direction dans laquelle on va (ne sers a rien)
    * @return Une Room aleatoire.
    */
   @Override
   public Room getExit(final String pDirection)
   {
       Son.JouerLeSon("Sons/Beamer.wav");
       return this.aRoomRandomizer.getRandomRoom();
   }
   
   /**
    * Retourne aRoomRandomizer.
    * @return Le RoomRandomizer
    */
   public RoomRandomizer getRoomRandomizer()
   {
       return this.aRoomRandomizer;
   }
}
