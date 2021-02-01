package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de choisir sa Race.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class RaceCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle RaceCommand.
     * @param words Le CommandWords correspondant
     */
    public RaceCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande de choix de la Race.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vRace = getSecondWord();
        pGameEngine.getPlayer().setRace(vRace);
        pGameEngine.getGUI().panelClasse();
        pGameEngine.getGUI().showImage("Images/classe.png");
        pGameEngine.getGUI().println("Please, choose a job.");
    }
}
