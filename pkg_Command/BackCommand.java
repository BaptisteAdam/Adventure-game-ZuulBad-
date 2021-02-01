package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de revenir sur ses pas.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class BackCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle BackCommand.
     * @param words Le CommandWords correspondant
     */
    public BackCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Back.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        if(!pGameEngine.getPlayer().encombrement())
        {
            String backOK = pGameEngine.getPlayer().back();
            if(backOK.equals("mort"))
                pGameEngine.death(" Exausted by this long journey, you take a nap.\n  " +  
                "But it's to never wake up... \n  You may have been slayed by a slime.");
            else if(backOK.equals("true"))
            {
                pGameEngine.printLocationInfo();
                if(pGameEngine.getPlayer().getImage() != null)
                {
                    pGameEngine.getGUI().showImage(pGameEngine.getPlayer().getImage());
                }
                pGameEngine.RoomAction();
                pGameEngine.combatDragon();
            }
            else
                pGameEngine.getGUI().println("You should move foreward first ! \n");
        }
        else
            pGameEngine.getGUI().println("You are carrying too many items, you must drop some of them.\n");
    }
}
