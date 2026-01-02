/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Cliente;
import Modelo.Productos;
import Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JEFFERSON
 */
public class ConsultaUsuarioPagos extends CConexion {

    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;

    public void cargarUsuarioBox(Usuario us, JComboBox comboUs) {

        String consulta = "select concat_ws(' ', nombre,apellido) as nombre_completo from usuarios "
                + "where usuarios.tipo='NE'";
        con = cn.EstablecerConexion();
        try {

            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            comboUs.removeAllItems();
            comboUs.addItem("--Seleccina--");
            while (rs.next()) {
                comboUs.addItem(rs.getString("nombre_completo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }*/

    public void consultarDatosUsuarioAPagos(Usuario us) {
        String consulta = "select usuarios.id_usuarios "
                + "from usuarios "
                + "where usuarios.tipo='NE' and usuarios.dni=" + us.getDni();
        //Statement st1;
        //Statement st2;

        con = cn.EstablecerConexion();
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                us.setId(Integer.parseInt(rs.getString("id_usuarios")));
            }

            System.out.println(us.getId());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 1");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public void insertarDatosUsuariosAPagos(Usuario us) {
        String consulta = "insert into pagos(id_usuarios,tipo) values (?,?)";

        con = cn.EstablecerConexion();
        try {
            CallableStatement cs = con.prepareCall(consulta);
            cs.setInt(1, us.getId());
            cs.setString(2, "NE");
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 2");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public void consultarDatosDetalleAPago(Usuario us) {
        String consulta = "select pg.id_pagos "
                + "from pagos pg "
                + "inner join usuarios us "
                + "on pg.id_usuarios=us.id_usuarios "
                + "where us.tipo='NE' and pg.tipo='NE' and us.dni=" + us.getDni();

        con = cn.EstablecerConexion();
        try {
            st = con.prepareCall(consulta);
            //ResultSet rs;
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                us.setId_fac_pag(Integer.parseInt(rs.getString("id_pagos")));
            }
            System.out.println(us.getId());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 3");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    public void insertarDatosDetalleAPago(Usuario us) {
        String consulta = "insert into detalle_pago(id_pagos,serv_adicional,precio_aum,sueldo,bonificacion,total,tipo) values (?,?,?,?,?,?,?)";
        con = cn.EstablecerConexion();
        try {
            CallableStatement cs = con.prepareCall(consulta);
            cs.setInt(1, us.getId_fac_pag());
            cs.setInt(2, us.getCant_aut_ven());
            cs.setDouble(3, us.getPrecio_adicion());
            cs.setDouble(4, us.pago());
            cs.setDouble(5, us.descuento_Beneficio());
            cs.setDouble(6, us.pagoFinal());
            cs.setString(7, "NE");
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 4");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public void mostrarTablaPagos(Usuario us, JTable paramTable) {
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

        modelo.addColumn("ID Pago");
        modelo.addColumn("USuario");
        modelo.addColumn("DNI");
        modelo.addColumn("Sueldo Mensual");
        modelo.addColumn("Cant. Serv. Adi.");
        modelo.addColumn("Precio serv. Adi.");
        modelo.addColumn("Bonificación");
        modelo.addColumn("Sueldo Total");
        modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);
        String sql = "select pg.id_pagos,concat_ws(' ', us.nombre,us.apellido),us.dni,dtp.sueldo, "
                + "dtp.serv_adicional,dtp.precio_aum,dtp.bonificacion,dtp.total,pg.hora,pg.fecha "
                + "from pagos pg "
                + "inner join usuarios us "
                + "on pg.id_usuarios=us.id_usuarios "
                + "inner join detalle_pago dtp "
                + "on pg.id_pagos=dtp.id_pagos "
                + "where us.tipo='NE' and pg.tipo='NE' and dtp.tipo='NE'";

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                us.setId_fac_pag(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setDni(rs.getInt(3));
                us.setSueldo(rs.getDouble(4));
                us.setCant_aut_ven(rs.getInt(5));
                us.setPrecio_adicion(rs.getDouble(6));
                us.setBenef_desc(rs.getDouble(7));
                us.setPag_final(rs.getDouble(8));
                us.setHora(rs.getString(9));
                us.setFecha(rs.getString(10));
                modelo.addRow(us.RegistroTablaPagos());
            }

            paramTable.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error NNNN" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    public void modificarDatosDetalleUsuarioPago(Usuario us) {

        String consulta = "update pagos set pagos.id_usuarios=? where pagos.id_pagos=? and pagos.tipo='NE'";

        con = cn.EstablecerConexion();
        try {

            CallableStatement cs = con.prepareCall(consulta);
            //cs.setInt(1, us.getId());
            cs.setInt(1, us.getId());
            cs.setInt(2, us.getId_fac_pag());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 7" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }

    }

    public void modificarDatosDetallePago(Usuario us) {

        String consulta = "UPDATE detalle_pago SET detalle_pago.serv_adicional = ?,"
                + "detalle_pago.precio_aum = ?,detalle_pago.sueldo = ?,detalle_pago.bonificacion = ?, "
                + "detalle_pago.total = ? WHERE detalle_pago.id_pagos = ? and detalle_pago.tipo='NE'";
        con = cn.EstablecerConexion();
        try {

            CallableStatement cs = con.prepareCall(consulta);
            //cs.setInt(1, us.getId());
            cs.setInt(1, us.getCant_aut_ven());
            cs.setDouble(2, us.getPrecio_adicion());
            cs.setDouble(3, us.pago());
            cs.setDouble(4, us.descuento_Beneficio());
            cs.setDouble(5, us.pagoFinal());
            cs.setInt(6, us.getId_fac_pag());
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 6" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error" + e.toString());
            }
        }

    }

    public void selecionUsuariosDetallePago(Usuario us) {
        String consulta = "select usuarios.rol from usuarios "
                + "where usuarios.tipo='NE' and usuarios.dni=" + us.getDni();
        con = cn.EstablecerConexion();
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                us.setRol(rs.getString("rol"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error 4");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    public void eliminarUsuariosDetallePagoTablaDetalle(Usuario us) {
        String consulta = "DELETE from detalle_pago WHERE detalle_pago.id_pagos=?;";

        con = cn.EstablecerConexion();
        try {

            CallableStatement cs = con.prepareCall(consulta);

            cs.setInt(1, us.getId_fac_pag());
            cs.execute();

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

    public void eliminarUsuariosDetallePagoTablaDetalleUpdate(Usuario us) {
        String consulta = "UPDATE detalle_pago SET detalle_pago.tipo='E' WHERE detalle_pago.id_pagos = ? and detalle_pago.tipo='NE'";

        con = cn.EstablecerConexion();
        try {

            CallableStatement cs = con.prepareCall(consulta);

            cs.setInt(1, us.getId_fac_pag());
            cs.execute();

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

    public void eliminarUsuariosDetallePagoTablaPagos(Usuario us) {
        String consulta = "DELETE from pagos WHERE pagos.id_pagos=?;";

        con = cn.EstablecerConexion();
        try {
            CallableStatement cs = con.prepareCall(consulta);
            cs.setInt(1, us.getId_fac_pag());
            cs.execute();

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

    public void eliminarUsuariosDetallePagoTablaPagosUpdate(Usuario us) {
        String consulta = "update pagos set pagos.tipo='E' where pagos.id_pagos=? and pagos.tipo='NE'";

        con = cn.EstablecerConexion();
        try {
            CallableStatement cs = con.prepareCall(consulta);
            cs.setInt(1, us.getId_fac_pag());
            cs.execute();

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

    public boolean buscarUsuariosDetallePago(Usuario us, JTable paramTable, JComboBox combo) {

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
        TableRowSorter<TableModel> ordenarTable = new TableRowSorter<TableModel>(modelo);
        paramTable.setRowSorter(ordenarTable);

        String combo2 = combo.getSelectedItem().toString();

        String consulta = "";

        if (combo2.equals("ID Pago")) {
            consulta = "select pg.id_pagos,concat_ws(' ', us.nombre,us.apellido),us.dni,dtp.sueldo, "
                    + "dtp.serv_adicional,dtp.precio_aum,dtp.bonificacion,dtp.total,pg.hora,pg.fecha "
                    + "from pagos pg "
                    + "inner join usuarios us "
                    + "on pg.id_usuarios=us.id_usuarios "
                    + "inner join detalle_pago dtp "
                    + "on pg.id_pagos=dtp.id_pagos "
                    + "where us.tipo='NE' and pg.tipo='NE' and dtp.tipo='NE' and "
                    + "pg.id_pagos =" + us.getId_fac_pag();

        } else if (combo2.equals("Nombre")) {
            consulta = "select pg.id_pagos,concat_ws(' ', us.nombre,us.apellido),us.dni,dtp.sueldo, "
                    + "dtp.serv_adicional,dtp.precio_aum,dtp.bonificacion,dtp.total,pg.hora,pg.fecha "
                    + "from pagos pg "
                    + "inner join usuarios us "
                    + "on pg.id_usuarios=us.id_usuarios "
                    + "inner join detalle_pago dtp "
                    + "on pg.id_pagos=dtp.id_pagos "
                    + "where us.tipo='NE' and pg.tipo='NE' and dtp.tipo='NE' and "
                    + "concat_ws(' ', us.nombre,us.apellido) like '%" + us.getNombre() + "%'";

        } else if (combo2.equalsIgnoreCase("DNI")) {
            consulta = "select pg.id_pagos,concat_ws(' ', us.nombre,us.apellido),us.dni,dtp.sueldo, "
                    + "dtp.serv_adicional,dtp.precio_aum,dtp.bonificacion,dtp.total,pg.hora,pg.fecha "
                    + "from pagos pg "
                    + "inner join usuarios us "
                    + "on pg.id_usuarios=us.id_usuarios "
                    + "inner join detalle_pago dtp "
                    + "on pg.id_pagos=dtp.id_pagos "
                    + "where us.tipo='NE' and pg.tipo='NE' and dtp.tipo='NE' and "
                    + "us.dni like '%" + us.getDni()+ "%'";
        } else if (combo2.equals("Fecha")) {
            consulta = "select pg.id_pagos,concat_ws(' ', us.nombre,us.apellido),us.dni,dtp.sueldo, "
                    + "dtp.serv_adicional,dtp.precio_aum,dtp.bonificacion,dtp.total,pg.hora,pg.fecha "
                    + "from pagos pg "
                    + "inner join usuarios us "
                    + "on pg.id_usuarios=us.id_usuarios "
                    + "inner join detalle_pago dtp "
                    + "on pg.id_pagos=dtp.id_pagos "
                    + "where us.tipo='NE' and pg.tipo='NE' and dtp.tipo='NE' and "
                    + " date_format(pg.fecha,'%M')='" + us.getNombre() + "'";
        }

        modelo.addColumn("ID Pago");
        modelo.addColumn("Usuario");
        modelo.addColumn("DNI");
        modelo.addColumn("Sueldo Mensual");
        modelo.addColumn("Cant. Serv. Adi.");
        modelo.addColumn("Precio serv. Adi.");
        modelo.addColumn("Bonificación");
        modelo.addColumn("Sueldo Total");
        modelo.addColumn("Hora");
        modelo.addColumn("Fecha");

        paramTable.setModel(modelo);

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {
                us.setId_fac_pag(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setDni(rs.getInt(3));
                us.setSueldo(rs.getDouble(4));
                us.setCant_aut_ven(rs.getInt(5));
                us.setPrecio_adicion(rs.getDouble(6));
                us.setBenef_desc(rs.getDouble(7));
                us.setPag_final(rs.getDouble(8));
                us.setHora(rs.getString(9));
                us.setFecha(rs.getString(10));
                modelo.addRow(us.RegistroTablaPagos());
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Elegir una opción correcta");
            return false;

        }
    }

    public List obtenerUsuariosPagos(Usuario us) {
        //con = cn.EstablecerConexion();
        List<Usuario> ListaPag = new ArrayList();
        String consulta = "select concat_ws(' ', us.nombre,us.apellido) as nombre_us, DATEDIFF(curdate(),pg.fecha) as fecha_dif, "
                + "us.dni,pg.id_pagos"
                + " "
                + "from pagos pg "
                + "inner join usuarios us "
                + "on pg.id_usuarios=us.id_usuarios "
                + "where pg.tipo='NE' and us.tipo='NE'";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario ususs = new Usuario();
                ususs.setNombre(rs.getString("nombre_us"));
                ususs.setFecha(rs.getString("fecha_dif"));
                ususs.setDni(rs.getInt("dni"));
                ususs.setId_fac_pag(rs.getInt("id_pagos"));
                ListaPag.add(ususs);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener stock sistmea" + e.toString());

        }
        return ListaPag;
    }

    public void verficarUsuPagos(Usuario ususs) {

        List<Usuario> ListaPag = this.obtenerUsuariosPagos(ususs);
        for (int i = 0; i < ListaPag.size(); i++) {
            if (ususs.getDni()==ListaPag.get(i).getDni() && Integer.parseInt(ListaPag.get(i).getFecha()) < 30) {
                ususs.setVer("Incorrecto");
            }
        }
    }
    
    public void verficarUsuIDPPag(Usuario ususs) {

        List<Usuario> ListaPag = this.obtenerUsuariosPagos(ususs);
        for (int i = 0; i < ListaPag.size(); i++) {
            if (ususs.getId_fac_pag()==ListaPag.get(i).getId_fac_pag()) {
                ususs.setVer("Incorrecto");
            }
        }
    }
    
    public void verficarUsuDNI(Usuario ususs) {

        List<Usuario> ListaPag = this.obtenerUsuariosPagos(ususs);
        for (int i = 0; i < ListaPag.size(); i++) {
            if (ususs.getDni()==ListaPag.get(i).getDni()) {
                ususs.setVer("Incorrecto");
            }
        }
    }
    
    public void verficarUsuNomb(Usuario ususs) {

        List<Usuario> ListaPag = this.obtenerUsuariosPagos(ususs);
        for (int i = 0; i < ListaPag.size(); i++) {
            if (ususs.getId_fac_pag()==ListaPag.get(i).getId_fac_pag()) {
                ususs.setVer("Incorrecto");
            }
        }
    }
}
