package pkg_Command;
import pkg_Engine.GameEngine;
/**
 * La commande permettant de bloquer l'aleatoire de la TransporterRoom.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class AleaCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle AleaCommand.
     * @param words Le CommandWords correspondant
     */
    public AleaCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Alea.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vEnable = getSecondWord();
        
        if(pGameEngine.getTest())
            if(vEnable == null)
            {
                pGameEngine.getMap().getTransporterRoom().getRoomRandomizer().setIndiceRoom(false);
                pGameEngine.getGUI().println("TransporterRoom randomized");
            }
            else if(vEnable.equals("enable"))
            {
                pGameEngine.getMap().getTransporterRoom().getRoomRandomizer().setIndiceRoom(true);
                pGameEngine.getGUI().println("TransporterRoom fixed");
            }
            else
                pGameEngine.getGUI().println("Alea what ?");
        else
            pGameEngine.getGUI().println("You are not allowed to do this.");
    }
}
