/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Cliente;
import Modelo.Productos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JEFFERSON
 */
public class ConsultaCliente extends CConexion {

    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    Statement st;
    CallableStatement cs;
    PreparedStatement ps;*/



    public void registrarCliente(Cliente cl) {
        con = cn.EstablecerConexion();
        String consulta = "insert into clientes(cod_empresa,nombre,apellido,dni,direccion,celular,tipo) values (?,?,?,?,?,?,?)";
        try {
            cs = con.prepareCall(consulta);
            cs.setString(1, "4530");
            cs.setString(2, cl.getNombre());
            cs.setString(3, cl.getApellido());
            cs.setInt(4, cl.getDni());
            cs.setString(5, cl.getCorreo());
            cs.setInt(6, cl.getTelefono());
            cs.setString(7, "NE");
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 1" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 1-1" + e.toString());
            }
        }

    }

    public void modificarCliente(Cliente cl) {
        con = cn.EstablecerConexion();
        String consulta = "update clientes set clientes.nombre = ?,clientes.apellido = ?,clientes.dni = ?,clientes.direccion = ?,"
                + "clientes.celular = ? WHERE clientes.id_cliente = ? and clientes.tipo='NE';";
        try {
            cs = con.prepareCall(consulta);
            cs.setString(1, cl.getNombre());
            cs.setString(2, cl.getApellido());
            cs.setInt(3, cl.getDni());
            cs.setString(4, cl.getCorreo());
            cs.setInt(5, cl.getTelefono());
            cs.setInt(6, cl.getId());

            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 2" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error2-1" + e.toString());
            }
        }
    }

    public void eliminarCliente(Cliente cl) {
        String consulta = "DELETE from clientes WHERE clientes.id_cliente=?;";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, cl.getId());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error3");

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error3-1" + e.toString());
            }
        }
    }

    public void eliminarClienteUpdate(Cliente cl) {
        String consulta = "update clientes set clientes.tipo='E' WHERE clientes.id_cliente = ? and clientes.tipo='NE'";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, cl.getId());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error3");

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error3-1" + e.toString());
            }
        }
    }

    public void mostrarTablaCliente(Cliente cl, JTable paramTable) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 7) {
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
        modelo.addColumn("Dirección");
        modelo.addColumn("Celular");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);
        String sql = "select clientes.id_cliente,clientes.nombre,clientes.apellido,"
                + "clientes.dni,clientes.direccion,clientes.celular,clientes.fecha "
                + "from clientes "
                + "where clientes.tipo='NE';";

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDni(rs.getInt(4));
                cl.setCorreo(rs.getString(5));
                cl.setTelefono(rs.getInt(6));
                cl.setFecha(rs.getString(7));
                /*for (int j=0;j<datos.length;j++){
                    datos[j]=rs.getString((j+1));
                    
                }*/
                modelo.addRow(cl.Registro());
            }
            paramTable.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error tabla" + e.toString());

        } finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error tabla" + e.toString());
            }
        }
    }

    public boolean buscarCliente(Cliente cl, JTable paramTable, JComboBox combo) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 7) {
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

        if (combo2.equalsIgnoreCase("Id")) {
            consulta = "select clientes.id_cliente,clientes.nombre,clientes.apellido,"
                    + "clientes.dni,clientes.direccion,clientes.celular,clientes.fecha "
                    + "from clientes"
                    + " WHERE clientes.tipo='NE' and clientes.id_cliente =" + cl.getDni();

        } else if (combo2.equalsIgnoreCase("Dni")) {
            consulta = "select clientes.id_cliente,clientes.nombre,clientes.apellido,"
                    + "clientes.dni,clientes.direccion,clientes.celular,clientes.fecha "
                    + "from clientes"
                    + " WHERE clientes.tipo='NE' and clientes.dni=" + cl.getDni();
        }

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Dirección");
        modelo.addColumn("Celular");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {
                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setApellido(rs.getString(3));
                cl.setDni(rs.getInt(4));
                cl.setCorreo(rs.getString(5));
                cl.setTelefono(rs.getInt(6));
                cl.setFecha(rs.getString(7));
                modelo.addRow(cl.Registro());
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Elegir una opción correcta"+e.toString());
            return false;
        }
    }

    public List obtenerListaDni(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Cliente> ListaStDn = new ArrayList();
        String consulta = "select clientes.id_cliente, clientes.dni from clientes where clientes.tipo='NE'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliDn = new Cliente();
                cliDn.setId_fac_pag(rs.getInt("id_cliente"));
                cliDn.setDni(rs.getInt("dni"));
                ListaStDn.add(cliDn);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener clientes dni" + e.toString());

        }
        return ListaStDn;
    }

    public void verificarDNI(Cliente cl) {
        List<Cliente> ListaStDn = this.obtenerListaDni(cl);
        for (int i = 0; i < ListaStDn.size(); i++) {
            if (cl.getDni() == ListaStDn.get(i).getDni()) {
                cl.setVer("Incorrecto");
            }
        }
    }
    
    
    public void verificarIDCli(Cliente cl) {
        List<Cliente> ListaStDn = this.obtenerListaDni(cl);
        for (int i = 0; i < ListaStDn.size(); i++) {
            if (cl.getId_fac_pag()== ListaStDn.get(i).getId_fac_pag()) {
                cl.setVer("Incorrecto");
            }
        }
    }
    
}
