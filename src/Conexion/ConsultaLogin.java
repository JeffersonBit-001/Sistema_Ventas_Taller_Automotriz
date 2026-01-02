/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author JEFFERSON
 */
public class ConsultaLogin extends CConexion{
    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    PreparedStatement pr;*/
    
    
    public void IngresarLoginUsuario(Usuario us){
        
        /*select usuarios.id_usuarios,usuarios.cod_empresa,"
                    + "concat_ws(' ', usuarios.nombre,usuarios.apellido) as Nom_com,"
                    + "usuarios.dni,usuarios.correo,usuarios.celular,"
                    + "usuarios.usuario,usuarios.pass,"
                    + "usuarios.rol*/
        try {
            String consulta="select "
                    + "concat_ws(' ', usuarios.nombre,usuarios.apellido) as Nom_com,"
                    + "usuarios.usuario,usuarios.pass,"
                    + "usuarios.rol from usuarios WHERE usuarios.usuario=? AND usuarios.pass=? and usuarios.tipo='NE'";
            con=cn.EstablecerConexion();       
            ps=con.prepareStatement(consulta);
            
            ps.setString(1,us.getUsuario());
            ps.setString(2, us.getPass());
            
            rs=ps.executeQuery();
            
            if (rs.next()){
                us.setUsuario(rs.getString("usuario"));
                us.setPass(rs.getString("pass"));
                us.setRol(rs.getString("rol"));
                us.setIdentificacion(rs.getString("Nom_com"));
            } else {
                us.setRol("Nada");
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
            } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
}
