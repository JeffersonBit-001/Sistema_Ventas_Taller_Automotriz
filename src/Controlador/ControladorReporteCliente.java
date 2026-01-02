/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexion.ConsultaBoletaIndividual;
import Conexion.ConsultaReporteCliente;
import Modelo.Cliente;
import Vista.FormEmpSistema;
import Vista.InterBoletaIndividual;
import Vista.InterReporteCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author JEFFERSON
 */
public class ControladorReporteCliente implements ActionListener, MouseListener {

    InterReporteCliente reporCli;
    Cliente cl;
    ConsultaReporteCliente csCli;
    FormEmpSistema emp;

    public ControladorReporteCliente(InterReporteCliente reporCli, Cliente cl, ConsultaReporteCliente csCli, FormEmpSistema emp) {
        this.reporCli = reporCli;
        this.cl = cl;
        this.csCli = csCli;
        this.emp = emp;
        csCli.mostrarDatos(reporCli.jtbTablaDeReportes);
        this.reporCli.btnBuscarReporteVentas.addActionListener(this);
        this.reporCli.btnEliminarReporteCliente.addActionListener(this);
        this.reporCli.jtbTablaDeReportes.addMouseListener(this);
        this.reporCli.btnRestaurarReporteCliente.addActionListener(this);
        this.reporCli.btnImprimirBoletaReport.addActionListener(this);
        reporCli.txtIdReporteCliente.setEnabled(false);
        reporCli.txtIdReporteCliente.setVisible(false);
        this.reporCli.btnEliminarReporteCliente.setEnabled(false);
        this.reporCli.btnImprimirBoletaReport.setEnabled(false);
        //this.reporCli.btnEliminarReporteCliente.setVisible(false);
        //this.reporCli.btnEliminarReporteCliente.setEnabled(false);
        this.ordenarTablaReporClie();
    }

    public void ordenarTablaReporClie() {
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(0).setPreferredWidth(30);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(0).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(1).setPreferredWidth(160);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(1).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(2).setPreferredWidth(90);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(2).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(3).setPreferredWidth(90);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(3).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(4).setPreferredWidth(90);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(4).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(5).setPreferredWidth(90);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(5).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(6).setPreferredWidth(80);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(6).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(7).setPreferredWidth(80);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(7).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(8).setPreferredWidth(80);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(8).setResizable(false);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(9).setPreferredWidth(75);
        reporCli.jtbTablaDeReportes.getColumnModel().getColumn(9).setResizable(false);
    }

    public void limpiarTabla() {
        reporCli.txtBuscarNombreReporte.setText("");
        reporCli.txtIdReporteCliente.setText("");
        reporCli.jbxBuscarReporteVentas.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == reporCli.btnBuscarReporteVentas) {
            if (reporCli.jbxBuscarReporteVentas.getSelectedItem().toString().equals("--Selecciona--")
                    || reporCli.txtBuscarNombreReporte.getText().equals("")) {
                if (reporCli.jbxBuscarReporteVentas.getSelectedItem().toString().equals("--Selecciona--")) {
                    JOptionPane.showMessageDialog(null, "Elige una Opción Correcta");
                } else if (reporCli.txtBuscarNombreReporte.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Completa los campos");
                }

            } else {
                if (reporCli.jbxBuscarReporteVentas.getSelectedItem().toString().equals("DNI")) {
                    if (csCli.verficarBusqueda(cl, reporCli.txtBuscarNombreReporte)) {
                        cl.setId(Integer.parseInt(reporCli.txtBuscarNombreReporte.getText()));
                        cl.setVer("Incorrecto");
                        csCli.obtenerListaDni(cl);
                        csCli.verificarDNI(cl);

                        if (Integer.parseInt(reporCli.txtBuscarNombreReporte.getText()) < 0) {
                            JOptionPane.showMessageDialog(null, "Dato inválido");
                        } else {
                            if (cl.getVer().equals("Correcto")) {
                                csCli.buscarTablaReportCliente(reporCli.jtbTablaDeReportes, cl, reporCli.jbxBuscarReporteVentas);
                                this.ordenarTablaReporClie();

                                if (reporCli.jtbTablaDeReportes.getRowCount() == 0) {
                                    csCli.mostrarDatos(reporCli.jtbTablaDeReportes);
                                    this.ordenarTablaReporClie();
                                    JOptionPane.showMessageDialog(null, "Datos No Encontrados");
                                }

                            } else {
                                csCli.mostrarDatos(reporCli.jtbTablaDeReportes);
                                this.ordenarTablaReporClie();
                                JOptionPane.showMessageDialog(null, "DNI no Registrado");

                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Verificar los datos");
                    }

                } else if (reporCli.jbxBuscarReporteVentas.getSelectedItem().toString().equals("Nombre")
                        || reporCli.jbxBuscarReporteVentas.getSelectedItem().toString().equals("Mes")) {
                    cl.setNombre(reporCli.txtBuscarNombreReporte.getText());
                    csCli.buscarTablaReportCliente(reporCli.jtbTablaDeReportes, cl, reporCli.jbxBuscarReporteVentas);
                    this.ordenarTablaReporClie();

                    if (reporCli.jtbTablaDeReportes.getRowCount() == 0) {
                        csCli.mostrarDatos(reporCli.jtbTablaDeReportes);
                        this.ordenarTablaReporClie();
                        JOptionPane.showMessageDialog(null, "Datos No Encontrados");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Elige una Opción Correcta");
                }
            }

        }

        if (e.getSource() == reporCli.btnEliminarReporteCliente) {
            int fila = reporCli.jtbTablaDeReportes.getSelectedRow();
            if (fila >= 0) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar", "  Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    cl.setId(Integer.parseInt(reporCli.txtIdReporteCliente.getText()));
                    csCli.eliminarTablaDetalleReportClienteUpdate(cl);
                    csCli.eliminarTablaReportFacturaClienteUpdate(cl);
                    csCli.mostrarDatos(reporCli.jtbTablaDeReportes);
                    this.limpiarTabla();
                    this.ordenarTablaReporClie();

                    JOptionPane.showMessageDialog(null, "Eliminación Exitosa");

                } else {
                    reporCli.jtbTablaDeReportes.clearSelection();
                }
            } else {

                JOptionPane.showMessageDialog(null, "Eliminación inválida");
            }
        }

        if (e.getSource() == reporCli.btnRestaurarReporteCliente) {
            this.limpiarTabla();
            csCli.mostrarDatos(reporCli.jtbTablaDeReportes);
            this.ordenarTablaReporClie();
            this.reporCli.btnEliminarReporteCliente.setEnabled(false);
            this.reporCli.btnImprimirBoletaReport.setEnabled(false);

        }

        if (e.getSource() == reporCli.btnImprimirBoletaReport) {
            this.reporCli.btnEliminarReporteCliente.setEnabled(false);
            InterBoletaIndividual interBoleta = new InterBoletaIndividual();

            cl.setId_fac_pag(Integer.parseInt(reporCli.txtIdReporteCliente.getText()));

            //csdtFac.conseguirNombreCliente(cl);
            //cl.setVer2(emp.jtxNombreEmpleado.getText());
            //csbl.imprimir(cl);
            ConsultaBoletaIndividual csbl = new ConsultaBoletaIndividual();
            ControladorBoletaIndividual controladoBoleta = new ControladorBoletaIndividual(cl, csbl, interBoleta);
            emp.dskPaneSistema.add(interBoleta);
            interBoleta.setVisible(true);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {
        if (e.getSource() == reporCli.jtbTablaDeReportes) {
            int fila = reporCli.jtbTablaDeReportes.getSelectedRow();

            if (fila >= 0) {
                reporCli.txtIdReporteCliente.setText(reporCli.jtbTablaDeReportes.getValueAt(fila, 0).toString());
                this.reporCli.btnEliminarReporteCliente.setEnabled(true);
                this.reporCli.btnImprimirBoletaReport.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                this.ordenarTablaReporClie();
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e
    ) {

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {

    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    @Override
    public void mouseExited(MouseEvent e
    ) {

    }

}
