package pkg_Command;

import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class CommandWords
{
    private static HashMap<String, CommandWord> sValidCommands;
    private HashMap<String, Command> aCommands;
    /**
     * Constructeur par defaut
     */
    public CommandWords()
    {
        this.sValidCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                this.sValidCommands.put(command.toString(), command);
            }
        }
        
        this.aCommands = new HashMap<String, Command>();
        this.aCommands.put("help", new HelpCommand(this));
        this.aCommands.put("race", new RaceCommand(this));
        this.aCommands.put("job", new ClasseCommand(this));
        this.aCommands.put("go", new GoCommand(this));
        this.aCommands.put("back", new BackCommand(this));
        this.aCommands.put("suicide", new SuicideCommand(this));
        this.aCommands.put("look", new LookCommand(this));
        this.aCommands.put("inventory", new InventoryCommand(this));
        this.aCommands.put("test", new TestCommand(this));
        this.aCommands.put("take", new TakeCommand(this));
        this.aCommands.put("drop", new DropCommand(this));
        this.aCommands.put("eat", new EatCommand(this));
        this.aCommands.put("orb", new BeamerCommand(this));
        this.aCommands.put("alea", new AleaCommand(this));
        this.aCommands.put("flee", new FleeCommand(this));
        this.aCommands.put("attack", new AttackCommand(this));
        this.aCommands.put("defend", new DefendCommand(this));
        this.aCommands.put("stats", new StatsCommand(this));
    }// CommandWords()

    /**
     * Find the CommandWord associated with a command word.
     * @param pCommandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Command getCommandWord(String pCommandWord)
    {
        return (Command)this.aCommands.get(pCommandWord);
    }
    
    /**
     * Verifie si une String donnee fait partie des commandes valides. 
     * @param pString la String a tester
     * @return true si pString est une comande valide, false sinon
     */
    public boolean isCommand( final String pString )
    {
         return this.sValidCommands.containsKey(pString);
    } // isCommand()
    
    /**
     * Retourne toute les commandes valides.
     * @return La liste de toutes les commandes valides.
     */
    public static String getCommandList()
    {
        String vCommand = "";
        int vNbCommand = 0;
        for(String command : sValidCommands.keySet())
        {
            switch(command)
            {
                case "test": break;
                case "race": break;
                case "job" : break;
                case "alea": break;
                case "stats" : break;
                default : vCommand += command + "   "; vNbCommand++; break;
            }
            if(vNbCommand == 5)
                vCommand += "\n   ";
            
        }
        return vCommand;
    }
} // CommandWords







