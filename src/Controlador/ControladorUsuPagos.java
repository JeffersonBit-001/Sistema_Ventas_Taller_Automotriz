/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.CConexion;
import Conexion.ConsultaUsuarioPagos;
import Modelo.Usuario;
import Vista.FormAdmSistema;
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
public class ControladorUsuPagos implements ActionListener, MouseListener {

    FormAdmSistema vista;
    Usuario us;
    ConsultaUsuarioPagos uspag;

    public ControladorUsuPagos(Usuario us, ConsultaUsuarioPagos uspag, FormAdmSistema vista) {
        this.us = us;
        this.uspag = uspag;
        this.vista = vista;
        //uspag.cargarUsuarioBox(us, vista.cbxBuscarUsuaSueldo);
        this.vista.btnSueldoUsuario.addActionListener(this);
        this.vista.btnAgregarSueldoUsu.addActionListener(this);
        this.vista.btnLimpiarSueldoUs.addActionListener(this);
        this.vista.jtblUsuarioSueldos.addMouseListener(this);
        this.vista.btnModificarSueldoUs.addActionListener(this);
        this.vista.btnEliminarSueldoUs.addActionListener(this);
        this.vista.btnBuscarDetallePagoUsuario.addActionListener(this);
        this.vista.btnRestauraDetallePagoUs.addActionListener(this);
        this.vista.jtxSueldoMensUsur.setEnabled(false);
        this.vista.jtxBonificacionUsuario.setEnabled(false);
        this.vista.txtSueldoTotal.setEnabled(false);
        this.vista.jtxIDsueldoFacturaUsuar.setEnabled(false);
        uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
        vista.jtxIDsueldoFacturaUsuar.setVisible(false);
        vista.jlabIDusuario.setVisible(false);
        this.vista.btnModificarSueldoUs.setEnabled(false);
        this.vista.btnEliminarSueldoUs.setEnabled(false);
        this.ordenarTabla();
    }

    public void ordenarTabla() {
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(0).setPreferredWidth(30);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(0).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(1).setPreferredWidth(160);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(1).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(2).setPreferredWidth(90);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(2).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(3).setPreferredWidth(90);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(3).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(4).setPreferredWidth(90);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(4).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(5).setPreferredWidth(90);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(5).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(6).setPreferredWidth(90);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(6).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(7).setPreferredWidth(90);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(7).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(8).setPreferredWidth(60);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(8).setResizable(false);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(9).setPreferredWidth(80);
        vista.jtblUsuarioSueldos.getColumnModel().getColumn(9).setResizable(false);
    }

    public void limpiarTabla() {
        vista.jtxSueldoMensUsur.setText("");
        vista.jtxCantAutosVenUsu.setText("");
        vista.jtxPrecioAdicionalUsu.setText("");
        vista.jtxBonificacionUsuario.setText("");
        vista.txtSueldoTotal.setText("");
        vista.btnGrupo.clearSelection();
        vista.jtxIDsueldoFacturaUsuar.setText("");

        vista.jtxtDniAgregarSueldo.setText("");
        this.vista.btnModificarSueldoUs.setEnabled(false);
        this.vista.btnEliminarSueldoUs.setEnabled(false);

    }

    public boolean verifcarDniUsuario(Usuario us, JTextField bus) {
        try {
            us.setDni(Integer.parseInt(bus.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifcarUsuario(Usuario us) {
        try {
            us.setDni(Integer.parseInt(vista.jtxtDniAgregarSueldo.getText()));
            us.setCant_aut_ven(Integer.parseInt(vista.jtxCantAutosVenUsu.getText()));
            us.setPrecio_adicion(Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-------------------------------------------------------------------------------------------------------------------------------

        if (e.getSource() == vista.btnAgregarSueldoUsu) {
            if (this.verifcarUsuario(us)) {
                if (vista.jtxCantAutosVenUsu.getText().equals("") || vista.jtxPrecioAdicionalUsu.getText().equals("")
                        || vista.jtxtDniAgregarSueldo.getText().equals("") || vista.btnGrupo.isSelected(null)) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {
                    if (Integer.parseInt(vista.jtxtDniAgregarSueldo.getText()) > 9999999
                            && Integer.parseInt(vista.jtxCantAutosVenUsu.getText()) >= 0
                            && Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()) >= 0) {
                        us.setDni(Integer.parseInt(vista.jtxtDniAgregarSueldo.getText()));
                        us.setVer("Correcto");
                        uspag.obtenerUsuariosPagos(us);
                        uspag.verficarUsuPagos(us);
                        if (us.getVer().equals("Incorrecto")) {
                            JOptionPane.showMessageDialog(null, "Este usuario ya recibió su sueldo este mes");
                        } else if (us.getVer().equals("Correcto")) {

                            if (vista.rdbAdmUsuarioSueldo.isSelected()) {
                                us.setRol("Administrador");
                                //us.setSueldo(us.pago()); //Double.parseDouble(vista.jtxSueldoMensUsur.getText())
                                us.setCant_aut_ven(Integer.parseInt(vista.jtxCantAutosVenUsu.getText()));
                                us.setPrecio_adicion(Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()));
                                //us.setBenef_desc(us.descuento_Beneficio());
                                //us.setPag_final(us.pagoFinal());

                            }

                            if (vista.rdbEmpUsuarioSueldo.isSelected()) {
                                us.setRol("Empleado");
                                //us.setSueldo(us.pago()); //Double.parseDouble(vista.jtxSueldoMensUsur.getText())
                                us.setCant_aut_ven(Integer.parseInt(vista.jtxCantAutosVenUsu.getText()));
                                us.setPrecio_adicion(Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()));
                                //us.setBenef_desc(us.descuento_Beneficio());
                                //us.setPag_final(us.pagoFinal());
                            }
                            uspag.consultarDatosUsuarioAPagos(us);
                            uspag.insertarDatosUsuariosAPagos(us);
                            uspag.consultarDatosDetalleAPago(us);
                            uspag.insertarDatosDetalleAPago(us);
                            uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                            this.ordenarTabla();
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Registro Exitoso");

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar los datos");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar los datos");
            }

        }

        //-------------------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == vista.btnLimpiarSueldoUs) {
            this.limpiarTabla();
            vista.jtxtDniAgregarSueldo.requestFocus();

        }
        //-------------------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == vista.btnModificarSueldoUs) {
            if (this.verifcarUsuario(us)) {
                if (vista.jtxCantAutosVenUsu.getText().equals("") || vista.jtxPrecioAdicionalUsu.getText().equals("")
                        || vista.jtxtDniAgregarSueldo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {
                    if (Integer.parseInt(vista.jtxtDniAgregarSueldo.getText()) > 9999999
                            && Integer.parseInt(vista.jtxCantAutosVenUsu.getText()) >= 0
                            && Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()) >= 0) {
                        us.setDni(Integer.parseInt(vista.jtxtDniAgregarSueldo.getText()));
                        us.setVer("Correcto");
                        uspag.obtenerUsuariosPagos(us);
                        uspag.verficarUsuPagos(us);
                        int fila2 = vista.jtblUsuarioSueldos.getSelectedRow();
                        String vari;
                        vari = vista.jtblUsuarioSueldos.getValueAt(fila2, 2).toString();
                        if (us.getVer().equals("Incorrecto") && !vista.jtxtDniAgregarSueldo.getText().equals(vari)) {
                            JOptionPane.showMessageDialog(null, "Este usuario ya recibió su pago este mes");
                        } else if ((us.getVer().equals("Correcto"))
                                || (us.getVer().equals("Incorrecto") && vista.jtxtDniAgregarSueldo.getText().equals(vari))) {
                            if (vista.rdbAdmUsuarioSueldo.isSelected()) {
                                us.setRol("Administrador");
                                us.setCant_aut_ven(Integer.parseInt(vista.jtxCantAutosVenUsu.getText()));
                                us.setPrecio_adicion(Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()));

                            }
                            if (vista.rdbEmpUsuarioSueldo.isSelected()) {
                                us.setRol("Empleado");
                                //us.setSueldo(us.pago()); //Double.parseDouble(vista.jtxSueldoMensUsur.getText())
                                us.setCant_aut_ven(Integer.parseInt(vista.jtxCantAutosVenUsu.getText()));
                                us.setPrecio_adicion(Double.parseDouble(vista.jtxPrecioAdicionalUsu.getText()));
                            }
                            //modifica el combobox primero de la tabla pagos
                            us.setId_fac_pag(Integer.parseInt(vista.jtxIDsueldoFacturaUsuar.getText()));
                            uspag.consultarDatosUsuarioAPagos(us);
                            uspag.modificarDatosDetalleUsuarioPago(us);

                            //modifica la tabla detalle_pago
                            //uspag.consultarDatosDetalleAPago(us, vista.cbxBuscarUsuaSueldo);
                            uspag.modificarDatosDetallePago(us);
                            uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                            this.ordenarTabla();

                            if (vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("ID Pago")
                                    || vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("DNI")) {
                                if (this.verifcarDniUsuario(us, vista.txtBuscarSelecionPagoUs)) {
                                    us.setDni(Integer.parseInt(vista.txtBuscarSelecionPagoUs.getText()));
                                    if (uspag.buscarUsuariosDetallePago(us, vista.jtblUsuarioSueldos, vista.cbxBuscarDetallePagoUs)) {
                                        this.ordenarTabla();
                                        this.limpiarTabla();
                                    }
                                }

                            } else if (vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("Nombre")
                                    || vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("Fecha")) {
                                us.setNombre(vista.txtBuscarSelecionPagoUs.getText());
                                if (uspag.buscarUsuariosDetallePago(us, vista.jtblUsuarioSueldos, vista.cbxBuscarDetallePagoUs)) {
                                    this.ordenarTabla();
                                    this.limpiarTabla();
                                }
                            }

                            /*if (!vista.cbxBuscarDetallePagoUs.getSelectedItem().equals("--Selecciona--")) {
                            us.setNombre(vista.txtBuscarSelecionPagoUs.getText());
                            if (uspag.buscarUsuariosDetallePago(us, vista.jtblUsuarioSueldos, vista.cbxBuscarDetallePagoUs)) {
                                this.ordenarTabla();
                            } else {
                                uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                                this.ordenarTabla();
                            }
                        }*/
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar los datos");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar los datos");
            }

        }
        //---------------------------------------------ELIMINAR----------------------------------------------------------------------------------

        if (e.getSource() == vista.btnEliminarSueldoUs) {

            int fila = vista.jtblUsuarioSueldos.getSelectedRow();
            if (fila >= 0) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    us.setId_fac_pag(Integer.parseInt(vista.jtxIDsueldoFacturaUsuar.getText()));

                    uspag.eliminarUsuariosDetallePagoTablaDetalleUpdate(us);
                    uspag.eliminarUsuariosDetallePagoTablaPagosUpdate(us);
                    //uspag.eliminarUsuariosDetallePagoTablaDetalle(us);
                    //spag.eliminarUsuariosDetallePagoTablaPagos(us);
                    uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                    //vista.jtblUsuarioSueldos.setCellSelectionEnabled(false);
                    this.ordenarTabla();
                    this.limpiarTabla();

                    JOptionPane.showMessageDialog(null, "Eliminación exitosa");
                } else {
                    this.limpiarTabla();
                    vista.jtblUsuarioSueldos.clearSelection();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }

        }

        if (e.getSource() == vista.btnBuscarDetallePagoUsuario) {

            if (vista.txtBuscarSelecionPagoUs.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completa los campos");
            } else {
                if (vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("ID Pago")
                        || vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("DNI")) {
                    if (this.verifcarDniUsuario(us, vista.txtBuscarSelecionPagoUs)) {
                        if (Integer.parseInt(vista.txtBuscarSelecionPagoUs.getText()) > 0) {
                            us.setVer("Correcto");

                            if (vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("ID Pago")) {
                                us.setId_fac_pag(Integer.parseInt(vista.txtBuscarSelecionPagoUs.getText()));
                                uspag.obtenerUsuariosPagos(us);
                                uspag.verficarUsuIDPPag(us);
                                if (us.getVer().equals("Correcto")) {
                                    uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                                    this.ordenarTabla();
                                    this.limpiarTabla();
                                    JOptionPane.showMessageDialog(null, "ID no encontrado");
                                } else {
                                    if (uspag.buscarUsuariosDetallePago(us, vista.jtblUsuarioSueldos, vista.cbxBuscarDetallePagoUs)) {
                                        this.ordenarTabla();
                                        this.limpiarTabla();
                                    }
                                }
                            } else if (vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("DNI")) {
                                us.setDni(Integer.parseInt(vista.txtBuscarSelecionPagoUs.getText()));
                                uspag.obtenerUsuariosPagos(us);
                                uspag.verficarUsuDNI(us);
                                if (us.getVer().equals("Correcto")) {
                                    uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                                    this.ordenarTabla();
                                    this.limpiarTabla();
                                    JOptionPane.showMessageDialog(null, "DNI no encontrado");
                                } else {
                                    if (uspag.buscarUsuariosDetallePago(us, vista.jtblUsuarioSueldos, vista.cbxBuscarDetallePagoUs)) {
                                        this.ordenarTabla();
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

                } else if (vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("Nombre")
                        || vista.cbxBuscarDetallePagoUs.getSelectedItem().toString().equals("Fecha")) {
                    us.setNombre(vista.txtBuscarSelecionPagoUs.getText());
                    if (uspag.buscarUsuariosDetallePago(us, vista.jtblUsuarioSueldos, vista.cbxBuscarDetallePagoUs)) {
                        this.ordenarTabla();
                        this.limpiarTabla();
                    }

                    if (vista.jtblUsuarioSueldos.getRowCount() == 0) {
                        uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
                        this.ordenarTabla();
                        JOptionPane.showMessageDialog(null, "Datos No Encontrados");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una opción correcta");
                }
            }

        }

        if (e.getSource() == vista.btnRestauraDetallePagoUs) {
            vista.txtBuscarSelecionPagoUs.setText("");
            vista.cbxBuscarDetallePagoUs.setSelectedIndex(0);
            uspag.mostrarTablaPagos(us, vista.jtblUsuarioSueldos);
            this.ordenarTabla();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.jtblUsuarioSueldos) {

            int fila2 = vista.jtblUsuarioSueldos.getSelectedRow();

            if (fila2 >= 0) {
                //guardar id factura

                vista.jtxIDsueldoFacturaUsuar.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 0).toString());
                vista.jtxtDniAgregarSueldo.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 2).toString());
                vista.jtxSueldoMensUsur.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 3).toString());
                vista.jtxCantAutosVenUsu.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 4).toString());
                vista.jtxPrecioAdicionalUsu.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 5).toString());
                vista.jtxBonificacionUsuario.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 6).toString());
                vista.txtSueldoTotal.setText(vista.jtblUsuarioSueldos.getValueAt(fila2, 7).toString());
                us.setDni(Integer.parseInt(vista.jtxtDniAgregarSueldo.getText()));
                uspag.selecionUsuariosDetallePago(us);
                //----------------------------------------- guardar id de tabla
                //us.setId_fac_pag(Integer.parseInt(vista.jtblUsuarioSueldos.getValueAt(fila2,0).toString()));
                //System.out.println(us.getId_fac_pag());
                if (us.getRol().equals("Administrador")) {
                    vista.rdbAdmUsuarioSueldo.setSelected(true);
                    vista.rdbEmpUsuarioSueldo.setSelected(false);
                }
                if (us.getRol().equals("Empleado")) {
                    vista.rdbEmpUsuarioSueldo.setSelected(true);
                    vista.rdbAdmUsuarioSueldo.setSelected(false);
                }

                this.vista.btnModificarSueldoUs.setEnabled(true);
                this.vista.btnEliminarSueldoUs.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");

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
