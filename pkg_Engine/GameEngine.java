package pkg_Engine;
import pkg_Command.Parser;
import pkg_Command.Command;
import pkg_Componnants.pkg_Entities.Player;
import pkg_Componnants.Map;
import pkg_Componnants.pkg_Entities.NPC;
import pkg_Componnants.Son;

import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
public class GameEngine
{
    private Parser aParser;
    private Player aPlayer;
    private UserInterface aGui;
    private Map aMap ;
    private boolean aTest = false;
    private boolean aCombat = false;
    private boolean aPassageSalle6 = false;
    private NPC aEnnemi = null;
    
    /**
     * Constructeur, cree un nouveau GameEngine.
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aMap = new Map();
        this.aPlayer = new Player(this.aMap.get(0));
    }

    /**
     * Cree un nouveau GUI.
     * @param userInterface le userInterface cree auparavant.
     */
    public void setGUI(UserInterface userInterface)
    {
        this.aGui = userInterface;
        this.printStart();
    }

    /**
     * Affiche un message d'instruction pour choisir sa race puis sa classe.
     */
    private void printStart()
    {
        this.aGui.print("\n");
        this.aGui.println("You are going to define yourself,");
        this.aGui.println("theses choises will impact your abilities.");
        this.aGui.println("So, choose carefully...\n");
        this.aGui.println("Please, choose a race.");
        this.aGui.showImage("Images/race.png");
    }
    
    /**
     * Affiche le message de "bienvenue".
     */
    public void printWelcome()
    {
        this.aGui.print("\n");
        this.aGui.println("You are an adventurer with neither fame nor name. ");
        this.aGui.println("Thus, you accepted a quest from an old wizard.");
        this.aGui.println("You have to retrieve the encient artifact : \n  the statuette of Anbrodema.\n");
        this.aGui.println("Legends tell that it is hidden in the depths \n  of the malicious dungeon of Xaar-Sharoth ! ");
        this.aGui.println("Your legend starts here and now !");
        this.aGui.println("\n  Type 'help' if you need help.");
        this.aGui.print("\n");
        this.aGui.println(this.aPlayer.stats());
        this.printLocationInfo();
        this.aGui.showImage(this.aPlayer.getImage());
    }
    
    /**
     * Recupere les information sur la aCurrentRoom et les affiche.
     */
    public void printLocationInfo()
    {
        this.aGui.println(this.aPlayer.getLocationInfo());
    }//printLocationInfo()
    
    /**
     * Avec une commande donnee, l'execute.
     * si cette commande finis le jeu, retourne true, sinon retourne false.
     * @param pCommandLine le string correspondant a la commande.
     */
    public void interpretCommand(String pCommandLine) 
    {
        this.aGui.println(pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);
        if(vCommand == null) {
            this.aGui.println("I don't know what you mean...");
        } 
        else {
            vCommand.execute(this);
        }
    }
    
    /**
     * Certaines Rooms ayant des actions a effectuer
     * (baisser la defence, baisser les PVs, afficher les statistiques du Player, lancer un combat, ...)
     * Ces actions sont faites ici.
     */
    public void RoomAction()
    {
        int vTypeRoom = this.aPlayer.getRoomType();
        if(this.aPlayer.getNbRoomChickenized() == 5)
        {
            this.aPlayer.attribuerStats();
            this.aPlayer.reequip();
            for(int i=0 ; i < this.aPlayer.getLvl() ; i++)
                        this.aPlayer.levelUp("", false);
            this.aPlayer.resetNbRoomChickenized();
            this.aPlayer.setChickenized(false);
            this.aGui.println("You recovered from the chickenizing spell.\n");
        }
        if(this.aMap.getMovingNPC()!=null && this.aMap.getMovingNPC().getCurrentRoom().getType()==20)
        {
            //if(this.aMap.getMovingNPC().getCurrentRoom().getType()==20)  
            {
                if(this.aMap.getMovingNPC().getCurrentRoom().getNPC("Chicken")!=null)
                {
                    int aNbRand = (int)Math.floor(Math.random()*21);
                    if(aNbRand>= 0)//le poulet tue le dragon et absorbe ses stats
                    {
                        this.aMap.getMovingNPC().getCurrentRoom().removeNPC("Chicken");
                        String[] vTabDial = {"Cot coot"};
                        NPC vNPC = new NPC("Dhovah-Chicken", "Images/dovhaPoulet.png", vTabDial, "dhovah", "beggard", null);
                        this.aMap.getMovingNPC().getCurrentRoom().addNPC(vNPC);
                        this.aMap.getMovingNPC().getCurrentRoom().removeMovingNPC();
                        this.aGui.println("You hear a frightful roar somewhere in the dungeon." );
                        Son.JouerLeSon("Sons/DragonRoar.wav");
                    }
                }
            }
        }
        switch(vTypeRoom)
        {
            case 2 : 
                this.aGui.println("Suddenly, a wizard appears before you.");
                int aNbRand = (int)Math.floor(Math.random()*4+1);
                if (aNbRand == 1)
                {
                   this.aGui.println("It's a black wizard, you're now cursed.");
                   this.aPlayer.changeDef(-2);
                }
                else if(aNbRand == 2)
                {
                   this.aGui.println("It's a pyromancer, he throws a fireball at you.");
                   this.aPlayer.changePV(-5);
                   this.zeroPV();
                }
                else if(aNbRand == 3)
                {
                   this.aGui.println("It's a light wizard, you're now blessed.");
                   this.aPlayer.changeDef(+2);
                }
                else 
                {
                   this.aGui.println("It's a necromancer, the dead bodies that where laying around\n  attack you.");
                   String[] vTabDial = {"Kak kakak kak."};
                   this.demarrerCombat( new NPC("Skeleton", "Images/skeleton.jpg", vTabDial, "drakeïde", "assassin", null) );
                   this.aGui.setFlee(false);
                }
                this.aGui.println("");
                    break;
            case 3 :
                this.aGui.println(this.aPlayer.stats());
                    break;
            case 4 :
                 if(this.aPlayer.getNPC("Adventurer")!=null)
                    if(this.aPlayer.getNPCMessage("Adventurer").equals("Death !"))
                        this.demarrerCombat(this.aPlayer.getNPC("Adventurer"));   
                    break; 
            case 6 :
                if(!aPassageSalle6)
                {
                    Son.JouerLeSon("Sons/Porte.wav");
                    aPassageSalle6 = true;
                }
            case 12 : 
                if(this.aPlayer.getNPC("Horde of zombies")!=null)
                    this.demarrerCombat(this.aPlayer.getNPC("Horde of zombies")); 
                    break;
            case 13 : 
                double AgiPlayer = Math.floor(Math.random()*21);
                double AgiFleche = Math.floor(Math.random()*21);
                if(this.aPlayer.getAgi() <= 0) 
                    this.death("You took an arrow in the knee and died on the move.");
                else if(this.aPlayer.getAgi() + AgiPlayer <= 15 + AgiFleche)
                {
                    this.aGui.println("You took an arrow in the back.\n");
                    this.aPlayer.changePV(-2);
                    this.aPlayer.changeAgi(-1);
                    this.zeroPVPiege();
                }
                else
                {
                    this.aGui.println("You dodged the arrow that were thrown at your back.\n");
                    this.aPlayer.changeAgi(-1);
                }
                    break;
            case 14 : 
                if(!this.aTest)
                {
                    this.aPlayer.attribuerStats();
                    this.aPlayer.reequip();
                    for(int i=0 ; i < this.aPlayer.getLvl() ; i++)
                        this.aPlayer.levelUp("", false);
                    this.aGui.println("After drinking at the fountain,\n  you feel the energy regaining your body.\n");
                }
                    break;
            case 15 : 
                AgiPlayer = Math.floor(Math.random()*21);
                AgiFleche = Math.floor(Math.random()*21);
                if(this.aPlayer.getAgi() <= 0) 
                    this.death("You fell into a pit full of spikes and died on the spot.");
                else if(this.aPlayer.getAgi() + AgiPlayer <= 10 + AgiFleche)
                {
                    this.aGui.println("You fell into a pit full of spikes but grabbed the edge at the last second,\n  you just suffer from a flesh wound.\n");
                    this.aPlayer.changePV(-4);
                    this.aPlayer.changeAgi(-1);
                    this.zeroPVPiege();
                }
                else
                {
                    this.aGui.println("Just before falling into a pit full of spikes, you stopped your move.\n");
                    this.aPlayer.changeAgi(-1);
                }
                    break;
            case 16 : 
                if(this.aPlayer.getNPC("Giant toad")!=null)
                    this.demarrerCombat(this.aPlayer.getNPC("Giant toad")); 
                    break;
            case 17 : 
                if(this.aPlayer.getNPC("Fatal Scythe")!=null)
                {
                    this.demarrerCombat(this.aPlayer.getNPC("Fatal Scythe")); 
                    this.aGui.setFlee(false);
                }
                    break;
            case 18 : 
                if(this.aPlayer.getNPC("Mounted chicken")!=null)
                {
                    this.demarrerCombat(this.aPlayer.getNPC("Mounted chicken")); 
                }
                    break;
            case 19 : 
                if(!this.aPlayer.chickenized() && !this.aTest)
                {
                    this.aPlayer.setChickenized(true);
                    this.aPlayer.setPV(2);
                    this.aPlayer.setAgi(2);
                    this.aPlayer.setDef(2);
                    this.aPlayer.setAtt(2);
                    this.aGui.println("You have been transformed into a chicken by a magic rune.\n");
                }
                    break;
            case 20 : 
                if(this.aPlayer.getNPC("Chicken")!=null)
                {
                    this.demarrerCombat(this.aPlayer.getNPC("Chicken")); 
                }
                else if(this.aPlayer.getNPC("Dhovah-Chicken")!=null)
                {
                    this.demarrerCombat(this.aPlayer.getNPC("Dhovah-Chicken")); 
                }
                    break;
        }
    }
    
    /**
     * Lancer le combat contre le dragon (MovingCharacter)
     */
    public void combatDragon()
    {
        if(this.aPlayer.isMovingCharacter())
        {
            if(!this.aCombat)
            {
                this.aGui.combatInterface();
                this.setCombat(true);
            }
            this.aGui.showImage(this.aPlayer.getMovingCharacter().getImage());
        }
    }
    
    /**
     * Lancer le combat contre tout autre ennemi que le dragon.
     * @param pNPC Le NPC contre qui le Player se bat
     */
    public void demarrerCombat(final NPC pNPC)
    {
        this.aEnnemi = pNPC;
        this.aGui.combatInterface();
        this.setCombat(true);
        this.aGui.showImage(pNPC.getImage());
    }
    
    /**
     * Arrete le combat
     * Remet l'interface d'exploration
     * @param pNopm Une String, le nom du monstre vaincu
     */
    public void finCombat(final String pNom)
    {
        this.aGui.panelDeplacement();
        this.setCombat(false);
        this.aGui.setFlee(true);//si c'était un combat impossible a fuir, rétablir la fuite
        this.aGui.println( this.aPlayer.levelUp(pNom, true) );
        this.aGui.println( this.aPlayer.loot(pNom) );
        if(aPlayer.possessBeamer())
            aGui.setBeamer(true);
        this.aGui.println("");
        if(this.aPlayer.getImage() != null)
            this.aGui.showImage(this.aPlayer.getImage());
    }
    
    
    //touts les getters et setters
    /**
     * retourne le UserInterface.
     * @return Une UserInterface
     */
    public UserInterface getGUI()
    {
        return this.aGui;
    }
    
    /**
     * retourne le Parser.
     * @return Un Parser
     */
    public Parser getParser()
    {
        return this.aParser;
    }
    
    /**
     * retourne la Player.
     * @return Un Player
     */
    public Player getPlayer()
    {
        return this.aPlayer;
    }
    
    /**
     * retourne aTest. Permet de savoir si le jeu est en phase de test.
     * @return Un boolean
     */
    public boolean getTest()
    {
        return this.aTest;
    }
    
    /**
     * Permet de changer la valeur de aTest.
     * @param pTest Un boolean, la nouvelle valeur de aTest
     */
    public void setTest(final boolean pTest)
    {
        this.aTest = pTest;
    }
    
    /**
     * retourne la Map.
     * @return Une Map
     */
    public Map getMap()
    {
        return this.aMap;
    }
    
    /**
     * Permet de changer la valeur de aCombat.
     * @param pBoolean Un boolean, la nouvelle valeur de aCombat
     */
    public void setCombat(final boolean pBoolean)
    {
        this.aCombat = pBoolean;
    }
    
    /**
     * retourne aCombat
     * @return Un boolean
     */
    public boolean combat()
    {
        return this.aCombat;
    }
    
    /**
     * Permet d'obtenir le NPC que l'on combat.
     * @return Un NPC
     */
    public NPC getEnnemi()
    {
        return this.aEnnemi;
    }
    
    /**
     * Permet de supprimer le NPC que l'on combat ((une fois le combat gagne)
     */
    public void removeEnnemi()
    {
        this.aEnnemi = null;
    }
  
    
    //Finir le jeu
    /**
     * verifie si le Player a plus de 0 PV (CaD qu'il est en vie)
     * Sinon, tue le Player.
     */
    public void zeroPV()
    {
        if(this.aPlayer.getPV()<= 0 )
            this.death("Your ennemy was too powerful. You died while fighting him.");
    }
    
    /**
     * verifie si le Player a plus de 0 PV (CaD qu'il est en vie)
     * Sinon, tue le Player.
     */
    public void zeroPVPiege()
    {
        if(this.aPlayer.getPV()<= 0 )
            this.death("You died because of the vicious traps of the dungeon.");
    }
    
    /**
     * Message de mort et image affiche si le joueur meurt pendant son combat contre le dragon.
     */
    public void deathByDragon()
    {
        this.aGui.println("The Dragon roasted you to death.");
        this.aGui.showImage( "Images/DeathDragon.jpg");
        this.aGui.enable(false);
        Son.JouerLeSon("Sons/DeathByDragon.wav");
    }
    
    /**
     * Permet d'arreter le jeu avec un message de mort.
     * Affiche une image "Game Over".
     * @param pMessage Le message que l'on veut afficher
     */
    public void death(final String pMessage)
    {
        this.aGui.println(pMessage);
        this.aGui.showImage( "Images/Game Over.jpg");
        this.aGui.enable(false);
        Son.JouerLeSon("Sons/Death.wav");
    }
    
    /**
     * Permet d'arreter le jeu avec un message de victoire.
     * Affiche une image "Win".
     */
    public void endGameWin()
    {
        this.aGui.println("\n  You are now rich and well known. \n  You have whatever you wanted to have. \n  \n  Thanks for playing.");
        this.aGui.showImage( "Images/Win.jpg");
        Son.JouerLeSon("Sons/Win.wav");
        this.aGui.enable(false);
    }
    
}








