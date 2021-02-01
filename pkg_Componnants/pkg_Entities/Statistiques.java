package pkg_Componnants.pkg_Entities;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Stock toutes les informations (PV, Def, Agi, Att) 
 * sur toutes les Races et toutes la Classes.
 *
 * @author Baptiste ADAM
 * @version v3 - 30/05/2017
 */
public class Statistiques
{
   private HashMap<String, Integer> aPV;
   private HashMap<String, Integer> aDef;
   private HashMap<String, Integer> aAgi;
   private HashMap<String, Integer> aAtt;
   
   private ArrayList<Integer> aLvlUp; 
   //L'indice de l'ArrayList correspond au niveau actuel du joueur. 
   //L'int correspond a l'exp qu'il faut pour lvl up.
   private HashMap<String, Integer> aExp;
   
   /**
    * Constructeur, cree les hashmaps contenant les statistiques 
    * de chaque race et de chaque classe triée par statistique.
    */
   public Statistiques()
   {
       this.aPV = new HashMap<String, Integer>();
       this.aPV.put("human", 7);
       this.aPV.put("elf", 4);
       this.aPV.put("dwarf", 8);
       this.aPV.put("half-orc", 10);
       this.aPV.put("feral", 6);
       this.aPV.put("drakeïde", 7);
       this.aPV.put("vampire", 4);
       this.aPV.put("goliath", 9);
       this.aPV.put("arachnean", 6);
       this.aPV.put("warrior", 10);
       this.aPV.put("rogue", 6);
       this.aPV.put("wizard", 6);
       this.aPV.put("paladin", 8);
       this.aPV.put("berserker", 10);
       this.aPV.put("sword_mage", 8);
       this.aPV.put("assassin", 4);
       this.aPV.put("protector", 10);
       int aNbRand = 3+(int)Math.floor(Math.random()*10);
       //le mandiant possede des stats aléatoire, 
       //elles peuvent donc être mieux que celles des autres classes
       this.aPV.put("beggard", aNbRand);
       this.aPV.put("dragon", 12);
       this.aPV.put("zombies", 155);
       this.aPV.put("specter", 1);
       this.aPV.put("chicken", 1);
       this.aPV.put("dhovah", 25);
       
       
       this.aDef = new HashMap<String, Integer>();
       this.aDef.put("human", 7);
       this.aDef.put("elf", 6);
       this.aDef.put("dwarf", 10);
       this.aDef.put("half-orc", 6);
       this.aDef.put("feral", 6);
       this.aDef.put("drakeïde", 8);
       this.aDef.put("vampire", 4);
       this.aDef.put("goliath", 9);
       this.aDef.put("arachnean", 8);
       this.aDef.put("warrior", 6);
       this.aDef.put("rogue", 5);
       this.aDef.put("wizard", 4);
       this.aDef.put("paladin", 10);
       this.aDef.put("berserker", 4);
       this.aDef.put("sword_mage", 6);
       this.aDef.put("assassin", 4);
       this.aDef.put("protector", 10);
       int aNbRand2 = 3+(int)Math.floor(Math.random()*10);
       this.aDef.put("beggard", aNbRand2);
       this.aDef.put("dragon", 12);
       this.aDef.put("zombies", 2);
       this.aDef.put("specter", 12);
       this.aDef.put("chicken", 1);
       this.aDef.put("dhovah", 25);
       
       this.aAgi = new HashMap<String, Integer>();
       this.aAgi.put("human", 7);
       this.aAgi.put("elf", 10);
       this.aAgi.put("dwarf", 4);
       this.aAgi.put("half-orc", 4);
       this.aAgi.put("feral", 10);
       this.aAgi.put("drakeïde", 3);
       this.aAgi.put("vampire", 10);
       this.aAgi.put("goliath", 2);
       this.aAgi.put("arachnean", 8);
       this.aAgi.put("warrior", 4);
       this.aAgi.put("rogue", 10);
       this.aAgi.put("wizard", 8);
       this.aAgi.put("paladin", 4);
       this.aAgi.put("berserker", 4);
       this.aAgi.put("sword_mage", 4);
       this.aAgi.put("assassin", 10);
       this.aAgi.put("protector", 4);
       int aNbRand3 = 3+(int)Math.floor(Math.random()*10);
       this.aAgi.put("beggard", aNbRand3);
       this.aAgi.put("dragon", 12);
       this.aAgi.put("zombies", 2);
       this.aAgi.put("specter", 16);
       this.aAgi.put("chicken", 1);
       this.aAgi.put("dhovah", 25);
       
       this.aAtt = new HashMap<String, Integer>();
       this.aAtt.put("human", 7);
       this.aAtt.put("elf", 8);
       this.aAtt.put("dwarf", 6);
       this.aAtt.put("half-orc", 8);
       this.aAtt.put("feral", 6);
       this.aAtt.put("drakeïde", 10);
       this.aAtt.put("vampire", 10);
       this.aAtt.put("goliath", 8);
       this.aAtt.put("arachnean", 6);
       this.aAtt.put("warrior", 8);
       this.aAtt.put("rogue", 7);
       this.aAtt.put("wizard", 10);
       this.aAtt.put("paladin", 6);
       this.aAtt.put("berserker", 10);
       this.aAtt.put("sword_mage", 10);
       this.aAtt.put("assassin", 10);
       this.aAtt.put("protector", 4);
       int aNbRand4 = 3+(int)Math.floor(Math.random()*10);
       this.aAtt.put("beggard", aNbRand4);
       this.aAtt.put("dragon", 12);
       this.aAtt.put("zombies", 3);
       this.aAtt.put("specter", 15);
       this.aAtt.put("chicken", 1);
       this.aAtt.put("dhovah", 25);
       
       this.aLvlUp = new ArrayList<Integer>();
       this.aLvlUp.add(0); //besoin de 0 exp pour monter au lvl 1 / juste pour ajuster l'indice au level du joueur
       this.aLvlUp.add(10);//lvl 1 -> 2  (indice 1)
       this.aLvlUp.add(20);//lvl 2 -> 3  (indice 2)
       this.aLvlUp.add(30);//etc..
       this.aLvlUp.add(40);
       this.aLvlUp.add(50);
       
       this.aExp = new HashMap<String, Integer>();
       this.aExp.put("", 0);
       this.aExp.put("Horde of zombies", 8);//La horde de zombies donne 8 exp.
       this.aExp.put("Chicken", 1);
       this.aExp.put("Dhovah-Chicken", 20);
       this.aExp.put("Dragon", 15);
       this.aExp.put("Fatal Scythe", 12);
       this.aExp.put("Mounted chicken", 4);
       this.aExp.put("Giant toad", 5);
       this.aExp.put("Adventurer", 5);
       this.aExp.put("Skeleton", 3);
   }
   
   /**
    * retourne  les PV de la Race ou de la Classe passe en parametre.
    * @param pS La string de la Race ou la Classe
    * @return Une int (les PVs)
    */
   public int getPV(final String pS)
   {
       return this.aPV.get(pS);
   }
   
   /**
    * retourne  la Defence de la Race ou de la Classe passe en parametre.
    * @param pS La string de la Race ou la Classe
    * @return Une int (la Defence)
    */
   public int getDef(final String pS)
   {
       return this.aDef.get(pS);
   }
   
   /**
    * retourne  l'Agilite de la Race ou de la Classe passe en parametre.
    * @param pS La string de la Race ou la Classe
    * @return Une int (l'Agilite)
    */
   public int getAgi(final String pS)
   {
       return this.aAgi.get(pS);
   }
   
   /**
    * retourne  les l'Attaque de la Race ou de la Classe passe en parametre.
    * @param pS La string de la Race ou la Classe
    * @return Une int (l'Attaque)
    */
   public int getAtt(final String pS)
   {
       return this.aAtt.get(pS);
   }
   
   /**
    * retourne l'exp qu'il faut au Player pour level up.
    * @param pLvl Un int, le level actuel du joueur
    * @return Un int, l'exp pour lvl up
    */
   public int getLvlUp(final int pLvl)
   {
       return this.aLvlUp.get(pLvl);
   }
   
   /**
    * retourne l'exp que donne un monstre quand il est tue.
    * @param pNom Un String, le nom du monstre
    * @return Un int, l'exp obtenu
    */
   public int getExp(final String pNom)
   {
       return this.aExp.get(pNom);
   }
}









