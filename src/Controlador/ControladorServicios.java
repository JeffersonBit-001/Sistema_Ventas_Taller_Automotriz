/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaServicios;
import Modelo.Servicios;
import Vista.InterServicioNuevo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author JEFFERSON
 */
public class ControladorServicios implements ActionListener, MouseListener {

    InterServicioNuevo interSer;
    Servicios ser;
    ConsultaServicios csser;

    public ControladorServicios(InterServicioNuevo interSer, Servicios ser, ConsultaServicios csser) {
        this.interSer = interSer;
        this.ser = ser;
        this.csser = csser;
        this.interSer.btnIngreServi.addActionListener(this);
        this.interSer.btnModifiServ.addActionListener(this);
        this.interSer.btnEliminServ.addActionListener(this);
        this.interSer.btnLimpServ.addActionListener(this);
        this.interSer.btnBuscarServicio.addActionListener(this);
        this.interSer.btnRestaurarTablaServicio.addActionListener(this);
        this.interSer.jtbServicio.addMouseListener(this);
        this.interSer.txtIdServ.setEnabled(false);
        this.interSer.txtHoraServicio.setEnabled(false);
        this.interSer.txtFecSer.setEnabled(false);
        this.interSer.txtHoraServicio.setVisible(false);
        this.interSer.txtFecSer.setVisible(false);
        this.interSer.jlbHoraServicio.setVisible(false);
        this.interSer.txtFecSer.setVisible(false);
        this.interSer.txtDesSer.setVisible(false);
        this.interSer.txtDesSer.setEnabled(false);
        this.interSer.jlabelFechaServici.setVisible(false);
        this.interSer.btnModifiServ.setEnabled(false);
        this.interSer.btnEliminServ.setEnabled(false);
        csser.mostrarTablaServicio(ser, interSer.jtbServicio);
        this.ordenarTablaServicios();

    }

    private void ordenarTablaServicios() {
        interSer.jtbServicio.getColumnModel().getColumn(0).setPreferredWidth(30);
        interSer.jtbServicio.getColumnModel().getColumn(0).setResizable(false);
        interSer.jtbServicio.getColumnModel().getColumn(1).setPreferredWidth(100);
        interSer.jtbServicio.getColumnModel().getColumn(1).setResizable(false);
        interSer.jtbServicio.getColumnModel().getColumn(2).setPreferredWidth(160);
        interSer.jtbServicio.getColumnModel().getColumn(2).setResizable(false);
        interSer.jtbServicio.getColumnModel().getColumn(3).setPreferredWidth(50);
        interSer.jtbServicio.getColumnModel().getColumn(3).setResizable(false);
        interSer.jtbServicio.getColumnModel().getColumn(4).setPreferredWidth(90);
        interSer.jtbServicio.getColumnModel().getColumn(4).setResizable(false);
        interSer.jtbServicio.getColumnModel().getColumn(5).setPreferredWidth(90);
        interSer.jtbServicio.getColumnModel().getColumn(5).setResizable(false);
    }

    private void limpiarTabla() {
        interSer.txtIdServ.setText("");
        interSer.txtNomSer.setText("");
        interSer.txtDesSer.setText("");
        interSer.txtArDescripcionServicio.setText("");
        interSer.txtPreSer.setText("");
        interSer.txtFecSer.setText("");
        this.interSer.btnModifiServ.setEnabled(false);
        this.interSer.btnEliminServ.setEnabled(false);

    }

    public boolean verificarServicio(Servicios ser) {
        try {
            ser.setDescripcion(interSer.txtNomSer.getText());
            ser.setNombre(interSer.txtArDescripcionServicio.getText());
            ser.setPrecio(Double.parseDouble(interSer.txtPreSer.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verficarBusqueda(Servicios ser) {
        try {
            ser.setId(Integer.parseInt(interSer.txtBuscaSerNom.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //-----------------------------------------INGRESAR---------------------------------------------------------------
        if (e.getSource() == interSer.btnIngreServi) {
            if (this.verificarServicio(ser)) {
                if (interSer.txtNomSer.getText().equals("") || interSer.txtArDescripcionServicio.getText().equals("")
                        || interSer.txtPreSer.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {
                    ser.setNombre(interSer.txtNomSer.getText());
                    ser.setVer("Correcto");
                    csser.obtenerLista(ser);
                    csser.verificarNombre(ser);
                    if (ser.getVer().equals("Incorrecto")) {
                        JOptionPane.showMessageDialog(null, "Servicio Existente - Vuelve a ingresar");
                    } else if (ser.getVer().equals("Correcto")) {
                        if (Double.parseDouble(interSer.txtPreSer.getText()) > 0) {
                            ser.setNombre(interSer.txtNomSer.getText());
                            //ser.setDescripcion(interSer.txtDesSer.getText());
                            ser.setDescripcion(interSer.txtArDescripcionServicio.getText());
                            ser.setPrecio(Double.parseDouble(interSer.txtPreSer.getText()));

                            csser.registrarServicio(ser);
                            csser.mostrarTablaServicio(ser, interSer.jtbServicio);
                            this.ordenarTablaServicios();
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Registro correcto");
                        } else {
                            JOptionPane.showMessageDialog(null, "Precio inválido");
                        }
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar datos");
            }

        }

        //-----------------------------------------MODIFICAR---------------------------------------------------------------
        if (e.getSource() == interSer.btnModifiServ) {
            if (this.verificarServicio(ser)) {
                if (interSer.txtNomSer.getText().equals("") || interSer.txtArDescripcionServicio.getText().equals("")
                        || interSer.txtPreSer.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {

                    ser.setNombre(interSer.txtNomSer.getText());
                    ser.setVer("Correcto");
                    csser.obtenerLista(ser);
                    csser.verificarNombre(ser);

                    int fila2 = interSer.jtbServicio.getSelectedRow();
                    String vari;
                    vari = interSer.jtbServicio.getValueAt(fila2, 1).toString();
                    if (ser.getVer().equals("Incorrecto") && !interSer.txtNomSer.getText().equals(vari)) {
                        JOptionPane.showMessageDialog(null, "DNI Existente - Vuelve a ingresar");
                    } else if ((ser.getVer().equals("Correcto"))
                            || (ser.getVer().equals("Incorrecto") && interSer.txtNomSer.getText().equals(vari))) {
                        if (Double.parseDouble(interSer.txtPreSer.getText()) > 0) {
                            ser.setId(Integer.parseInt(interSer.txtIdServ.getText()));
                            ser.setNombre(interSer.txtNomSer.getText());
                            ser.setDescripcion(interSer.txtArDescripcionServicio.getText());
                            //ser.setDescripcion(interSer.txtDesSer.getText());
                            ser.setPrecio(Double.parseDouble(interSer.txtPreSer.getText()));

                            csser.modificarServicio(ser);
                            csser.mostrarTablaServicio(ser, interSer.jtbServicio);
                            this.ordenarTablaServicios();
                            if (interSer.cbxBuscSer.getSelectedItem().toString().equals("ID")) {
                                if (this.verficarBusqueda(ser)) {
                                    ser.setId(Integer.parseInt(interSer.txtBuscaSerNom.getText()));
                                    if (csser.buscarServicio(ser, interSer.jtbServicio, interSer.cbxBuscSer)) {
                                        this.ordenarTablaServicios();
                                        this.limpiarTabla();
                                    }
                                }
                            } else if (interSer.cbxBuscSer.getSelectedItem().toString().equals("Nombre")
                                    || interSer.cbxBuscSer.getSelectedItem().toString().equals("Descripción")) {
                                ser.setNombre(interSer.txtBuscaSerNom.getText());
                                if (csser.buscarServicio(ser, interSer.jtbServicio, interSer.cbxBuscSer)) {
                                    this.ordenarTablaServicios();
                                    this.limpiarTabla();
                                }
                            }
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Modificación Exitosa");
                        } else {
                            JOptionPane.showMessageDialog(null, "Precio inválido");
                        }
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Valor inválido");
            }

        }

        //-----------------------------------------ELIMINAR---------------------------------------------------------------
        if (e.getSource() == interSer.btnEliminServ) {
            int fila = interSer.jtbServicio.getSelectedRow();
            if (fila >= 0) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    ser.setId(Integer.parseInt(interSer.txtIdServ.getText()));
                    //csser.eliminarServicio(ser);
                    csser.eliminarServicioUpdate(ser);
                    csser.mostrarTablaServicio(ser, interSer.jtbServicio);

                    //interSer.jtbServicio.setCellSelectionEnabled(false);
                    this.ordenarTablaServicios();
                    this.limpiarTabla();
                    JOptionPane.showMessageDialog(null, "Eliminación exitosa");
                } else {
                    this.limpiarTabla();
                    interSer.jtbServicio.clearSelection();
                }
            } else {

                //interSer.jtbServicio.setCellSelectionEnabled(false);
                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }

        }

        //-----------------------------------------LIMPIAR CASILLEROS---------------------------------------------------------------
        if (e.getSource() == interSer.btnLimpServ) {
            this.limpiarTabla();
            interSer.txtNomSer.requestFocus();

        }

        //-----------------------------------------BUSCAR---------------------------------------------------------------
        if (e.getSource() == interSer.btnBuscarServicio) {
            if (interSer.txtBuscaSerNom.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completar el campo");

            } else {
                if (interSer.cbxBuscSer.getSelectedItem().toString().equals("ID")) {
                    if (this.verficarBusqueda(ser)) {

                        if (Integer.parseInt(interSer.txtBuscaSerNom.getText()) > 0) {
                            ser.setVer("Correcto");
                            ser.setId(Integer.parseInt(interSer.txtBuscaSerNom.getText()));

                            csser.obtenerLista(ser);
                            csser.verificarID(ser);

                            if (ser.getVer().equals("Correcto")) {
                                csser.mostrarTablaServicio(ser, interSer.jtbServicio);
                                this.ordenarTablaServicios();
                                this.limpiarTabla();
                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                            } else {
                                if (csser.buscarServicio(ser, interSer.jtbServicio, interSer.cbxBuscSer)) {
                                    this.ordenarTablaServicios();
                                    this.limpiarTabla();
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Verificar datos");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar los datos");
                    }
                } else if (interSer.cbxBuscSer.getSelectedItem().toString().equals("Nombre")
                        || interSer.cbxBuscSer.getSelectedItem().toString().equals("Descripción")) {
                    ser.setNombre(interSer.txtBuscaSerNom.getText());
                    if (csser.buscarServicio(ser, interSer.jtbServicio, interSer.cbxBuscSer)) {
                        this.ordenarTablaServicios();
                        this.limpiarTabla();
                    }

                    if (interSer.jtbServicio.getRowCount() == 0) {
                        csser.mostrarTablaServicio(ser, interSer.jtbServicio);
                        this.ordenarTablaServicios();
                        JOptionPane.showMessageDialog(null, "Datos No Encontrados");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Elige una opción correcta");
                }
            }

        }

        //-----------------------------------------RESTAURAR TABLA---------------------------------------------------------------
        if (e.getSource() == interSer.btnRestaurarTablaServicio) {
            interSer.cbxBuscSer.setSelectedIndex(0);
            interSer.txtBuscaSerNom.setText("");
            csser.mostrarTablaServicio(ser, interSer.jtbServicio);
            this.ordenarTablaServicios();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == interSer.jtbServicio) {

            int fila = interSer.jtbServicio.getSelectedRow();

            if (fila >= 0) {
                interSer.txtIdServ.setText(interSer.jtbServicio.getValueAt(fila, 0).toString());
                interSer.txtNomSer.setText(interSer.jtbServicio.getValueAt(fila, 1).toString());
                interSer.txtArDescripcionServicio.setText(interSer.jtbServicio.getValueAt(fila, 2).toString());
                //interSer.txtDesSer.setText(interSer.jtbServicio.getValueAt(fila, 2).toString());
                interSer.txtPreSer.setText(interSer.jtbServicio.getValueAt(fila, 3).toString());
                interSer.txtHoraServicio.setText(interSer.jtbServicio.getValueAt(fila, 4).toString());
                interSer.txtFecSer.setText(interSer.jtbServicio.getValueAt(fila, 5).toString());
                this.interSer.btnModifiServ.setEnabled(true);
                this.interSer.btnEliminServ.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                this.ordenarTablaServicios();
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
