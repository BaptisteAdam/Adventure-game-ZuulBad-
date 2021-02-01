package pkg_Command;
import pkg_Engine.GameEngine;

import javax.swing.JOptionPane;
/**
 * La commande permettant de manger.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class EatCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle EatCommand.
     * @param words Le CommandWords correspondant
     */
    public EatCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Eat.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vItem;
        if(!pGameEngine.getTest())
        {
            Object[] possibleValues = pGameEngine.getPlayer().getInventoryItemTab();
    
            Object vObject = JOptionPane.showInputDialog(null,
                 "What do you want to eat ?", "eat",
                 JOptionPane.INFORMATION_MESSAGE, null,
                 possibleValues, possibleValues[0]);
                          
            vItem =(String)vObject;     
        }
        else
            vItem = getSecondWord();
            
        pGameEngine.getGUI().println(this.eat(pGameEngine, vItem));
    }
    
    /**
    * Permet de manger l'item passe en parametre si c'est possible.
    * @param pGameEngine Le GameEngine faisant l'action
    * @param pSecondWord L'item que l'on veut manger
    * @return La String correspondant a l'action effectuee.
    */
   public String eat(final GameEngine pGameEngine, final String pSecondWord)
   {
       if(pSecondWord!=null)  
           if(pGameEngine.getPlayer().getInventory().possessItem(pSecondWord))
               switch  (pSecondWord)
               {
                   case "cookie":
                        this.eatCookie(pGameEngine);
                        return "You  have eaten now and you are not hungry anymore.\n  Your carry capacity has expended. \n";
                   case "steak":
                        this.eatSteak(pGameEngine);
                        return "You have eaten now and you are not hungry anymore.\n  You healed yourself.\n";
                   case "mushroom":
                        return this.eatMushroom(pGameEngine);
                   case "potion" :
                        this.eatPotion(pGameEngine);
                        return "You have eaten now and you are not hungry anymore.\n  You feel lighter, your agility increased.\n";
                   case "rotten flesh" :
                        this.eatRottenFlesh(pGameEngine);
                        return "You have eaten now and you are not hungry anymore.\n  You healed yourself but feel tired.\n";
                   default : 
                        return "You can't eat this. \n";
               }
           else
                return "You do not have this Item. \n";
       else
            return "You didn't eat anything.\n";
   }
   
   /**
    * Mange un cookie.
    * Augmente le poids portable de 10 kg.
    * Supprime le cookie de l'inventaire.
    * @param pGameEngine Le GameEngine faisant l'action
    */
   private void eatCookie(final GameEngine pGameEngine)
   {
       pGameEngine.getPlayer().changeMAX_WEIGHT(10.0);
       pGameEngine.getPlayer().getInventory().removeItem(pGameEngine.getPlayer().getInventory().getItem("cookie"));
   }
   
   /**
    * Mange un steak.
    * augmente les PVs de 10.
    * Supprime le steak de l'inventaire.
    * @param pGameEngine Le GameEngine faisant l'action
    */
   private void eatSteak(final GameEngine pGameEngine)
   {
       pGameEngine.getPlayer().changePV(10);
       pGameEngine.getPlayer().getInventory().removeItem(pGameEngine.getPlayer().getInventory().getItem("steak"));
   }
   
   /**
    * Mange un mushroom.
    * reduit les PVs de 3 ou les augmente de 15.
    * Supprime le mushroom de l'inventaire.
    * @param pGameEngine Le GameEngine faisant l'action
    * @return Le String a afficher
    */
   private String eatMushroom(final GameEngine pGameEngine)
   {
       double aNbRand = Math.round(Math.random()*2);
       pGameEngine.getPlayer().getInventory().removeItem(pGameEngine.getPlayer().getInventory().getItem("mushroom"));
       if(aNbRand < 1.2)
       {
           pGameEngine.getPlayer().changePV(-3);
           return "You have eaten now and you are not hungry anymore.\n  You poisoned yoursef.";
       }
       else
       {
           pGameEngine.getPlayer().changePV(15);
           return "You have eaten now and you are not hungry anymore.\n  You feel revitalized.";
       }
   }
   
   /**
    * "Mange" une potion.
    * augmente l'Agilite de 4.
    * Supprime la potion de l'inventaire.
    * @param pGameEngine Le GameEngine faisant l'action
    */
   private void eatPotion(final GameEngine pGameEngine)
   {
       pGameEngine.getPlayer().changeAgi(4);
       pGameEngine.getPlayer().getInventory().removeItem(pGameEngine.getPlayer().getInventory().getItem("potion"));
   }
   
   /**
    * "Mange" une potion.
    * augmente l'Agilite de 4.
    * Supprime la potion de l'inventaire.
    * @param pGameEngine Le GameEngine faisant l'action
    */
   private void eatRottenFlesh(final GameEngine pGameEngine)
   {
       pGameEngine.getPlayer().changePV(6);
       pGameEngine.getPlayer().changeMAX_WEIGHT(-5);
       pGameEngine.getPlayer().getInventory().removeItem(pGameEngine.getPlayer().getInventory().getItem("rotten flesh"));
   }
}
