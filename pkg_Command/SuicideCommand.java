package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de se suicider.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class SuicideCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle SuicideCommand.
     * @param words Le CommandWords correspondant
     */
    public SuicideCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Suicide.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        pGameEngine.death("Thrown into despair by the difficulty of the task, \n  you suicide yourself...");
    }
}
