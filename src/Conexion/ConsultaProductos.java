/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Productos;
import Modelo.Servicios;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JEFFERSON
 */
public class ConsultaProductos extends CConexion {

    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    Statement st;
    CallableStatement cs;
    PreparedStatement ps;*/

    public void registrarProducto(Productos prod) {
        String consulta = "insert into productos(cod_empresa,nombre,descripcion,cantidad,precio_unidad,total,tipo) values (?,?,?,?,?,?,?)";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setString(1, "4530");
            cs.setString(2, prod.getNombre());
            cs.setString(3, prod.getDescripcion());
            cs.setDouble(4, prod.getCantidad());
            cs.setDouble(5, prod.getPrecio_unidad());
            cs.setDouble(6, prod.calcularPrecioTotal());
            cs.setString(7, "NE");
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

    public void modificarProducto(Productos prod) {
        con = cn.EstablecerConexion();
        String consulta = "update productos"
                + " set productos.nombre=?,productos.descripcion=?,productos.cantidad=?, productos.precio_unidad=?,"
                + "productos.total=? where productos.id_productos=? and productos.tipo='NE'";
        try {
            cs = con.prepareCall(consulta);
            cs.setString(1, prod.getNombre());
            cs.setString(2, prod.getDescripcion());
            cs.setDouble(3, prod.getCantidad());
            cs.setDouble(4, prod.getPrecio_unidad());
            cs.setDouble(5, prod.calcularPrecioTotal());
            cs.setInt(6, prod.getId());
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

    public void eliminarProducto(Productos prod) {
        String consulta = "delete from productos where productos.id_productos=?";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, prod.getId());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error3" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error3-1" + e.toString());
            }
        }
    }

    public void eliminarProductoUpdate(Productos prod) {
        String consulta = "update productos"
                + " set productos.tipo='E' where productos.id_productos=? and productos.tipo='NE'";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, prod.getId());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error3" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error3-1" + e.toString());
            }
        }
    }

    public void mostrarTablaProducto(Productos prod, JTable paramTable) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 8) {
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
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio/Unidad");
        modelo.addColumn("Total");
        modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);
        String consulta = "select productos.id_productos,productos.nombre,"
                + "productos.descripcion,productos.cantidad,productos.precio_unidad,productos.total,"
                + "productos.hora,productos.fecha from productos "
                + "where productos.tipo='NE'";

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {

                prod.setId(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setCantidad(rs.getInt(4));
                prod.setPrecio_unidad(rs.getDouble(5));
                prod.setHora(rs.getString(7));
                prod.setFecha(rs.getString(8));

                modelo.addRow(prod.Registro());
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

    public boolean buscarProducto(Productos prod, JTable paramTable, JComboBox combo) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 8) {
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
            consulta = "select productos.id_productos,productos.nombre,"
                    + "productos.descripcion,productos.cantidad,productos.precio_unidad,productos.total,"
                    + "productos.hora,productos.fecha from productos"
                    + " WHERE productos.tipo='NE' and productos.id_productos like '%" + prod.getId() + "%'";
        } else if (combo2.equalsIgnoreCase("Nombre")) {
            consulta = "select productos.id_productos,productos.nombre,"
                    + "productos.descripcion,productos.cantidad,productos.precio_unidad,productos.total,"
                    + "productos.hora,productos.fecha from productos"
                    + " WHERE productos.tipo='NE' and productos.nombre like '%" + prod.getNombre() + "%'";
        } else if (combo2.equalsIgnoreCase("Descripción")) {
            consulta = "select productos.id_productos,productos.nombre,"
                    + "productos.descripcion,productos.cantidad,productos.precio_unidad,productos.total,"
                    + "productos.hora,productos.fecha from productos"
                    + " WHERE productos.tipo='NE' and productos.descripcion like '%" + prod.getNombre() + "%'";
        }

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio/Unidad");
        modelo.addColumn("Total");
        modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {
                prod.setId(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setCantidad(rs.getInt(4));
                prod.setPrecio_unidad(rs.getDouble(5));
                prod.setHora(rs.getString(7));
                prod.setFecha(rs.getString(8));

                modelo.addRow(prod.Registro());
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Elegir una opción correcta"+e.toString());
            return false;
        }
    }

    public List obtenerLista(Productos prod) {
        //con = cn.EstablecerConexion();
        List<Productos> ListaStProd = new ArrayList();
        String consulta = "select productos.id_productos, productos.nombre from productos where productos.tipo='NE'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos prodDn = new Productos();
                prodDn.setId(rs.getInt("id_productos"));
                prodDn.setNombre(rs.getString("nombre"));
                ListaStProd.add(prodDn);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener clientes dni" + e.toString());

        }
        return ListaStProd;
    }

    public void verificarNombre(Productos prod) {
        List<Productos> ListaStProd = this.obtenerLista(prod);
        for (int i = 0; i < ListaStProd.size(); i++) {
            if (ListaStProd.get(i).getNombre().equalsIgnoreCase(prod.getNombre())) {
                prod.setVer("Incorrecto");
            }
        }
    }
    
    public void verificarId(Productos prod) {
        List<Productos> ListaStProd = this.obtenerLista(prod);
        for (int i = 0; i < ListaStProd.size(); i++) {
            if (prod.getId()==ListaStProd.get(i).getId()) {
                prod.setVer("Incorrecto");
            }
        }
    }

}
