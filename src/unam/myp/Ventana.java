package unam.myp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase que nos da toda la interfaz grafica
 * para que el programa funcione
 */

public class Ventana {

    /* Las cartas del TabbedPane*/
    JPanel card1;
    JPanel card2;
    JPanel card3;
    
    /*Tabbed Pane a utilizar*/
    JTabbedPane tabbedPane;    

    /*Todos los botones utilizados en el tabbedPane*/
    JButton b;
    JButton b2;
    JButton b3;
    JButton i;
    JButton i2;
    JButton i3;
    JButton el;
    JButton el2;
    JButton el3;
    
    /*Jlabel que muestra un mensaje de texto*/
    JLabel textLabel;
    
    final static int extraWindowWidth = 100;

    /**
     * Metodo que hace casi todo el trabajo
     * pues añade todo  lo necesario a nuestra 
     * interfaz gráfica
     * @param pane el Container para utilizar
     */
    
    public void addComponentToPane(Container pane) {
	tabbedPane = new JTabbedPane();
        //Create the "cards".
	card1 = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };

	textLabel = new JLabel("¡Clickea el nombre para obtener imagen hermano!");
	
	b =new JButton("Buscar");
	b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String text = JOptionPane.showInputDialog("Inserta tu Busqueda");
		    if(text != null){
			JTable tab = bdd.busca("SELECT * FROM JUGADORES WHERE IDjg LIKE \'%"+text+"%\'" +
					       "OR NOMBRE LIKE \'%"+text+"%\'"+
					       "OR PAIS LIKE \'%"+text+"%\'"+
					       "OR ESTATURA LIKE \'%"+text+"%\'"+
					       "OR POSICION LIKE \'%"+text+"%\'");
			JScrollPane p = new JScrollPane(tab,
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card1.removeAll();
			card1.add(p);
			card1.add(b);
			card1.add(el);
			card1.add(i);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });

	i =new JButton("Agregar");
	i.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String ins = JOptionPane.showInputDialog("Jugador con el sig formato: NOMBRE,PAIS,ESTATURA,POSICION");
		    if(ins != null){
			ins = ins.replaceAll(" ","");
			String [] array = ins.split(",");
			if(array.length==4)
			    bdd.inserta("INSERT INTO JUGADORES"+ 
					"(NOMBRE,PAIS,ESTATURA,POSICION)"+
					"VALUES ('"+array[0]+"','"+array[1]+"','"+array[2]+"','"+array[3]+"');");
			JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM JUGADORES"),
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card1.removeAll();
			card1.add(p);
			card1.add(b);
			card1.add(el);
			card1.add(i);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });

	el =new JButton("Eliminar");
	el.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String del = JOptionPane.showInputDialog("Inserta el ID del jugador a eliminar:");
		    if(del != null){
			del = del.replaceAll(" ","");		    
			bdd.elimina("DELETE FROM JUGADORES WHERE IDjg = " + del);
				    JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM JUGADORES"),
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card1.removeAll();
			card1.add(p);
			card1.add(b);
			card1.add(i);
			card1.add(el);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });
	
	card1.add(b);
	card1.add(i);
	card1.add(el);
	card1.add( new JLabel(new ImageIcon("./src/unam/myp/extra/IMG/jugador.jpg")));
	
	card2 = new JPanel();
	b2 =new JButton("Buscar");
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String text = JOptionPane.showInputDialog("Inserta tu Busqueda");
		    JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM EQUIPOS WHERE IDeq LIKE \'%"+text+"%\'" +
							      "OR NOMBRE LIKE \'%"+text+"%\'"+
							      "OR ENTRENADOR LIKE \'%"+text+"%\'"+
							      "OR ESTADIO LIKE \'%"+text+"%\'"+
							      "OR FUNDACION LIKE \'%"+text+"%\'"+
							      "OR PROPIETARIO LIKE \'%"+text+"%\'"),
						    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		    p.setPreferredSize(new Dimension(600, 500));
		    card2.removeAll();
		    card2.add(p);
		    card2.add(b2);
		    card2.add(el2);
		    card2.add(i2);	
		    card1.add(textLabel);	    
		    tabbedPane.updateUI();
		    bdd.cierra();
		}
	    });

	i2 =new JButton("Agregar");
	i2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String ins = JOptionPane.showInputDialog("EQUIPO con el sig formato: NOMBRE,ENTRENADOR,ESTADIO,FUNDACION,PROPIETARIO");
		    if(ins != null){
			ins = ins.replaceAll(" ","");
			String [] array = ins.split(",");
			if(array.length==5)
			    bdd.inserta("INSERT INTO JUGADORES"+ 
					"(NOMBRE,ENTRENADOR,ESTADIO,FUNDACION,PROPIETARIO)"+
					"VALUES ('"+array[0]+"','"+array[1]+"','"+array[2]+"','"+array[3]+"','"+array[4]+"');");
			JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM EQUIPOS"),
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card2.removeAll();
			card2.add(p);
			card2.add(b2);
			card2.add(el2);
			card2.add(i2);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });

	el2 =new JButton("Eliminar");
	el2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String del = JOptionPane.showInputDialog("Inserta el ID del EQUIPO a eliminar:");
		    if(del != null){
			del = del.replaceAll(" ","");		    
			bdd.elimina("DELETE FROM EQUIPOS WHERE IDeq = " + del);
				    JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM EQUIPOS"),
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card2.removeAll();
			card2.add(p);
			card2.add(b2);
			card2.add(i2);
			card2.add(el2);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });

	
	card2.add(b2);
	card2.add(el2);
	card2.add(i2);	
	card2.add( new JLabel(new ImageIcon("./src/unam/myp/extra/IMG/equipo.jpg")));
	
	card3 = new JPanel();
	b3 =new JButton("Buscar");
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String text = JOptionPane.showInputDialog("Inserta tu Busqueda");
		    JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM LIGAS WHERE IDlg LIKE \'%"+text+"%\'" +
							      "OR ANIO LIKE \'%"+text+"%\'"+
							      "OR NOMBRE LIKE \'%"+text+"%\'"+
							      "OR SEDEF LIKE \'%"+text+"%\'"+
							      "OR ASISTENCIA LIKE \'%"+text+"%\'"+
							      "OR CG LIKE \'%"+text+"%\'"+
							      "OR LIDER LIKE \'%"+text+"%\'"+
							      "OR CAMPEON LIKE \'%"+text+"%\'"+
							      "OR SUBCAMPEON LIKE \'%"+text+"%\'"+
							      "OR DESCENDIDO LIKE \'%"+text+"%\'"+
							      "OR DTCAMP LIKE \'%"+text+"%\'"),
						    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		    p.setPreferredSize(new Dimension(700, 500));
		    card3.removeAll();
		    card3.add(p);
		    card3.add(b3);
		    card2.add(el3);
		    card2.add(i3);	
		    card1.add(textLabel);
		    tabbedPane.updateUI();
		    bdd.cierra();
		}
	    });
	
	i3 =new JButton("Agregar");
	i3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String ins = JOptionPane.showInputDialog("Liga con el sig formato: ANIO,NOMBRE,SEDEF,"
							     +"ASISTENCIA,DTCAMP");
		    if(ins != null){
			ins = ins.replaceAll(" ","");
			String [] array = ins.split(",");
			if(array.length==5)
			    bdd.inserta("INSERT INTO JUGADORES"+ 
					"(ANIO,NOMBRE,SEDEF,ASISTENCIA,DTCAMP)"+
					"VALUES ('"+array[0]+"','"+array[1]+"','"+array[2]+"','"+array[3]+"','"+array[4]+"');");
			JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM JUGADORES"),
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card1.removeAll();
			card1.add(p);
			card1.add(b3);
			card1.add(el3);
			card1.add(i3);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });

	el3 =new JButton("Eliminar");
	el3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    BDD bdd = new BDD();
		    bdd.conectame();
		    String del = JOptionPane.showInputDialog("Inserta el ID de la liga a eliminar:");
		    if(del != null){
			del = del.replaceAll(" ","");		    
			bdd.elimina("DELETE FROM LIGAS WHERE IDjg = " + del);
				    JScrollPane p = new JScrollPane(bdd.busca("SELECT * FROM JUGADORES"),
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		    			
			p.setPreferredSize(new Dimension(600,500));			
			card1.removeAll();
			card1.add(p);
			card1.add(b3);
			card1.add(i3);
			card1.add(el3);
			card1.add(textLabel);
			tabbedPane.updateUI();
			bdd.cierra();
		    }//if
		}
	    });
	
	card3.add(el3);
	card3.add(i3);
	card3.add(b3);
	card3.add(new JLabel(new ImageIcon("./src/unam/myp/extra/IMG/balon.jpg")));	
	
        tabbedPane.addTab("JUGADORES", card1);
        tabbedPane.addTab("EQUIPOS", card2);
        tabbedPane.addTab("LIGAS", card3);
 
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
 
    /**
     * Crea y muestra la interfaz gráfica de usuario
     * (Graphic User Interface)     
     */
    
    public static void createAndShowGUI() {

        JFrame frame = new JFrame("Aplicacion Chingona");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 

        Ventana demo = new Ventana();
        demo.addComponentToPane(frame.getContentPane());
 	
        frame.pack();
        frame.setVisible(true);
    }        
}
