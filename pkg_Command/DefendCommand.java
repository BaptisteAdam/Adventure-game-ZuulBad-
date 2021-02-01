package pkg_Command;

import pkg_Engine.GameEngine;
import pkg_Componnants.Son;
/**
 * La commande permettant de se suicider.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class DefendCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle DefendCommand.
     * @param words Le CommandWords correspondant
     */
    public DefendCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Defend.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        if(pGameEngine.getPlayer().getMovingCharacter()!=null)
        {
            //Combat contre le Dragon
            int vPlayerDef = pGameEngine.getPlayer().getDef();
            
            int vDragonDef = pGameEngine.getPlayer().getMovingCharacter().getDef();
            int vDragonAtt = pGameEngine.getPlayer().getMovingCharacter().getAtt();
            
            //des nombres aléatoire entre 0 et 20 inclu. (correspond à un jet d'attaque (et de defence) )
            int vAttDragon = (int)Math.floor(Math.random()*21);
            int vDefDragon = (int)Math.floor(Math.random()*21);
            //
            int vDefPlayer = (int)Math.floor(Math.random()*11);
            
            //Attaque du Dragon
            Son.JouerLeSon("Sons/Defend.wav");
            if(vDragonAtt + vAttDragon >= 1.5*vPlayerDef + vDefPlayer)//L'attaque du Dragon passe la defence augmentée du Player
            {
                pGameEngine.getPlayer().changePV(-vDragonAtt/3);
                pGameEngine.getGUI().println("The Dragon broke through your defence and inflicted you some damages.\n");
                if(vAttDragon >= 20)//si coup critique
                {
                    pGameEngine.getPlayer().changePV(-vDragonAtt/3);
                    pGameEngine.getGUI().println("Critical Hit !");
                }
            }  
            else //L'attaque du Dragon ne passe pas la defence augmentée du Player, il se prends quelques dégats
            {
                if(vDefPlayer >= 9)//défense critique
                {
                    pGameEngine.getPlayer().getMovingCharacter().changePV(-2);
                    pGameEngine.getGUI().println("You were able to block the Dragon's attack,\n  and infliced him a few damages.\n");
                }
                else
                    pGameEngine.getGUI().println("You were able to block the Dragon's attack.\n");
            }
            //Afficher les stats du Player et du Dragon.
            pGameEngine.getGUI().println(pGameEngine.getPlayer().stats());
            pGameEngine.getGUI().println(pGameEngine.getPlayer().getMovingCharacter().stats());
            
            //Vérifier si l'un des deux est mort.
            if(pGameEngine.getPlayer().getMovingCharacter().getPV()<=0)
            {
                pGameEngine.getGUI().println("You slayed the Dragon. The path to become a renowned hero is on you.");
                pGameEngine.finCombat("Dragon");//Quitter le combat
                pGameEngine.getPlayer().removeMovingCharacter();
            }
            if(pGameEngine.getPlayer().getPV()<=0)
            {
                pGameEngine.deathByDragon();
            }
        }
        else
        {
            //Combat contre tout autre ennemi
            String vNom = pGameEngine.getEnnemi().getNom();
            
            int vPlayerDef = pGameEngine.getPlayer().getDef();
            
            int vEnnemiDef = pGameEngine.getEnnemi().getDef();
            int vEnnemiAtt = pGameEngine.getEnnemi().getAtt();
            
            //des nombres aléatoire entre 0 et 20 inclu.
            int vAttEnnemi = (int)Math.floor(Math.random()*21);
            int vDefEnnemi = (int)Math.floor(Math.random()*21);
            //Le Player n'attaque pas, comme sa défence est augmenté, son jet est un peu plus petit
            int vDefPlayer = (int)Math.floor(Math.random()*11);
            
            //Attaque de l'ennemi
            Son.JouerLeSon("Sons/Defend.wav");
            if(vEnnemiDef + vAttEnnemi >= vPlayerDef + vDefPlayer)
            {
                pGameEngine.getPlayer().changePV(-vEnnemiAtt/3);
                pGameEngine.getGUI().println("You weren't able to defend against the " + vNom + "'s attack \n  and took some damages." );
                if(vAttEnnemi >= 20)//si coup critique
                {
                    pGameEngine.getPlayer().changePV(-vEnnemiAtt/3);
                    pGameEngine.getGUI().println("Critical Hit !\n");
                }
                else
                    pGameEngine.getGUI().println("");
            }  
            else
            {
                if(vDefPlayer >= 9)
                {
                    pGameEngine.getEnnemi().changePV(-2);
                    pGameEngine.getGUI().println("You were able to block the " + vNom + "'s attack,\n  and infliced him a few damages.\n");
                }
                else
                    pGameEngine.getGUI().println("You were able to block the " + vNom + "'s attack.\n");
            }
            //Afficher les stats du Player et de l'ennemi.
            pGameEngine.getGUI().println(pGameEngine.getPlayer().stats());
            pGameEngine.getGUI().println(pGameEngine.getEnnemi().stats());    
            
            //Vérifier si l'un des deux est mort.
            if(pGameEngine.getEnnemi().getPV()<=0)
            {
                pGameEngine.getGUI().println("Your fight with the " + vNom + " is a victory !");
                pGameEngine.finCombat(vNom);//Quitter le combat
                pGameEngine.getPlayer().removeNPC(vNom);
                pGameEngine.removeEnnemi();
            }
            pGameEngine.zeroPV();
        }
    }
}


