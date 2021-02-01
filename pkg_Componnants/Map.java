package pkg_Componnants;

import java.util.ArrayList;

import pkg_Componnants.pkg_Items.Item;
import pkg_Componnants.pkg_Rooms.Room;
import pkg_Componnants.pkg_Rooms.TransporterRoom;
import pkg_Componnants.pkg_Entities.NPC;
import pkg_Componnants.pkg_Entities.MovingCharacter;
/**
 * Cette classe crée une Map pou le jeu. 
 * Celle-ci est faite aleatoirement avec un nombre 
 * maximum de Room limitant la taille. Une type est attribué a chaque Room, 
 * il represente les differentes actions qu'il s'y passera.
 * De plus, il est assuré qu'une Room de type 5 est ete cree puisque c'est 
 * celle qui comporte la Statuette, est donc, le seul moyen de gagner.
 *
 * @author Baptiste Adam
 * @version v1 - 13/05/2017
 */
public class Map
{
   private static int numRoom = 1;
   private static final double MAX_ROOM = 30; //nombre minimum 5 
   
   private ArrayList<Room> aAllRooms ;
   private TransporterRoom aTransporterRoom;

   /**
    * Constructeur, cree une nouvelle Map
    */
   public Map()
   {
       this.aAllRooms = new ArrayList<Room>();
       this.createMap();
   }
   
   /**
    * S'assure que l'ArrayList est bien vide avant d'y ajouter les nouvelles Rooms.
    * De plus, recommence l'action autant de fois que necessaire pour qu'il y ais une Room de Type 5,
    * c'est a dire, pour que le jeu sois finissable.
    */
   private void createMap()
   {
       int nbType5 = 0;
       int nbType9 = 0;
      // while(nbType5 != 1 && nbType9 != 1)
       {
           nbType5 = 0;
           nbType9 = 0;
           this.clearRooms();
           this.createRooms();
           for(Room vRoom : this.aAllRooms)
           {
              if (vRoom.getType() == 5)
                 nbType5++;
              else if (vRoom.getType() == 9)
                 nbType9++;
           }
       }
   }
   
   /**
    * Efface toutes les Rooms de l'ArrayList
    * Reinitialise le Compteur de Room.
    */
   private void clearRooms()
   {
       this.aAllRooms.clear();
       this.numRoom = 1;
   }
   
   /**
    * Initialise la premiere Room (la porte du donjon) et sa sortie vers la premiere Room vers le nord.
    * Puis, genere aleatoirement toutes les autres salles}.
    * S'assure que les sorties ajoutees a chaque salle n'efface pas le chemin permettant de retourner dans la salle precedente.
    */
   private void createRooms()
   {
       //génère l'entrée du donjon
       Room vOutside = new Room( "in front of a massive wooden door made of dark ebony wood", "Images/Wooden door.jpg", 0);
       this.aAllRooms.add( vOutside );
       
       Item vBranch = new Item("You see a tree branch on the ground.", 0.6, "branch");
       this.aAllRooms.get(0).addItem(vBranch);
       
       //génère la premiere salle du donjon, elle se trouve forcément au nord de l'entrée et est sans retour 
       this.aAllRooms.add( this.RoomType6("south", vOutside) );
       vOutside.setExit("north", this.aAllRooms.get(1));

       //génère le reste des salles aléatoirement
       String vDirectionPrecedente = "south"; //la seule sortie de la derniere salle déjà créée. Ne doit pas sercir pour une autre sortie.
       for(int i=1 ; i<Math.floor(this.MAX_ROOM/3) ; i++)
       {
           //Générer une direction pour sortir de la salle actuelle ( aAllRooms.get(i) )
           String vDirection = this.genererDirection();
           while(vDirection.equals(vDirectionPrecedente))
                vDirection = this.genererDirection();
           //générer une Room qui permet de traverser dans le sens inverse et de revenir dans la salle actuelle.
           Room vRoom = this.genererRoom(this.invDirection(vDirection), this.aAllRooms.get(i) );
           if(this.aAllRooms.get(i).getType()!=5)
               this.aAllRooms.get(i).setExit(vDirection, vRoom); 
           //mémoriser la sortie "retour sur ses pas" de la nouvelle salle pour eviter de mettre une autre sortie au même endroit.
           vDirectionPrecedente = this.invDirection(vDirection);
           this.aAllRooms.add(vRoom);
       }
       
       for (double z = Math.floor(this.MAX_ROOM/3) ; z < this.MAX_ROOM-2 ; z++)
       {
           int aNbRand = (int)Math.floor(Math.random()*(z-1)+1);
           this.createRoomRamification(aNbRand);
       }
       
       int aNbRand = (int)Math.floor(Math.random()*(this.MAX_ROOM-2)+1);
       this.createTransporterRoom(aNbRand);
    }   
    
   /**
    * Cree des ramifications de Rooms sur les Rooms deja existantes.
    * Si la Room sur laquelle la ramification veut etre cree est de type 5, le raccord n'est pas fait..
    * (la type 5 étant la fin, il est inutile d'y avoir un acces vers une nouvelle Room)
    * @param pIndice L'indice de la Room sur laquelle on cree une ramification.
    */
    private void createRoomRamification(final int pIndice)
   {
       String[] vTabDirection = {"north", "south", "east", "west", "up", "down"};
       ArrayList<String> vTabDirectionRoom = new ArrayList<String>();
       for(int i=0 ; i<vTabDirection.length ;i++)
       {
           if (this.aAllRooms.get(pIndice).getExit(vTabDirection[i])!=null)
               vTabDirectionRoom.add(vTabDirection[i]);
       }
       
       String vDirection = this.genererDirection();;
       boolean ok = false;
       while(ok == false)
       {
           ok = true;
           vDirection = this.genererDirection();
           for(String vExits : vTabDirectionRoom)
               if(vExits.equals(vDirection) )
                  ok = false;
       }
       
       Room vRoom2 = this.genererRoom(this.invDirection(vDirection), this.aAllRooms.get(pIndice) );
       if(this.aAllRooms.get(pIndice).getType()!=5 )
           this.aAllRooms.get(pIndice).setExit(vDirection, vRoom2); 
       this.aAllRooms.add( vRoom2);
   }
   
   /**
    * Cree une TransporterRoom sur les Rooms deja existantes.
    * Si la Room sur laquelle la TransporterRoom veut etre cree est de type 5, le raccord n'est pas fait..
    * (la type 5 étant la fin, il est inutile d'y avoir un acces vers une nouvelle Room)
    * @param pIndice L'indice de la Room sur laquelle on cree la TransporterRoom.
    */
   public void createTransporterRoom(final int pIndice)
   {
       String[] vTabDirection = {"north", "south", "east", "west", "up", "down"};
       ArrayList<String> vTabDirectionRoom = new ArrayList<String>();
       for(int i=0 ; i<vTabDirection.length ;i++)
       {
           if (aAllRooms.get(pIndice).getExit(vTabDirection[i])!=null)
               vTabDirectionRoom.add(vTabDirection[i]);
       }
       
       String vDirection = this.genererDirection();;
       boolean ok = false;
       while(ok == false)
       {
           ok = true;
           vDirection = this.genererDirection();
           for(String vExits : vTabDirectionRoom)
               if(vExits.equals(vDirection) )
                  ok = false;
       }
       
       this.aTransporterRoom = RoomType8(this.invDirection(vDirection), this.aAllRooms.get(pIndice) );
       if(this.aAllRooms.get(pIndice).getType()!=5 )
           this.aAllRooms.get(pIndice).setExit(vDirection, this.aTransporterRoom); 
   }
   
   /**
    * Accesseur, permet d'obtenir la Room dont l'indice est passe en parametre.
    * @param pInt L'incide de la Room dans l'ArrayList.
    * @return La Room recherchee.
    */
   public Room get(final int pInt)
   {
       return this.aAllRooms.get(pInt);
   }
   
   /**
    * Retourne la TransporterRoom.
    * @return Une TransporterRoom
    */
   public TransporterRoom getTransporterRoom()
   {
       return this.aTransporterRoom;
   }
   
   /**
    * retourne le nombre de Room genere sous forme de String.
    * @return Une String
    */
   public String getNumRoom()
   {
        String vNum ="";
        return vNum + this.numRoom;
   }
   
   /**
    * retourne le MovingCharacter, quelque sois la Room dans laquelle il se trouve.
    * retourne null si il n'en existe plus.
    * @return Un MovingCharacter002
    */
   public MovingCharacter getMovingNPC()
   {
       for(Room room : this.aAllRooms)
           if(room.getMovingNPC()!=null)
               return room.getMovingNPC();
       if(this.aTransporterRoom.getMovingNPC()!=null)
               return aTransporterRoom.getMovingNPC();
       return null;
   }
   
   
   //Génération des directions et des Rooms
   /**
    * Retourne la direction opposee a celle passee en parametre.
    * @param pDirection Une direction.
    * @return La direction opposee.
    */
   private String invDirection( final String pDirection)
   {
       if(pDirection.equals("north"))
           return "south";
       else if(pDirection.equals("south"))
           return "north";
       else if(pDirection.equals("west"))
           return "east";
       else if(pDirection.equals("east"))
           return "west";
       else if(pDirection.equals("up"))
           return "down";
       else
           return "up";
   }
   
   /**
    * genere une direction aleatoirement.
    * @return Une direction
    */
   public String genererDirection()
   {
       double aNbRand = Math.round(Math.random()*6);
       if(aNbRand == 1.0)
           return "north";
       else if(aNbRand == 2.0)
           return "south";
       else if(aNbRand == 3.0)
           return "west";
       else if(aNbRand == 4.0)
           return "east";
       else if(aNbRand == 5.0)
           return "down";
       else 
           return "up";
   }
   
   /**
    * Genere une Room aleatoirement entre les diferents types disponibles.
    * La seule sortie de celle ci est la sortie pour "revenir sur ses pas".
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room
    */
   private Room genererRoom(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {
       long aNbRand = Math.round(Math.random()*100);
       if(0.0<=aNbRand && aNbRand<10.0)//cookie-steak-mushroom
           return this.RoomType1(pStringRoomPrecedent, pRoomPrecedente);
       
       else if(10.0<=aNbRand && aNbRand<12.0)//beamer
           return this.RoomType7(pStringRoomPrecedent, pRoomPrecedente);
       
       else if(12.0<=aNbRand && aNbRand<17.0)//tresorerie
           return this.RoomType10(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (17.0<=aNbRand && aNbRand<24.0)//aventurier mort a looter
           return this.RoomType11(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (24.0<=aNbRand && aNbRand<31.0)//mirroir
           return this.RoomType3(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (31.0<=aNbRand && aNbRand<39.0)//fontaine
           return this.RoomType14(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (39.0<=aNbRand && aNbRand<46.0)//fleche dans le dos
           return this.RoomType13(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (46.0<=aNbRand && aNbRand<53.0)//trappe
           return this.RoomType15(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (53.0<=aNbRand && aNbRand<60.0)//transformation poulet
           return this.RoomType19(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (60.0<=aNbRand && aNbRand<65.0)//magiciens
           return this.RoomType2(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (65.0<=aNbRand && aNbRand<70.0)//aventurier fou
           return this.RoomType4(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (70.0<=aNbRand && aNbRand<72.0)//nid du dragon
           return this.RoomType9(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (72.0<=aNbRand && aNbRand<77.0)//horde de zombies
           return this.RoomType12(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (77.0<=aNbRand && aNbRand<82.0)//crapaud geant
           return this.RoomType16(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (82.0<=aNbRand && aNbRand<87.0)//spectre
           return this.RoomType17(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (87.0<=aNbRand && aNbRand<92.0)//poulet-monté
           return this.RoomType18(pStringRoomPrecedent, pRoomPrecedente);
       
       else if (92.0<=aNbRand && aNbRand<97.0)//dovah-poulet
           return this.RoomType20(pStringRoomPrecedent, pRoomPrecedente);
       
       else //statuette
           return this.RoomType5(pStringRoomPrecedent, pRoomPrecedente);
   }

   
   //Les différents types de Rooms
   /**
    * Type de Room numero 1.
    * Un cookie est pose sur une table.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 1.
    */
   private Room RoomType1(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {
       Room vRoom = new Room("in a corridor, the seiling is far ahead of you", "Images/type 1.png", 1);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       Item vItem;
       int aNbRand = (int)Math.floor(Math.random()*3);
       int aNbRand2 = (int)Math.round(Math.random()*100);
       if(aNbRand == 1)
            vItem = new Item("A cookie is placed in an obvious way on a table.", 0.02, "cookie");
       else if(aNbRand == 2)
            vItem = new Item("A huge steak is steal bleeding an a table.", 1.5, "steak");
       else
            vItem = new Item("A mushroom grew on the feet of a table.", 0.01, "mushroom");
       vRoom.addItem(vItem);
       if(aNbRand2<=30)
       {
           vItem = new Item("A potion with a strange blue liquid rest on a table.", 0.2, "potion");
           vRoom.addItem(vItem);
       }
       
       
       this.numRoom++;
       return vRoom;
    }
   
   /**
    * Type de Room numero 2.
    * Un magicien apparait. (4 type différents : 4 effets bonus/malus)
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 2.
    */
   private Room RoomType2(final String pStringRoomPrecedent, final Room pRoomPrecedente )
   {   
       String vDescription = "in an empty Room";
       
       Room vRoom = new Room(vDescription, "Images/type 2.png", 2);
       
       //Les magiciens sont gérés dans GameEngine
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
 
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 3.
    * Un mirroir qui permet de voir ses statistiques y est present.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 3.
    */
   private Room RoomType3(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a room without much light.\n  You see yourself" 
                           + "  in a mirror on the wall", "Images/type 3.png", 3);
       
       //Le mirroir est géré dans GameEngine.
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);

       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 4. 
    * Un aventurier fou hère dans cette salle.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 4.
    */
   private Room RoomType4(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in some kind of ruins, an adventurer\n  have been wandering here for quiet some time","Images/type 4.jpg", 4);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       String[] vTabDial = {"The path is bright...", "The path is dark...", "Death !"}; 
       NPC vNPC = new NPC("Adventurer", "Images/Adventurer.jpg", vTabDial, "human", "warrior", null);
       vRoom.addNPC(vNPC);

       this.numRoom++;
       return vRoom;
   }
   
   /**
    * 
    * Type de Room numero 5.
    * Un cul de sac. C'est l'emplacement de la Statuette.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 5.
    */
   private Room RoomType5(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a beautiful room. \n" +
         "  The walls are richly decorated but there is nothing except for a pedestal", 
         "Images/type 5.jpg", 5);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       //la Statuette s'y trouve.
       Item vStatuette = new Item("A statuette of a beautiful woman almost naked is on the pedestal.", 1, "Statuette");
       vRoom.addItem(vStatuette);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 6.
    * Cette Room vous empeche de retourner en arriere.
    * @param pStringRoomPrecedent N'est pas utilise.
    * @param pRoomPrecedente N'est pas utilise.
    * @return Une Room de type 6.
    */
   private Room RoomType6(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a dark room. The light shines far away.\n" +
         "  You hear the doors close and lock.\n" +
         "  It seems that you can't go back anymore", 
         "Images/type 6.jpg", 6);
       
       //impossible de revenir sur ses pas
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 7.
    * Cette Room possede un beamer.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 7.
    */
   private Room RoomType7(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a room in ruins. Some parts of the statues are destroyed", 
         "Images/type 7.jpg", 7);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       //le beamer est dans cette salle
       vRoom.addBeamer();
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 8. 
    * Cette Room est une "Transporter Room", 
    * les sorties menent dans une salle aleatoire du donjon. 
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 8.
    */
   private TransporterRoom RoomType8(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       String vDirection = this.genererDirection();
       while(vDirection.equals(pStringRoomPrecedent) || vDirection.equals("up") || vDirection.equals("down"))
           vDirection = this.genererDirection();
       
       TransporterRoom vRoom = new TransporterRoom("in a dark, gloomy room. At the " +  vDirection + ",\n  "
                           + "a strange portal shines with a mesmeryzing blue aura",
                             "Images/type 8.jpg", 8, aAllRooms);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       //une sortie vers la Room d'ou l'on viens est générée, 
       //c'est uniquement pour avoir au moins deux sorties sur cette Room.
       //De toute facon, quand on sort,on est téléporté aléatoirement quelque sois la direction.
       vRoom.setExit(vDirection, pRoomPrecedente);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 9.
    * La salle ou un dragon a fait son nid (MovingCharacter)
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 9.
    */
   private Room RoomType9(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in gigantic room, a huge nest with giant eggs in it\n" +
         "  occupy the center, it seems still used...", 
         "Images/type 9.jpg", 9);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       //ajout du dragon (MovingCharacter)
       String[] vTabDial = {"RRROOOAAARRR"};
       Item vArmor = new Item("An armor made of Dragon'scales apeared from nowhere.", 1, "dragon scale armor");
       MovingCharacter vNPC = new MovingCharacter("Dragon", "Images/Dragon.jpg", vTabDial, "dragon" ,"dragon",vArmor ,vRoom) ;
       vRoom.addMovingNPC(vNPC);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 10.
    * Une tresorie, s'y trouve une epee legendaire, une armure legendaire et beaucoup d'or.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 10.
    */
   private Room RoomType10(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in gigantic room, tons of treasures lay everywhere on the ground", 
         "Images/type 10.jpg", 10);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       Item vEpeeL = new Item("A legendary sword that shines with a warm aura\n      is dispodes on a weapon rack.", 2.0, "legendary sword");
       Item vArmureL = new Item("A legendary armor that shines with a warm aura\n      is equiped on a model.", 4.0, "legendary armor");
       Item vGold1 = new Item("On the ground is a small purse full of gold.", 2.2, "gold purse");
       Item vGold2 = new Item("On the ground is a small chest full of gold.", 8.0, "gold chest");
       vRoom.addItem(vEpeeL);
       vRoom.addItem(vArmureL);
       vRoom.addItem(vGold1);
       vRoom.addItem(vGold2);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 11.
    * Une epee ou une armure d'un aventurier decede peut y etre pris.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 11.
    */
   private Room RoomType11(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a gloomy room, a dead adventurer rot in a corner", 
         "Images/type 11.jpg", 11);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       Item vItem;
       int aNbRand = (int)Math.round(Math.random()*1);
       if(aNbRand == 1)
            vItem = new Item("Near the entrance, a rusty sword is laying.", 2.5 , "rusty sword");
       else 
            vItem = new Item("A rusty armor attract your gaze, it might still be useable.", 4.5, "rusty armor");
       vRoom.addItem(vItem);
           

       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 12. 
    * Une horde de zombie y rode.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 12.
    */
   private Room RoomType12(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a dirty room, a lot of corpses make the area smell really nasty.",
                             "Images/type 12.jpg", 12);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       String[] vTabDial = {"ouargarrheubaa", "garrheubaa", "baahouargarr"};
       Item vFlesh = new Item("Some decomposed flesh that previously was the leg of a zombie\n  is rotting on the ground.", 0.8, "rotten flesh");
       NPC vNPC = new NPC("Horde of zombies", "Images/zombies.jpg", vTabDial, "zombies", "zombies", vFlesh);
       vRoom.addNPC(vNPC);

       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 13. 
    * Un piege s'y trouve : une fleche dans le dos (peut etre esquive)
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 13.
    */
   private Room RoomType13(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a room with stranges creatures hanging from wooks.\n  But that's not all, you forsee a trap",
                             "Images/type 13.jpg", 13);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 14. 
    * Une fontaine permet de reset ses statistiques
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 14.
    */
   private Room RoomType14(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a room with a soothing atmosphere.\n  A fountain of quicksilver thirst you out",
                             "Images/type 14.jpg", 14);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 15. 
    * Un piege s'y trouve : une trappe pleine de pic. (peut etre esquive)
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 15.
    */
   private Room RoomType15(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a room with a strange blue glowing crystal hanging from the rooftop",
                             "Images/type 15.jpg", 15);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 16. 
    * Un crapaud geant s'y tapis.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 16.
    */
   private Room RoomType16(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in the sewer of the dungeon.\n  A river made of waste water flows in the center",
                             "Images/type 16.jpg", 16);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       String[] vTabDial = {"splash", "crroooâ"};
       NPC vNPC = new NPC("Giant toad", "Images/toad.jpg", vTabDial, "half-orc", "protector", null);
       vRoom.addNPC(vNPC);
       
       this.numRoom++;
       return vRoom;
   }
   
    /**
    * Type de Room numero 17. 
    * Un spectre hante les lieux.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 17.
    */
   private Room RoomType17(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a sinister room, maybe the crypt.\n  The gargoyle statues makes you shiver down the spine ",
                             "Images/type 17.jpg", 17);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       String[] vTabDial = {"...", "....."};
       Item vScythe = new Item("A spectral scythe float in the corner.",1,"spectral scythe");
       NPC vNPC = new NPC("Fatal Scythe", "Images/specter.jpg", vTabDial, "specter", "specter", vScythe);
       vRoom.addNPC(vNPC);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 18. 
    * Un poulet monte garde les lieux de tout intrus.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 18.
    */
   private Room RoomType18(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a large room, large enouth to let it enter a horse",
                             "Images/type 18.png", 18);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       String[] vTabDial = {"Cot coot", "Death to the stranger !", "Plunderer, you shall not leave alive.", "I must keep the treasure safe."};
       NPC vNPC = new NPC("Mounted chicken", "Images/chicken.jpg", vTabDial, "beggard", "beggard", null);
       vRoom.addNPC(vNPC);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 19. 
    * Le Player y est transforme en poulet.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 19.
    */
   private Room RoomType19(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in a large and devastated corridor,\n  a lot of candelabra enlighten the area with a frightening purple fire",
                             "Images/type 19.jpg", 19);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       this.numRoom++;
       return vRoom;
   }
   
   /**
    * Type de Room numero 20. 
    * Un poulet vous attaque. (ses stats sont random)
    * Le poulet peut tuer le dragon et obtenir des boosts de stats.
    * @param pStringRoomPrecedent La direction permettant de retourner dans la Room precedente.
    * @param pRoomPrecedente La Room precedente.
    * @return Une Room de type 20.
    */
   private Room RoomType20(final String pStringRoomPrecedent, final Room pRoomPrecedente)
   {   
       Room vRoom = new Room("in what seems to be a hen house",
                             "Images/type 20.jpg", 20);
       
       //revenir sur ses pas
       vRoom.setExit(pStringRoomPrecedent, pRoomPrecedente);
       
       String[] vTabDial = {"Cot coot"};
       NPC vNPC = new NPC("Chicken", "Images/poulet.jpg", vTabDial, "chicken", "beggard", null);
       vRoom.addNPC(vNPC);
       
       this.numRoom++;
       return vRoom;
   }
}
