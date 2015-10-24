package unam.myp;

import javax.swing.*;

/**
 * Clase Principal que contiene al
 * metodo Main
 */

public class Main {
    public static void main (String [] args){        	
	new BDD();	

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    Ventana.createAndShowGUI();
		}
	    });
    }    
}//Main

