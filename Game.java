import pkg_Engine.GameEngine;
import pkg_Engine.UserInterface;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class.
 * 
 *  This main class creates the necessary implementation objects and starts the game off.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2.0 (Jan 2003)
 */

public class Game
{
	private UserInterface aGui;
	private GameEngine aEngine;

    /**
     * Cree le jeu, un nouveau GUI et un nouveau GameEngine.
     */
    public Game() 
    {
		this.aEngine = new GameEngine();
		this.aGui = new UserInterface(aEngine);
		this.aEngine.setGUI(aGui);
    }
    
    public static void main(final String[] pArg)
    {
        new Game();
    }
}
