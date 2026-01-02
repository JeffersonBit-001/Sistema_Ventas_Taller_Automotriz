/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author JEFFERSON
 */
public class CConexion {
    String usuarioR="root";
    String contr="root";
    String ip="localhost";
    String puerto="3306";
    String bd="proyecto_poo";
    
    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;
    CallableStatement cs;
    
    String cadena="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection EstablecerConexion(){
        Connection conex=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conex=DriverManager.getConnection(cadena,usuarioR,contr);
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Problemas en la conexión"+e.toString());
        }
        return conex;
    }
    
    
}
