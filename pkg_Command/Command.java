package pkg_Command;
import pkg_Engine.GameEngine;

public abstract class Command
{

    private String aSecondWord;
    
    /**
     * Constructeur, cree une nouvelle commande.
     */
    public Command()
    {
        this.aSecondWord = null;
    } //Command()
    
    /**
     * Permet d'obtenir la chaine de caracteres de "this.aSecondWord".
     * @return Retourne le second mot de la commande.
     */
    public String getSecondWord()
    {
        return this.aSecondWord; 
    } //getSecondWord()
    
    /**
     * Définie le second de la commande. 
     * null si il n'y en a pas.
     * @param pSecondWord Le second mot de la commande
     */
    public void setSecondWord(String pSecondWord)
    {
        this.aSecondWord = pSecondWord;
    }
    
    /**
     * Permet de savoir si il y a un second mot (dans "this.aSecondWord".).
     * @return true ou false 
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord!=null;
    } //hasSecondWord()
    
    /**
     * Execute la commande. 
     * @param pGameEngine Le gameEngine gerant le jeu
     * 
     */
    public abstract void execute(GameEngine pGameEngine);
    
} // Command























