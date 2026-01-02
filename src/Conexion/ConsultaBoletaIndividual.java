/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelo.Autos;
import Modelo.Cliente;
import Modelo.TallerAutomotriz;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JEFFERSON
 */
public class ConsultaBoletaIndividual extends CConexion {

    CConexion cn = new CConexion();

    public void datosEmpresa(Cliente cl) {
        con = cn.EstablecerConexion();

        String consulta = "select * from empresa";
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                cl.setCod(rs.getString("ruc"));
                cl.setCorreo(rs.getString("dueño"));
                cl.setDireccion(rs.getString("direccion"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DNI no registrado" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error nombre" + e.toString());
            }
        }

    }


    public void datosCliente(Cliente cl) {
        con = cn.EstablecerConexion();

        String consulta = "select cl.id_cliente,us.usuario,concat_ws(' ', us.nombre,us.apellido) as nombre_us,"
                + "concat_ws(' ', cl.nombre,cl.apellido) as nombre_al,cl.dni,cl.direccion,cl.celular,defac.cantidad_servicios,"
                + "defac.total_servicio,defac.desc_igv,"
                + "defac.pagocondesc,f.fecha "
                + "from factura2 f "
                + "inner join clientes cl "
                + "on f.id_cliente=cl.id_cliente "
                + "inner join usuarios us "
                + "on f.id_usuarios=us.id_usuarios "
                + "inner join detalle_factura2 defac "
                + "on f.id_factura=defac.id_factura "
                + "where defac.id_factura=" + cl.getId_fac_pag();
        try {
            st = con.prepareCall(consulta);
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                cl.setId_iden(rs.getInt("id_cliente"));
                //cl.setId_iden2(rs.getInt("id_usuarios"));
                cl.setUsu(rs.getString("usuario"));
                cl.setVer2(rs.getString("nombre_us"));
                cl.setNombre(rs.getString("nombre_al"));
                cl.setDni(rs.getInt("dni"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setTelefono(rs.getInt("celular"));
                cl.setCant_ser(rs.getInt("cantidad_servicios"));
                cl.setPago(rs.getDouble("total_servicio"));
                cl.setBenef_desc(rs.getDouble("desc_igv"));
                cl.setPag_final(rs.getInt("pagocondesc"));
                cl.setFecha(rs.getString("fecha"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DNI no registrado" + e.toString());

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error nombre" + e.toString());
            }
        }

    }
    
    
    public void mostrarTabla(Cliente cl, JTable paramTable) {

        con = cn.EstablecerConexion();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 5) {
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

        modelo.addColumn("Código");
        modelo.addColumn("Servicio");
        modelo.addColumn("Descripción");
        modelo.addColumn("Placa-Auto");
        modelo.addColumn("Precio");
        

        paramTable.setModel(modelo);
        String consulta = "select sr.id_servicio,sr.nombre,sr.descripcion,vh.placa,sr.precio"
                        + " "
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
                        + "where fc.tipo='C' and fc.tipo2='NE' and defac.tipo='C' and defac.tipo2='NE' and fc.id_factura=" + cl.getId_fac_pag()+
                " group by sr.nombre";

        try {
            st = con.createStatement();

            rs = st.executeQuery(consulta);
            DecimalFormat df = new DecimalFormat("SER00000");
            
            String[] datos = new String[5];

            while (rs.next()) {
                datos[0] = df.format(rs.getInt(1));
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
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
    

    public void imprimir(Cliente cl) {
        Document documento = new Document();
        try {

            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + cl.getNombre().trim() + ".pdf"));
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Img/BannerPDF.png");
            header.scaleToFit(400, 800);
            header.setAlignment(Chunk.ALIGN_CENTER);
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            DecimalFormat df = new DecimalFormat("FAC00000");
            parrafo.add("SASEL AUTOMOTRIZ E.I.R.L. \n "
                    + "Especialistas en las marcas premium BMW, MB, AUDI \n "
                    + "Servicio de Mecánica en general. Venta de repuestos\n "
                    + "Código de Factura: " + df.format(cl.getId_fac_pag())
                    + "\n \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tablaClientePrin = new PdfPTable(2);

            tablaClientePrin.setWidthPercentage(100);
            float[] tamEncabezado = new float[]{40f, 30f};
            tablaClientePrin.setWidths(tamEncabezado);
            tablaClientePrin.setHorizontalAlignment(Element.ALIGN_LEFT);

            this.datosEmpresa(cl);
            tablaClientePrin.addCell("<<<<Datos Generales>>>> \nRuc: " + cl.getCod() + "\nGerente:" + cl.getCorreo()
                    + "\nDirección: " + cl.getDireccion() + "\nTelf: (+51) 955 240 301 \n");

            this.datosCliente(cl);
            tablaClientePrin.addCell("<<<<Datos Cliente>>>> \nNombre: " + cl.getNombre() + "\nDni: " + cl.getDni()
                    + "\nDirección: " + cl.getDireccion() + "\nTelf: (+51) " + cl.getTelefono() + "\n");

            //PdfPTable tablaCliente2 = new PdfPTable(5);
            documento.add(tablaClientePrin);

            Paragraph parrafo3 = new Paragraph();
            parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo3.add("\n \n \nInformación de los Servicios \n \n");
            DecimalFormat df2 = new DecimalFormat("SER00000");
            documento.add(parrafo3);

            PdfPTable tablaCliente = new PdfPTable(5);

            tablaCliente.addCell("ID");
            tablaCliente.addCell("Servicio");
            tablaCliente.addCell("Descripción");
            tablaCliente.addCell("Placa-Auto");
            tablaCliente.addCell("Precio");

            try {
                con = cn.EstablecerConexion();

                ps = con.prepareStatement(
                        "select sr.id_servicio,sr.nombre,sr.descripcion,vh.placa,sr.precio"
                        + " "
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
                        + "where fc.tipo='C' and fc.tipo2='NE' and defac.tipo='C' and defac.tipo2='NE' and fc.id_factura=" + cl.getId_fac_pag()
                );
                rs = ps.executeQuery();

                if (rs.next()) {
                    do {

                        tablaCliente.addCell(df2.format(rs.getInt(1)));
                        tablaCliente.addCell(rs.getString(2));
                        tablaCliente.addCell(rs.getString(3));
                        tablaCliente.addCell(rs.getString(4));
                        tablaCliente.addCell("S/." + rs.getString(5));

                    } while (rs.next());
                    documento.add(tablaCliente);
                }

            } catch (Exception e) {
            }

            /*Paragraph parrafo2 = new Paragraph();
            parrafo2.setAlignment(Paragraph.ALIGN_CENTER);

            documento.add(parrafo2);*/
            Paragraph parrafo4 = new Paragraph();
            parrafo4.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo4.add("\n \nSubTotal: S/." + cl.pago() + "                     " + "Descuento: S/. " + cl.getBenef_desc() + "                     " + "Total S/." + cl.getPag_final() + " \n \n");

            documento.add(parrafo4);

            Paragraph parrafo5 = new Paragraph();
            parrafo5.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo5.add("\n \n________________________                                ___________________________"
                    + "\n Firma Cliente: " + cl.getNombre() + "                                   Firma Vendedor: " + cl.getVer2());

            documento.add(parrafo5);

            documento.close();
        } catch (DocumentException | IOException e) {

        }

    }

}
