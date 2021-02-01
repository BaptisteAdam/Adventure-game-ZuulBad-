package pkg_Componnants.pkg_Items;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Cette classe cree une collection d'item 
 * qui sera utilisee par le Player et les Rooms.
 * Elle correspond donc a l'inventaire du Player.
 * Elle regroupe toutes actions a effectuer sur les collections d'items
 * qui etaient dans Player et Room pour eviter le duplication de code.
 *
 * @author Baptiste Adam
 * @version v2 - 15/04/2017
 */
public class ItemList
{
   private HashMap<String, Item> aItemList; 
   private double aTotalWeight;
   
   /**
    * Constructeur, cree un nouveau ItemList.
    */
   public ItemList()
   {
       this.aItemList = new HashMap<String, Item>();
       this.aTotalWeight = 0;
   }
   
   /**
    * Ajoute un Item dans l'ItemList.
    * @param pItem L'item à ajouter.
    */
   public void addItem(final Item pItem)
   {
       this.aItemList.put(pItem.getMotclef(), pItem );
       this.aTotalWeight += pItem.getWeight();
   }
   
   /**
    * Renvois l'Item lie au motclef.
    * @param pMotclef Le motclef de l'item recherche.
    * @return L'Item recherche.
    */
   public Item getItem(final String pMotclef)
   {
       Set<String> keys = this.aItemList.keySet();
       for(String motclef : keys)
       {
            if(motclef.equals(pMotclef))
            {
                return this.aItemList.get(motclef);
            }
       }
       return null;
   } 
   
   /**
    * Permet de savoir si l'item passe en parametre est dans l'itemList.
    * @param pMotclef Le motclef de l'item recherche.
    * @return true si l'item est dans l'itemList, false sinon.
    */
   public boolean possessItem(final String pMotclef)
   {
       if(this.getItem(pMotclef)!= null)
            return true;
       else
            return false;
   }
   
   /**
    * Supprime l'item passe en parametre de l'itemList.
    * Soustrait son poid au poids total.
    * @param pItem L'item a supprimer.
    */
   public void removeItem(final Item pItem)
   {
     if(this.aItemList.containsKey(pItem.getMotclef()))
         {
             this.aTotalWeight -= pItem.getWeight();
             this.aItemList.remove(  pItem.getMotclef() );
         }
   }
   
   /**
    * Retourne la String de tout les Item contenu dans itemList.
    * @return Une String de la forme "Item : ...  ...  ... "
    */
   public String getItemListString()
   {
       String vString = "Items : ";
       Set<String> keys = this.aItemList.keySet();
       for(String motclef : keys)
       {
            vString += motclef + ", ";
       }
       return vString;
   }
   
   /**
    * Retourne la description de tout les item contenu dans l'itemList.
    * @return Une string de la forme " [description de l'item] ([nom de l'item]) " avec un item par ligne.                             
    */
   public String getItemDescription()
   {
       String vDescriptions = " ";
       Set<String> keys = this.aItemList.keySet();
       for(String motclef : keys)
       {
            vDescriptions += this.aItemList.get(motclef).getDescription() + "   (" + motclef + ")" + ".\n  ";
       }
       return vDescriptions;
   }
   
   /**
    * Retourne l'ArrayList contenant tout les motclefs des items presents dans la HashMap.
    * @return Une ArrayList
    */
   public ArrayList<String> getItemTab()
   {
       ArrayList<String> vTab = new ArrayList<String>();
       Set<String> keys = this.aItemList.keySet();
       for(String motclef : keys)
       {
            vTab.add(motclef);
       }
       return vTab;
   }
   
   
   /**
    * retourne les motclefs de tout les items presents dans l'itemList dans un tableau.
    * @return Un tableau de String
    */
   public String[] getItemTab2()
   {
       String[] vTab = new String[this.aItemList.size()+1];
       int i=0;
       for(String motclef : this.aItemList.keySet())
       {
            vTab[i] = motclef;
            i++;
       }
       //vTab[i] = "nothing";
       return vTab;
   }
   
   /**
    * Retourne le poids total de tous les items de l'itemList.
    * @return Le poids total de tous les items.
    */
   public double getTotalWeight()
   {
       return this.aTotalWeight;
   }
   
}









