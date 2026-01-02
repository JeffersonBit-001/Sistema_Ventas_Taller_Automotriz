/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Cliente;
import Modelo.Servicios;
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
public class ConsultaServicios extends CConexion {

    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    Statement st;
    CallableStatement cs;
    PreparedStatement ps;*/


    public void registrarServicio(Servicios ser) {
        String consulta = "insert into servicios(cod_empresa,nombre,descripcion,precio,tipo) values (?,?,?,?,?)";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setString(1, "4530");
            cs.setString(2, ser.getNombre());
            cs.setString(3, ser.getDescripcion());
            cs.setDouble(4, ser.getPrecio());
            cs.setString(5, "NE");
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

    public void modificarServicio(Servicios ser) {
        con = cn.EstablecerConexion();
        String consulta = "update servicios"
                + " set servicios.nombre=?,servicios.descripcion=?,servicios.precio=? where servicios.id_servicio=? "
                + "and servicios.tipo='NE'";
        try {
            cs = con.prepareCall(consulta);
            cs.setString(1, ser.getNombre());
            cs.setString(2, ser.getDescripcion());
            cs.setDouble(3, ser.getPrecio());
            cs.setInt(4, ser.getId());
            cs.execute();

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

    public void eliminarServicio(Servicios ser) {
        String consulta = "delete from servicios where servicios.id_servicio=?";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, ser.getId());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error3"+e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error3-1" + e.toString());
            }
        }
    }
    
    
    public void eliminarServicioUpdate(Servicios ser) {
        String consulta = "update servicios"
                + " set servicios.tipo='E' where servicios.id_servicio=? and servicios.tipo='NE'";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, ser.getId());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error3"+e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error3-1" + e.toString());
            }
        }
    }
    

    public void mostrarTablaServicio(Servicios ser, JTable paramTable) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 6) {
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
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);
        String consulta = "select servicios.id_servicio,servicios.nombre,"
                + "servicios.descripcion,servicios.precio,servicios.hora,servicios.fecha from servicios "
                + "where servicios.tipo='NE'";

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {

                ser.setId(rs.getInt(1));
                ser.setNombre(rs.getString(2));
                ser.setDescripcion(rs.getString(3));
                ser.setPrecio(rs.getDouble(4));
                ser.setHora(rs.getString(5));
                ser.setFecha(rs.getString(6));

                modelo.addRow(ser.Registro());
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

    public boolean buscarServicio(Servicios ser, JTable paramTable, JComboBox combo) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 6) {
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
            consulta = "select servicios.id_servicio,servicios.nombre,servicios.descripcion,"
                    + "servicios.precio,servicios.hora,servicios.fecha"
                    + " from servicios"
                    + " WHERE servicios.tipo='NE' and servicios.id_servicio=" + ser.getId();
        } else if (combo2.equalsIgnoreCase("Nombre")) {
            consulta = "select servicios.id_servicio,servicios.nombre,servicios.descripcion,"
                    + "servicios.precio,servicios.hora,servicios.fecha"
                    + " from servicios"
                    + " WHERE servicios.tipo='NE' and servicios.nombre like '%" + ser.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Descripción")) {
            consulta = "select servicios.id_servicio,servicios.nombre,servicios.descripcion,"
                    + "servicios.precio,servicios.hora,servicios.fecha"
                    + " from servicios"
                    + " WHERE servicios.tipo='NE' and servicios.descripcion like '%" + ser.getNombre() + "%'";
        }

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Hora");
        modelo.addColumn("Fecha");


        paramTable.setModel(modelo);

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {
                ser.setId(rs.getInt(1));
                ser.setNombre(rs.getString(2));
                ser.setDescripcion(rs.getString(3));
                ser.setPrecio(rs.getDouble(4));
                ser.setHora(rs.getString(5));
                ser.setFecha(rs.getString(6));

                modelo.addRow(ser.Registro());
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Elegir una opción correcta"+e.toString());
            return false;
        }
    }
    
    public List obtenerLista(Servicios ser) {
        //con = cn.EstablecerConexion();
        List<Servicios> ListaStSer = new ArrayList();
        String consulta = "select servicios.id_servicio, servicios.nombre from servicios where servicios.tipo='NE'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicios serDn = new Servicios();
                serDn.setId(rs.getInt("id_servicio"));
                serDn.setNombre(rs.getString("nombre"));
                ListaStSer.add(serDn);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener clientes dni" + e.toString());

        }
        return ListaStSer;
    }

    public void verificarNombre(Servicios ser) {
        List<Servicios> ListaStSer = this.obtenerLista(ser);
        for (int i = 0; i < ListaStSer.size(); i++) {
            if (ListaStSer.get(i).getNombre().equalsIgnoreCase(ser.getNombre())) {
                ser.setVer("Incorrecto");
            }
        }
    }
    
    public void verificarID(Servicios ser) {
        List<Servicios> ListaStSer = this.obtenerLista(ser);
        for (int i = 0; i < ListaStSer.size(); i++) {
            if (ser.getId()==ListaStSer.get(i).getId()) {
                ser.setVer("Incorrecto");
            }
        }
    }

}
