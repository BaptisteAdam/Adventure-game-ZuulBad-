package pkg_Command;
import pkg_Engine.GameEngine;

import javax.swing.JOptionPane;
/**
 * La commande permettant d'utiliser le beamer (orb).
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class BeamerCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle BeamerCommand.
     * @param words Le CommandWords correspondant
     */
    public BeamerCommand(CommandWords words)
    {
        commandWords = words;
    }
    
    /**
     * Execute la commande Beamer.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vAction;
        if(!pGameEngine.getTest())
        {
            Object[] possibleValues = {"charge", "fire", null};
    
            Object vObject = JOptionPane.showInputDialog(null,
                 "What do you want to do ?", "orb",
                 JOptionPane.INFORMATION_MESSAGE, null,
                 possibleValues, possibleValues[0]);
                          
            vAction =(String)vObject; 
        }
        else
            vAction = getSecondWord();
            
        if(vAction == null )
            pGameEngine.getGUI().println("You didn't do anything with the orb. \n");
        else
        {
            if(vAction.equals("charge"))
            {
                boolean chargeOK = pGameEngine.getPlayer().chargeBeamer();
                if(chargeOK)
                    pGameEngine.getGUI().println("The orb is charged, fire to teleport.\n");
                else 
                    pGameEngine.getGUI().println("You must have an orb to charge it.\n");
            }
            else if(vAction.equals("fire"))
            {
                String fireOK = pGameEngine.getPlayer().fireBeamer();
                switch(fireOK){
                    case "true" :
                        pGameEngine.printLocationInfo();
                        if(pGameEngine.getPlayer().getImage() != null)
                            pGameEngine.getGUI().showImage(pGameEngine.getPlayer().getImage());
                        break;
                    case "false": pGameEngine.getGUI().println("The orb must be charged before firing.\n"); break;
                    case "noBeamer": pGameEngine.getGUI().println("You must have a charged orb to fire it.\n");
                }
            }
            else if(vAction.equals("hrthgrtlgh") && pGameEngine.getTest() == true)
            //N'AS PAS VOCATION A ETRE UTILISE PAR LE JOUEUR
            {
                pGameEngine.getPlayer().takeBeamer();
            }
        }
    }
}
