package pkg_Componnants.pkg_Entities;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;

import pkg_Componnants.pkg_Items.ItemList;
import pkg_Componnants.pkg_Items.Item;
import pkg_Componnants.pkg_Items.Beamer;
import pkg_Componnants.pkg_Rooms.Room;
import pkg_Componnants.Son;
/**
 * Cette classe s'occupe de garder tout ce qui concerne le player : 
 *     Sa position sur la map, son inventaire, son poids maximum portable.
 * Elle s'occupe aussi de faire les actions impliquant le player :
 *     Ce déplacer d'une salle a une autre, prendre ou poser un objet, manger.
 * Gere aussi l'encombrement du player, s'il est encombre, il ne peux plus se deplacer.
 *
 * @author Baptiste Adam
 * @version v2 - 28/04/2017
 */
public class Player
{
   private String aRace;
   private String aJob;
   private Statistiques aStats;
   private int aPV;
   private int aDef;
   private int aAgi;
   private int aAtt;
   
   private int aLvl = 1; //le level du Player
   private int aExp = 0; //l'exp que possede le Player (remise a 0 a chaque level up)
   
   private Room aCurrentRoom;
   private Stack<Room> allPreviousRoom;
   private ItemList aInventory;
   private static double MAX_WEIGHT = 12.0;
   private static boolean ENCOMBREMENT = false;
   private Beamer aBeamer;
   
   private boolean chickenized = false;
   private int aNbRoomChickenized = 0;
   
   /**
    * Constructeur, cree un nouveau Player.
    * @param pRoom La Room dans laquelle le joueur commence.
    */
   public Player(final Room pRoom)
   {
       this.aCurrentRoom = pRoom;
       this.aInventory = new ItemList();
       this.allPreviousRoom = new Stack();
       this.aStats = new Statistiques();
   }
   
   /**
    * permet de passer le poids portable max a 100 000.
    * N'EST UTILISE QUE DANS UN TEST
    */
   public void setStats()
   {
       this.MAX_WEIGHT = 100000;
       this.aPV = 100000;
       this.aAgi = 100000;
       this.aDef = 100000;
       this.aAtt = 100000;
   }
   
   /**
    * permet de savoir si le joueur est encombre.
    * @return true si le Player est encombre
    */
   public boolean encombrement()
   {
       return this.ENCOMBREMENT;
   }
   
   /**
    * retourne chickenized, permet de savoir si le Player est un poulet.
    * @return Un boolean
    */
   public boolean chickenized()
   {
       return this.chickenized;
   }
   
   /**
    * Permet de changer la valeur de chickenized
    * @param pBoolean Un boolean, la nouvelle valeur de chickenized
    */
   public void setChickenized(final boolean pBoolean)
   {
       this.chickenized = pBoolean;
   }
   
   /**
    * Permet d'obtenir le nombre de Room qu'il s'est passe de puis la transformation en poulet.
    * @return Un int
    */
   public int getNbRoomChickenized()
   {
       return this.aNbRoomChickenized;
   }
   
   /**
    * Remet aNbRoomChickenized a 0. Le Player n'est plus un poulet.
    */
   public void resetNbRoomChickenized()
   {
       this.aNbRoomChickenized = 0;
   }
   
   /**
    * retourne l'inventaire du Player.
    * @return Une ItemList
    */
   public ItemList getInventory()
   {
       return this.aInventory;
   }
   
   /**
    * additionne au poids max la valeur passé en parametre.
    * @param pDouble Un double
    */
   public void changeMAX_WEIGHT(final double pDouble)
   {
       this.MAX_WEIGHT += pDouble;
   }
   
   /**
    * retourne le level du Player.
    * @return Un int
    */
   public int getLvl()
   {
       return this.aLvl;
   }
   
   /**
    * retourne true si le Player possede le beamer.
    * @return Un booleen
    */
   public boolean possessBeamer()
   {
       return this.aBeamer!=null;
   }
   
   /**
    * retourne le tableau de String contenant tous les Items de la CurrentRoom.
    * @return Un tableau de String
    */
   public String[] getCurrentRoomItemTab()
   {
       return this.aCurrentRoom.getRoomItemTab();
   }
   
   /**
    * retourne le tableau de String contenant tous les Items de l'inventaire.
    * @return Un tableau de String
    */
   public String[] getInventoryItemTab()
   {
       if(this.aBeamer==null)
           return this.aInventory.getItemTab2();
       else
       {
           ArrayList<String> vArrayList = this.aInventory.getItemTab();
           String[] vTab = new String[vArrayList.size() +2];
           vTab[0] = "orb";
           int i =1;
           for(String motclef : vArrayList){
                vTab[i] = motclef;
                i++;
            }
           return vTab;
        }
   }
   
   
   
   //gérer les Characters
   /**
    * Retourne le MovingCharacter le aCurrentRoom (null si il n'y en a pas).
    * @return Un MovingCharacter
    */
   public MovingCharacter getMovingCharacter()
   {
       return this.aCurrentRoom.getMovingNPC();
   }
   
   /**
    * retourne true si il y a un MovingCharacter dan la CurrentRoom.
    * @return Un boolean
    */
   public boolean isMovingCharacter()
   {
       return this.aCurrentRoom.getMovingNPC()!=null;
   }
   
   /**
    * Supprime le MovingCharacter (apres un combat contre celui-ci).
    */
   public void removeMovingCharacter()
   {
       this.aCurrentRoom.removeMovingNPC();
   }
   
   /**
    * Retourne le message du NPC dont le nom est passe en parametre.
    * (Le joueur parle avec lui)
    * @param pNom Une String, le nom du NPC
    * @return Une String, le message du NPC
    */
   public String getNPCMessage(final String pNom)
   {
       return this.aCurrentRoom.getNPCMessage(pNom);
   }
   
   /**
    * Retourne le NPC dont le nom est passe en parametre.
    * @param pNom Une String, le nom du NPC
    * @return Le NPC
    */
   public NPC getNPC(final String pNom)
   {
       return this.aCurrentRoom.getNPC(pNom);
   }
   
   /**
    * Supprime le NPC dont le nom est passe en parametre (apres un combat contre celui-ci).
    * @param pNom Une String ,le nom du NPC a supprimer
    */
   public void removeNPC(final String pNom)
   {
       this.aCurrentRoom.removeNPC(pNom);
   }
   
   /**
    * Depose l'item que possedait le monstre vaincu
    * et dont le nom est passe en parametre.
    * @param pNom Une String, le nom du monstre vaincu
    * @return Une String, un message indiquant qu'un item a ete depose dans la Room
    */
   public String loot(final String pNom)
   {
        Item vLoot;
        boolean vB = false;
        if(pNom.equals("Dragon") )
        {
            vLoot = this.aCurrentRoom.getMovingNPC().getItem();
            this.aCurrentRoom.addItem(vLoot);
            vB = true;
        }
        else if(!pNom.equals("Skeleton"))
        {
            vLoot = this.aCurrentRoom.getNPC(pNom).getItem();
            if(vLoot!=null)
            {
                this.aCurrentRoom.addItem(vLoot);
                vB = true;
            }
        }
        if(vB)
            return "The " + pNom + " has loot something.";    
        return "";
   }
   
   
   
   //gérer les statistiques
   /**
    * Permet de definir la race du Player.
    * @param pRace La String de la Race.
    */
   public void setRace(final String pRace)
   {
       this.aRace = pRace;
   }
   
   /**
    * Permet de definir la classe du Player.
    * @param pClasse La String de la Classe.
    */
   public void setClasse(final String pClasse)
   {
       this.aJob = pClasse;
   }
   
   /**
    * Retourne les PV actuel du Player.
    * @return Un int, les PV du Player
    */
   public int getPV()
   {
       return this.aPV;
   }
   
   /**
    * Permet de definir les PVs du Player.
    * @param pPV Un int : les PVs
    */
   public void setPV( final int pPV)
   {
       this.aPV = pPV;
   }
   
   /**
    * Permet de faire varier les PV.
    * @param pPV La valeur a ajouter
    */
   public void changePV(final int pPV)
   {
       this.aPV += pPV;
   }
   
   /**
    * Retourne la Defense actuelle du Player.
    * @return Un int, la Def du Player
    */
   public int getDef()
   {
       return this.aDef;
   }
   
   /**
    * Permet de definir la Defence du Player.
    * @param pDef Un int : la Defence
    */
   public void setDef( final int pDef)
   {
       this.aDef = pDef;
   }
   
   /**
    * Permet de faire varier la Def.
    * @param pDef La valeur a ajouter
    */
   public void changeDef(final int pDef)
   {
       this.aDef += pDef;
   }
   
   /**
    * Retourne l'Agilite actuelle du Player.
    * @return Un int, l'Agi du Player
    */
   public int getAgi()
   {
       return this.aAgi;
   }
   
   /**
    * Permet de definir l'Agilite du Player.
    * @param pAgi Un int : l'Agilite
    */
   public void setAgi( final int pAgi)
   {
       this.aAgi = pAgi;
   }
   
   /**
    * Permet de faire varier l'Agi.
    * @param pAgi La valeur a ajouter
    */
   public void changeAgi(final int pAgi)
   {
       this.aAgi += pAgi;
   }
   
   /**
    * Retourne l'Attaque actuelle du Player.
    * @return Un int, l'Att du Player
    */
   public int getAtt()
   {
       return this.aAtt;
   }
   
   /**
    * Permet de definir l'Attaque du Player.
    * @param pAtt Un int : l'Attaque
    */
   public void setAtt( final int pAtt)
   {
       this.aAtt = pAtt;
   }
   
   /**
    * Permet de faire varier l'Att.
    * @param pAtt La valeur a ajouter
    */
   public void changeAtt(final int pAtt)
   {
       this.aAtt += pAtt;
   }
   
   /**
    * Attribut les stattistiques du Player grace a sa race et sa classe (job)
    */
   public void attribuerStats()
   {
       int vPV = this.aStats.getPV(aRace) + this.aStats.getPV(aJob);
       int vDef = this.aStats.getDef(aRace) + this.aStats.getDef(aJob);
       int vAgi = this.aStats.getAgi(aRace) + this.aStats.getAgi(aJob);
       int vAtt = this.aStats.getAtt(aRace) + this.aStats.getAtt(aJob);
       
       this.aPV = vPV + (this.aLvl -1)*2;
       this.aDef = vDef + (this.aLvl -1)*2;
       this.aAgi = vAgi + (this.aLvl -1)*2;
       this.aAtt = vAtt + (this.aLvl -1)*2;
   }
   
   /**
    * attribut l'exp gagne lors du combat et verifie si le Player a lvl up.
    * @param pNom Le nom du monstre vaincu
    * @param pBoolean Un boolean, true si il faut jouer le son de lvl up.
    * @return Une String, Un message si level up, rien sinon.
    */
   public String levelUp(final String pNom, final boolean pBoolean)
   {
       int vExpGagnee = this.aStats.getExp(pNom);
       int vExpToLvl = this.aStats.getLvlUp(aLvl);
       
       if(this.aExp + vExpGagnee >= vExpToLvl)
       {
           this.aExp += vExpGagnee;
           //les stats du Player augmentent.
           this.changePV(2);
           this.changeAgi(2);
           this.changeDef(2);
           this.changeAtt(2);
            //l'exp pour lvl up est consomée.
           if(pBoolean)//true si c'est un lvl naturel, 
            {          //false si c'est la fontaine : c'est juste un rééquilibrage
               this.aExp -= vExpToLvl;//l'exp utilisée pour le lvl up est soustraite
               this.aLvl++;//le lvl augmente de 1
               Son.JouerLeSon("Sons/LevelUp.wav");//le son du lvl up est joué
           }
           return "By killing the " + pNom + ", you feel stronger.";
       }
       return "";
   }
   
   /**
    * Affiche les statistiques du Player.
    * @return Une String contenant toutes les statistiques du Player
    */
   public String stats()
   {
       String vS = "You are a  level " + this.aLvl + " " + this.aRace + " " + this.aJob + " : " +
                   "\n    PV: " + this.aPV + "   -   Def: " + this.aDef 
                 + "\n    Agi:" + this.aAgi + "   -   Att: " + this.aAtt + "\n";
       return vS;
   }
  
   
   
   //gérer aCurrentRoom 
   /**
    * Change aCurrentRoom avec vNextRoom,
    * ajoute l'ancienne aCurrentRoom a allPreviousRoom.
    * Gere aussi l'encombrement du Player.
    * @param pDirection La direction vers laquelle on se dirige.
    * @return  "mort" si le MAX_WEIGHT passe a 0, "true" si le joueur a change de Room avc succes, "false" sinon.
    */
   public String changeRoom(final String pDirection)
   {
       Room vNextRoom = this.aCurrentRoom.getExit(pDirection);
       if (vNextRoom == null)
           return "false";
       else 
       {
           
           //Si le déplacement est possible, le poids max portable diminue.
           //Si celui-ci attends 0 ou moins, c'est la mort de fatigue.
           this.MAX_WEIGHT -= 0.25; 
           if(this.MAX_WEIGHT <= 0)
               return "mort";
           if(this.MAX_WEIGHT < this.getPlayerWeight())
               this.ENCOMBREMENT =true;
           //mémoriser aCurrentRoom comme la plus recente des PreviousRoom
           this.allPreviousRoom.push(this.aCurrentRoom);
           //avancer vers la nouvelle Room
           this.aCurrentRoom = vNextRoom;
           //efface les previousRooms si on arrive dans une Room de type 6 (ou on ne peux pas revenir sur ses pas)
           if(this.aCurrentRoom.getType()==6)
               this.emptyAllPreviousRoom();
           if(this.chickenized)
               this.aNbRoomChickenized++;
           return "true";
       }
   }
   
   /**
    * permet d'obtenir le poid total que porte le player. 
    * (inventaire + beamer)
    * @return Un double
    */
   private double getPlayerWeight()
   {
       double vW = Math.round(this.aInventory.getTotalWeight());
       if(this.aBeamer!=null)
            vW += this.aBeamer.getWeight();
       return vW;
   }
   
   /**
    * permet de vider le stack aAllPreviousRoom.
    */
   private void emptyAllPreviousRoom()
   {
      while(!this.allPreviousRoom.empty())
          this.allPreviousRoom.pop();
   }
   
   /**
     * Permet de retourner dans la Room que l'on viens de quitter si celle-ci existe.
     * Gere aussi l'encombrement du Player.
     * @return  "mort" si le MAX_WEIGHT passe a 0, "true" si le joueur a change de Room avc succes, "false" sinon.
     */
    public String back()
    {
        if(this.allPreviousRoom.empty())
            return "false";
        else
        {
            this.MAX_WEIGHT -= 0.5;
            if(this.MAX_WEIGHT <= 0)
                return "mort";
            if(this.MAX_WEIGHT<this.getPlayerWeight())
                this.ENCOMBREMENT =true;
            this.aCurrentRoom = this.allPreviousRoom.pop();
            if(this.chickenized)
                this.aNbRoomChickenized++;
            return "true";
        }
    }
   
   /**
    * retourne la String contenant toutes les infos sur aCurrentRoom.
    * @return La longDescription de aCurrentRoom.
    */
   public String getLocationInfo()
   {
       return this.aCurrentRoom.getLongDescription();
   }
   
   /**
    * Renvois l'image de la currentRoom.
    * @return L'adresse de l'image de aCurrentRoom.
    */
   public String getImage()
   {
       return this.aCurrentRoom.getImageName();
   }
   
   /**
    * permet d'avoir le type de aCurrrentRoom.
    * @return Le type de aCurrentRoom.
    */
   public int getRoomType()
   {
       return this.aCurrentRoom.getType();
   }
   
   
   
   //Gérer aInventory
   /**
    * Permet de prendre un Item se trouvant dans aCurrentRoom.
    * Appelle aussi takeAction( . ) pour gerer les actions des objets.
    * @param pItem l'item que l'on veut prendre.
    * @return La String correspondant a l'action effectuee.
    */
   private String take(final Item pItem)
   {
       if(this.getPlayerWeight() + pItem.getWeight() <= this.MAX_WEIGHT)
       {
           this.aInventory.addItem(pItem);
           this.aCurrentRoom.removeItem(pItem);
           if(pItem.getMotclef().equals("Statuette"))
           {
               return "You have retrieved the statuette of Anbrodema.";
           }
           String vAction = this.takeAction(pItem.getMotclef());
           return "The " + pItem.getMotclef() + " is now in your inventory.\n" + vAction;
       }
       else
           return "You can't take this Item";
   }
   
   /**
    * effectue l'action apropriée en fonction de si l'Item passe en parametre existe
    *  - affectue take( . ) si l'Item existe
    *  - sinon, renvois "This Item do not exist."
    *  @param pSecondWord L'item que l'on veut prendre.
    *  @return La String correspondant a l'action effectuée.
    */
   public String takeTest(final String pSecondWord)
   {
       Item vI = this.aCurrentRoom.getItem(pSecondWord);
        if(vI!=null)
            return this.take(vI);
        else
            return "This Item does not exist.\n";
   }
   
   /**
    * Certain item ont une fonction des lors qu'ils sont dans l'inventaire (les equipements)
    * Cette action est fait ici, Un Message adequat est renvoye.
    * @param pNom Une String, le nom de l'item que le player vient de prendre
    * @return Une String, le message lie a l'objet
    */
   private String takeAction(final String pNom)
   {
       switch(pNom)
       {
           case "legendary sword" : this.changeAtt(4); return "  Your attack increased by 4.\n";
           case "legendary armor" : this.changeDef(4); return "  Your defence increased by 4.\n";
           case "rusty sword" : this.changeAtt(2); return "  Your attack increased by 2.\n";
           case "rusty armor" : this.changeDef(2); return "  Your defence increased by 2.\n";
           case "dragon scale armor" : this.changeDef(6); return "  Your defence increased by 6.\n";
           case "spectral scythe" : this.changeAtt(6); return "  Your attack increased by 6.\n";
           default : return "";
       }
   }
   
   /**
    * Permet de poser un Item de l'inventaire dans aCurrentRoom.
    * Appelle aussi dropAction( . ) pour gerer les actions des objets.
    * @param pItem L'item que l'on veut poser.
    * @return La String correspondant a l'action effectuee.
    */
   private String drop(final Item pItem)
   {
      aCurrentRoom.addItem(pItem);
      aInventory.removeItem(pItem);
      if(MAX_WEIGHT>this.getPlayerWeight())
               ENCOMBREMENT =false;
      String vAction = this.dropAction(pItem.getMotclef());
      return "The " + pItem.getMotclef() + " is no longer in your inventory.\n" + vAction;
   }
   
   /**
    * effectue l'action apropriee en fonction de si l'Item passe en parametre existe
    *  - affectue drop( . ) si l'Item existe
    *  - sinon, renvois "You don't have this item"
    *  @param pSecondWord L'item que l'on veut poser.
    * @return La String correspondant a l'action effectuee.
    */
   public String dropTest(final String pSecondWord)
   {
       Item vI = this.aInventory.getItem(pSecondWord);
        if(vI!=null)
            return this.drop(vI);
        else
            return "You don't have this item.\n";
   }
   
   /**
    * Certain item ont une fonction des lors qu'ils sont dans l'inventaire (les equipements)
    * De fait, des qu'il ne sont plus dans l'inventaire, cette fonction doit etre retiree.
    * Cette action est fait ici, Un Message adequat est renvoye.
    * @param pNom Une String, le nom de l'item que le player vient de prendre
    * @return Une String, le message lie a l'objet
    */
   private String dropAction(final String pNom)
   {
       switch(pNom)
       {
           case "legendary sword" : this.changeAtt(-4); return "  Your attack decreased by 4.\n";
           case "legendary armor" : this.changeDef(-4); return "  Your defence decreased by 4.\n";
           case "rusty sword" : this.changeAtt(-2); return "  Your attack decreased by 2.\n";
           case "rusty armor" : this.changeDef(-2) ; return "  Your defence decreased by 2.\n";
           case "scale armor" : this.changeDef(-6); return "  Your defence decreased by 6.\n";
           case "spectral scythe" : this.changeAtt(-6); return "  Your attack decreased by 6.\n";
           default : return "";
       }
   }
   
   /**
    * Re-equipe tout les items de l'inventaire.
    * Est utilise apres avoir bu a la fontaine.
    */
   public void reequip()
    {
        ArrayList<String> vTab = this.aInventory.getItemTab();
        for(String motclef : vTab)
        {
            this.takeAction(motclef);
        }
    }
   
   /**
    * Retourne la string de l'inventaire et tout ce qu'il contient + le beamer s'il existe + le poids porte par rapport au poids portable.
    * @return Une String.
    */
   public String getInventoryString()
   {
       String vInventory = this.aInventory.getItemListString();
       double vPlayerWeight = this.aInventory.getTotalWeight();
       if(aBeamer!=null)
       {
           vInventory += this.aBeamer.getMotclef(); 
           vPlayerWeight += this.aBeamer.getWeight();
       }
       vInventory += "\n  " + vPlayerWeight + "/" + this.MAX_WEIGHT + "\n";
       return vInventory;
   }
   
   //Gérer le Beamer
   /**
    * permet de prendre le beamer dans aCurrentRoom si c'est possible.
    * @return Une string correspondant a l'action effectuee.
    */
   public String takeBeamer()
   {
       if(this.aBeamer==null)    
       {
           if(this.aInventory.getTotalWeight()+5 <= this.MAX_WEIGHT)  
           {
               this.aBeamer = new Beamer();
               this.aCurrentRoom.removeBeamer();
               return "You obtained an orb, make good use of it.\n";
           }
           return "It's too heavy.\n";
       }
       return "You already have an orb, it's useless to have two.\n";
   }
   
   /**
    * permet de poser le beamer dans aCurrentRoom.
    * @return Une string correspondant a l'action effectuee.
    */
   public String dropBeamer()
   {
       this.aBeamer = null;
       this.aCurrentRoom.addBeamer();
       return "you droped your orb, is it really a good idea ?\n";
   }
   
   /**
    * charge le beamer si c'est possible.
    * @return Une string correspondant a l'action effectuee.
    */
   public boolean chargeBeamer()
   {
       if(this.aBeamer!=null)
       {
           this.aBeamer.setChargedRoom(this.aCurrentRoom);
           return true;
       }
       return false;
   }
   
   /**
    * active le beamer si c'est possible.
    * @return Une string correspondant a l'action effectuee.
    */
   public String fireBeamer()
   {
       if(this.aBeamer!=null)  
           if(this.aBeamer.getChargedRoom()!=null)
           {
                this.aCurrentRoom = this.aBeamer.getChargedRoom();
                this.aBeamer.setChargedRoom(null);
                this.emptyAllPreviousRoom();
                Son.JouerLeSon("Sons/Beamer.wav");
                return "true";
           }
           else
                return "false";
       return "noBeamer";
   }
}




