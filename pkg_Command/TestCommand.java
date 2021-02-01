package pkg_Command;
import pkg_Engine.GameEngine;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
/**
 * La commande permettant de tester les fonctionnalites.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class TestCommand extends Command
{
    private CommandWords commandWords;
    /**
     * Constructeur, cree une nouvelle TestCommand.
     * @param words Le CommandWords correspondant
     */
    public TestCommand(CommandWords words)
    {
        this.commandWords = words;
    }
    
    /**
     * Execute la commande Test.
     * @param pGameEngine Le GameEngine faisant l'action
     */
    public void execute(GameEngine pGameEngine)
    {
        String vNomFichier = getSecondWord();
        pGameEngine.setTest(true);
        Scanner vSc;
        try { // pour "essayer" les instructions suivantes
            vSc = new Scanner( new File("TESTS/" + vNomFichier + ".txt" ) );
            while ( vSc.hasNextLine() ) {
                String vLigne = vSc.nextLine();
                pGameEngine.interpretCommand( vLigne );
                // traitement de la ligne lue
            } // while
        } // try
        catch ( final Exception pFNFE ) {
            pGameEngine.getGUI().print(" FICHIER NON TROUVÉ \n");
            // traitement en cas d'exception
        } // catch
        pGameEngine.getGUI().print(" test effectué\n");
        pGameEngine.setTest(false);
    }
}
