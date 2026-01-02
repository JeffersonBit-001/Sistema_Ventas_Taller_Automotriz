/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaAuto;
import Modelo.Autos;
import Modelo.Cliente;
import Vista.InterVehiculoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author JEFFERSON
 */
public class ControladorAuto implements ActionListener, MouseListener {

    InterVehiculoCliente interVeh;
    Autos aut;
    ConsultaAuto cscl;

    public ControladorAuto(InterVehiculoCliente interVeh, Autos aut, ConsultaAuto cscl) {
        this.interVeh = interVeh;
        this.aut = aut;
        this.cscl = cscl;
        this.interVeh.btnRegistrarVehiculo.addActionListener(this);
        this.interVeh.btnModificarVehiculo.addActionListener(this);
        this.interVeh.btnLimpiarVehiculo.addActionListener(this);
        this.interVeh.btnEliminarVehiculo.addActionListener(this);
        this.interVeh.btnBuscarVehiculo.addActionListener(this);
        this.interVeh.btnRestaurarVehiculo.addActionListener(this);
        this.interVeh.tablaVehiculoCli.addMouseListener(this);
        this.interVeh.txtVehiCli.setEnabled(false);
        this.interVeh.txtHorClieVe.setEnabled(false);
        this.interVeh.txtFecClieVe.setEnabled(false);
        this.interVeh.jLabel7.setEnabled(false);
        this.interVeh.jLabel7.setVisible(false);
        this.interVeh.jLabel5.setEnabled(false);
        this.interVeh.jLabel5.setVisible(false);
        this.interVeh.txtHorClieVe.setVisible(false);
        this.interVeh.txtFecClieVe.setVisible(false);
        this.interVeh.btnModificarVehiculo.setEnabled(false);
        this.interVeh.btnEliminarVehiculo.setEnabled(false);

        cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
        this.ordenarTablaVehiculos();
    }

    private void ordenarTablaVehiculos() {
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(0).setPreferredWidth(30);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(0).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(1).setPreferredWidth(100);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(1).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(2).setPreferredWidth(100);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(2).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(3).setPreferredWidth(60);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(3).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(4).setPreferredWidth(80);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(4).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(5).setPreferredWidth(150);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(5).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(6).setPreferredWidth(70);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(6).setResizable(false);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(7).setPreferredWidth(70);
        interVeh.tablaVehiculoCli.getColumnModel().getColumn(7).setResizable(false);
    }

    private void limpiarTabla() {
        interVeh.txtVehiCli.setText("");
        interVeh.txtdniClieVehiculo.setText("");
        interVeh.txtPlacaClieVehiculo.setText("");
        interVeh.txtModeloClieVehiculo.setText("");
        interVeh.txtColorrClieVehiculo.setText("");
        interVeh.txtFecClieVe.setText("");
        interVeh.txtHorClieVe.setText("");
        this.interVeh.btnModificarVehiculo.setEnabled(false);
        this.interVeh.btnEliminarVehiculo.setEnabled(false);

    }

    public boolean verificarBusqueda(Autos aut) {
        try {
            aut.setId(Integer.parseInt(interVeh.txtBuscarNombreParaVehiculo.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verificarDatosAutos(Autos aut) {
        try {
            aut.setCod(interVeh.txtPlacaClieVehiculo.getText());
            aut.setDni_cliente(Integer.parseInt(interVeh.txtdniClieVehiculo.getText()));
            aut.setDescripcion(interVeh.txtModeloClieVehiculo.getText());
            aut.setColor(interVeh.txtColorrClieVehiculo.getText());
            //aut.setId(Integer.parseInt(interVeh.txtdniClieVehiculo.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == interVeh.btnRegistrarVehiculo) {

            //----------------------------------Registrar------------------------------------------------------------------------
            if (this.verificarDatosAutos(aut)) {
                if (interVeh.txtdniClieVehiculo.getText().equals("")
                        || interVeh.txtPlacaClieVehiculo.getText().equals("")
                        || interVeh.txtModeloClieVehiculo.getText().equals("")
                        || interVeh.txtColorrClieVehiculo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                    if (Integer.parseInt(interVeh.txtdniClieVehiculo.getText()) > 9999999) {
                        //aut.setCod(interVeh.txtPlacaClieVehiculo.getText());
                        aut.setCod(interVeh.txtPlacaClieVehiculo.getText());
                        aut.setVer("Correcto");
                        cscl.obtenerListaPlaca(aut);
                        cscl.verificarPlaca(aut);
                        if (aut.getVer().equals("Incorrecto")) {
                            JOptionPane.showMessageDialog(null, "Placa Existente - Volver a registrar");

                        } else if (aut.getVer().equals("Correcto")) {
                            aut.setDni_cliente(Integer.parseInt(interVeh.txtdniClieVehiculo.getText()));
                            aut.setCod(interVeh.txtPlacaClieVehiculo.getText());
                            aut.setDescripcion(interVeh.txtModeloClieVehiculo.getText());
                            aut.setColor(interVeh.txtColorrClieVehiculo.getText());
                            cscl.conseguirIDAuto(aut);
                            cscl.registrarAuto(aut);
                            cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
                            this.ordenarTablaVehiculos();
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Cliente registrado");
                        } else {
                            JOptionPane.showMessageDialog(null, "DNI Desconocido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar DNI");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar registros");
            }

        }

        //------------------------------------Modificar--------------------------------------------------------------------
        if (e.getSource() == interVeh.btnModificarVehiculo) {

            if (this.verificarDatosAutos(aut)) {
                if (interVeh.txtdniClieVehiculo.getText().equals("") || interVeh.txtPlacaClieVehiculo.getText().equals("")
                        || interVeh.txtModeloClieVehiculo.getText().equals("")
                        || interVeh.txtColorrClieVehiculo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                    if (Integer.parseInt(interVeh.txtdniClieVehiculo.getText()) > 9999999) {
                        aut.setCod(interVeh.txtPlacaClieVehiculo.getText());
                        aut.setVer("Correcto");
                        cscl.obtenerListaPlaca(aut);
                        cscl.verificarPlaca(aut);
                        int fila2 = interVeh.tablaVehiculoCli.getSelectedRow();
                        String vari;
                        vari = interVeh.tablaVehiculoCli.getValueAt(fila2, 1).toString();
                        System.out.println(vari);
                        if (aut.getVer().equals("Incorrecto") && !interVeh.txtPlacaClieVehiculo.getText().equals(vari)) {
                            JOptionPane.showMessageDialog(null, "Placa Existente - Volver a ingresar");

                        } else if ((aut.getVer().equals("Correcto"))
                                || (aut.getVer().equals("Incorrecto") && interVeh.txtPlacaClieVehiculo.getText().equals(vari))) {
                            aut.setDni_cliente(Integer.parseInt(interVeh.txtdniClieVehiculo.getText()));
                            aut.setId2(Integer.parseInt(interVeh.txtVehiCli.getText()));
                            aut.setCod(interVeh.txtPlacaClieVehiculo.getText());
                            aut.setDescripcion(interVeh.txtModeloClieVehiculo.getText());
                            aut.setColor(interVeh.txtColorrClieVehiculo.getText());
                            cscl.conseguirIDAuto(aut);
                            if (cscl.modificarAuto(aut)) {
                                cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
                                this.ordenarTablaVehiculos();

                                if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("ID Vehículo")
                                        || interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("DNI Cliente")) {
                                    if (this.verificarBusqueda(aut)) {
                                        aut.setId(Integer.parseInt(interVeh.txtBuscarNombreParaVehiculo.getText()));
                                        if (cscl.buscarAuto(aut, interVeh.tablaVehiculoCli, interVeh.cbxSeleClienxBuscar)) {
                                            this.ordenarTablaVehiculos();
                                            this.limpiarTabla();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Verificar datos");
                                    }
                                } else if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("Placa")
                                        || interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("Modelo")) {
                                    aut.setNombre(interVeh.txtBuscarNombreParaVehiculo.getText());
                                    if (cscl.buscarAuto(aut, interVeh.tablaVehiculoCli, interVeh.cbxSeleClienxBuscar)) {
                                        this.ordenarTablaVehiculos();
                                        this.limpiarTabla();
                                    }
                                }
                                this.limpiarTabla();
                                JOptionPane.showMessageDialog(null, "Cliente Modificado");

                            } else {
                                JOptionPane.showMessageDialog(null, "DNI Desconocido");
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar DNI");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar registros");
            }
        }

        //------------------------------------Limpiar--------------------------------------------------------------------
        if (e.getSource() == interVeh.btnLimpiarVehiculo) {
            this.limpiarTabla();
            interVeh.txtdniClieVehiculo.requestFocus();
        }
        //------------------------------------Eliminar--------------------------------------------------------------------   
        if (e.getSource() == interVeh.btnEliminarVehiculo) {

            int fila = interVeh.tablaVehiculoCli.getSelectedRow();
            if (fila >= 0) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    aut.setId(Integer.parseInt(interVeh.txtVehiCli.getText()));
                    //cscl.eliminarAuto(aut);
                    cscl.eliminarAutoUpdate(aut);
                    cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);

                    this.ordenarTablaVehiculos();
                    this.limpiarTabla();

                    JOptionPane.showMessageDialog(null, "Eliminación con Éxito");
                } else {
                    this.limpiarTabla();
                    interVeh.tablaVehiculoCli.clearSelection();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }

        }
        //------------------------------------Buscar--------------------------------------------------------------------
        if (e.getSource() == interVeh.btnBuscarVehiculo) {
            if (interVeh.txtBuscarNombreParaVehiculo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completa el campo");
            } else {
                if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("ID Vehículo")
                        || interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("DNI Cliente")) {
                    if (this.verificarBusqueda(aut)) {

                        if (Integer.parseInt(interVeh.txtBuscarNombreParaVehiculo.getText()) > 0) {
                            aut.setVer("Correcto");

                            if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("ID Vehículo")) {
                                aut.setId(Integer.parseInt(interVeh.txtBuscarNombreParaVehiculo.getText()));
                                cscl.obtenerListaPlaca(aut);
                                cscl.verificarIDAut(aut);
                                if (aut.getVer().equals("Correcto")) {
                                    cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
                                    this.ordenarTablaVehiculos();
                                    this.limpiarTabla();
                                    JOptionPane.showMessageDialog(null, "ID no encontrado");
                                } else {
                                    if (cscl.buscarAuto(aut, interVeh.tablaVehiculoCli, interVeh.cbxSeleClienxBuscar)) {
                                        this.ordenarTablaVehiculos();
                                        this.limpiarTabla();
                                    }
                                }
                            } else if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("DNI Cliente")) {
                                aut.setDni_cliente(Integer.parseInt(interVeh.txtBuscarNombreParaVehiculo.getText()));
                                cscl.obtenerListaPlaca(aut);
                                cscl.verificarDNICli(aut);
                                if (aut.getVer().equals("Correcto")) {
                                    cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
                                    this.ordenarTablaVehiculos();
                                    this.limpiarTabla();
                                    JOptionPane.showMessageDialog(null, "DNI no encontrado");
                                } else {
                                    if (cscl.buscarAuto(aut, interVeh.tablaVehiculoCli, interVeh.cbxSeleClienxBuscar)) {
                                        this.ordenarTablaVehiculos();
                                        this.limpiarTabla();
                                    }
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Verificar los datos");
                        }

                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar datos");
                    }
                } else if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("Placa")) {
                    aut.setCod(interVeh.txtBuscarNombreParaVehiculo.getText());
                    aut.setVer("Correcto");
                    cscl.obtenerListaPlaca(aut);
                    cscl.verificarPlaca(aut);

                    if (aut.getVer().equals("Correcto")) {
                        cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
                        this.ordenarTablaVehiculos();
                        this.limpiarTabla();
                        JOptionPane.showMessageDialog(null, "Placa no encontrada");
                    } else {
                        if (cscl.buscarAuto(aut, interVeh.tablaVehiculoCli, interVeh.cbxSeleClienxBuscar)) {
                            this.ordenarTablaVehiculos();
                            this.limpiarTabla();
                        }
                    }

                } else if (interVeh.cbxSeleClienxBuscar.getSelectedItem().toString().equals("Modelo")) {
                    aut.setDescripcion(interVeh.txtBuscarNombreParaVehiculo.getText());
                    aut.setVer("Correcto");
                    cscl.obtenerListaPlaca(aut);
                    cscl.verificarModelo(aut);
                    if (aut.getVer().equals("Correcto")) {
                        cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
                        this.ordenarTablaVehiculos();
                        this.limpiarTabla();
                        JOptionPane.showMessageDialog(null, "Modelo no encontrado");
                    } else {
                        if (cscl.buscarAuto(aut, interVeh.tablaVehiculoCli, interVeh.cbxSeleClienxBuscar)) {
                            this.ordenarTablaVehiculos();
                            this.limpiarTabla();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una opción correcta");
                }

            }

        }
        //------------------------------------LIimpiar tabla de busqueda --------------------------------------------------------------------

        if (e.getSource()
                == interVeh.btnRestaurarVehiculo) {
            interVeh.txtBuscarNombreParaVehiculo.setText("");
            interVeh.cbxSeleClienxBuscar.setSelectedIndex(0);
            cscl.mostrarTablaAuto(aut, interVeh.tablaVehiculoCli);
            this.ordenarTablaVehiculos();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == interVeh.tablaVehiculoCli) {
            int fila = interVeh.tablaVehiculoCli.getSelectedRow();

            if (fila >= 0) {
                interVeh.txtVehiCli.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 0).toString());
                interVeh.txtPlacaClieVehiculo.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 1).toString());
                interVeh.txtModeloClieVehiculo.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 2).toString());
                interVeh.txtColorrClieVehiculo.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 3).toString());
                interVeh.txtdniClieVehiculo.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 4).toString());
                interVeh.txtHorClieVe.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 6).toString());
                interVeh.txtFecClieVe.setText(interVeh.tablaVehiculoCli.getValueAt(fila, 7).toString());
                this.interVeh.btnModificarVehiculo.setEnabled(true);
                this.interVeh.btnEliminarVehiculo.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "Error fila");
                //this.ordenarTablaUs();
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
