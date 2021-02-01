package pkg_Componnants.pkg_Rooms;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

import pkg_Componnants.pkg_Items.ItemList;
import pkg_Componnants.pkg_Items.Item;
import pkg_Componnants.pkg_Items.Beamer;
import pkg_Componnants.pkg_Entities.NPC;
import pkg_Componnants.pkg_Entities.MovingCharacter;
/**
 * Cette classe crée les Rooms qui seront utilisee pour la Map.
 * Elle permet d'atribuer les Exits et de stocker les items pouvant
 * etre present dedans.
 * Les rooms possedent aussi un type pour les différencier.
 *
 * @author Baptiste Adam
 * @version v3 - 13/05/2017
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aAllItems;
    private Beamer aBeamer;
    private int aTypeRoom;
    private HashMap<String, NPC> aAllNPCs;
    private MovingCharacter aMovingNPC;

    /**
     * Constructeur, cree un nouveau lieu.
     * @param pDescrition La description de la Room
     * @param pImage L'image de la Room
     * @param pTypeRoom Le type de la Room
     */
    public Room(final String pDescrition, final String pImage, final int pTypeRoom)
    {
        this.aDescription = pDescrition;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aAllItems = new ItemList();
        this.aTypeRoom = pTypeRoom;
        this.aAllNPCs = new HashMap<String, NPC>();
    } //Room()

    /**
     * Permet d'obtenir la descrition du lieu dans lequel on est.
     * @return Une string avec la description de la Room.
     */
    public String getDescription()
    {
        return this.aDescription;
    } //getDescrition()

    /**
     * Retourne la String contenant toutes les descriptions des items dans la Room.
     * @return La description de tous les items.
     */
    private String getItemDescription()
    {
        return this.aAllItems.getItemDescription();
    }

    public String getItemListString()
    {
        return this.aAllItems.getItemListString();
    }

    public String[] getRoomItemTab()
    {
        if(this.aBeamer==null)
            return this.aAllItems.getItemTab2();
        else
        {
            ArrayList<String> vArrayList = this.aAllItems.getItemTab();
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

    /**
     * retourne le dialogue de tout les NPCs present dans la Room.
     * @return Une String
     */
    private String getDialogue()
    {
        String vString = "";
        Set<String> keys = this.aAllNPCs.keySet();
        for(String nom : keys)
        {
            vString += nom + " : " 
            + this.aAllNPCs.get(nom).getDialogue() + "\n  ";
        }
        if(this.aMovingNPC!=null)
            vString+= this.aMovingNPC.getNom() + " : " 
            + this.aMovingNPC.getDialogue() + "\n";
        return vString;
    }

    /**
     * Retourne une description longue de la salle de la forme :
     *    You are in the living room
     *    [la descriptions des items]
     *    [le dialogue des NPCs]
     *    Exits : north west
     *@return Une String, La description de la Room.
     */
    public String getLongDescription()
    {
        String vBeamerDescription = "";
        if(this.aBeamer!=null)
            vBeamerDescription += this.aBeamer.getDescription() 
            + "   (" + this.aBeamer.getMotclef() + ").\n";

        return "You are " + this.aDescription + ".\n " 
        + this.getItemDescription() + vBeamerDescription 
        + this.getDialogue() 
        + this.getExitString();
    }//getLongDescription()

    /**
     * Configure la sortie de cette Room
     * @param pDirection La direction de la sortie.
     * @param pExit La Room dans la direction concernee.
     */
    public void setExit(final String pDirection, final Room pExit)
    {
        this.aExits.put(pDirection, pExit);
    } //setExit()

    /**
     * Retourne la Room dans laquelle on arrive si on se dirige dans la direction 'pDirection'.
     * Si il n'y a pas de Room dans cette direction, retourne null.
     * @param pDirection La direction vers laquelle on se dirige.
     * @return La sortie correspondant a la direction.
     */
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }  //getExits()

    /**
     * Permet de  retourner la String contenant la liste de toutes les sorties de la piece 
     * @return La string avec les exits.
     */
    private String getExitString()
    {
        String vExits = "  Exits :";
        Set<String> keys = this.aExits.keySet();
        /* Set<Type_des_clés> ma_variable = ma_HashMap.keySet();
        met dans ma_variable l'ensemble des clés de ma_HashMap  */
        for(String exit : keys)
        {
            vExits += " " + exit;
        }
        vExits += "\n";
        return vExits;
    }

    /**
     * Retourne le type de la Room.
     * @return Un int correspondant au type de la Room.
     */
    public int getType()
    {
        return this.aTypeRoom;
    }

    /**
     * Retourne une String avec le nom de l'image.
     * @return Une string avec le nom de l'image.
     */
    public String getImageName()
    {
        return this.aImageName;
    }

    /**
     * ajoute un beamer a la Room.
     */
    public void addBeamer()
    {
        this.aBeamer = new Beamer();
    }

    /**
     * enleve le beamer de la Room.
     */
    public void removeBeamer()
    {
        this.aBeamer = null;
    }

    /**
     * Permet d'ajouter un Item a la Room.
     * @param pItem L'item que l'on veut ajouter.
     */
    public void addItem(final Item pItem)
    {
        this.aAllItems.addItem(pItem );
    }

    /**
     * Permet d'obtenir un Item grace a son motclef.
     * @param pMotclef Le motclef de l'item que l'on cherche.
     * @return L'item que l'on cherche.
     */
    public Item getItem(final String pMotclef)
    {
        return this.aAllItems.getItem(pMotclef);
    }

    /**
     * Permet de supprimer un Item.
     * @param pItem L'item que l'on veut supprimer.
     */
    public void removeItem(final Item pItem)
    {
        this.aAllItems.removeItem(pItem);
    }

    /**
     * Ajoute le NPC passe en parametre.
     * @param pNPC Le NPC que l'on veux ajouter a la Room
     */
    public void addNPC(final NPC pNPC)
    {
        this.aAllNPCs.put(pNPC.getNom(), pNPC);
    }

    /**
     * Supprime le NPC dont le nom est passe en parametre.
     * @param pNom Une String, le nom du NPC a supprimer
     */
    public void removeNPC(final String pNom)
    {
        if(this.aAllNPCs.containsKey(pNom))
            this.aAllNPCs.remove(pNom);
    }

    /**
     * Ajoute le MovingCharacter passe en parametre a la Room.
     * @param pMovingNPC Le MovingCharacter a ajouter
     */
    public void addMovingNPC(final MovingCharacter pMovingNPC)
    {
        this.aMovingNPC = pMovingNPC;
    }

    /**
     * Supprime le MovingCharacter de la Room.
     */
    public void removeMovingNPC()
    {
        this.aMovingNPC = null;
    }

    /**
     * retourne le MovingCharacter present dans cette Room.
     * (null si il n'y en a pas)
     * @return Un MovingCharacter
     */
    public MovingCharacter getMovingNPC()
    {
        return this.aMovingNPC;
    }

    /**
     * Retourne le dialogue du NPC dont le nom est passe en parametre.
     * @param pNom Une String, le nom du NPC dont on veux le dialogue
     * @return Une String, le message du NPC
     */
    public String getNPCMessage(final String pNom)
    {
        if(this.aAllNPCs.containsKey(pNom))
            return this.aAllNPCs.get(pNom).getDialogue();
        return null;
    }

    /**
     * Retourne le NPC dont le nom a ete passe ne parametre.
     * (nul s'il n'est pas dans la Room)
     * @param pNom Une String, le nom du NPC que l'on veux obtenir
     * @return Le NPC que l'on veux obtenir
     */
    public NPC getNPC(final String pNom)
    {
        if(this.aAllNPCs.containsKey(pNom))
            return this.aAllNPCs.get(pNom);
        return null;
    }
} // Room










