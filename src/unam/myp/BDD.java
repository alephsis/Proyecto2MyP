package unam.myp;

import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

/**
 * Clase que maneja toda la interacción con la
 * Base de datos, es la parte del Controlador
 * usando el patrón MVC
 */

public class BDD {
    
    /* La coneccion con la base de datos */
    Connection conn = null;
    /* Objeto para utilizar SQL */   
    Statement stmt = null;
    
    /* Tabla de datos obtenidos de la base */
    JTable table;
    
    /* El respectivo driver para SQLite y el URL de la base */
    static final String JDBC_DRIVER = "org.sqlite.JDBC";  
    static final String DB_URL = "jdbc:sqlite:./src/unam/myp/extra/bddligamx.db";
    
    /* Usuario y contraseña para accesar ala base de datos */    
    static final String USER = "username";
    static final String PASS = "password";
   
    /**
     * Inicia la conexión con la base
     * de datos y crea un objeto para
     * manejar SQL
     */
    
    public void conectame () {
	try{
	    Class.forName(JDBC_DRIVER);      
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    conn.setAutoCommit(false);
	    stmt = conn.createStatement();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }//conectame
    
    /**
     * Metodo que se basa en una declaración SQL y
     * que con base en eso busca en la base de datos,
     * además crea una tabla con los datos encontrados
     * @param sql la declaracion SQL
     * @return una tabla con los datos encontrados
     */
    
    public JTable busca(String sql){
	try{
	    ResultSet rs = stmt.executeQuery(sql);	
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int numberOfColumns = rsmd.getColumnCount();
	    String [] columnNames = new String[numberOfColumns];
	    for(int i = 0; i<numberOfColumns;i++)
		columnNames[i] = rsmd.getColumnName(i+1);
	    int counter = 0;
	    while(rs.next())
		counter++;
	    Object[][] data = new Object[counter][numberOfColumns];
	    counter = 0;
	    rs = stmt.executeQuery(sql);
	    while (rs.next()){				
		for(int j = 0;j<numberOfColumns;j++)
		    data[counter][j] = rs.getString(j+1);
		counter++;
	    }//while		
	    table = new JTable(data , columnNames);
	    table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
			    if(table.getSelectedColumn() == 1 ){
				JFrame frame = new JFrame("IMAGEN");
				String s = (String)table.getValueAt(table.getSelectedRow(),1);
				s = s.replaceAll(" ","_");
				s = s.replaceAll("ñ","ni");
				if(new File("./src/unam/myp/extra/IMG/"+s+".jpg").isFile()){
				    JLabel img = new JLabel(new ImageIcon("./src/unam/myp/extra/IMG/"+s+".jpg"));				
				    frame.add(img);	    
				    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				    frame.pack();
				    frame.setVisible(true);			       
				}//if
			    }//if
			}//if
		    }//if
		});
	    return table;
	}catch(Exception e){
	    e.printStackTrace();
	    return new JTable();
	}	
    }//busca()	      

    /**
     * Recibe una cadena que debe ser una declaracion
     * SQL e inserta según se necesite
     * @param sql la declaracion SQL
     */
    
    public void inserta(String sql) {
	try{
	stmt.executeUpdate(sql);
	}catch(Exception e){e.printStackTrace();}
    }//inserta

    /**
     * Recibe una cadena que debe ser una declaracion
     * SQL y elimina según se necesite
     * @param sql la declaracion SQL
     */
    
    public void elimina(String sql) {
	try{
	stmt.executeUpdate(sql);
	}catch(Exception e){e.printStackTrace();}
    }//elimina
    
    /**
     * Termina toda interacción con
     * la base de datos cerrando todo y
     * liberando recursos
     */
    
    public void cierra(){
	try{
	    stmt.close();
	    conn.close();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }//cierra
        
}//Class
