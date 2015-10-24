package unam.myp.test;

import java.sql.*;
import javax.swing.*;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import unam.myp.BDD;

/**
 *Clase para puebas unitarias de la clase {@link BDD}.
 */
public class TestBDD{

    private BDD bdd;
    /* La coneccion con la base de datos */
    private Connection conn = null;
    /* Objeto para utilizar SQL */   
    private Statement stmt = null;
    
    /**
     * Inicializa a bdd para que pueda ser usado y 
     * establece una conexión con la base de datos
     */

    public TestBDD(){
	bdd = new BDD();
	try{
	    Class.forName("org.sqlite.JDBC");      
	    conn = DriverManager.getConnection("jdbc:sqlite:./src/unam/myp/extra/bddligamx.db","username","password");
	    conn.setAutoCommit(false);
	    stmt = conn.createStatement();
	}catch(Exception e){
	    e.printStackTrace();
	}
    }//Bob

    /**
     *Prueba unitaria para {@link BDD#busca}
     */  
    @Test public void testBusca(){
	try{
	    bdd.conectame();
	    JTable tab1 = bdd.busca("SELECT * FROM JUGADORES");
	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM JUGADORES");
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int numberOfColumns = rsmd.getColumnCount();
	    int counter = 0;
	    while(rs.next())
		counter++;
	    rs = stmt.executeQuery("SELECT * FROM JUGADORES");
	    Assert.assertTrue(tab1.getColumnCount() == numberOfColumns);
	    Assert.assertTrue(tab1.getRowCount() == counter);
	    
	    String [] columnNames = new String[numberOfColumns];
	    for(int i = 0; i<numberOfColumns;i++)
		columnNames[i] = rsmd.getColumnName(i+1);
	    Object[][] data = new Object[counter][numberOfColumns];
	    int counter2 = 0;
	    while (rs.next()){				
		for(int j = 0;j<numberOfColumns;j++)
		    data[counter2][j] = rs.getString(j+1);
		counter2++;
	    }//while		
	    JTable tab2 = new JTable(data , columnNames);	    
	    for(int i = 0;i<counter;i++)
		for(int j = 0;j<numberOfColumns;j++)
		    Assert.assertTrue(tab1.getValueAt(i,j).equals(tab2.getValueAt(i,j)));	    
	    stmt.close();	    	    
	    conn.close();
	 }catch(Exception e){
	    e.printStackTrace();	    
	    Assert.fail();
	}
    }//TestBusca
}//class
