package pkg_Command;
import pkg_Engine.GameEngine;

import javax.swing.JOptionPane;
/**
 * La commande permettant de poser un objet.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class DropCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle DropCommand.
     * @param words Le CommandWords correspondant
     */
    public DropCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Drop.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vItem;
        if(!pGameEngine.getTest())
        {
            Object[] possibleValues = pGameEngine.getPlayer().getInventoryItemTab();
    
            Object vObject = JOptionPane.showInputDialog(null,
                 "What do you want to drop ?", "drop",
                 JOptionPane.INFORMATION_MESSAGE, null,
                 possibleValues, possibleValues[0]);
                          
            vItem =(String)vObject;
        }
        else 
            vItem =getSecondWord();
            
        if(vItem == null)
            pGameEngine.getGUI().println("You didn't drop anything. \n");
        else
        {
            String vS;
            if(vItem.equals("orb"))
            {
                vS = pGameEngine.getPlayer().dropBeamer();
                pGameEngine.getGUI().setBeamer(false);
            }
            else
                vS = pGameEngine.getPlayer().dropTest(vItem);
            pGameEngine.getGUI().println(vS);
        }
    }
}
