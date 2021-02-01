package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de choisir sa classe.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class ClasseCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle ClasseCommand.
     * @param words Le CommandWords correspondant
     */
    public ClasseCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande de choix de la classe.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vClasse = getSecondWord();
        pGameEngine.getPlayer().setClasse(vClasse);
        pGameEngine.getPlayer().attribuerStats();
        pGameEngine.getGUI().panelDeplacement();
        pGameEngine.printWelcome();
    }
}
