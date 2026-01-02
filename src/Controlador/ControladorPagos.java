/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaBoletaIndividual;
import Conexion.ConsultaPagos;
import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Productos;
import Modelo.Servicios;
import Vista.FormEmpSistema;
import Vista.InterBoletaIndividual;
import Vista.InterPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author JEFFERSON
 */
public class ControladorPagos implements ActionListener, MouseListener {

    Cliente cl;
    Servicios ser;
    Productos prod;
    Autos aut;
    InterPagos interFac;
    ConsultaPagos csdtFac;
    FormEmpSistema emp;
    ConsultaBoletaIndividual csbl;

    int totalProd = 0;

    public ControladorPagos(Cliente cl, Servicios ser, Productos prod, Autos aut, InterPagos interFac, ConsultaPagos csdtFac, FormEmpSistema emp,
            ConsultaBoletaIndividual csbl) {
        this.cl = cl;
        this.ser = ser;
        this.prod = prod;
        this.aut = aut;
        this.interFac = interFac;
        this.csdtFac = csdtFac;
        this.emp = emp;
        this.csbl = csbl;
        this.interFac.btnBuscarPlacaVehiculoDN.addActionListener(this);
        this.interFac.btnCalcularServicioFactura.addActionListener(this);
        this.interFac.btnModificarClienteFactura.addActionListener(this);
        this.interFac.btnEliminarClienteFactura.addActionListener(this);
        this.interFac.BtnLimpiarCasilleros.addActionListener(this);
        this.interFac.btnBuscarFactura.addActionListener(this);
        this.interFac.btnAgruparServicios.addActionListener(this);
        this.interFac.btnCalcularPrecioTotalFactura.addActionListener(this);
        this.interFac.btnImportaDetalleFactura.addActionListener(this);
        this.interFac.btnImportarBoletaFactura.addActionListener(this);
        this.interFac.tablaRegistroCliente.addMouseListener(this);

        this.interFac.txtCantServicios.setEnabled(false);
        this.interFac.txtPrecioServiciosFactura.setEnabled(false);
        this.interFac.txtIgvServicios.setEnabled(false);
        this.interFac.txtPrecioServicios.setEnabled(false);
        this.interFac.txtIdDetalleFactura.setEnabled(false);
        this.interFac.jlblIdDetalleFactura.setVisible(false);
        this.interFac.txtIdDetalleFactura.setVisible(false);
        this.interFac.btnNuevoCliente.setVisible(false);
        this.interFac.btnCalcularPrecioTotalFactura.setEnabled(false);
        this.interFac.btnImportaDetalleFactura.setEnabled(false);
        this.interFac.btnImportarBoletaFactura.setEnabled(false);
        this.interFac.btnBuscarFactura.setEnabled(false);
        //this.interFac.btnBuscarFactura.setVisible(false);
        this.interFac.btnCalcularServicioFactura.setEnabled(false);
        this.interFac.btnModificarClienteFactura.setEnabled(false);
        this.interFac.btnEliminarClienteFactura.setEnabled(false);
        this.interFac.btnAgruparServicios.setEnabled(false);
        this.interFac.BtnLimpiarCasilleros.setEnabled(false);

        this.interFac.cbxCargarPlacaVehiFatura.setEnabled(false);
        this.interFac.cbxFacturaAgregarServicio.setEnabled(false);
        this.interFac.cbxCargarProductosFatura.setEnabled(false);
        this.interFac.txtCantidadProductoFactura.setEnabled(false);

        this.interFac.bcxBuscarClientesFactura.setEnabled(false);
        this.interFac.txtBuscarClienteFactura.setEnabled(false);

        //this.interFac.txtIdDetalleFactura.setVisible(false);
        csdtFac.cargarItemServicio(interFac.cbxFacturaAgregarServicio);
        csdtFac.cargarItemProducto(interFac.cbxCargarProductosFatura);
        csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
        this.ordenarTabla1();
        if (interFac.tablaRegistroCliente.getRowCount() > 0) {
            String cantidadRepuesta = interFac.tablaRegistroCliente.getValueAt(0, 2).toString();
            interFac.jtxDniClienteFactura.setEnabled(false);
            interFac.jtxDniClienteFactura.setText(cantidadRepuesta);
            interFac.btnBuscarPlacaVehiculoDN.setEnabled(false);
            cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
            csdtFac.cargarIdPlaca(cl, interFac.cbxCargarPlacaVehiFatura);
            this.interFac.btnCalcularServicioFactura.setEnabled(true);
            this.interFac.btnAgruparServicios.setEnabled(true);
            this.interFac.cbxCargarPlacaVehiFatura.setEnabled(true);
            this.interFac.cbxFacturaAgregarServicio.setEnabled(true);
            this.interFac.cbxCargarProductosFatura.setEnabled(true);
            this.interFac.txtCantidadProductoFactura.setEnabled(true);
            this.interFac.BtnLimpiarCasilleros.setEnabled(true);
            this.interFac.bcxBuscarClientesFactura.setEnabled(true);
            this.interFac.txtBuscarClienteFactura.setEnabled(true);
            this.interFac.btnBuscarFactura.setEnabled(true);
        }
        csdtFac.conseguirIdUsuario(cl, emp.jtxNombreEmpleado);

    }

    private void limpiarTabla() {
        this.interFac.jtxDniClienteFactura.setText("");
        this.interFac.txtCantidadProductoFactura.setText("");
        this.interFac.cbxCargarPlacaVehiFatura.setSelectedIndex(0);
        this.interFac.cbxCargarPlacaVehiFatura.removeAllItems();
        this.interFac.cbxFacturaAgregarServicio.setSelectedIndex(0);
        this.interFac.cbxCargarProductosFatura.setSelectedIndex(0);
        this.interFac.bcxBuscarClientesFactura.setSelectedIndex(0);
        this.interFac.txtBuscarClienteFactura.setText("");
        this.interFac.txtCantServicios.setText("");
        this.interFac.txtPrecioServiciosFactura.setText("");
        this.interFac.txtIgvServicios.setText("");
        this.interFac.txtPrecioServicios.setText("");
        this.interFac.cbxCargarPlacaVehiFatura.addItem("--Selecciona--");
        this.csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
        this.interFac.btnBuscarPlacaVehiculoDN.setEnabled(true);
        this.interFac.jtxDniClienteFactura.setEnabled(true);
        this.interFac.btnCalcularServicioFactura.setEnabled(false);
        this.interFac.btnModificarClienteFactura.setEnabled(false);
        this.interFac.btnEliminarClienteFactura.setEnabled(false);
        this.interFac.btnAgruparServicios.setEnabled(false);
        this.interFac.BtnLimpiarCasilleros.setEnabled(false);
        this.interFac.cbxCargarPlacaVehiFatura.setEnabled(false);
        this.interFac.cbxFacturaAgregarServicio.setEnabled(false);
        this.interFac.cbxCargarProductosFatura.setEnabled(false);
        this.interFac.txtCantidadProductoFactura.setEnabled(false);
        //cl.setId_iden2(Integer.parseInt(emp.jtxNombreEmpleado.getText()));
    }

    private void ordenarTabla1() {
        interFac.tablaRegistroCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(0).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(1).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(2).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(3).setPreferredWidth(90);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(3).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(4).setPreferredWidth(140);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(4).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(5).setPreferredWidth(90);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(5).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(6).setPreferredWidth(100);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(6).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(7).setPreferredWidth(100);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(7).setResizable(false);
        //interFac.tablaRegistroCliente.getColumnModel().getColumn(8).setPreferredWidth(100);
        //interFac.tablaRegistroCliente.getColumnModel().getColumn(8).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(8).setPreferredWidth(80);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(8).setResizable(false);

    }

    private void ordenarTabla2() {
        interFac.tablaRegistroCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(0).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(1).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(2).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(3).setPreferredWidth(90);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(3).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(4).setPreferredWidth(140);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(4).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(5).setPreferredWidth(90);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(5).setResizable(false);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(6).setPreferredWidth(60);
        interFac.tablaRegistroCliente.getColumnModel().getColumn(6).setResizable(false);
    }

    private boolean verificarCantidad(Productos prod) {
        try {
            prod.setComprobar(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean verificarDNI(Cliente cl) {
        try {
            cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    
    private boolean verificarDNI2(Cliente cl) {
        try {
            cl.setDni(Integer.parseInt(interFac.txtBuscarClienteFactura.getText()));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //----------------------------------BUSCAR PLACA DEL AUTO DEL VEHICULO DEL CLIENTE-----------------------------------------------------------------
        if (e.getSource() == interFac.btnBuscarPlacaVehiculoDN) {
            if (interFac.jtxDniClienteFactura.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Complete el campo");
            } else {
                if (this.verificarDNI(cl)) {

                    cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                    cl.setVer("Incorrecto");
                    csdtFac.obtenerListaDni(cl);
                    csdtFac.verificarDNI(cl);

                    if (cl.getVer().equals("Correcto")) {
                        csdtFac.cargarIdPlaca(cl, interFac.cbxCargarPlacaVehiFatura);
                        this.interFac.btnCalcularServicioFactura.setEnabled(true);
                        this.interFac.cbxCargarPlacaVehiFatura.setEnabled(true);
                        this.interFac.cbxFacturaAgregarServicio.setEnabled(true);
                        this.interFac.cbxCargarProductosFatura.setEnabled(true);
                        this.interFac.txtCantidadProductoFactura.setEnabled(true);
                        this.interFac.BtnLimpiarCasilleros.setEnabled(true);
                    } else {
                        this.interFac.btnCalcularServicioFactura.setEnabled(false);
                        this.interFac.cbxCargarPlacaVehiFatura.setEnabled(false);
                        this.interFac.cbxFacturaAgregarServicio.setEnabled(false);
                        this.interFac.cbxCargarProductosFatura.setEnabled(false);
                        this.interFac.txtCantidadProductoFactura.setEnabled(false);
                        this.interFac.BtnLimpiarCasilleros.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "DNI No Encontrado");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Dato inválido");
                }

            }
        }

        //----------------------------------CALCULAR PAGO----------------------------------------------------------------
        if (e.getSource() == interFac.btnCalcularServicioFactura) {

            if (interFac.jtxDniClienteFactura.getText().equals("")
                    || interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString().equals("--Selecciona--")
                    || interFac.cbxFacturaAgregarServicio.getSelectedItem().toString().equals("--Selecciona--")
                    || interFac.cbxCargarProductosFatura.getSelectedItem().toString().equals("--Selecciona--")
                    || interFac.txtCantidadProductoFactura.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Selecciona todos los campos");

            } else {
                if (this.verificarCantidad(prod)) {
                    if (Integer.parseInt(interFac.txtCantidadProductoFactura.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Cantidad inválida");
                    } else {
                        cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                        cl.setVer("Correcto");

                        csdtFac.verificarDatosPago(cl, prod, interFac.cbxCargarPlacaVehiFatura,
                                interFac.cbxFacturaAgregarServicio,
                                interFac.cbxCargarProductosFatura, interFac.txtCantidadProductoFactura);
                        /*int total = cantidadComprobacion + prod.getCantidad2();
                prod.setCantidad2(total);*/

                        if (interFac.tablaRegistroCliente.getRowCount() > 0) {

                            interFac.txtIdDetalleFactura.setText(interFac.tablaRegistroCliente.getValueAt(0, 0).toString());
                        }

                        if (cl.getVer().equals("Incorrecto")) {
                            JOptionPane.showMessageDialog(null, "Opción ya ingresada");

                        } else {
                            cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                            aut.setCod(interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString());
                            ser.setNombre(interFac.cbxFacturaAgregarServicio.getSelectedItem().toString());
                            prod.setNombre(interFac.cbxCargarProductosFatura.getSelectedItem().toString());
                            prod.setCantidad(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));

                            int cantidadComprobacion = prod.getCantidad();

                            csdtFac.conseguirCantidadProductos(prod);
                            int total = prod.getCantidad() - cantidadComprobacion;

                            if (cantidadComprobacion < prod.getCantidad()) {
                                csdtFac.disminuirStock(prod, total);
                                prod.setCantidad(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));
                                csdtFac.conseguirIdCliente(cl);
                                csdtFac.conseguirIdAutos(aut);
                                csdtFac.conseguirIdServicio(ser);
                                csdtFac.conseguirIdProducto(prod);
                                if (interFac.tablaRegistroCliente.getRowCount() == 0) {
                                    csdtFac.insertarFacturaCliente(cl);
                                }

                                //csdtFac.insertarFacturaCliente(cl);
                                //cl.setId_fac_pag(Integer.parseInt(interFac.txtIdDetalleFactura.getText()));
                                csdtFac.conseguirIDFactura(cl, aut, ser, prod);

                                if (interFac.tablaRegistroCliente.getRowCount() > 0) {
                                    //int fila12 = interFac.tablaRegistroCliente.getSelectedRow();
                                    int codId = Integer.parseInt(interFac.tablaRegistroCliente.getValueAt(0, 0).toString());
                                    cl.setId_fac_pag(codId);
                                }

                                csdtFac.insertarDatosTabladetallePago(cl, prod, ser, aut);
                                //Disminuir stock

                                System.out.println(total);
                                //----------------------------------------
                                csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
                                this.ordenarTabla1();
                                this.interFac.jtxDniClienteFactura.setEnabled(false);
                                this.interFac.btnBuscarPlacaVehiculoDN.setEnabled(false);
                                this.interFac.btnAgruparServicios.setEnabled(true);
                                this.interFac.txtCantidadProductoFactura.setText("");
                                this.interFac.cbxFacturaAgregarServicio.setSelectedIndex(0);
                                this.interFac.cbxCargarProductosFatura.setSelectedIndex(0);
                                this.interFac.bcxBuscarClientesFactura.setEnabled(true);
                                this.interFac.txtBuscarClienteFactura.setEnabled(true);
                                this.interFac.btnModificarClienteFactura.setEnabled(false);
                                this.interFac.btnEliminarClienteFactura.setEnabled(false);
                                this.interFac.btnBuscarFactura.setEnabled(true);
                                JOptionPane.showMessageDialog(null, "Registro exitoso");
                            } else {//if (prod.getCantidad() - prod.getCantidad2() == 0)
                                this.ordenarTabla1();
                                JOptionPane.showMessageDialog(null, "Elija una cantidad menor de productos");
                            }
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida");
                }

            }

        }

        //----------------------------------MODIFICAR PLACA DEL AUTO DEL VEHICULO DEL CLIENTE-----------------------------------------------------------------
        if (e.getSource() == interFac.btnModificarClienteFactura) {

            if (interFac.jtxDniClienteFactura.getText().equals("")
                    || interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString().equals("--Selecciona--")
                    || interFac.cbxFacturaAgregarServicio.getSelectedItem().toString().equals("--Selecciona--")
                    || interFac.cbxCargarProductosFatura.getSelectedItem().toString().equals("--Selecciona--")
                    || interFac.txtCantidadProductoFactura.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona todos los campos");

            } else {
                if (this.verificarCantidad(prod)) {
                    if (Integer.parseInt(interFac.txtCantidadProductoFactura.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Cantidad inválida");
                    } else {

                        cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                        cl.setVer("Correcto");

                        csdtFac.verificarDatosPago(cl, prod, interFac.cbxCargarPlacaVehiFatura,
                                interFac.cbxFacturaAgregarServicio,
                                interFac.cbxCargarProductosFatura, interFac.txtCantidadProductoFactura);

                        int fila2 = interFac.tablaRegistroCliente.getSelectedRow();
                        String placa_vari = interFac.tablaRegistroCliente.getValueAt(fila2, 3).toString();
                        String servicio_vari = interFac.tablaRegistroCliente.getValueAt(fila2, 4).toString();
                        String producto_vari = interFac.tablaRegistroCliente.getValueAt(fila2, 6).toString();

                        if (cl.getVer().equals("Incorrecto")
                                && !interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString().equals(placa_vari)
                                && !interFac.cbxFacturaAgregarServicio.getSelectedItem().toString().equals(servicio_vari)
                                && !interFac.cbxCargarProductosFatura.getSelectedItem().toString().equals(producto_vari)) {
                            JOptionPane.showMessageDialog(null, "Opción ya ingresada");

                        } else if ((cl.getVer().equals("Correcto"))
                                || (cl.getVer().equals("Incorrecto") && interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString().equals(placa_vari)
                                && interFac.cbxFacturaAgregarServicio.getSelectedItem().toString().equals(servicio_vari)
                                && interFac.cbxCargarProductosFatura.getSelectedItem().toString().equals(producto_vari))) {
                            cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                            aut.setCod(interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString());
                            ser.setNombre(interFac.cbxFacturaAgregarServicio.getSelectedItem().toString());
                            prod.setNombre(interFac.cbxCargarProductosFatura.getSelectedItem().toString());
                            prod.setCantidad(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));

                            int cantidadComprobacion = prod.getCantidad();

                            int fila = interFac.tablaRegistroCliente.getSelectedRow();
                            int cantidadcambio = Integer.parseInt(interFac.tablaRegistroCliente.getValueAt(fila, 7).toString());

                            csdtFac.conseguirCantidadProductos(prod);
                            int total = prod.getCantidad() - cantidadComprobacion + cantidadcambio;

                            if (cantidadComprobacion <= prod.getCantidad()) {
                                csdtFac.disminuirStock(prod, total);
                                /*
                            prod.setCantidad(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));

                            csdtFac.conseguirIdCliente(cl);
                            csdtFac.conseguirIdAutos(aut);
                            csdtFac.conseguirIdServicio(ser);
                            csdtFac.conseguirIdProducto(prod);*/

                                int filaVerifi = interFac.tablaRegistroCliente.getSelectedRow();
                                String placa = interFac.tablaRegistroCliente.getValueAt(filaVerifi, 3).toString();
                                String servicio = interFac.tablaRegistroCliente.getValueAt(filaVerifi, 4).toString();
                                String producto = interFac.tablaRegistroCliente.getValueAt(filaVerifi, 6).toString();
                                int cantidad_prod_se = Integer.parseInt(interFac.tablaRegistroCliente.getValueAt(fila, 7).toString());

                                aut.setCod(placa);
                                ser.setNombre(servicio);
                                prod.setNombre(producto);
                                prod.setCantidad(cantidad_prod_se);

                                csdtFac.conseguirIdCliente(cl);
                                csdtFac.conseguirIdAutos(aut);
                                csdtFac.conseguirIdServicio(ser);
                                csdtFac.conseguirIdProducto(prod);

                                int id_cli = cl.getId();
                                int id_ser = ser.getId();
                                int id_prod = prod.getId();
                                int id_aut = aut.getId();
                                int cant = prod.getCantidad();

                                cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                                aut.setCod(interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString());
                                ser.setNombre(interFac.cbxFacturaAgregarServicio.getSelectedItem().toString());
                                prod.setNombre(interFac.cbxCargarProductosFatura.getSelectedItem().toString());
                                prod.setCantidad(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));

                                csdtFac.conseguirIdCliente(cl);
                                csdtFac.conseguirIdAutos(aut);
                                csdtFac.conseguirIdServicio(ser);
                                csdtFac.conseguirIdProducto(prod);

                                cl.setId_fac_pag(Integer.parseInt(interFac.txtIdDetalleFactura.getText()));
                                csdtFac.modificarFacturaCliente(cl);
                                //ya tengo id cliente (dni)

                                //cl.setId_fac_pag(Integer.parseInt(interFac.txtIdDetalleFactura.getText()));
                                csdtFac.conseguirIDFactura(cl, aut, ser, prod);

                                csdtFac.modificarDatosTabladetallePago(cl, prod, aut, ser, id_aut, id_ser, id_prod, cant);
                                csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
                                this.interFac.btnModificarClienteFactura.setEnabled(false);
                                this.interFac.btnEliminarClienteFactura.setEnabled(false);

                                this.ordenarTabla1();
                                interFac.txtCantidadProductoFactura.setText("");
                                interFac.cbxFacturaAgregarServicio.setSelectedIndex(0);
                                interFac.cbxCargarProductosFatura.setSelectedIndex(0);
                                JOptionPane.showMessageDialog(null, "Modificación exitosa");
                            } else {
                                this.ordenarTabla1();
                                JOptionPane.showMessageDialog(null, "Elija una cantidad menor de productos");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Opción ya ingresada");
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida");
                }

            }

        }

        //----------------------------------ELIMINAR FACTURA-----------------------------------------------------------------
        if (e.getSource() == interFac.btnEliminarClienteFactura) {

            int fila = interFac.tablaRegistroCliente.getSelectedRow();
            if (fila >= 0) {

                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    prod.setNombre(interFac.cbxCargarProductosFatura.getSelectedItem().toString());

                    int cantidadeliminar = Integer.parseInt(interFac.tablaRegistroCliente.getValueAt(fila, 7).toString());
                    csdtFac.conseguirCantidadProductos(prod);
                    int total2 = prod.getCantidad() + cantidadeliminar;
                    //devuelve la cantidad de productos
                    csdtFac.disminuirStock(prod, total2);
                    cl.setId_fac_pag(Integer.parseInt(interFac.txtIdDetalleFactura.getText()));
                    //csdtFac.eliminarDetalleFactura(cl);
                    //csdtFac.eliminarFactura(cl);

                    aut.setCod(interFac.cbxCargarPlacaVehiFatura.getSelectedItem().toString());
                    ser.setNombre(interFac.cbxFacturaAgregarServicio.getSelectedItem().toString());
                    prod.setNombre(interFac.cbxCargarProductosFatura.getSelectedItem().toString());

                    csdtFac.conseguirIdAutos(aut);
                    csdtFac.conseguirIdServicio(ser);
                    csdtFac.conseguirIdProducto(prod);
                    prod.setCantidad(Integer.parseInt(interFac.txtCantidadProductoFactura.getText()));

                    csdtFac.eliminarDetalleFacturaUpdate(cl, prod, aut, ser);

                    //csdtFac.eliminarFacturaUpdate(cl);
                    csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
                    interFac.txtCantidadProductoFactura.setText("");
                    interFac.cbxFacturaAgregarServicio.setSelectedIndex(0);
                    interFac.cbxCargarProductosFatura.setSelectedIndex(0);
                    this.interFac.btnModificarClienteFactura.setEnabled(false);
                    this.interFac.btnEliminarClienteFactura.setEnabled(false);

                    this.ordenarTabla1();

                    if (interFac.tablaRegistroCliente.getRowCount() == 0) {
                        this.limpiarTabla();
                        this.interFac.bcxBuscarClientesFactura.setEnabled(false);
                        this.interFac.txtBuscarClienteFactura.setEnabled(false);
                        this.interFac.btnBuscarFactura.setEnabled(false);

                        this.ordenarTabla1();
                    }

                    if (interFac.tablaRegistroCliente.getRowCount() == 0) {
                        csdtFac.eliminarFacturaUpdate(cl);
                    }

                    JOptionPane.showMessageDialog(null, "Eliminación exitosa");
                } else {
                    interFac.tablaRegistroCliente.clearSelection();
                }

            } else {

                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }

        }

        //----------------------------------LIMPIAR-----------------------------------------------------------------
        if (e.getSource() == interFac.BtnLimpiarCasilleros) {
            interFac.jtxDniClienteFactura.setText("");
            interFac.txtCantidadProductoFactura.setText("");

            interFac.cbxFacturaAgregarServicio.setSelectedIndex(0);
            interFac.cbxCargarProductosFatura.setSelectedIndex(0);

            interFac.txtCantServicios.setText("");
            interFac.txtPrecioServiciosFactura.setText("");
            interFac.txtIgvServicios.setText("");
            interFac.txtPrecioServicios.setText("");
            interFac.bcxBuscarClientesFactura.setSelectedIndex(0);
            interFac.txtBuscarClienteFactura.setText("");
            interFac.txtBuscarClienteFactura.setEditable(true);
            csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);

            this.interFac.btnCalcularServicioFactura.setEnabled(false);
            this.interFac.btnModificarClienteFactura.setEnabled(false);
            this.interFac.btnEliminarClienteFactura.setEnabled(false);
            this.interFac.btnAgruparServicios.setEnabled(false);

            this.interFac.btnCalcularPrecioTotalFactura.setEnabled(false);
            this.interFac.btnImportaDetalleFactura.setEnabled(false);
            this.interFac.btnImportarBoletaFactura.setEnabled(false);
            this.interFac.btnBuscarFactura.setEnabled(false);

            if (interFac.tablaRegistroCliente.getRowCount() > 0) {
                String cantidadRepuesta = interFac.tablaRegistroCliente.getValueAt(0, 2).toString();
                interFac.jtxDniClienteFactura.setEnabled(false);
                interFac.jtxDniClienteFactura.setText(cantidadRepuesta);
                interFac.btnBuscarPlacaVehiculoDN.setEnabled(false);
                this.interFac.btnAgruparServicios.setEnabled(true);
                this.interFac.btnCalcularServicioFactura.setEnabled(true);
                this.interFac.btnBuscarFactura.setEnabled(true);

                this.interFac.bcxBuscarClientesFactura.setEnabled(true);
                this.interFac.txtBuscarClienteFactura.setEnabled(true);
                //cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                //csdtFac.cargarIdPlaca(cl, interFac.btnCargarPlacaVehiFatura);
            }
            this.ordenarTabla1();
        }

        //----------------------------------BUSCAR FACTURA-----------------------------------------------------------------
        if (e.getSource() == interFac.btnBuscarFactura) {

            if (interFac.txtBuscarClienteFactura.getText().equals("")
                    || interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("--Selecciona--")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            } else {

                if (interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("DNI")) {
                    if (this.verificarDNI2(cl)) {
                        cl.setDni(Integer.parseInt(interFac.txtBuscarClienteFactura.getText()));
                        if (cl.getDni() > 0) {
                            cl.setVer("Incorrecto");
                            csdtFac.obtenerListaDni(cl);
                            csdtFac.verificarDNI(cl);

                            if (cl.getVer().equals("Correcto")) {
                                if (csdtFac.buscarTablaFacturaCliente(interFac.tablaRegistroCliente, cl, interFac.bcxBuscarClientesFactura)) {
                                    this.ordenarTabla1();
                                    this.interFac.btnAgruparServicios.setEnabled(true);

                                } else {
                                    this.ordenarTabla1();
                                    JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "DNI No encontrado");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Datos Erróneos");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Datos Erróneos");
                    }

                } else if (interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("Nombre Servicio")
                        || interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("Nombre Producto")) {
                    cl.setNombre(interFac.txtBuscarClienteFactura.getText());
                    if (csdtFac.buscarTablaFacturaCliente(interFac.tablaRegistroCliente, cl, interFac.bcxBuscarClientesFactura)) {
                        this.ordenarTabla1();
                        this.interFac.btnAgruparServicios.setEnabled(true);
                        

                    } else {
                        this.ordenarTabla1();
                        JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
                    }

                    if (interFac.tablaRegistroCliente.getRowCount() == 0) {
                        //int fila12 = interFac.tablaRegistroCliente.getSelectedRow();
                        csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
                        this.ordenarTabla1();
                        JOptionPane.showMessageDialog(null, "Datos No Encontrados");
                    }

                }

            }
        }

        //----------------------------------AGRUPAR SERVICIOS -----------------------------------------------------------------
        if (e.getSource() == interFac.btnAgruparServicios) {
            if (interFac.txtBuscarClienteFactura.getText().equals("")
                    || interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("--Selecciona--")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            } else if (!interFac.txtBuscarClienteFactura.getText().equals("")
                    && interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("DNI")) {

                if (this.verificarDNI2(cl)) {
                    cl.setDni(Integer.parseInt(interFac.txtBuscarClienteFactura.getText()));

                    if (cl.getDni() > 0) {
                        cl.setVer("Incorrecto");
                        csdtFac.obtenerListaDni(cl);
                        csdtFac.verificarDNI(cl);

                        if (cl.getVer().equals("Correcto")) {
                            if (csdtFac.agruparTablaFacturaCliente(interFac.tablaRegistroCliente, cl, interFac.bcxBuscarClientesFactura)) {
                                this.ordenarTabla2();
                                this.interFac.btnCalcularPrecioTotalFactura.setEnabled(true);
                                this.interFac.btnCalcularServicioFactura.setEnabled(false);
                                this.interFac.btnModificarClienteFactura.setEnabled(false);
                                this.interFac.btnEliminarClienteFactura.setEnabled(false);
                                this.interFac.btnBuscarFactura.setEnabled(false);
                                this.interFac.btnAgruparServicios.setEnabled(false);
                                interFac.txtBuscarClienteFactura.setEditable(false);

                            } else {
                                //this.ordenarTabla1();
                                JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "DNI No Encontrado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresar un Valor Válido");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Dato Erróneo");
                }
                //cl.setNombre(interFac.txtBuscarClienteFactura.getText());

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona el campo DNI");
            }
        }

        //----------------------------------CALCULAR PRECIO -----------------------------------------------------------------
        if (e.getSource() == interFac.btnCalcularPrecioTotalFactura) {
            if (interFac.txtBuscarClienteFactura.getText().equals("")
                    || interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("--Selecciona--")) {
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            } else {
                cl.setDni(Integer.parseInt(interFac.txtBuscarClienteFactura.getText()));
                csdtFac.precioTablaDetalleFactura(interFac.tablaRegistroCliente, cl, interFac.bcxBuscarClientesFactura);
                interFac.txtCantServicios.setText(String.valueOf(cl.getCant_ser()));
                interFac.txtPrecioServiciosFactura.setText(String.valueOf(cl.getPago()));
                DecimalFormat df = new DecimalFormat("#.000");
                interFac.txtIgvServicios.setText(String.valueOf(df.format(cl.descuento_Beneficio())));
                interFac.txtPrecioServicios.setText(String.valueOf(cl.pagoFinal()));
                interFac.btnImportaDetalleFactura.setEnabled(true);
                //System.out.println(cl.getPago());
                //System.out.println(cl.getCant_ser());
            }

        }
        //----------------------------------IMPORTAR DETALLE FACTURA -----------------------------------------------------------------
        if (e.getSource() == interFac.btnImportaDetalleFactura) {
            if ((interFac.txtBuscarClienteFactura.getText().equals("")
                    || interFac.bcxBuscarClientesFactura.getSelectedItem().toString().equals("--Selecciona--"))
                    || interFac.txtCantServicios.getText().equals("")
                    || interFac.txtPrecioServiciosFactura.getText().equals("")
                    || interFac.txtIgvServicios.getText().equals("")
                    || interFac.txtPrecioServicios.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No hay nada para importar");
            } else {
                cl.setCant_ser(Integer.parseInt(interFac.txtCantServicios.getText()));
                cl.setPago(Double.parseDouble(interFac.txtPrecioServiciosFactura.getText()));
                cl.setDni(Integer.parseInt(interFac.txtBuscarClienteFactura.getText()));

                //int fila12 = interFac.tablaRegistroCliente.getSelectedRow();
                cl.setId_fac_pag(Integer.parseInt(interFac.tablaRegistroCliente.getValueAt(0, 0).toString()));
                int guardar = cl.getId_fac_pag();

                csdtFac.conseguirIdCliente(cl);
                csdtFac.insertarDatosDetalleFactura(cl);
                /*csdtFac.obtenerStockUsado(cl);
                csdtFac.obtenerStockSistema(cl);
                csdtFac.ActualizarStock2(prod, cl);*/
                csdtFac.obtenerFacturasCliente(cl);
                csdtFac.modificarFacturaCliente2(cl);
                csdtFac.obtenerFacturasCliente2(cl);
                csdtFac.modificarFacturaCliente3(cl);
                this.interFac.btnCalcularPrecioTotalFactura.setEnabled(false);
                this.interFac.btnImportaDetalleFactura.setEnabled(false);
                this.interFac.btnImportarBoletaFactura.setEnabled(true);
                csdtFac.mostrarTablaFacturaCliente(interFac.tablaRegistroCliente);
                //prod.setCantidad2(0);
                cl.setCant_ser(Integer.parseInt(interFac.txtCantServicios.getText()));
                this.ordenarTabla1();
                this.limpiarTabla();
                interFac.jtxDniClienteFactura.setEnabled(true);
                interFac.btnBuscarPlacaVehiculoDN.setEnabled(true);

                interFac.txtIdDetalleFactura.setText(String.valueOf(guardar));
                JOptionPane.showMessageDialog(null, "Importe con éxito");

            }

        }

        if (e.getSource() == interFac.btnImportarBoletaFactura) {

            //ConsultaBoletaIndividual csbl = new ConsultaBoletaIndividual();
            InterBoletaIndividual interBoleta = new InterBoletaIndividual();

            cl.setId_fac_pag(Integer.parseInt(interFac.txtIdDetalleFactura.getText()));
            csdtFac.conseguirNombreCliente(cl);
            cl.setVer2(emp.jtxNombreEmpleado.getText());

            csbl.imprimir(cl);

            ControladorBoletaIndividual controladoBoleta = new ControladorBoletaIndividual(cl, csbl, interBoleta);
            emp.dskPaneSistema.add(interBoleta);
            interBoleta.setVisible(true);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == interFac.tablaRegistroCliente) {

            int fila = interFac.tablaRegistroCliente.getSelectedRow();

            if (fila >= 0) {
                interFac.txtIdDetalleFactura.setText(interFac.tablaRegistroCliente.getValueAt(fila, 0).toString());
                interFac.jtxDniClienteFactura.setText(interFac.tablaRegistroCliente.getValueAt(fila, 2).toString());
                cl.setDni(Integer.parseInt(interFac.jtxDniClienteFactura.getText()));
                csdtFac.cargarIdPlaca(cl, interFac.cbxCargarPlacaVehiFatura);
                interFac.cbxCargarPlacaVehiFatura.setSelectedItem(interFac.tablaRegistroCliente.getValueAt(fila, 3).toString());
                interFac.cbxFacturaAgregarServicio.setSelectedItem(interFac.tablaRegistroCliente.getValueAt(fila, 4).toString());
                interFac.cbxCargarProductosFatura.setSelectedItem(interFac.tablaRegistroCliente.getValueAt(fila, 6).toString());
                interFac.txtCantidadProductoFactura.setText(interFac.tablaRegistroCliente.getValueAt(fila, 7).toString());
                this.interFac.btnModificarClienteFactura.setEnabled(true);
                this.interFac.btnEliminarClienteFactura.setEnabled(true);

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
