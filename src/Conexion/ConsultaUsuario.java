/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Cliente;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JEFFERSON
 */
public class ConsultaUsuario extends CConexion {

    
    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;*/

    public void registrarUsuario(Usuario us) {

        String consulta = "insert into usuarios(cod_empresa,nombre,apellido,dni,correo,celular,usuario,pass,rol,tipo) values (?,?,?,?,?,?,?,?,?,?)";

        con=cn.EstablecerConexion();
        try {
            
            CallableStatement cs =  con.prepareCall(consulta);

            cs.setString(1, "4530");
            cs.setString(2, us.getNombre());
            cs.setString(3, us.getApellido());
            cs.setInt(4, us.getDni());
            cs.setString(5, us.getCorreo());
            cs.setInt(6, us.getTelefono());
            cs.setString(7, us.getUsuario());
            cs.setString(8, us.getPass());
            cs.setString(9, us.getRol());
            cs.setString(10, "NE");

            cs.execute();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
            

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public void modificarUsuario(Usuario us) {

        String consulta = "UPDATE usuarios SET usuarios.nombre = ?,usuarios.apellido = ?,usuarios.dni = ?,usuarios.correo = ?,usuarios.celular = ?,"
                + "usuarios.usuario = ?, usuarios.pass = ?,usuarios.rol = ? WHERE usuarios.id_usuarios = ? and usuarios.tipo='NE'";
        con=cn.EstablecerConexion();
        try {
            
            CallableStatement cs =  con.prepareCall(consulta);
            
            cs.setString(1, us.getNombre());
            cs.setString(2, us.getApellido());
            cs.setInt(3, us.getDni());
            cs.setString(4, us.getCorreo());
            cs.setInt(5, us.getTelefono());
            cs.setString(6, us.getUsuario());
            cs.setString(7, us.getPass());
            cs.setString(8, us.getRol());
            cs.setInt(9, us.getId());
            cs.execute();

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }
    }

    public void eliminar(Usuario us) {

        String consulta = "DELETE from usuarios WHERE usuarios.id_usuarios=?;";

        con=cn.EstablecerConexion();
        try {
            
            CallableStatement cs =  con.prepareCall(consulta);

            cs.setInt(1, us.getId());
            cs .execute();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }
    }
    
    
    public void eliminarUpdate(Usuario us) {
        String consulta = "UPDATE usuarios SET usuarios.tipo = ? WHERE usuarios.id_usuarios = ? and usuarios.tipo='NE'";
        con=cn.EstablecerConexion();
        try {
            
            CallableStatement cs =  con.prepareCall(consulta);
            
            cs.setString(1, "E");
            cs.setInt(2, us.getId());
            cs.execute();

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }
    }

    public void mostrarTablaUsuario(Usuario us, JTable paramTable) {
        
        con=cn.EstablecerConexion();
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 10) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        /*FormAdmSistema adm=new FormAdmSistema();
        modelo=(DefaultTableModel) adm.jTable1.getModel();*/
        TableRowSorter<TableModel> ordenarTable = new TableRowSorter<TableModel>(modelo);
        paramTable.setRowSorter(ordenarTable);

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Correo");
        modelo.addColumn("Celular");
        modelo.addColumn("Usuario");
        modelo.addColumn("Pass");
        modelo.addColumn("Rol");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);
        String sql = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios "
                + "where usuarios.tipo='NE'";

        
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                
                us.setId(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setApellido(rs.getString(3));
                us.setDni(rs.getInt(4));
                us.setCorreo(rs.getString(5));
                us.setTelefono(rs.getInt(6));
                us.setUsuario(rs.getString(7));
                us.setPass(rs.getString(8));
                us.setRol(rs.getString(9));
                us.setFecha(rs.getString(10));
                /*for (int j=0;j<datos.length;j++){
                    datos[j]=rs.getString((j+1));
                    
                }*/       
                modelo.addRow(us.Registro());
            }
            paramTable.setModel(modelo);
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
           
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }
    }
    
    
    public boolean buscarUsuario(Usuario us,JTable paramTable, JComboBox combo){
        
        con=cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 10) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        TableRowSorter<TableModel> ordenarTable = new TableRowSorter<TableModel>(modelo);
        paramTable.setRowSorter(ordenarTable);

        String combo2 = combo.getSelectedItem().toString();

        String consulta = "";

        if (combo2.equalsIgnoreCase("ID")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " WHERE usuarios.tipo='NE' and usuarios.id_usuarios =" + us.getId_fac_pag();
        } else if (combo2.equalsIgnoreCase("Nombre")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " WHERE usuarios.tipo='NE' and usuarios.nombre like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Apellido")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " WHERE usuarios.tipo='NE' and usuarios.apellido like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Correo")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " WHERE usuarios.tipo='NE' and usuarios.correo like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Teléfono")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " WHERE usuarios.tipo='NE' and usuarios.celular like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Usuario")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " usuarios usuarios.tipo='NE' and WHERE usuarios.usuario like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Rol")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " WHERE usuarios.tipo='NE' and usuarios.rol like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Fecha")) {
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios"
                    + " usuarios usuarios.tipo='NE' and WHERE usuarios.fecha like '%" + us.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("DNI")){
            consulta = "select usuarios.id_usuarios,usuarios.nombre,usuarios.apellido,usuarios.dni,"
                + "usuarios.correo,usuarios.celular,usuarios.usuario,usuarios.pass,"
                + "usuarios.rol,usuarios.fecha from usuarios "
                    + "WHERE usuarios.tipo='NE' and usuarios.dni like '%" + us.getDni() + "%'";
        }

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Correo");
        modelo.addColumn("Celular");
        modelo.addColumn("Usuario");
        modelo.addColumn("Pass");
        modelo.addColumn("Rol");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);
        

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {
                us.setId(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setApellido(rs.getString(3));
                us.setDni(rs.getInt(4));
                us.setCorreo(rs.getString(5));
                us.setTelefono(rs.getInt(6));
                us.setUsuario(rs.getString(7));
                us.setPass(rs.getString(8));
                us.setRol(rs.getString(9));
                us.setFecha(rs.getString(10));
                modelo.addRow(us.Registro());
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Elegir una opción correcta"+e.toString());
            return false;
        }
    }
    
    public List obtenerLista(Usuario us) {
        //con = cn.EstablecerConexion();
        List<Usuario> ListaStUs = new ArrayList();
        String consulta = "select usuarios.dni,usuarios.id_usuarios,usuarios.usuario from usuarios where usuarios.tipo='NE'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usDn = new Usuario();
                usDn.setDni(rs.getInt("dni"));
                usDn.setId_fac_pag(rs.getInt("id_usuarios"));
                usDn.setUsuario(rs.getString("usuario"));
                ListaStUs.add(usDn);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener clientes dni" + e.toString());

        }
        return ListaStUs;
    }

    public void verificarDNI(Usuario us) {
        List<Usuario> ListaStUs = this.obtenerLista(us);
        for (int i = 0; i < ListaStUs.size(); i++) {
            if (us.getDni() == ListaStUs.get(i).getDni()) {
                us.setVer("Incorrecto");
            }
        }
    }
    

    public void verificarID(Usuario us) {
        List<Usuario> ListaStUs = this.obtenerLista(us);
        for (int i = 0; i < ListaStUs.size(); i++) {
            if (us.getId_fac_pag()== ListaStUs.get(i).getId_fac_pag()) {
                us.setVer("Incorrecto");
            }
        }
    }
    
    
    public void verificarUsuario(Usuario us) {
        List<Usuario> ListaStUs = this.obtenerLista(us);
        for (int i = 0; i < ListaStUs.size(); i++) {
            if (ListaStUs.get(i).getUsuario().equals(us.getUsuario())) {
                us.setVer2("Incorrecto");
            }
        }
    }
    
}
