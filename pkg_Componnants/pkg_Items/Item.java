package pkg_Componnants.pkg_Items;

public class Item
{
    private String aDescription;
    private double aWeight;
    private String aMotclef;
    
    /**
     * Constructeur, cree un nouveau Item.
     * @param pDescription La description de l'item.
     * @param pWeight Le poids de l'item.
     * @param pMotclef Le motclef de l'item.
     */
    public Item(final String pDescription, final double pWeight, final String pMotclef)
    {
        this.aDescription = pDescription;
        this.aWeight = pWeight;
        this.aMotclef = pMotclef;
    }
    
    /**
     * Retourne la description de l'item.
     * @return La description de l'item.
     */
    public String getDescription()
    {
        return this.aDescription ;
    }
    
    /**
     * Retourne le poids de l'item.
     * @return Le poids de l'item.
     */
    public double getWeight()
    {
        return this.aWeight ;
    }
    
    /**
     * Retourne le motclef de l'item.
     * @return Le motclef de l'item.
     */
    public String getMotclef()
    {
        return this.aMotclef;
    }
}

