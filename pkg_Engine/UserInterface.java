package pkg_Engine;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.GridLayout; 
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.net.URL;

import javax.swing.JFrame; 
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling (DB edited) + Baptiste Adam modified the jFrame.
 * @version 1.0 (Jan 2003)
 */
public class UserInterface extends JFrame implements ActionListener 
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    
    private ArrayList<JButton> aAllButtons;
    private JButton aBoutonWest = new JButton("go west");
    private JButton aBoutonNorth = new JButton("go north");
    private JButton aBoutonSouth = new JButton("go south");
    private JButton aBoutonEast = new JButton("go east");
    private JButton aBoutonUp = new JButton("go up");
    private JButton aBoutonDown = new JButton("go down");
    private JButton aBoutonLook = new JButton("look");
    private JButton aBoutonSuicide = new JButton("suicide");
    private JButton aBoutonBack = new JButton("back");
    private JButton aBoutonInventory = new JButton("inventory");
    private JButton aBoutonTake = new JButton("take");
    private JButton aBoutonDrop = new JButton("drop");
    private JButton aBoutonEat = new JButton("eat");
    private JButton aBoutonBeamer = new JButton("orb");
    
    private JButton aBoutonHumain = new JButton("human");
    private JButton aBoutonElfe = new JButton("elf");
    private JButton aBoutonNain = new JButton("dwarf");
    private JButton aBoutonDemiOrc = new JButton("half-orc");
    private JButton aBoutonFeral = new JButton("feral");
    private JButton aBoutonDrakeide = new JButton("drakeïde");
    private JButton aBoutonVampire = new JButton("vampire");
    private JButton aBoutonGoliath = new JButton("goliath");
    private JButton aBoutonArachnean = new JButton("arachnean");
    
    private JButton aBoutonWarrior = new JButton("warrior");
    private JButton aBoutonWizard = new JButton("wizard");
    private JButton aBoutonRogue = new JButton("rogue");
    private JButton aBoutonPaladin = new JButton("paladin");
    private JButton aBoutonBerserker = new JButton("berserker");
    private JButton aBoutonWeaponMage = new JButton("sword_mage");
    private JButton aBoutonProtector = new JButton("protector");
    private JButton aBoutonAssassin = new JButton("assassin");
    private JButton aBoutonVagabond = new JButton("beggard");
    
    private JButton aBoutonAttack = new JButton("attack");
    private JButton aBoutonDefend = new JButton("defend");
    private JButton aBoutonFlee = new JButton("flee");
    
    private JPanel aPanelChoixRace;
    private JPanel aPanelChoixClasse; //est aussi utilisé pour l'interface des combats
    private JPanel aPanelDeplacement;
    
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText La String a afficher.
     */
    public void print( final String pText )
    {
        this.aLog.append(" " + pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText La String a afficher.
     */
    public void println( final String pText )
    {
        this.print(" " + pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName La string avec le chemin vers l'image.
     */
    public void showImage( final String pImageName )
    {
        URL vImageURL = this.getClass().getClassLoader().getResource( pImageName );
        if ( vImageURL == null )
            System.out.println( "image not found" );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
        for(JButton vButton : this.aAllButtons)
            vButton.setEnabled( pOnOff );
    } // enable(.)

    /**
     * Set up graphical user interface for the choice of the Race.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Xaar-Sharoth : Anbrodema's quest" );
        this.aEntryField = new JTextField( 34 );
        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(470, 178) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();
        
        this.aAllButtons = new ArrayList<JButton>(8);
        
        this.aAllButtons.add(aBoutonWest);
        this.aAllButtons.add(aBoutonNorth);
        this.aAllButtons.add(aBoutonSouth);
        this.aAllButtons.add(aBoutonEast);
        this.aAllButtons.add(aBoutonUp);
        this.aAllButtons.add(aBoutonDown);
        this.aAllButtons.add(aBoutonLook);
        this.aAllButtons.add(aBoutonSuicide);
        this.aAllButtons.add(aBoutonBack);
        this.aAllButtons.add(aBoutonInventory);
        this.aAllButtons.add(aBoutonHumain);
        this.aAllButtons.add(aBoutonElfe);
        this.aAllButtons.add(aBoutonNain);
        this.aAllButtons.add(aBoutonDemiOrc);
        this.aAllButtons.add(aBoutonFeral);
        this.aAllButtons.add(aBoutonDrakeide);
        this.aAllButtons.add(aBoutonVampire);
        this.aAllButtons.add(aBoutonGoliath);
        this.aAllButtons.add(aBoutonArachnean);
        this.aAllButtons.add(aBoutonWarrior);
        this.aAllButtons.add(aBoutonWizard);
        this.aAllButtons.add(aBoutonRogue);
        this.aAllButtons.add(aBoutonPaladin);
        this.aAllButtons.add(aBoutonBerserker);
        this.aAllButtons.add(aBoutonWeaponMage);
        this.aAllButtons.add(aBoutonProtector);
        this.aAllButtons.add(aBoutonAssassin);
        this.aAllButtons.add(aBoutonVagabond);
        this.aAllButtons.add(aBoutonAttack);
        this.aAllButtons.add(aBoutonDefend);
        this.aAllButtons.add(aBoutonFlee);
        this.aAllButtons.add(aBoutonTake);
        this.aAllButtons.add(aBoutonDrop);
        this.aAllButtons.add(aBoutonEat);
        this.aAllButtons.add(aBoutonBeamer);
        
        for(JButton vButton : this.aAllButtons)
            vButton.addActionListener(this);
        
        aBoutonHumain.setActionCommand("race human");
        aBoutonElfe.setActionCommand("race elf");
        aBoutonNain.setActionCommand("race dwarf");
        aBoutonDemiOrc.setActionCommand("race half-orc");
        aBoutonFeral.setActionCommand("race feral");
        aBoutonDrakeide.setActionCommand("race drakeïde");
        aBoutonVampire.setActionCommand("race vampire");
        aBoutonGoliath.setActionCommand("race goliath");
        aBoutonArachnean.setActionCommand("race arachnean");
        
        aBoutonWarrior.setActionCommand("job warrior");
        aBoutonWizard.setActionCommand("job wizard");
        aBoutonRogue.setActionCommand("job rogue");
        aBoutonPaladin.setActionCommand("job paladin");
        aBoutonBerserker.setActionCommand("job berserker");
        aBoutonWeaponMage.setActionCommand("job sword_mage");
        aBoutonProtector.setActionCommand("job protector");
        aBoutonAssassin.setActionCommand("job assassin");
        aBoutonVagabond.setActionCommand("job beggard");
            
            
        JPanel cPanel = new JPanel();
        JPanel cPanel_2 = new JPanel();
        JPanel cPanel_3 = new JPanel();
        JPanel cPanel_4 = new JPanel();
        JPanel cPanel_5 = new JPanel();
        
        //positionner tout les boutons de choix de race
        //ligne du dessus
        cPanel_4.setLayout( new GridLayout(1, 5) );
        cPanel_4.add( this.aBoutonHumain );
        cPanel_4.add( this.aBoutonElfe );
        cPanel_4.add( this.aBoutonNain );
        cPanel_4.add( this.aBoutonDrakeide );
        cPanel_4.add( this.aBoutonVampire );
        //ligne du dessous
        cPanel_5.setLayout( new GridLayout(1, 4) );
        cPanel_5.add( this.aBoutonDemiOrc );  
        cPanel_5.add( this.aBoutonFeral );
        cPanel_5.add( this.aBoutonGoliath );
        cPanel_5.add( this.aBoutonArachnean );
        
        cPanel.setLayout( new GridLayout(2, 1) );
        cPanel.add( cPanel_4 );
        cPanel.add( cPanel_5 );
        cPanel.setPreferredSize( new Dimension(450, 100) );
        
        //positionner les boutons au dessus du ListScroller
        cPanel_2.setLayout( new BorderLayout() );
        cPanel_2.add(cPanel, BorderLayout.NORTH );
        cPanel_2.add(vListScroller, BorderLayout.CENTER );
        cPanel_2.setPreferredSize( new Dimension(300, 242) );
        
        //Mise en forme de la Frame.
        cPanel_3.setLayout( new BorderLayout() );
        cPanel_3.add( this.aImage, BorderLayout.NORTH );
        cPanel_3.add( cPanel_2, BorderLayout.CENTER );
        cPanel_3.add( this.aEntryField, BorderLayout.SOUTH );
        this.aPanelChoixRace = cPanel_3;
        
        this.aMyFrame.getContentPane().add( this.aPanelChoixRace, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aEntryField.addActionListener( this );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()
    
    /**
     * Set up graphical user interface for the choice of the Job.
     */
    public void panelClasse()
    {
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(470, 178) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        JPanel xPanel = new JPanel();
        JPanel xPanel_2 = new JPanel();
        JPanel xPanel_3 = new JPanel();
        JPanel xPanel_4 = new JPanel();
        JPanel xPanel_5 = new JPanel();
        
        //positionner tout les boutons de choix de classe
        //ligne du dessus
        xPanel_4.setLayout( new GridLayout(1, 4) );
        xPanel_4.add( this.aBoutonProtector );  
        xPanel_4.add( this.aBoutonWarrior );
        xPanel_4.add( this.aBoutonPaladin );
        xPanel_4.add( this.aBoutonBerserker );
        //ligne du dessous
        xPanel_5.setLayout( new GridLayout(1, 5) );
        xPanel_5.add(this.aBoutonWeaponMage);
        xPanel_5.add(this.aBoutonAssassin);
        xPanel_5.add(this.aBoutonWizard);
        xPanel_5.add(this.aBoutonRogue);
        xPanel_5.add(this.aBoutonVagabond);
       
        xPanel.setLayout( new GridLayout(2, 1) );
        xPanel.add( xPanel_4 );
        xPanel.add( xPanel_5 );
        xPanel.setPreferredSize( new Dimension(450, 100) );
        
        //positionner les boutons a coté du ListScroller
        xPanel_2.setLayout( new BorderLayout() );
        xPanel_2.add(xPanel, BorderLayout.NORTH );
        xPanel_2.add(vListScroller, BorderLayout.CENTER );
        xPanel_2.setPreferredSize( new Dimension(300, 242) );
        
        //Mise en forme de la Frame
        xPanel_3.setLayout( new BorderLayout() );
        xPanel_3.add( this.aImage, BorderLayout.NORTH );
        xPanel_3.add( xPanel_2, BorderLayout.CENTER );
        xPanel_3.add( this.aEntryField, BorderLayout.SOUTH );
        this.aPanelChoixClasse = xPanel_3;
        
        this.aMyFrame.getContentPane().remove(this.aPanelChoixRace );
        this.aMyFrame.getContentPane().add(this.aPanelChoixClasse , BorderLayout.CENTER );
        this.aMyFrame.getContentPane().validate();
    }
    
    /**
     * Set up graphical user interface for exploring the dungeon.
     */
    public void panelDeplacement()
    {
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(470, 178) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        JPanel vPanel = new JPanel();
        JPanel vPanel_2 = new JPanel();
        JPanel vPanel_3 = new JPanel();
        JPanel vPanel_4 = new JPanel();
        JPanel vPanel_5 = new JPanel();
        JPanel vPanel_6 = new JPanel();
        JPanel vPanel_7 = new JPanel();
        JPanel vPanel_8 = new JPanel();
        
        //Déplacements cardinaux + back
        vPanel.setLayout( new BorderLayout() );
        this.aBoutonNorth.setPreferredSize( new Dimension(60, 65) );
        vPanel.add(this.aBoutonNorth, BorderLayout.NORTH);
        vPanel.add(this.aBoutonWest, BorderLayout.WEST);
        vPanel.add(this.aBoutonEast, BorderLayout.EAST);
        this.aBoutonSouth.setPreferredSize( new Dimension(60, 65) );
        vPanel.add(this.aBoutonSouth, BorderLayout.SOUTH);
        vPanel.add(this.aBoutonBack, BorderLayout.CENTER);
        
        //Déplacements haut/bas
        vPanel_2.setPreferredSize( new Dimension(100, 100) );
        vPanel_2.setLayout( new GridLayout(2, 1) );
        vPanel_2.add(this.aBoutonUp);
        vPanel_2.add(this.aBoutonDown);

        //take, drop sur une ligne
        vPanel_4.setLayout( new GridLayout(1, 3) );
        vPanel_4.setPreferredSize( new Dimension(470, 32) );
        vPanel_4.add(this.aBoutonTake);
        vPanel_4.add(this.aBoutonDrop);
        vPanel_4.add(this.aBoutonEat);
        
        // take, drop sous le ListScroller
        vPanel_5.setLayout( new BorderLayout() );
        vPanel_5.add(vPanel_4, BorderLayout.SOUTH);
        vPanel_5.add(vListScroller, BorderLayout.CENTER);
        
        //tout les déplacements et ListScroller sur une ligne : l'interface d'interaction
        vPanel_6.setLayout( new BorderLayout() );
        vPanel_6.add(vPanel, BorderLayout.CENTER);
        vPanel_6.add(vPanel_2, BorderLayout.WEST);
        vPanel_6.add(vPanel_5, BorderLayout.EAST);
        
        //look, inventaire, eat, suicide sur une ligne
        vPanel_3.setLayout( new GridLayout(1, 4) );
        vPanel_3.setPreferredSize( new Dimension(470, 32) );
        vPanel_3.add(this.aBoutonBeamer);
        vPanel_3.add(this.aBoutonLook);
        vPanel_3.add(this.aBoutonInventory);
        vPanel_3.add(this.aBoutonSuicide);
        this.setBeamer(false);
        
        //look, inventaire, ... sous l'interface d'interaction
        vPanel_8.setLayout( new BorderLayout() );
        vPanel_8.add(vPanel_3, BorderLayout.SOUTH);
        vPanel_8.add(vPanel_6, BorderLayout.CENTER);
        
        //Mise en forme de la frame :   Image en haut   -   interface d'interaction au centre   -   la barre de saisie en bas
        vPanel_7.setLayout( new BorderLayout() );
        vPanel_7.add( this.aImage, BorderLayout.NORTH );
        vPanel_7.add( vPanel_8, BorderLayout.CENTER );
        vPanel_7.add( this.aEntryField, BorderLayout.SOUTH );
        this.aPanelDeplacement = vPanel_7;
        
        this.aMyFrame.getContentPane().remove(this.aPanelChoixClasse);
        this.aMyFrame.getContentPane().add( this.aPanelDeplacement, BorderLayout.CENTER );
        this.aMyFrame.getContentPane().validate();
    }
    
    /**
     * Set up graphical user interface for Combat.
     */
    public void combatInterface()
    {
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(470, 178) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        JPanel vPanel = new JPanel();
        JPanel vPanel_2 = new JPanel();
        JPanel vPanel_3 = new JPanel();
        JPanel vPanel_4 = new JPanel();
        
        //Attaquer au dessus de Defendre
        vPanel.setLayout( new GridLayout(2, 1) );
        vPanel.add(this.aBoutonAttack);
        vPanel.add(this.aBoutonDefend);
        
        //Les actions a gauche du ListScroller
        vPanel_2.setLayout( new BorderLayout() );
        vPanel_2.add(this.aBoutonFlee, BorderLayout.WEST);
        this.aBoutonFlee.setPreferredSize( new Dimension(120, 120) );
        vPanel_2.add(vPanel, BorderLayout.CENTER);
        vPanel_2.add(vListScroller, BorderLayout.EAST);
        vPanel_2.setPreferredSize( new Dimension(300, 242) );
        
        //Mise en forme de la Frame.
        vPanel_3.setLayout( new BorderLayout() );
        vPanel_3.add( this.aImage, BorderLayout.NORTH );
        vPanel_3.add( vPanel_2, BorderLayout.CENTER );
        vPanel_3.add( this.aEntryField, BorderLayout.SOUTH );
        this.aPanelChoixClasse = vPanel_3;
        
        this.aMyFrame.getContentPane().remove(this.aPanelDeplacement);
        this.aMyFrame.getContentPane().add( this.aPanelChoixClasse, BorderLayout.CENTER );
        this.aMyFrame.getContentPane().validate();
    }
    
    /**
     * Enable or disable the Flee button for the Combats that are concerned.
     * @param pOnOff A boolean
     */
    public void setFlee(final boolean pOnOff)
    {
        this.aBoutonFlee.setEnabled( pOnOff );
    }
    
    /**
     * Enable or disable the Beamer button (orb).
     * @param pOnOff A boolean
     */
    public void setBeamer(final boolean pOnOff)
    {
        this.aBoutonBeamer.setEnabled( pOnOff );
    }
    
    /**
     * Actionlistener interface for entry textfield.
     * @param pE An ActionEvent
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        if(pE.getSource().getClass() == this.aBoutonWest.getClass())
        {
            this.aEngine.interpretCommand( pE.getActionCommand());
        }
        else 
        {   this.processCommand();}
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
    
   
    
    } // UserInterface 
