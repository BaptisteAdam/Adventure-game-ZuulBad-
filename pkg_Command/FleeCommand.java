package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de se suicider.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class FleeCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle FleeCommand.
     * @param words Le CommandWords correspondant
     */
    public FleeCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Flee.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        if(pGameEngine.getPlayer().getAgi()<=0)
            pGameEngine.death("You were attacked by behind while fleeing and died.");
        else
        {
            pGameEngine.getGUI().panelDeplacement();
            pGameEngine.getPlayer().changeAgi(-3);
            pGameEngine.setCombat(false);
            if(pGameEngine.getPlayer().getImage() != null)
                pGameEngine.getGUI().showImage(pGameEngine.getPlayer().getImage());
            String vEnnemi;
            if(pGameEngine.getPlayer().getMovingCharacter()!=null)
                vEnnemi = pGameEngine.getPlayer().getMovingCharacter().getNom();
            else
                vEnnemi = pGameEngine.getEnnemi().getNom();
            pGameEngine.getGUI().println("You fled, the " + vEnnemi + " does not see you any more.\n");
        }
    }
}


