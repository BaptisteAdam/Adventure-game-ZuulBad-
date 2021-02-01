package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant d'afficher l'inventaire.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class InventoryCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle InventoryCommand.
     * @param words Le CommandWords correspondant
     */
    public InventoryCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Inventory.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        pGameEngine.getGUI().println(pGameEngine.getPlayer().getInventoryString());
    }
}
