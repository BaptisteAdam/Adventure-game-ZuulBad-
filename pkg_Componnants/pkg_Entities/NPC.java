package pkg_Componnants.pkg_Entities;

import pkg_Componnants.pkg_Items.Item;
/**
 * Write a description of class Character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC
{
    private String aNom;
    private String aDialogue;
    private String aImage;
    
    private Statistiques aStats;
    private int aPV ;
    private int aDef;
    private int aAgi;
    private int aAtt;
    
    private Item aItem;
    
    /**
     * Constructeur, cree un nouveau NPC.
     * @param pNom Une String, Le nom du NPC
     * @param pImage Une String, L'image du NPC
     * @param pDialogue Un tableau de String, Les phrases que prononce le NPC
     * @param pRace Une String, La race du NPC
     * @param pJob Une String, La classe du NPC
     * @param pItem L'Item que porte le NPC
     */
    public NPC(final String pNom, final String pImage, 
                     final String[] pDialogue, 
                     final String pRace, final String pJob,
                     final Item pItem)
    {
        this.aNom = pNom;
        this.aImage = pImage;
        int aNbRand = (int)Math.floor(Math.random()*pDialogue.length);
        this.aDialogue = pDialogue[aNbRand];
        this.aStats = new Statistiques();
        this.attribuerStats(pRace, pJob);
        this.aItem = pItem;
    }
    
    /**
    * Attribut les statistiques du NPC grace a sa race et sa classe (job)
    * @param pRace La arce du NPC 
    * @param pJob La classe du NPC
    */
    public void attribuerStats(final String pRace, final String pJob)
    {
       int vPV = this.aStats.getPV(pRace) + this.aStats.getPV(pJob);
       int vDef = this.aStats.getDef(pRace) + this.aStats.getDef(pJob);
       int vAgi = this.aStats.getAgi(pRace) + this.aStats.getAgi(pJob);
       int vAtt = this.aStats.getAtt(pRace) + this.aStats.getAtt(pJob);
       
       this.aPV = vPV;
       this.aDef = vDef;
       this.aAgi = vAgi;
       this.aAtt = vAtt;
    }
    
    /**
     * retourne le nom du NPC.
     * @return Une String
     */
    public String getNom()
    {
       return this.aNom;
    }
    
    /**
     * retourne le dialogue qui a ete selectionne pour le NPC.
     * @return Une String
     */
    public String getDialogue()
    {
       return this.aDialogue;
    }
    
    /**
     * retourne l'image du NPC.
     * @return Une String
     */
    public String getImage()
    {
        return this.aImage;
    }
    
    /**
    * Retourne les PV actuel du NPC.
    * @return Un int, les PV du NPC
    */
    public int getPV()
    {
       return this.aPV;
    }
   
    /**
     * Permet de faire varier les PV.
     * @param pPV La valeur a ajouter
     */
    public void changePV(final int pPV)
    {
       this.aPV += pPV;
    }
   
    /**
     * Retourne la Defense actuelle du NPC.
     * @return Un int, la Def du NPC
     */
    public int getDef()
    {
       return this.aDef;
    }
   
    /**
     * Permet de faire varier la Def.
     * @param pDef La valeur a ajouter
     */
    public void changeDef(final int pDef)
    {
        this.aDef += pDef;
    }
   
    /**
     * Retourne l'Agilite actuelle du NPC.
     * @return Un int, l'Agi du NPC
     */
    public int getAgi()
    {
        return this.aAgi;
    }
   
    /**
     * Permet de faire varier l'Agi.
     * @param pAgi La valeur a ajouter
     */
    public void changeAgi(final int pAgi)
    {
       this.aAgi += pAgi;
    }
   
    /**
     * Retourne l'Attaque actuelle du NPC.
     * @return Un int, l'Att du NPC
     */
    public int getAtt()
    {
       return this.aAtt;
    }
   
    /**
     * Permet de faire varier l'Att.
     * @param pAtt La valeur a ajouter
     */
    public void changeAtt(final int pAtt)
    {
       this.aAtt += pAtt;
    }
   
    /**
     * Affiche les statistiques du NPC.
     * @return Une String contenant toutes les statistiques du NPC
     */
    public String stats()
    {
       String vS = "The " + this.aNom + " : " +
                   "\n    PV: " + this.aPV + "   -   Def: " + this.aDef 
                 + "\n    Agi:" + this.aAgi + "   -   Att: " + this.aAtt + "\n";
       return vS;
    }
   
    /**
     * retourne l'Item que possede le NPC.
     * @return Un Item
     */
    public Item getItem()
    {
       return this.aItem;
    }
}
 




