package pkg_Command;

import pkg_Engine.GameEngine;
import pkg_Componnants.Son;
/**
 * La commande permettant de se suicider.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class AttackCommand extends Command
{
    private CommandWords commandWords;
    
    /**
     * Constructeur, cree une nouvelle AttackCommand.
     * @param words Le CommandWords correspondant
     */
    public AttackCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Attack.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        if(pGameEngine.getPlayer().getMovingCharacter()!=null)
        {
            //Combat contre le Dragon
            int vPlayerDef = pGameEngine.getPlayer().getDef();
            int vPlayerAtt = pGameEngine.getPlayer().getAtt();
            
            int vDragonDef = pGameEngine.getPlayer().getMovingCharacter().getDef();
            int vDragonAtt = pGameEngine.getPlayer().getMovingCharacter().getAtt();
            
            //des nombres aléatoire entre 0 et 20 inclu. (correspond a un jet d'attaque (et de defence) )
            int vAttPlayer = (int)Math.floor(Math.random()*21);
            int vAttDragon = (int)Math.floor(Math.random()*21);
            int vDefPlayer = (int)Math.floor(Math.random()*21);
            int vDefDragon = (int)Math.floor(Math.random()*21);
            
            //Attaque du Player
            Son.JouerLeSon("Sons/Attack.wav");
            if(vPlayerAtt + vAttPlayer >= vDragonDef + vDefDragon)//L'attaque du Player passe la defence du Dragon
            {
                pGameEngine.getPlayer().getMovingCharacter().changePV(-vPlayerAtt/3);
                pGameEngine.getGUI().println("You inficted some damages to the Dragon.");
                if(vAttPlayer >= 20)//si coup critique
                {
                    pGameEngine.getPlayer().getMovingCharacter().changePV(-vPlayerAtt/3);
                    pGameEngine.getGUI().println("Critical Hit !");
                }
            }
            else //L'attaque du player ne passe pas la defence du Dragon
                pGameEngine.getGUI().println("The Dragon scales were to thick for your attack to damage him.");
            
            //Attaque du Dragon
            if(vPlayerDef <= 0) // si le player n'a pas e défence, il meurt
                pGameEngine.death("The Dragon sliced you in half.");
            else if(vDragonAtt + vAttDragon >= vPlayerDef + vDefPlayer)//L'attaque du Dragon passe la defence du Player
            {
                pGameEngine.getPlayer().changePV(-vDragonAtt/3);
                pGameEngine.getGUI().println("The Dragon broke through your defence and inflicted you some damages.");
                if(vAttDragon >= 20)//si coup critique
                {
                    pGameEngine.getPlayer().changePV(-vDragonAtt/3);
                    pGameEngine.getGUI().println("Critical Hit !\n");
                }
                else
                    pGameEngine.getGUI().println("");
            }  
            else //L'attaque du Dragon ne passe pas la defence du Player
                pGameEngine.getGUI().println("You dodged the attack of the Dragon.\n");
            
            //Afficher les stats du Player et du Dragon.
            pGameEngine.getGUI().println(pGameEngine.getPlayer().stats());
            pGameEngine.getGUI().println(pGameEngine.getPlayer().getMovingCharacter().stats());
            
            //Vérifier si l'un des deux est mort.
            if(pGameEngine.getPlayer().getMovingCharacter().getPV()<=0)//le dragon est Mort
            {
                pGameEngine.getGUI().println("You slayed the Dragon. The path to become a renowned hero is on you.");
                pGameEngine.finCombat("Dragon");//Quitter le combat
                pGameEngine.getPlayer().removeMovingCharacter();
            }
            if(pGameEngine.getPlayer().getPV()<=0)//Le Player est mort
            {
                pGameEngine.deathByDragon();
            }
        }
        else
        {
            //Combat contre tout autre ennemi
            String vNom = pGameEngine.getEnnemi().getNom();
            
            int vPlayerDef = pGameEngine.getPlayer().getDef();
            int vPlayerAtt = pGameEngine.getPlayer().getAtt();
            
            int vEnnemiDef = pGameEngine.getEnnemi().getDef();
            int vEnnemiAtt = pGameEngine.getEnnemi().getAtt();
            
            int vAttPlayer = (int)Math.floor(Math.random()*21);//des nombres aléatoire entre 0 et 20 inclu.
            int vAttEnnemi = (int)Math.floor(Math.random()*21);
            int vDefPlayer = (int)Math.floor(Math.random()*21);
            int vDefEnnemi = (int)Math.floor(Math.random()*21);
            
            //Attaque du Player
            Son.JouerLeSon("Sons/Attack.wav");
            if(vPlayerAtt + vAttPlayer >= vEnnemiDef + vDefEnnemi)
            {
                pGameEngine.getEnnemi().changePV(-vPlayerAtt/3);
                pGameEngine.getGUI().println("You inficted some damages to the " + vNom + ".");
                if(vAttPlayer >= 20)//si coup critique
                {
                    pGameEngine.getEnnemi().changePV(-vPlayerAtt/3);
                    pGameEngine.getGUI().println("Critical Hit !");
                }
            }
            else
                pGameEngine.getGUI().println("The defence of the " + vNom + " is sturdy enough to resist your attack.");
                
            //Attaque de l'ennemi
            if(vPlayerDef <= 0)
                pGameEngine.death("The " + vNom + " sliced you in half.");
            else if(vEnnemiDef + vAttEnnemi >= vPlayerDef + vDefPlayer)
            {
                pGameEngine.getPlayer().changePV(-vEnnemiAtt/3);
                pGameEngine.getGUI().println("You weren't able to defend against the " + vNom + "'s attack \n  and took some damages." );
                if(vAttEnnemi >= 20)//si coup critique
                {
                    pGameEngine.getPlayer().changePV(-vPlayerAtt/3);
                    pGameEngine.getGUI().println("Critical Hit !\n");
                }
                else
                    pGameEngine.getGUI().println("");
            }  
            else
                pGameEngine.getGUI().println("You were able to parry the " + vNom + "'s attack.\n");
            
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


