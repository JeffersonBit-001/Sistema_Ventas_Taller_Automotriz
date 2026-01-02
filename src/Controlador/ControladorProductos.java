/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaProductos;
import Modelo.Productos;
import Vista.InterProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author JEFFERSON
 */
public class ControladorProductos implements ActionListener, MouseListener {

    InterProductos interProd;
    Productos prod;
    ConsultaProductos csprod;

    public ControladorProductos(InterProductos interProd, Productos prod, ConsultaProductos csprod) {
        this.interProd = interProd;
        this.prod = prod;
        this.csprod = csprod;
        this.interProd.btnCalcularProducto.addActionListener(this);
        this.interProd.btnInsertarProductos.addActionListener(this);
        this.interProd.btnModificarProducto.addActionListener(this);
        this.interProd.btnEliminarProducto.addActionListener(this);
        this.interProd.btnLimpiarProducto.addActionListener(this);
        this.interProd.btnBuscarProducto.addActionListener(this);
        this.interProd.btnActualizarProductoTabla.addActionListener(this);
        this.interProd.tablaProductos.addMouseListener(this);
        this.interProd.txtIDProducto.setEnabled(false);
        this.interProd.jtxfieldHoraProducto.setVisible(false);
        this.interProd.txtHoraProducto.setEnabled(false);
        this.interProd.txtHoraProducto.setVisible(false);
        this.interProd.jtxFechaProducto.setVisible(false);
        this.interProd.txtFechaProducto.setEnabled(false);
        this.interProd.txtFechaProducto.setVisible(false);
        this.interProd.txtDescripcionProducto.setVisible(false);
        this.interProd.txtDescripcionProducto.setEnabled(false);
        this.interProd.txtPrecioTotalProducto.setEnabled(false);
        this.interProd.btnModificarProducto.setEnabled(false);
        this.interProd.btnEliminarProducto.setEnabled(false);
        csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
        this.ordenarTablaServicios();
    }

    private void limpiarTabla() {
        interProd.txtIDProducto.setText("");
        interProd.txtNombreProducto.setText("");
        interProd.txtDescripcionProducto.setText("");
        interProd.txtCantidadProducto.setText("");
        interProd.txtPrecioUnidadProducto.setText("");
        interProd.txtPrecioTotalProducto.setText("");
        interProd.txtHoraProducto.setText("");
        interProd.txtFechaProducto.setText("");
        interProd.txtArDescripcionProducto.setText("");
        this.interProd.btnModificarProducto.setEnabled(false);
        this.interProd.btnEliminarProducto.setEnabled(false);
    }

    private void ordenarTablaServicios() {
        interProd.tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(30);
        interProd.tablaProductos.getColumnModel().getColumn(0).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
        interProd.tablaProductos.getColumnModel().getColumn(1).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(140);
        interProd.tablaProductos.getColumnModel().getColumn(2).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
        interProd.tablaProductos.getColumnModel().getColumn(3).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(50);
        interProd.tablaProductos.getColumnModel().getColumn(4).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
        interProd.tablaProductos.getColumnModel().getColumn(5).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(90);
        interProd.tablaProductos.getColumnModel().getColumn(6).setResizable(false);
        interProd.tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(90);
        interProd.tablaProductos.getColumnModel().getColumn(7).setResizable(false);
    }

    public boolean verificarProductos(Productos prod) {
        try {
            prod.setNombre(interProd.txtNombreProducto.getText());
            prod.setDescripcion(interProd.txtArDescripcionProducto.getText());
            prod.setCantidad(Integer.parseInt(interProd.txtCantidadProducto.getText()));
            prod.setPrecio_unidad(Double.parseDouble(interProd.txtPrecioUnidadProducto.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verificarBusqueda(Productos prod) {

        try {
            prod.setId(Integer.parseInt(interProd.txtBuscarProducto.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-----------------------------------------CALCULAR PRECIO---------------------------------------------------------------
        if (e.getSource() == interProd.btnCalcularProducto) {

            if (this.verificarProductos(prod)) {
                prod.setCantidad(Integer.parseInt(interProd.txtCantidadProducto.getText()));
                prod.setPrecio_unidad(Double.parseDouble(interProd.txtPrecioUnidadProducto.getText()));
                interProd.txtPrecioTotalProducto.setText(String.valueOf(prod.calcularPrecioTotal()));
            } else {
                JOptionPane.showMessageDialog(null, "Verificar datos");
            }
        }
        //-----------------------------------------Insertar---------------------------------------------------------------
        if (e.getSource() == interProd.btnInsertarProductos) {
            if (this.verificarProductos(prod)) {

                if (interProd.txtNombreProducto.getText().equals("") || interProd.txtArDescripcionProducto.getText().equals("")
                        || interProd.txtCantidadProducto.getText().equals("") || interProd.txtPrecioUnidadProducto.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {

                    prod.setNombre(interProd.txtNombreProducto.getText());
                    prod.setVer("Correcto");
                    csprod.obtenerLista(prod);
                    csprod.verificarNombre(prod);
                    if (prod.getVer().equals("Incorrecto")) {
                        JOptionPane.showMessageDialog(null, "Producto Existente - Vuelve a ingresar");
                    } else if (prod.getVer().equals("Correcto")) {
                        if (Double.parseDouble(interProd.txtPrecioUnidadProducto.getText()) > 0
                                && Integer.parseInt(interProd.txtCantidadProducto.getText()) >= 0) {
                            prod.setNombre(interProd.txtNombreProducto.getText());
                            prod.setDescripcion(interProd.txtArDescripcionProducto.getText());
                            //prod.setDescripcion(interProd.txtDescripcionProducto.getText());
                            prod.setCantidad(Integer.parseInt(interProd.txtCantidadProducto.getText()));
                            prod.setPrecio_unidad(Double.parseDouble(interProd.txtPrecioUnidadProducto.getText()));
                            csprod.registrarProducto(prod);
                            csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
                            this.ordenarTablaServicios();
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Registro correcto");
                        } else {
                            JOptionPane.showMessageDialog(null, "Precio Inválido");
                        }

                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar datos");
            }

        }

        //-----------------------------------------MODIFICAR---------------------------------------------------------------
        if (e.getSource() == interProd.btnModificarProducto) {

            if (this.verificarProductos(prod)) {
                if (interProd.txtNombreProducto.getText().equals("") || interProd.txtArDescripcionProducto.getText().equals("")
                        || interProd.txtCantidadProducto.getText().equals("") || interProd.txtPrecioUnidadProducto.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completa todo los campos");

                } else {

                    prod.setNombre(interProd.txtNombreProducto.getText());
                    prod.setVer("Correcto");
                    csprod.obtenerLista(prod);
                    csprod.verificarNombre(prod);

                    int fila2 = interProd.tablaProductos.getSelectedRow();
                    String vari;
                    vari = interProd.tablaProductos.getValueAt(fila2, 1).toString();
                    if (prod.getVer().equals("Incorrecto") && !interProd.txtNombreProducto.getText().equals(vari)) {
                        JOptionPane.showMessageDialog(null, "DNI Existente - Vuelve a ingresar");
                    } else if ((prod.getVer().equals("Correcto"))
                            || (prod.getVer().equals("Incorrecto") && interProd.txtNombreProducto.getText().equals(vari))) {
                        if (Double.parseDouble(interProd.txtPrecioUnidadProducto.getText()) > 0
                                && Integer.parseInt(interProd.txtCantidadProducto.getText()) >= 0) {
                            prod.setId(Integer.parseInt(interProd.txtIDProducto.getText()));
                            prod.setNombre(interProd.txtNombreProducto.getText());
                            prod.setDescripcion(interProd.txtArDescripcionProducto.getText());
                            //prod.setDescripcion(interProd.txtDescripcionProducto.getText());
                            prod.setCantidad(Integer.parseInt(interProd.txtCantidadProducto.getText()));
                            prod.setPrecio_unidad(Double.parseDouble(interProd.txtPrecioUnidadProducto.getText()));
                            csprod.modificarProducto(prod);
                            csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
                            this.ordenarTablaServicios();
                            //NO SE DESORDENE LA TABLA AL MODIFICAR UN REGISTRO(QUE SOLO SE MUESTRE ESE DATO)
                            if (interProd.cbxBuscarProducto.getSelectedItem().toString().equals("ID")) {
                                if (this.verificarBusqueda(prod)) {
                                    prod.setId(Integer.parseInt(interProd.txtBuscarProducto.getText()));
                                    if (csprod.buscarProducto(prod, interProd.tablaProductos, interProd.cbxBuscarProducto)) {
                                        this.ordenarTablaServicios();
                                        this.limpiarTabla();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Verificar datos");
                                }

                            } else if (interProd.cbxBuscarProducto.getSelectedItem().toString().equals("Nombre")
                                    || interProd.cbxBuscarProducto.getSelectedItem().toString().equals("Descripción")) {
                                prod.setNombre(interProd.txtBuscarProducto.getText());
                                if (csprod.buscarProducto(prod, interProd.tablaProductos, interProd.cbxBuscarProducto)) {
                                    this.ordenarTablaServicios();
                                    this.limpiarTabla();
                                }
                            }
                            this.limpiarTabla();
                            JOptionPane.showMessageDialog(null, "Modifación Existosa");
                        } else {
                            JOptionPane.showMessageDialog(null, "Precio Inválido");
                        }

                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Verificar datos");
            }

        }

        //-----------------------------------------ELIMINAR---------------------------------------------------------------
        if (e.getSource() == interProd.btnEliminarProducto) {

            int fila = interProd.tablaProductos.getSelectedRow();
            if (fila >= 0) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    prod.setId(Integer.parseInt(interProd.txtIDProducto.getText()));
                    //csprod.eliminarProducto(prod);
                    csprod.eliminarProductoUpdate(prod);
                    csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
                    this.ordenarTablaServicios();
                    this.limpiarTabla();
                    JOptionPane.showMessageDialog(null, "Eliminación exitosa");
                } else {
                    this.limpiarTabla();
                    interProd.tablaProductos.clearSelection();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }

        }

        //-----------------------------------------LIMPIAR CASILLEROS---------------------------------------------------------------
        if (e.getSource()
                == interProd.btnLimpiarProducto) {
            this.limpiarTabla();
            interProd.txtNombreProducto.requestFocus();
        }

        //-----------------------------------------BUSCAR---------------------------------------------------------------
        if (e.getSource()
                == interProd.btnBuscarProducto) {

            if (interProd.txtBuscarProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Completa el campo");
            } else {
                if (interProd.cbxBuscarProducto.getSelectedItem().toString().equals("ID")) {
                    if (this.verificarBusqueda(prod)) {
                        if (Integer.parseInt(interProd.txtBuscarProducto.getText()) > 0) {
                            prod.setVer("Correcto");
                            prod.setId(Integer.parseInt(interProd.txtBuscarProducto.getText()));

                            csprod.obtenerLista(prod);
                            csprod.verificarId(prod);

                            if (prod.getVer().equals("Correcto")) {
                                csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
                                this.ordenarTablaServicios();
                                this.limpiarTabla();
                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                            } else {
                                if (csprod.buscarProducto(prod, interProd.tablaProductos, interProd.cbxBuscarProducto)) {
                                    this.ordenarTablaServicios();
                                    this.limpiarTabla();
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Verificar datos");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar datos");
                    }

                } else if (interProd.cbxBuscarProducto.getSelectedItem().toString().equals("Nombre")
                        || interProd.cbxBuscarProducto.getSelectedItem().toString().equals("Descripción")) {
                    prod.setNombre(interProd.txtBuscarProducto.getText());
                    if (csprod.buscarProducto(prod, interProd.tablaProductos, interProd.cbxBuscarProducto)) {
                        this.ordenarTablaServicios();
                        this.limpiarTabla();
                    }
                    
                    if (interProd.tablaProductos.getRowCount() == 0) {
                        csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
                        this.ordenarTablaServicios();
                        JOptionPane.showMessageDialog(null, "Datos No Encontrados");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una opción correcta");
                }
            }

        }

        //-----------------------------------------RESTAURAR TABLA---------------------------------------------------------------
        if (e.getSource()
                == interProd.btnActualizarProductoTabla) {
            interProd.cbxBuscarProducto.setSelectedIndex(0);
            interProd.txtBuscarProducto.setText("");
            csprod.mostrarTablaProducto(prod, interProd.tablaProductos);
            this.ordenarTablaServicios();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == interProd.tablaProductos) {

            int fila = interProd.tablaProductos.getSelectedRow();

            if (fila >= 0) {
                interProd.txtIDProducto.setText(interProd.tablaProductos.getValueAt(fila, 0).toString());
                interProd.txtNombreProducto.setText(interProd.tablaProductos.getValueAt(fila, 1).toString());
                interProd.txtArDescripcionProducto.setText(interProd.tablaProductos.getValueAt(fila, 2).toString());
                //interProd.txtDescripcionProducto.setText(interProd.tablaProductos.getValueAt(fila, 2).toString());
                interProd.txtCantidadProducto.setText(interProd.tablaProductos.getValueAt(fila, 3).toString());
                interProd.txtPrecioUnidadProducto.setText(interProd.tablaProductos.getValueAt(fila, 4).toString());
                interProd.txtPrecioTotalProducto.setText(interProd.tablaProductos.getValueAt(fila, 5).toString());
                interProd.txtHoraProducto.setText(interProd.tablaProductos.getValueAt(fila, 6).toString());
                interProd.txtFechaProducto.setText(interProd.tablaProductos.getValueAt(fila, 7).toString());
                this.interProd.btnModificarProducto.setEnabled(true);
                this.interProd.btnEliminarProducto.setEnabled(true);

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
