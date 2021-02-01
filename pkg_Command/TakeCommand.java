package pkg_Command;
import pkg_Engine.GameEngine;

import javax.swing.JOptionPane;
/**
 * La commande permettant de prendre un objet.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class TakeCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle TakeCommand.
     * @param words Le CommandWords correspondant
     */
    public TakeCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Take.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {                   
        String vItem;
        if(!pGameEngine.getTest())
        {
            Object[] possibleValues = pGameEngine.getPlayer().getCurrentRoomItemTab();
    
            Object vObject = JOptionPane.showInputDialog(null,
                 "What do you want to take ?", "take",
                 JOptionPane.INFORMATION_MESSAGE, null,
                 possibleValues, possibleValues[0]);
                          
            vItem =(String)vObject;  
        }
        else 
            vItem = getSecondWord();

        if(vItem == null)
            pGameEngine.getGUI().println("You didn't take anything. \n");
        else
        {
            String vS;
            if(vItem.equals("orb"))
            {
                vS = pGameEngine.getPlayer().takeBeamer();
                pGameEngine.getGUI().setBeamer(true);
            }
            else
                vS = pGameEngine.getPlayer().takeTest(vItem);
            pGameEngine.getGUI().println(vS);
            if(vS.equals("You have retrieved the statuette of Anbrodema."))
                   pGameEngine.endGameWin(); 
        }
    }
}
