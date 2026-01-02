/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Productos;
import Modelo.Servicios;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class ConsultaPagos extends CConexion {

    CConexion cn = new CConexion();

    /*onnection con;
    ResultSet rs;
    Statement st;
    CallableStatement cs;
    PreparedStatement ps;*/
    public void cargarIdPlaca(Cliente cl, JComboBox comboplaca) {
        con = cn.EstablecerConexion();

        String consulta = "select * "
                + "from clientes cl "
                + "inner join vehiculos vh "
                + "on cl.id_cliente=vh.id_cliente "
                + "where cl.tipo='NE' and vh.tipo='NE' and cl.dni =" + cl.getDni();
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            comboplaca.removeAllItems();
            comboplaca.addItem("--Selecciona--");
            while (rs.next()) {
                comboplaca.addItem(rs.getString("vh.placa"));
            }

            if (comboplaca.getItemCount() == 1) {
                JOptionPane.showMessageDialog(null, "DNI no registrado");
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "DNI no registrado");

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 1" + e.toString());
            }
        }

    }

    public void cargarItemServicio(JComboBox comboSer) {
        con = cn.EstablecerConexion();
        String consulta = "select * from servicios WHERE servicios.tipo='NE'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            comboSer.removeAllItems();
            comboSer.addItem("--Selecciona--");
            while (rs.next()) {
                comboSer.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 2");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 2" + e.toString());
            }
        }
    }

    public void cargarItemProducto(JComboBox comboProd) {
        con = cn.EstablecerConexion();
        String consulta = "select * from productos where productos.tipo='NE'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            comboProd.removeAllItems();
            comboProd.addItem("--Selecciona--");
            while (rs.next()) {
                comboProd.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 3");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 3" + e.toString());
            }
        }
    }

    public void conseguirIdCliente(Cliente cl) {
        con = cn.EstablecerConexion();
        String consulta = "select clientes.id_cliente from clientes "
                + "where clientes.tipo='NE' and clientes.dni =" + cl.getDni();
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                cl.setId(rs.getInt("id_cliente"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar idcliente" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 4" + e.toString());
            }
        }
    }

    public void conseguirIdAutos(Autos aut) {
        con = cn.EstablecerConexion();
        String consulta = "select vehiculos.id_vehiculos from vehiculos "
                + "where vehiculos.tipo='NE' and vehiculos.placa like '%" + aut.getCod() + "%'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                aut.setId(rs.getInt("id_vehiculos"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar id_vehiculo" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 5" + e.toString());
            }
        }
    }

    public void conseguirIdServicio(Servicios ser) {
        con = cn.EstablecerConexion();
        String consulta = "select servicios.id_servicio from servicios "
                + "where servicios.tipo='NE' and servicios.nombre like '%" + ser.getNombre() + "%'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                ser.setId(rs.getInt("id_servicio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar id_servicio" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 6" + e.toString());
            }
        }
    }

    public void conseguirIdProducto(Productos prod) {
        con = cn.EstablecerConexion();
        String consulta = "select productos.id_productos from productos "
                + "where productos.tipo='NE' and productos.nombre like '%" + prod.getNombre() + "%'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                prod.setId(rs.getInt("id_productos"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar id_productos" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 7" + e.toString());
            }
        }
    }

    public void insertarFacturaCliente(Cliente cl) {
        con = cn.EstablecerConexion();
        String consultaprincipal = "insert into factura2(id_cliente,id_usuarios,tipo,tipo2) values (?,?,?,?)";
        try {
            cs = con.prepareCall(consultaprincipal);
            cs.setInt(1, cl.getId());
            cs.setInt(2, cl.getId_iden2());
            cs.setString(3, "S");
            cs.setString(4, "NE");

            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error para registrar ususus" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 8" + e.toString());
            }
        }
    }

    public void mostrarTablaFacturaCliente(JTable paramTable) {
        con = cn.EstablecerConexion();
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override

            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 9) {
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

        String sql = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),cl.dni,vh.placa,"
                + "sr.nombre,sr.precio,prod.nombre,defac.cant_prod_usados,fc.fecha "
                + "from factura2 fc "
                + "inner join clientes cl "
                + "on fc.id_cliente=cl.id_cliente "
                + "inner join detalle_factura2 defac "
                + "on fc.id_factura=defac.id_factura "
                + "inner join servicios sr "
                + "on defac.id_servicio=sr.id_servicio "
                + "inner join productos prod "
                + "on defac.id_productos=prod.id_productos "
                + "inner join vehiculos vh  "
                + "on defac.id_vehiculos=vh.id_vehiculos "
                + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                + "fc.tipo='S' and fc.tipo2='NE' AND defac.tipo='S' and defac.tipo2='NE'";

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("DNI");
        modelo.addColumn("Placa");
        modelo.addColumn("Servicio");
        modelo.addColumn("Precio");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        //modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        String[] datos = new String[9];
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                for (int i = 0; i < 9; i++) {
                    datos[i] = rs.getString((i + 1));
                }

                modelo.addRow(datos);
            }

            paramTable.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de mostrar datos" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 14" + e.toString());
            }
        }

    }

    public void modificarFacturaCliente(Cliente cl) {
        con = cn.EstablecerConexion();
        String consulta = "update factura2 set factura2.id_cliente=?"
                + " WHERE factura2.id_factura=? and factura2.tipo2='NE' and factura2.tipo='S';";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, cl.getId());
            cs.setInt(2, cl.getId_fac_pag());

            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 9" + e.toString());
        }
    }

    public List obtenerFacturasCliente(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Cliente> ListaSt3 = new ArrayList();
        String consulta = "select fc.id_factura "
                + "from factura2 fc "
                + "inner join clientes cl "
                + "on fc.id_cliente=cl.id_cliente "
                + "where cl.dni=" + cl.getDni() + " and cl.tipo='NE' and fc.tipo='S' AND fc.tipo2='NE'";

        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli3 = new Cliente();
                cli3.setId_fac_pag(rs.getInt("id_factura"));
                ListaSt3.add(cli3);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error obtener factura cliente" + e.toString());

        }
        return ListaSt3;
    }

    public List obtenerFacturasCliente2(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Cliente> ListaSt4 = new ArrayList();
        String consulta = "select fc.id_factura "
                + "from factura2 fc "
                + "inner join clientes cl "
                + "on fc.id_cliente=cl.id_cliente "
                + "inner join detalle_factura2 det "
                + "on fc.id_factura=det.id_factura "
                + "where  cl.dni=" + cl.getDni() + " and cl.tipo='NE' and fc.tipo='C' and fc.tipo2='NE' and det.tipo='S' and "
                + "det.tipo2='NE'";

        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli4 = new Cliente();
                cli4.setId_fac_pag(rs.getInt("id_factura"));
                ListaSt4.add(cli4);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error obtener factura cliente" + e.toString());

        }
        return ListaSt4;
    }

    public void modificarFacturaCliente2(Cliente cl) {
        List<Cliente> ListaSt3 = this.obtenerFacturasCliente(cl);
        String consulta = "update factura2 set factura2.tipo=? WHERE factura2.id_factura=? and factura2.tipo='S' and factura2.tipo2='NE'";
        for (int i = 0; i < ListaSt3.size(); i++) {
            try {

                con = cn.EstablecerConexion();
                ps = con.prepareStatement(consulta);
                ps.setString(1, "C");
                ps.setInt(2, ListaSt3.get(i).getId_fac_pag());

                ps.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error 23" + e.toString());
            }
        }
    }

    public void modificarFacturaCliente3(Cliente cl) {
        List<Cliente> ListaSt4 = this.obtenerFacturasCliente2(cl);
        String consulta = "update detalle_factura2 set detalle_factura2.tipo=? WHERE detalle_factura2.id_factura=? "
                + "and detalle_factura2.tipo='S' and detalle_factura2.tipo2='NE'";
        for (int i = 0; i < ListaSt4.size(); i++) {
            try {
                con = cn.EstablecerConexion();
                ps = con.prepareStatement(consulta);
                ps.setString(1, "C");
                ps.setInt(2, ListaSt4.get(i).getId_fac_pag());

                ps.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error 24" + e.toString());
            }
        }
    }

    public void eliminarFactura(Cliente cl) {

        con = cn.EstablecerConexion();
        String consulta = "delete from factura2 where factura2.id_factura=?;";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, cl.getId_fac_pag());
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 13");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 13" + e.toString());
            }
        }
    }

    public void eliminarFacturaUpdate(Cliente cl) {

        con = cn.EstablecerConexion();
        String consulta = "update factura2 set factura2.tipo2='E' WHERE factura2.id_factura=? and factura2.tipo2='NE' and factura2.tipo='S';";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, cl.getId_fac_pag());
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 13");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 13" + e.toString());
            }
        }
    }

    public void eliminarDetalleFacturaUpdate(Cliente cl, Productos prod, Autos aut, Servicios ser) {

        con = cn.EstablecerConexion();
        String consulta = "update detalle_factura2 set detalle_factura2.tipo2='E' WHERE detalle_factura2.id_factura=? and "
                + "detalle_factura2.id_vehiculos=? and detalle_factura2.id_servicio=? and detalle_factura2.id_productos=? and "
                + "detalle_factura2.cant_prod_usados=? "
                + "and detalle_factura2.tipo2='NE' and detalle_factura2.tipo='S';";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, cl.getId_fac_pag());
            cs.setInt(2, aut.getId());
            cs.setInt(3, ser.getId());
            cs.setInt(4, prod.getId());
            cs.setInt(5, prod.getCantidad());
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 21");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 21" + e.toString());
            }
        }
    }

    public void eliminarDetalleFactura(Cliente cl) {

        con = cn.EstablecerConexion();
        String consulta = "delete from detalle_factura2 where detalle_factura2.id_factura=?;";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, cl.getId_fac_pag());
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 21");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 21" + e.toString());
            }
        }
    }

    public void conseguirCantidadProductos(Productos prod) {
        con = cn.EstablecerConexion();
        String consulta = "select productos.cantidad from productos "
                + "where productos.nombre like '%" + prod.getNombre() + "%' and productos.tipo='NE'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                prod.setCantidad(rs.getInt("cantidad"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar id_productos" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 12" + e.toString());
            }
        }
    }

    public void insertarDatosTabladetallePago(Cliente cl, Productos prod, Servicios ser, Autos aut) {
        con = cn.EstablecerConexion();
        String consultaprincipal = "insert into "
                + "detalle_factura2(id_factura,id_vehiculos,id_servicio,id_productos,"
                + "cantidad_servicios,cant_prod_usados,total_servicio,desc_igv,pagocondesc,tipo,tipo2) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            cs = con.prepareCall(consultaprincipal);
            cs.setInt(1, cl.getId_fac_pag());
            cs.setInt(2, aut.getId());
            cs.setInt(3, ser.getId());
            cs.setInt(4, prod.getId());
            cs.setInt(5, 0);
            cs.setInt(6, prod.getCantidad());
            cs.setDouble(7, 0);
            cs.setDouble(8, 0);
            cs.setDouble(9, 0);
            cs.setString(10, "S");
            cs.setString(11, "NE");

            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error para registrar" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 10" + e.toString());
            }
        }
    }

    public void modificarDatosTabladetallePago(Cliente cl, Productos prod, Autos aut, Servicios ser,
            int id_aut, int id_ser, int id_prod, int cant) {
        con = cn.EstablecerConexion();
        String consultaprincipal = "update detalle_factura2 as dt "
                + "inner join factura2 as f "
                + "on dt.id_factura=f.id_factura "
                + "set dt.cant_prod_usados=?, dt.id_vehiculos=?, dt.id_servicio=?,dt.id_productos=? "
                + "WHERE f.id_factura=? and "
                + "dt.id_vehiculos=? and dt.id_servicio=? and dt.id_productos=? and "
                + "dt.cant_prod_usados=? "
                + "AND f.tipo='S' and dt.tipo='S' and dt.tipo2='NE' AND f.tipo2='NE'";
        try {
            cs = con.prepareCall(consultaprincipal);
            //cs.setInt(1, cl.getId_fac_pag());
            cs.setInt(1, prod.getCantidad());
            cs.setInt(2, aut.getId());
            cs.setInt(3, ser.getId());
            cs.setInt(4, prod.getId());
            cs.setInt(5, cl.getId_fac_pag());
            cs.setInt(6, id_aut);
            cs.setInt(7, id_ser);
            cs.setInt(8, id_prod);
            cs.setInt(9, cant);

            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error para registrar" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 11" + e.toString());
            }
        }
    }

    public void conseguirIDFactura(Cliente cl, Autos aut, Servicios ser, Productos prod) {
        con = cn.EstablecerConexion();
        String consulta = "select fac.id_factura "
                + "from factura2 fac "
                + "where fac.id_cliente=" + cl.getId()
                + " and fac.tipo='S' AND fac.tipo2='NE'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                cl.setId_fac_pag(rs.getInt("id_factura"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar id_factura" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 18" + e.toString());
            }
        }
    }

    public boolean buscarTablaFacturaCliente(JTable paramTable, Cliente cl, JComboBox combo) {
        con = cn.EstablecerConexion();
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override

            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 9) {
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

        String combo2 = combo.getSelectedItem().toString();

        String consulta = "";

        if (combo2.equalsIgnoreCase("DNI")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),cl.dni,vh.placa,"
                    + "sr.nombre,sr.precio,prod.nombre,defac.cant_prod_usados,fc.fecha "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "cl.dni like '%" + cl.getDni() + "%' and defac.tipo='S' and defac.tipo2='NE'";
        } else if (combo2.equalsIgnoreCase("Nombre Servicio")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),cl.dni,vh.placa,"
                    + "sr.nombre,sr.precio,prod.nombre,defac.cant_prod_usados,fc.fecha "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "sr.nombre like '%" + cl.getNombre() + "%' and defac.tipo='S' and defac.tipo2='NE'";
        } else if (combo2.equalsIgnoreCase("Nombre Producto")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),cl.dni,vh.placa,"
                    + "sr.nombre,sr.precio,prod.nombre,defac.cant_prod_usados,fc.fecha "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "prod.nombre like '%" + cl.getNombre() + "%' and defac.tipo='S' and defac.tipo2='NE'";
        }

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("DNI");
        modelo.addColumn("Placa");
        modelo.addColumn("Servicio");
        modelo.addColumn("Precio");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        //modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        String[] datos = new String[9];
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                for (int i = 0; i < 9; i++) {
                    datos[i] = rs.getString((i + 1));
                }

                modelo.addRow(datos);
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error de mostrar datos" + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                //JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }

    }

    public boolean agruparTablaFacturaCliente(JTable paramTable, Cliente cl, JComboBox combo) {
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

        String combo2 = combo.getSelectedItem().toString();

        String consulta = "";

        if (combo2.equalsIgnoreCase("DNI")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),cl.dni,vh.placa,"
                    + "sr.nombre,sr.precio,count(*) as Num_serv "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "cl.dni=" + cl.getDni() + " and defac.tipo='S' and defac.tipo2='NE'"
                    + " group by cl.dni,sr.nombre,vh.placa "
                    + "order by fc.id_factura asc";
        } else if (combo2.equalsIgnoreCase("Nombre")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),cl.dni,vh.placa,"
                    + "sr.nombre,sr.precio,count(*) as Num_serv "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "concat_ws(' ', cl.nombre,cl.apellido) like '%" + cl.getNombre() + "%' " + " and defac.tipo='S' and defac.tipo2='NE'"
                    + "group by cl.dni,sr.nombre,vh.placa "
                    + "order by fc.id_factura asc";
        }

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("DNI");
        modelo.addColumn("Placa");
        modelo.addColumn("Servicio");
        modelo.addColumn("Precio Servicio");
        modelo.addColumn("Cantidad de Servicios");

        paramTable.setModel(modelo);

        String[] datos = new String[7];
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    datos[i] = rs.getString((i + 1));
                }

                modelo.addRow(datos);
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error de mostrar datos" + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                //JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }

    }

    public void precioTablaDetalleFactura(JTable paramTable, Cliente cl, JComboBox combo) {
        con = cn.EstablecerConexion();

        String combo2 = combo.getSelectedItem().toString();
        String consulta = "";

        if (combo2.equalsIgnoreCase("DNI")) {
            consulta = "select "
                    + "sr.precio as precio_serv "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "cl.dni=" + cl.getDni() + " and defac.tipo='S' and defac.tipo2='NE'"
                    + " group by cl.dni,sr.nombre,vh.placa;";
        } else if (combo2.equalsIgnoreCase("Nombre")) {
            consulta = "select "
                    + "sr.precio as precio_serv "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh  "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.tipo='NE' and vh.tipo='NE' and sr.tipo='NE' and prod.tipo='NE' and "
                    + "concat_ws(' ', cl.nombre,cl.apellido) like '%" + cl.getNombre() + "%' " + " and defac.tipo='S' AND defac.tipo2='NE'"
                    + "group by cl.dni,sr.nombre,vh.placa;";
        }

        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            double pagoAcumulador = 0;
            int a = 0;
            while (rs.next()) {
                cl.setPago(rs.getInt("precio_serv"));
                //double pagoAcumulador=0;
                pagoAcumulador = pagoAcumulador + cl.getPago();
                cl.setPago(pagoAcumulador);
                a++;//numero de servicios
                cl.setCant_ser(a);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar idcliente" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 19" + e.toString());
            }
        }

    }

    public void insertarDatosDetalleFactura(Cliente cl) {
        con = cn.EstablecerConexion();
        DecimalFormat df = new DecimalFormat("#.000");
        String consultaprincipal = "update detalle_factura2 as dt "
                + "inner join factura2 as f "
                + "on dt.id_factura=f.id_factura "
                + "set dt.cantidad_servicios=?,dt.total_servicio=?,dt.desc_igv=?,dt.pagocondesc=? "
                + "WHERE f.id_cliente=? and dt.tipo='S' and dt.tipo2='NE';";
        try {
            cs = con.prepareCall(consultaprincipal);
            cs.setInt(1, cl.getCant_ser());
            cs.setDouble(2, cl.getPago());
            cs.setString(3, df.format(cl.descuento_Beneficio()));
            cs.setDouble(4, cl.pagoFinal());
            cs.setInt(5, cl.getId());

            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error para registrar" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 20" + e.toString());
            }
        }
    }

    public List obtenerStockUsado(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Productos> ListaSt = new ArrayList();
        String consulta = "select prod.nombre,sum(defac.cant_prod_usados) as suma "
                + "from factura2 fc "
                + "inner join clientes cl "
                + "on fc.id_cliente=cl.id_cliente "
                + "inner join detalle_factura2 defac "
                + "on fc.id_factura=defac.id_factura "
                + "inner join productos prod "
                + "on defac.id_productos=prod.id_productos "
                + "inner join servicios ser "
                + "on defac.id_servicio=ser.id_servicio "
                + "where cl.tipo='NE' and prod.tipo='NE' and cl.dni=" + cl.getDni() + " and defac.tipo='S' and defac.tipo2='NE'"
                + "group by prod.nombre;";

        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos prod = new Productos();
                prod.setNombre(rs.getString("nombre"));
                prod.setCantidad(rs.getInt("suma"));
                ListaSt.add(prod);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error obtener stock cliente" + e.toString());

        }
        return ListaSt;
    }

    public List obtenerStockSistema(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Productos> ListaSt2 = new ArrayList();
        String consulta = "select productos.nombre,productos.cantidad from productos where productos.tipo='NE'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos prod = new Productos();
                prod.setCantidad2(rs.getInt("cantidad"));
                ListaSt2.add(prod);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener stock sistmea" + e.toString());

        }
        return ListaSt2;
    }

    public void ActualizarStock2(Productos prod, Cliente cl) {

        List<Productos> ListaSt = this.obtenerStockUsado(cl);
        List<Productos> ListaSt2 = this.obtenerStockSistema(cl);
        for (int i = 0; i < ListaSt.size(); i++) {
            //System.out.println(cant1+cant2+nombreR);
            String consulta = "update productos set productos.cantidad=? where productos.nombre=? and productos.tipo='NE'";
            try {
                con = cn.EstablecerConexion();
                ps = con.prepareStatement(consulta);
                //cs.setInt(1, cl.getId_fac_pag());
                ps.setInt(1, ListaSt2.get(i).getCantidad2() - ListaSt.get(i).getCantidad());
                ps.setString(2, ListaSt.get(i).getNombre());

                ps.execute();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error para actualizar stock" + e.toString());
            }
        }

    }

    public void obtenerCantidadStokProducto(Productos prod, int cantidadComprobacion, JComboBox opcion) {
        String consulta = "select productos.cantidad from productos where productos.tipo='NE' "
                + "and productos.nombre" + opcion.getSelectedItem().toString();
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                prod.setCantidad(rs.getInt("cantidad"));
            }
        } catch (Exception e) {
        }

    }

    public void disminuirStock(Productos prod, int total) {
        con = cn.EstablecerConexion();
        String consulta = "update productos set productos.cantidad=? where productos.tipo='NE' "
                + "and productos.nombre='" + prod.getNombre() + "'";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, total);
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 13");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 13" + e.toString());
            }
        }
    }

    public List obtenerMaterialesProductos(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Productos> ListaStMat = new ArrayList();
        String consulta = "select vh.placa as vehi,ser.nombre as serv,prod.nombre as prod,det.cant_prod_usados as cantidad "
                + "from detalle_factura2 det "
                + "inner join factura2 f "
                + "on det.id_factura=f.id_factura "
                + "inner join clientes cl "
                + "on f.id_cliente=cl.id_cliente "
                + "inner join servicios ser "
                + "on det.id_servicio=ser.id_servicio "
                + "inner join vehiculos vh "
                + "on det.id_vehiculos=vh.id_vehiculos "
                + "inner join productos prod "
                + "on det.id_productos=prod.id_productos "
                + "where cl.dni=" + cl.getDni() + " and det.tipo2='NE' and f.tipo2='NE' and cl.tipo='NE' AND det.tipo='S'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos prod2 = new Productos();
                prod2.setNombre(rs.getString("prod"));
                prod2.setCantidad2(rs.getInt("cantidad"));
                ListaStMat.add(prod2);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener productos sistmea" + e.toString());

        }
        return ListaStMat;
    }

    public List obtenerMaterialesServicio(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Servicios> ListaStSerz = new ArrayList();
        String consulta = "select vh.placa as veh,ser.nombre as ser,prod.nombre as prod,det.cant_prod_usados as cantidad "
                + "from detalle_factura2 det "
                + "inner join factura2 f "
                + "on det.id_factura=f.id_factura "
                + "inner join clientes cl "
                + "on f.id_cliente=cl.id_cliente "
                + "inner join servicios ser "
                + "on det.id_servicio=ser.id_servicio "
                + "inner join vehiculos vh "
                + "on det.id_vehiculos=vh.id_vehiculos "
                + "inner join productos prod "
                + "on det.id_productos=prod.id_productos "
                + "where cl.dni=" + cl.getDni() + " and det.tipo2='NE' and f.tipo2='NE' and cl.tipo='NE' AND det.tipo='S'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicios serz = new Servicios();
                serz.setNombre(rs.getString("ser"));
                ListaStSerz.add(serz);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener servicio sistmea" + e.toString());

        }
        return ListaStSerz;
    }

    public List obtenerMaterialesVehiculo(Cliente cl) {
        //con = cn.EstablecerConexion();
        List<Autos> ListaStSAut = new ArrayList();
        String consulta = "select vh.placa as veh,ser.nombre as ser,prod.nombre as prod,det.cant_prod_usados as cantidad "
                + "from detalle_factura2 det "
                + "inner join factura2 f "
                + "on det.id_factura=f.id_factura "
                + "inner join clientes cl "
                + "on f.id_cliente=cl.id_cliente "
                + "inner join servicios ser "
                + "on det.id_servicio=ser.id_servicio "
                + "inner join vehiculos vh "
                + "on det.id_vehiculos=vh.id_vehiculos "
                + "inner join productos prod "
                + "on det.id_productos=prod.id_productos "
                + "where cl.dni=" + cl.getDni() + " and det.tipo2='NE' and f.tipo2='NE' and cl.tipo='NE' and det.tipo='S'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Autos aut2 = new Autos();
                aut2.setCod(rs.getString("veh"));
                ListaStSAut.add(aut2);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener autos sistmea" + e.toString());

        }
        return ListaStSAut;
    }

    public void verificarDatosPago(Cliente cl, Productos prod, JComboBox placavehi, JComboBox nom_serv, JComboBox nom_produ, JTextField cant) {
        List<Productos> ListaStMat = this.obtenerMaterialesProductos(cl);
        List<Servicios> ListaStSerz = this.obtenerMaterialesServicio(cl);
        List<Autos> ListaStSAut = this.obtenerMaterialesVehiculo(cl);

        for (int i = 0; i < ListaStMat.size(); i++) {

            if (ListaStSAut.get(i).getCod().equalsIgnoreCase(placavehi.getSelectedItem().toString())
                    && ListaStSerz.get(i).getNombre().equalsIgnoreCase(nom_serv.getSelectedItem().toString())
                    && ListaStMat.get(i).getNombre().equalsIgnoreCase(nom_produ.getSelectedItem().toString())) {
                cl.setVer("Incorrecto");
            }
        }
    }

    public void conseguirNombreCliente(Cliente cl) {
        con = cn.EstablecerConexion();
        String consulta = "select cl.nombre "
                + "from factura2 f "
                + "inner join clientes cl "
                + "on f.id_cliente=cl.id_cliente "
                + "where f.id_factura =" + cl.getId_fac_pag();
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                cl.setNombre(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar idcliente" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error nombre" + e.toString());
            }
        }
    }

    public void conseguirIdUsuario(Cliente cl, JLabel nom_trabaj) {
        con = cn.EstablecerConexion();
        String consulta = "select us.id_usuarios from usuarios as us "
                + "where concat_ws(' ', us.nombre,us.apellido) like '%" + nom_trabaj.getText() + "%' and us.tipo='NE'";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            //setNombreComboCli(sr1.getString("id_cliente"));
            while (rs.next()) {
                cl.setId_iden2(rs.getInt("id_usuarios"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar ID_USUARIOS" + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error 7" + e.toString());
            }
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
                cl.setVer("Correcto");
            }
        }
    }

    
}
