package pkg_Command;
import pkg_Engine.GameEngine;

/**
 * La commande permettant d'afficher la description des Rooms.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class LookCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle LookCommand.
     * @param words Le CommandWords correspondant
     */
    public LookCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Look.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        pGameEngine.printLocationInfo();
        pGameEngine.RoomAction();
        pGameEngine.combatDragon();
    }
}
