package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de se suicider.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class StatsCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle StatsCommand.
     * @param words Le CommandWords correspondant
     */
    public StatsCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Stats. Affiche les statistiques du Player.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        pGameEngine.getGUI().println(pGameEngine.getPlayer().stats());
    }
}
