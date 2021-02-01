package pkg_Componnants;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Son extends Thread {
    private URL u1;//l'url de ton fichier son
    private static AudioClip s1;//le son créé depuis ton url
 
    public Son(final String pString)throws Exception {
        u1 = this.getClass().getClassLoader().getResource(pString);
         s1 = Applet.newAudioClip(u1);
    }
    
    public static void jouer() {
        s1.play();
    }
    
    public void jouerEnBoucle() {
        s1.loop();
    }
    
    public void arreter() {
        s1.stop();
    }
    
    public static void JouerLeSon(final String pString)
    {
        try { 
            Son vSon = new Son(pString);
            vSon.jouer();
        }
        catch(Exception pE){
            System.out.print("Fichier audio non trouvé");
        }
        
        
    }
}








