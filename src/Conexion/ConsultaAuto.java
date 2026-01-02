/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Autos;
import Modelo.Cliente;
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
public class ConsultaAuto extends CConexion {
    CConexion cn = new CConexion();
    /*Connection con;
    ResultSet rs;
    Statement st;
    CallableStatement cs;
    PreparedStatement ps;*/

    
    public void conseguirIDAuto(Autos aut){
        String consulta = "select cl.id_cliente "
                + "from clientes as cl "
                + "left join vehiculos vh "
                + "on cl.id_cliente=vh.id_cliente "
                + " where cl.tipo='NE' and cl.dni = "+aut.getDni_cliente();
        con=cn.EstablecerConexion();
        try {

            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                aut.setId(rs.getInt("cl.id_cliente"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error cc");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    public void registrarAuto(Autos aut) {
        String consulta = "insert into vehiculos(id_cliente,placa,modelo,color,tipo) values (?,?,?,?,?)";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, aut.getId());
            cs.setString(2, aut.getCod());
            cs.setString(3, aut.getDescripcion());
            cs.setString(4, aut.getColor());
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

    public boolean modificarAuto(Autos aut) {
        con = cn.EstablecerConexion();
        String consulta = "UPDATE vehiculos SET vehiculos.id_cliente = ?,vehiculos.placa = ?,vehiculos.modelo = ?,vehiculos.color = ?"
                + " WHERE vehiculos.id_vehiculos = ? and vehiculos.tipo='NE'";
        try {
            cs = con.prepareCall(consulta);
            cs.setInt(1, aut.getId());
            cs.setString(2, aut.getCod());
            cs.setString(3, aut.getDescripcion());
            cs.setString(4, aut.getColor());
            cs.setInt(5, aut.getId2());
            cs.execute();

            cs.execute();

            return true;
        } catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null, "DNI Desconocido" + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error2-1" + e.toString());
            }
        }
    }

    public void eliminarAuto(Autos aut) {
        String consulta = "DELETE from vehiculos WHERE vehiculos.id_vehiculos=?;";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, aut.getId());
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
    
    
    
    public void eliminarAutoUpdate(Autos aut) {
        String consulta = "UPDATE vehiculos SET vehiculos.tipo='E'"
                + " WHERE vehiculos.id_vehiculos = ? and vehiculos.tipo='NE'";

        con = cn.EstablecerConexion();
        try {

            cs = con.prepareCall(consulta);

            cs.setInt(1, aut.getId());
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

    public void mostrarTablaAuto(Autos aut, JTable paramTable) {

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

        modelo.addColumn("ID Vehículo");
        modelo.addColumn("Placa");
        modelo.addColumn("Modelo");
        modelo.addColumn("Color");
        modelo.addColumn("DNI Cliente");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Hora de Registro");
        modelo.addColumn("Fecha de Registro");
        

        paramTable.setModel(modelo);
        String sql = "select ve.id_vehiculos ,ve.placa, ve.modelo,ve.color, cl.dni,concat_ws(' ', cl.nombre,cl.apellido),ve.hora,"
                + "ve.fecha "
                + "from clientes cl "
                + "inner join vehiculos ve "
                + "on cl.id_cliente=ve.id_cliente "
                + "where cl.tipo='NE' and ve.tipo='NE'";

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                aut.setId(rs.getInt(1));
                aut.setCod(rs.getString(2));
                aut.setDescripcion(rs.getString(3));
                aut.setColor(rs.getString(4));
                aut.setDni_cliente(rs.getInt(5));
                aut.setNombre(rs.getString(6));
                aut.setHora(rs.getString(7));
                aut.setFecha(rs.getString(8));

                modelo.addRow(aut.Registro());
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

    public boolean buscarAuto(Autos aut, JTable paramTable, JComboBox combo) {

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

        if (combo2.equalsIgnoreCase("DNI Cliente")) {
            consulta = "select ve.id_vehiculos ,ve.placa, ve.modelo,ve.color, cl.dni,concat_ws(' ', cl.nombre,cl.apellido), ve.hora,ve.fecha "
                    + "from clientes cl "
                    + "inner join vehiculos ve "
                    + "on cl.id_cliente=ve.id_cliente "
                    + "where cl.tipo='NE' and ve.tipo='NE' and cl.dni like '%" + aut.getDni_cliente()+ "%'";
        } else if (combo2.equalsIgnoreCase("ID Vehículo")) {
            consulta = "select ve.id_vehiculos ,ve.placa, ve.modelo,ve.color, cl.dni,concat_ws(' ', cl.nombre,cl.apellido), ve.hora,ve.fecha "
                    + "from clientes cl "
                    + "inner join vehiculos ve "
                    + "on cl.id_cliente=ve.id_cliente "
                    + "where cl.tipo='NE' and ve.tipo='NE' and ve.id_vehiculos =" + aut.getId();
        } else if (combo2.equalsIgnoreCase("Placa")) {
            consulta = "select ve.id_vehiculos ,ve.placa, ve.modelo,ve.color, cl.dni,concat_ws(' ', cl.nombre,cl.apellido), ve.hora,ve.fecha "
                    + "from clientes cl "
                    + "inner join vehiculos ve "
                    + "on cl.id_cliente=ve.id_cliente "
                    + "where cl.tipo='NE' and ve.tipo='NE' and ve.placa like '%" + aut.getCod()+ "%'";
        } else if (combo2.equalsIgnoreCase("Modelo")) {
            consulta = "select ve.id_vehiculos ,ve.placa, ve.modelo,ve.color, cl.dni,concat_ws(' ', cl.nombre,cl.apellido), ve.hora,ve.fecha "
                    + "from clientes cl "
                    + "inner join vehiculos ve "
                    + "on cl.id_cliente=ve.id_cliente "
                    + "where cl.tipo='NE' and ve.tipo='NE' and ve.modelo like '%" + aut.getDescripcion()+ "%'";
        }

        modelo.addColumn("ID Vehículo");
        modelo.addColumn("Placa");
        modelo.addColumn("Modelo");
        modelo.addColumn("Color");
        modelo.addColumn("DNI Cliente");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Hora de Registro");
        modelo.addColumn("Fecha de Registro");
        

        paramTable.setModel(modelo);

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);

            while (rs.next()) {
                aut.setId(rs.getInt(1));
                aut.setCod(rs.getString(2));
                aut.setDescripcion(rs.getString(3));
                aut.setColor(rs.getString(4));
                aut.setDni_cliente(rs.getInt(5));
                aut.setNombre(rs.getString(6));
                aut.setHora(rs.getString(7));
                aut.setFecha(rs.getString(8));

                modelo.addRow(aut.Registro());
            }

            paramTable.setModel(modelo);
            return true;

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Elegir una opción correcta"+e.toString());
            return false;
        }
    }
    
    public List obtenerListaPlaca(Autos aut) {
        //con = cn.EstablecerConexion();
        List<Autos> ListaStVeh = new ArrayList();
        //String consulta = "select vehiculos.placa from vehiculos where vehiculos.tipo='NE' ";
        String consulta = "select vh.id_vehiculos,vh.placa,cl.dni,vh.modelo "
                + "from clientes cl "
                + "inner join vehiculos vh "
                + "on cl.id_cliente=vh.id_cliente "
                + "where vh.tipo='NE' ";
        try {
            con = cn.EstablecerConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Autos aut2 = new Autos();
                aut2.setId(rs.getInt("id_vehiculos"));
                aut2.setDni_cliente(rs.getInt("dni"));
                aut2.setCod(rs.getString("placa"));
                aut2.setDescripcion(rs.getString("modelo"));
                ListaStVeh.add(aut2);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error obtener placa vehiculos" + e.toString());

        }
        return ListaStVeh;
    }
    
    public void verificarPlaca(Autos aut){
        List<Autos> ListaStVeh = this.obtenerListaPlaca(aut);
        for (int i=0;i<ListaStVeh.size();i++){
            if(ListaStVeh.get(i).getCod().equalsIgnoreCase(aut.getCod())){
                aut.setVer("Incorrecto");
                
            }
        }
    }
    
    public void verificarIDAut(Autos aut){
        List<Autos> ListaStVeh = this.obtenerListaPlaca(aut);
        for (int i=0;i<ListaStVeh.size();i++){
            if(aut.getId() == ListaStVeh.get(i).getId()){
                aut.setVer("Incorrecto");
                
            }
        }
    }
    
    public void verificarDNICli(Autos aut){
        List<Autos> ListaStVeh = this.obtenerListaPlaca(aut);
        for (int i=0;i<ListaStVeh.size();i++){
            if(aut.getDni_cliente()== ListaStVeh.get(i).getDni_cliente()){
                aut.setVer("Incorrecto");
                
            }
        }
    }
    
    public void verificarModelo(Autos aut){
        List<Autos> ListaStVeh = this.obtenerListaPlaca(aut);
        for (int i=0;i<ListaStVeh.size();i++){
            if(ListaStVeh.get(i).getDescripcion().equalsIgnoreCase(aut.getDescripcion())){
                aut.setVer("Incorrecto");
                
            }
        }
    }
}
