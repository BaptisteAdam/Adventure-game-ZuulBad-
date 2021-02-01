package pkg_Command;
import pkg_Engine.GameEngine;
import pkg_Componnants.pkg_Entities.MovingCharacter;
/**
 * La commande permettant de se deplacer.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class GoCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle GoCommand.
     * @param words Le CommandWords correspondant
     */
    public GoCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Go.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vDirection = getSecondWord();
        if(!pGameEngine.combat())
            if(!pGameEngine.getPlayer().encombrement())
            {
                if(vDirection==null) 
                {
                    // if there is no second word, we don't know where to go...
                    pGameEngine.getGUI().println("Go where ? \n");
                    return;
                }
                
                //si test win a été tapé. mot imcompréensible pour eviter de la taper manuellement
                if(vDirection.equals("gdkfjbfsdl") && pGameEngine.getTest() )
                    {
                        this.goRandom(pGameEngine, 5); return;
                    }
                else if(vDirection.equals("zonclsdcndsjkl") && pGameEngine.getTest() )
                    {
                        this.goRandom(pGameEngine, 1); return;
                    }
        
                String vChangeRoomOK = pGameEngine.getPlayer().changeRoom(vDirection);
                if(vChangeRoomOK.equals("mort"))
                    pGameEngine.death(" Exausted by this long journey, you take a nap.\n  " +  
                    "But it's to never wake up... \n  You may have been slayed by a slime.");
                else if(vChangeRoomOK.equals("true"))
                {
                    this.moveNPC(pGameEngine);
                    pGameEngine.printLocationInfo();
                    if(pGameEngine.getPlayer().getImage() != null)
                        pGameEngine.getGUI().showImage(pGameEngine.getPlayer().getImage());
                    pGameEngine.RoomAction();
                    pGameEngine.combatDragon();
                }
                else
                    pGameEngine.getGUI().println("There is no door ! \n");
            }
            else
                pGameEngine.getGUI().println("You are carrying too many items, you must drop some of them.\n");
        else
            pGameEngine.getGUI().println("You are in combat, you cannot move right now.\n");
    }
    
    /**
     * Avance dans une direction au hasard jusqu'a ce que
     * l'on soit dans la salle dont le type est passe en parametre.
     * N'A DE VOCATION A ETRE UTILISE QUE POUR UN TEST
     * @param pGameEngine Le GameEngine qui execute l'action
     * @param pType Le type de la Room dans laquelle on veux aller.
     */
    public void goRandom(final GameEngine pGameEngine, final int pType)
    {
        pGameEngine.getPlayer().setStats();
        int nbPassage = 0;
        while(pGameEngine.getPlayer().getRoomType()!=pType)
        {
            if(pGameEngine.combat())
            {
                Command vCommand2 = new AttackCommand(commandWords);
                pGameEngine.getGUI().println("attack" );
                vCommand2.execute(pGameEngine);
            }
            
            String vD = pGameEngine.getMap().genererDirection();
            Command vCommand = new GoCommand(commandWords);
            vCommand.setSecondWord(vD);
            pGameEngine.getGUI().println("go " + vD );
            vCommand.execute(pGameEngine);
            nbPassage++;
            if(nbPassage == 10000)
            {
                pGameEngine.getGUI().println("objectif inaccessible" );
                return;
            }
        }
    }

    /**
     * Permet de deplacer le MovingCharacter.
     * (tant qu'il n'a pas change de salle, on recommence l'action)
     * @param pGameEngine Le GameEngine executant l'action.
     */
    private void moveNPC(final GameEngine pGameEngine)
    {
        MovingCharacter vNPC = pGameEngine.getMap().getMovingNPC();
        if(vNPC!=null)
        {
            vNPC.getCurrentRoom().removeMovingNPC();        
            
            String vDirection = pGameEngine.getMap().genererDirection();
            boolean moveOK = vNPC.move(vDirection);
            while(!moveOK)
            {
                vDirection = pGameEngine.getMap().genererDirection();
                moveOK = vNPC.move(vDirection);
            }
            vNPC.getCurrentRoom().addMovingNPC(vNPC);  
        }   
    }
    }







