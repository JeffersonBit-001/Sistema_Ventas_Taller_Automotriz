/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.FormAdmSistema;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
//import Procesos.ProcesosForma01;
import Conexion.ConsultaUsuario;

/**
 *
 * @author JEFFERSON
 */
public class ControladorFormUsuarios implements ActionListener, MouseListener {

    FormAdmSistema vista;
    Usuario us;
    ConsultaUsuario csus;
    //DefaultTableModel modelo;

    //constructor
    public ControladorFormUsuarios(Usuario us, ConsultaUsuario csus, FormAdmSistema vista) {
        this.vista = vista;
        this.us = us;
        this.csus = csus;
        this.vista.btnPanUsuarios.addActionListener(this);
        this.vista.btnAgUs.addActionListener(this);
        this.vista.btbModUs.addActionListener(this);
        this.vista.btnElUs.addActionListener(this);
        this.vista.btnResUs.addActionListener(this);
        this.vista.jtbUsrs.addMouseListener(this);
        this.vista.btnBusUs.addActionListener(this);
        this.vista.btnRestaurarTablaUsuario.addActionListener(this);
        //vista.jTabbedPaneAdministrador.setVisible(false);
        this.vista.jTabbedPaneAdministrador.setEnabledAt(0, false);
        this.vista.jTabbedPaneAdministrador.setEnabledAt(1, false);
        //this.vista.jTabbedPaneAdministrador.setEnabledAt(2, false);
        //this.vista.jTabbedPaneAdministrador.setVisible(false);
        this.vista.txtIdUs.setEnabled(false);
        this.vista.txtFeUsu.setEnabled(false);
        this.vista.btbModUs.setEnabled(false);
        this.vista.btnElUs.setEnabled(false);
        this.vista.txtFeUsu.setVisible(false);
        this.vista.jLabel14.setVisible(false);
        //this.vista.txtFeUsu.setVisible(false);
        this.csus.mostrarTablaUsuario(us, vista.jtbUsrs);
        this.ordenarTablaUs();

    }

    public void ordenarTablaUs() {
        vista.jtbUsrs.getColumnModel().getColumn(0).setPreferredWidth(30);
        vista.jtbUsrs.getColumnModel().getColumn(0).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(1).setPreferredWidth(100);
        vista.jtbUsrs.getColumnModel().getColumn(1).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(2).setPreferredWidth(100);
        vista.jtbUsrs.getColumnModel().getColumn(2).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(3).setPreferredWidth(90);
        vista.jtbUsrs.getColumnModel().getColumn(3).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(4).setPreferredWidth(160);
        vista.jtbUsrs.getColumnModel().getColumn(4).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(5).setPreferredWidth(90);
        vista.jtbUsrs.getColumnModel().getColumn(5).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(6).setPreferredWidth(100);
        vista.jtbUsrs.getColumnModel().getColumn(6).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(7).setPreferredWidth(150);
        vista.jtbUsrs.getColumnModel().getColumn(7).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(8).setPreferredWidth(90);
        vista.jtbUsrs.getColumnModel().getColumn(8).setResizable(false);
        vista.jtbUsrs.getColumnModel().getColumn(9).setPreferredWidth(75);
        vista.jtbUsrs.getColumnModel().getColumn(9).setResizable(false);
    }

    public void limpiarTabla() {
        vista.txtIdUs.setText("");
        vista.txtNomUs.setText("");
        vista.txtApUs.setText("");
        vista.jtxdniUsuarios.setText("");
        vista.txtCorUs.setText("");
        vista.txtCelUs.setText("");
        vista.txtUsUs.setText("");
        vista.txtPasUs.setText("");
        vista.cbxRol.setSelectedIndex(0);
        vista.txtFeUsu.setText("");
        vista.jtbUsrs.clearSelection();
        this.vista.btbModUs.setEnabled(false);
        this.vista.btnElUs.setEnabled(false);

    }

    private boolean verificarUsuarios(Usuario us) {
        try {
            us.setNombre(vista.txtNomUs.getText());
            us.setApellido(vista.txtApUs.getText());
            us.setCorreo(vista.txtCorUs.getText());
            us.setUsuario(vista.txtUsUs.getText());
            us.setPass(String.valueOf(vista.txtPasUs.getPassword()));
            us.setDni(Integer.parseInt(vista.jtxdniUsuarios.getText()));
            us.setTelefono(Integer.parseInt(vista.txtCelUs.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean verificarBusqueda(Usuario us) {
        try {
            us.setDni(Integer.parseInt(vista.txtBUs.getText()));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-----------------------------------------------------------------------------------------------------Botón REGISTRAR

        //ver pestaña 1:
        if (e.getSource() == vista.btnAgUs) {
            if (this.verificarUsuarios(us)) {
                if (vista.txtNomUs.getText().equals("") || vista.txtApUs.getText().equals("")
                        || vista.txtCorUs.getText().equals("") || vista.txtCelUs.getText().equals("")
                        || vista.txtUsUs.getText().equals("") || String.valueOf(vista.txtPasUs.getPassword()).equals("")
                        || vista.cbxRol.getSelectedItem().toString().equals("--Selecciona--")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {
                    if (Integer.parseInt(vista.jtxdniUsuarios.getText()) > 9999999
                            && Integer.parseInt(vista.txtCelUs.getText()) > 900000000) {
                        us.setDni(Integer.parseInt(vista.jtxdniUsuarios.getText()));
                        us.setVer("Correcto");
                        us.setVer2("Correcto");
                        csus.obtenerLista(us);
                        csus.verificarDNI(us);
                        csus.verificarUsuario(us);
                        if (us.getVer().equals("Incorrecto") || us.getVer2().equals("Incorrecto")) {
                            if(us.getVer().equals("Incorrecto")){
                                JOptionPane.showMessageDialog(null, "DNI Existente - Vuelve a ingresar");
                            }
                            if(us.getVer2().equals("Incorrecto")){
                                JOptionPane.showMessageDialog(null, "USUARIO Existente - Vuelve a ingresar");
                            }
                        } 
                        else if (us.getVer().equals("Correcto") && us.getVer2().equals("Correcto")) {

                            us.setNombre(vista.txtNomUs.getText());
                            us.setApellido(vista.txtApUs.getText());
                            us.setDni(Integer.parseInt(vista.jtxdniUsuarios.getText()));
                            us.setCorreo(vista.txtCorUs.getText());
                            us.setTelefono(Integer.parseInt(vista.txtCelUs.getText()));
                            us.setUsuario(vista.txtUsUs.getText());
                            us.setPass(String.valueOf(vista.txtPasUs.getPassword()));
                            us.setRol(vista.cbxRol.getSelectedItem().toString());

                            csus.registrarUsuario(us);
                            csus.mostrarTablaUsuario(us, vista.jtbUsrs);
                            this.ordenarTablaUs();
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Registro correcto");

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar los datos");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar los datos");
            }

        }
        //--------------------------------------------------------------------------------------------------------------Botón REGISTRAR

        //--------------------------------------------------------------------------------------------------------------Botón LIMPIAR
        if (e.getSource() == vista.btnResUs) {
            this.limpiarTabla();
            vista.txtNomUs.requestFocus();
        }

        //--------------------------------------------------------------------------------------------------------------Botón LIMPIAR
        //--------------------------------------------------------------------------------------------------------------Botón MODIFICAR
        if (e.getSource() == vista.btbModUs) {
            if (this.verificarUsuarios(us)) {
                if (vista.txtNomUs.getText().equals("") || vista.txtApUs.getText().equals("")
                        || vista.txtCorUs.getText().equals("") || vista.txtCelUs.getText().equals("")
                        || vista.txtUsUs.getText().equals("") || String.valueOf(vista.txtPasUs.getPassword()).equals("")
                        || vista.cbxRol.getSelectedItem().toString().equals("--Selecciona--")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {
                    if (Integer.parseInt(vista.jtxdniUsuarios.getText()) > 9999999
                            && Integer.parseInt(vista.txtCelUs.getText()) > 900000000) {
                        us.setDni(Integer.parseInt(vista.jtxdniUsuarios.getText()));
                        us.setVer("Correcto");
                        us.setVer2("Correcto");
                        csus.obtenerLista(us);
                        csus.verificarDNI(us);
                        csus.verificarUsuario(us);
                        int fila2 = vista.jtbUsrs.getSelectedRow();
                        String vari;
                        String vari2;
                        vari = vista.jtbUsrs.getValueAt(fila2, 3).toString();
                        vari2 = vista.jtbUsrs.getValueAt(fila2, 6).toString();
                        if ((us.getVer().equals("Incorrecto") && !vista.jtxdniUsuarios.getText().equals(vari))
                                || (us.getVer2().equals("Incorrecto") && !vista.txtUsUs.getText().equals(vari2))) {
                            if(us.getVer().equals("Incorrecto") && !vista.jtxdniUsuarios.getText().equals(vari)){
                                JOptionPane.showMessageDialog(null, "DNI Existente - Vuelve a ingresar");
                            }
                            
                            if(us.getVer2().equals("Incorrecto") && !vista.txtUsUs.getText().equals(vari2)){
                                JOptionPane.showMessageDialog(null, "USUARIO Existente - Vuelve a ingresar");
                            }
                            
                            
                        } else if ((us.getVer().equals("Correcto"))
                                || ((us.getVer().equals("Incorrecto") && vista.jtxdniUsuarios.getText().equals(vari))
                                || (us.getVer2().equals("Incorrecto") && vista.txtUsUs.getText().equals(vari2)
                                ))
                                
                                
                                ) {
                            us.setId(Integer.parseInt(vista.txtIdUs.getText()));
                            us.setNombre(vista.txtNomUs.getText());
                            us.setApellido(vista.txtApUs.getText());
                            us.setDni(Integer.parseInt(vista.jtxdniUsuarios.getText()));
                            us.setCorreo(vista.txtCorUs.getText());
                            us.setTelefono(Integer.parseInt(vista.txtCelUs.getText()));
                            us.setUsuario(vista.txtUsUs.getText());
                            us.setPass(String.valueOf(vista.txtPasUs.getPassword()));
                            us.setRol(vista.cbxRol.getSelectedItem().toString());

                            csus.modificarUsuario(us);
                            csus.mostrarTablaUsuario(us, vista.jtbUsrs);
                            this.ordenarTablaUs();

                            if (vista.cbxBusUs.getSelectedItem().toString().equals("ID")
                                    || vista.cbxBusUs.getSelectedItem().toString().equals("DNI")) {
                                if (this.verificarBusqueda(us)) {
                                    us.setDni(Integer.parseInt(vista.txtBUs.getText()));
                                    if (csus.buscarUsuario(us, vista.jtbUsrs, vista.cbxBusUs)) {
                                        this.ordenarTablaUs();
                                        this.limpiarTabla();
                                    }
                                }
                            } else if (vista.cbxBusUs.getSelectedItem().toString().equals("Rol")) {
                                us.setNombre(vista.txtBUs.getText());
                                if (csus.buscarUsuario(us, vista.jtbUsrs, vista.cbxBusUs)) {
                                    this.ordenarTablaUs();
                                    this.limpiarTabla();
                                }
                            }
                            this.limpiarTabla();

                            JOptionPane.showMessageDialog(null, "Modificación exitosa");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verficar los datos");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verficar los datos");
            }

        }

        //--------------------------------------------------------------------------------------------------------------Botón MODIFICAR
        //--------------------------------------------------------------------------------------------------------------Botón ELIMINAR
        if (e.getSource() == vista.btnElUs) {
            int fila = vista.jtbUsrs.getSelectedRow();
            if (fila >= 0) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    us.setId(Integer.parseInt(vista.txtIdUs.getText()));

                    //csus.eliminar(us);
                    csus.eliminarUpdate(us);
                    csus.mostrarTablaUsuario(us, vista.jtbUsrs);

                    //vista.jtbUsrs.setCellSelectionEnabled(true);
                    this.ordenarTablaUs();
                    this.limpiarTabla();
                    JOptionPane.showMessageDialog(null, "Eliminación exitosa");
                } else {
                    limpiarTabla();
                    vista.jtbUsrs.clearSelection();
                }
            } else {

                //vista.jtbUsrs.setCellSelectionEnabled(true);
                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }
        }

        //--------------------------------------------------------------------------------------------------------------Botón ELIMINAR
        //--------------------------------------------------------------------------------------------------------------Botón Buscar
        if (e.getSource() == vista.btnBusUs) {

            if (vista.txtBUs.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completa el campo de búsqueda");
            } else {
                if (vista.cbxBusUs.getSelectedItem().toString().equals("ID")
                        || vista.cbxBusUs.getSelectedItem().toString().equals("DNI")) {
                    if (this.verificarBusqueda(us)) {
                        if (Integer.parseInt(vista.txtBUs.getText()) > 0) {
                            us.setVer("Correcto");

                            if (vista.cbxBusUs.getSelectedItem().toString().equals("ID")) {
                                us.setId_fac_pag(Integer.parseInt(vista.txtBUs.getText()));
                                csus.obtenerLista(us);
                                csus.verificarID(us);
                                if (us.getVer().equals("Correcto")) {
                                    csus.mostrarTablaUsuario(us, vista.jtbUsrs);
                                    this.ordenarTablaUs();
                                    this.limpiarTabla();
                                    JOptionPane.showMessageDialog(null, "ID no encontrado");
                                } else {
                                    if (csus.buscarUsuario(us, vista.jtbUsrs, vista.cbxBusUs)) {
                                        this.ordenarTablaUs();
                                        this.limpiarTabla();
                                    }
                                }
                            } else if (vista.cbxBusUs.getSelectedItem().toString().equals("DNI")) {
                                us.setDni(Integer.parseInt(vista.txtBUs.getText()));
                                csus.obtenerLista(us);
                                csus.verificarDNI(us);
                                if (us.getVer().equals("Correcto")) {
                                    csus.mostrarTablaUsuario(us, vista.jtbUsrs);
                                    this.ordenarTablaUs();
                                    this.limpiarTabla();
                                    JOptionPane.showMessageDialog(null, "DNI no encontrado");
                                } else {
                                    if (csus.buscarUsuario(us, vista.jtbUsrs, vista.cbxBusUs)) {
                                        this.ordenarTablaUs();
                                        this.limpiarTabla();
                                    }
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Verificar los datos");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar los datos");
                    }
                } else if (vista.cbxBusUs.getSelectedItem().toString().equals("Rol")) {
                    us.setNombre(vista.txtBUs.getText());
                    if (us.getNombre().equals("Administrador") || us.getNombre().equals("Empleado")) {
                        if (csus.buscarUsuario(us, vista.jtbUsrs, vista.cbxBusUs)) {
                            this.ordenarTablaUs();
                            this.limpiarTabla();
                        }
                    } else {
                        csus.mostrarTablaUsuario(us, vista.jtbUsrs);
                        this.ordenarTablaUs();
                        this.limpiarTabla();
                        JOptionPane.showMessageDialog(null, "Rol inválido");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una opción correcta");
                }

                //csus.buscarUsuario(us, vista.jtbUsrs, vista.cbxBusUs);
            }

        }

        if (e.getSource()
                == vista.btnRestaurarTablaUsuario) {
            vista.txtBUs.setText("");
            vista.cbxBusUs.setSelectedIndex(0);
            csus.mostrarTablaUsuario(us, vista.jtbUsrs);
            this.ordenarTablaUs();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.jtbUsrs) {

            int fila = vista.jtbUsrs.getSelectedRow();

            if (fila >= 0) {
                vista.txtIdUs.setText(vista.jtbUsrs.getValueAt(fila, 0).toString());
                vista.txtNomUs.setText(vista.jtbUsrs.getValueAt(fila, 1).toString());
                vista.txtApUs.setText(vista.jtbUsrs.getValueAt(fila, 2).toString());
                vista.jtxdniUsuarios.setText(vista.jtbUsrs.getValueAt(fila, 3).toString());
                vista.txtCorUs.setText(vista.jtbUsrs.getValueAt(fila, 4).toString());
                vista.txtCelUs.setText(vista.jtbUsrs.getValueAt(fila, 5).toString());
                vista.txtUsUs.setText(vista.jtbUsrs.getValueAt(fila, 6).toString());
                vista.txtPasUs.setText(vista.jtbUsrs.getValueAt(fila, 7).toString());
                vista.cbxRol.setSelectedItem(vista.jtbUsrs.getValueAt(fila, 8).toString());
                vista.txtFeUsu.setText(vista.jtbUsrs.getValueAt(fila, 9).toString());
                this.vista.btbModUs.setEnabled(true);
                this.vista.btnElUs.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                this.ordenarTablaUs();
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
