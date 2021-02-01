package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant d'afficher le message d'aide.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class HelpCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle HelpCommand.
     * @param words Le CommandWords correspondant
     */
    public HelpCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Help.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        pGameEngine.getGUI().println("You are lost. You are alone.");
        pGameEngine.getGUI().println("You wander around in some dark room full of gobelins." + "\n");
        pGameEngine.getGUI().print("Your command words are: \n   " + pGameEngine.getParser().showCommands() + "\n \n");
    }
}
