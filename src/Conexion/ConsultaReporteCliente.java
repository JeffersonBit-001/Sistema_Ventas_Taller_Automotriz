/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
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
public class ConsultaReporteCliente extends CConexion {

    CConexion cn = new CConexion();

    /*Connection con;
    ResultSet rs;
    Statement st;
    CallableStatement cs;*/
    public boolean verficarBusqueda(Cliente cl, JTextField busqueda) {
        try {
            cl.setDni(Integer.parseInt(busqueda.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void mostrarDatos(JTable paramTable) {
        con = cn.EstablecerConexion();
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

        String sql = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),"
                + "cl.dni,cl.celular,count(DISTINCT sr.nombre) cant_servc,"
                + "sum(defac.cant_prod_usados),defac.total_servicio,defac.desc_igv,"
                + "defac.pagocondesc,fc.fecha "
                + "from factura2 fc "
                + "inner join clientes cl "
                + "on fc.id_cliente=cl.id_cliente "
                + "inner join detalle_factura2 defac "
                + "on fc.id_factura=defac.id_factura "
                + "inner join vehiculos vh "
                + "on defac.id_vehiculos=vh.id_vehiculos "
                + "inner join servicios sr "
                + "on defac.id_servicio=sr.id_servicio "
                + "inner join productos prod "
                + "on defac.id_productos=prod.id_productos "
                + "where fc.tipo='C' and fc.tipo2='NE' and defac.tipo='C' and defac.tipo2='NE' "
                + "group by fc.id_factura "
                + "order by fc.id_factura asc";

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("DNI");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Total Servicios");
        modelo.addColumn("Total Productos");
        modelo.addColumn("Monto");
        modelo.addColumn("IGV");
        modelo.addColumn("Monto Total");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        String[] datos = new String[10];
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                for (int i = 0; i < 10; i++) {
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
                JOptionPane.showMessageDialog(null, "Error 1" + e.toString());
            }
        }

    }

    public void buscarTablaReportCliente(JTable paramTable, Cliente cl, JComboBox combo) {
        con = cn.EstablecerConexion();
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

        String combo2 = combo.getSelectedItem().toString();

        String consulta = "";

        if (combo2.equalsIgnoreCase("DNI")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),"
                    + "cl.dni,cl.celular,count(DISTINCT sr.nombre) cant_servc,"
                    + "sum(defac.cant_prod_usados),defac.total_servicio,defac.desc_igv,"
                    + "defac.pagocondesc,fc.fecha "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where cl.dni =" + cl.getId() + " and "
                    + "fc.tipo='C' and fc.tipo2='NE' and defac.tipo='C' and defac.tipo2='NE' "
                    + "group by fc.id_factura "
                    + "order by fc.id_factura asc";

        } else if (combo2.equalsIgnoreCase("Nombre")) {
            consulta = "select fc.id_factura,concat_ws(' ', cl.nombre,cl.apellido),"
                    + "cl.dni,cl.celular,count(DISTINCT sr.nombre) cant_servc,"
                    + "sum(defac.cant_prod_usados),defac.total_servicio,defac.desc_igv,"
                    + "defac.pagocondesc,fc.fecha "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where concat_ws(' ', cl.nombre,cl.apellido) like '%" + cl.getNombre() + "%' and "
                    + "fc.tipo='C' and fc.tipo2='NE' and defac.tipo='C' and defac.tipo2='NE' "
                    + "group by fc.id_factura "
                    + "order by fc.id_factura asc";
        } else if (combo2.equalsIgnoreCase("Mes")) {
            consulta = "select fc.id_factura,concat_ws(' ', "
                    + "cl.nombre,cl.apellido),cl.dni,cl.celular,count(DISTINCT sr.nombre) cant_servc,"
                    + "sum(defac.cant_prod_usados),defac.total_servicio,defac.desc_igv,"
                    + "defac.pagocondesc,fc.fecha "
                    + "from factura2 fc "
                    + "inner join clientes cl "
                    + "on fc.id_cliente=cl.id_cliente "
                    + "inner join detalle_factura2 defac "
                    + "on fc.id_factura=defac.id_factura "
                    + "inner join vehiculos vh "
                    + "on defac.id_vehiculos=vh.id_vehiculos "
                    + "inner join servicios sr "
                    + "on defac.id_servicio=sr.id_servicio "
                    + "inner join productos prod "
                    + "on defac.id_productos=prod.id_productos "
                    + "where date_format(fc.fecha,'%M')='" + cl.getNombre() + "' and "
                    + "fc.tipo='C' and fc.tipo2='NE' and defac.tipo='C' and defac.tipo2='NE' "
                    + "group by fc.id_factura "
                    + "order by fc.id_factura asc";
            //"SET lc_time_names = 'es_ES'; "
        }

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("DNI");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Total Servicios");
        modelo.addColumn("Total Productos");
        modelo.addColumn("Monto");
        modelo.addColumn("IGV");
        modelo.addColumn("Monto Total");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        String[] datos = new String[10];
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                for (int i = 0; i < 10; i++) {
                    datos[i] = rs.getString((i + 1));
                }

                modelo.addRow(datos);
            }

            paramTable.setModel(modelo);

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error de mostrar datos" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                //JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }

    }

    public void eliminarTablaDetalleReportClienteUpdate(Cliente cl) {
        //String consulta = "update detalle_factura set detalle_factura.tipo2='E' where detalle_factura.id_factura=? and detalle_factura.tipo2='NE' and detalle_factura.tipo='C'";
        String consulta = "update detalle_factura2 as defac "
                + "inner join factura2 f "
                + "on defac.id_factura=f.id_factura "
                + "set defac.tipo2='E' "
                + "where "
                + "f.id_factura=? and defac.tipo2='NE' and defac.tipo='C' and f.tipo2='NE'";
        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, cl.getId());
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

    public void eliminarTablaReportFacturaClienteUpdate(Cliente cl) {
        //String consulta = "update factura2 set factura2.tipo2='E' where detalle_factura.id_factura=? and detalle_factura.tipo2='NE' and detalle_factura.tipo='C'";
        String consulta = "update factura2 as f "
                + "inner join detalle_factura2 defac "
                + "on f.id_factura=defac.id_factura "
                + "set f.tipo2='E' where "
                + "f.id_factura=? and defac.tipo2='E' and defac.tipo='C' and f.tipo2='NE'";
        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, cl.getId());
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
