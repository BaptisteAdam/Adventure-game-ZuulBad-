package pkg_Command;
/**

 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
    UNKNOWN("?"), SUICIDE("suicide"), HELP("help"), LOOK("look"), EAT("eat"),
    INVENTORY("inventory"), JOB("job"), RACE("race"), TEST("test"),
    TAKE("take"), DROP("drop"), BEAMER("orb"), GO("go"), BACK("back"),
    ALEA("alea");
    
     // The command string.
    private String aCommandString;
    
    /**
     * Initialise with the corresponding command word.
     * @param pCommandString The command string.
     */
    CommandWord(String pCommandString)
    {
        this.aCommandString = pCommandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return this.aCommandString;
    }
}
