/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author JEFFERSON
 */
public class Productos extends TallerAutomotriz {

    private int cantidad = 0;
    private int cantidad2 = 0;
    private double precio_unidad;
    private int comprobar = 0;

    public Productos() {
    }

    public Productos(int cantidad, double precio_unidad, int id, String cod, String nombre, String descripcion,
            String fecha, String hora, int cantidad2, String ver, String ver2, int comprobar) {
        super(id, cod, nombre, descripcion, fecha, hora, ver, ver2);
        this.cantidad = cantidad;
        this.precio_unidad = precio_unidad;
        this.cantidad2 = cantidad2;
        this.comprobar = comprobar;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unidad() {
        return precio_unidad;
    }

    public void setPrecio_unidad(double precio_unidad) {
        this.precio_unidad = precio_unidad;
    }

    public double calcularPrecioTotal() {
        return getCantidad() * getPrecio_unidad();
    }

    public int getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public int getComprobar() {
        return comprobar;
    }

    public void setComprobar(int comprobar) {
        this.comprobar = comprobar;
    }

    @Override
    public Object[] Registro() {
        Object[] fila = {super.getId(), super.getNombre(), super.getDescripcion(), getCantidad(),
            getPrecio_unidad(), calcularPrecioTotal(), super.getHora(),
            super.getFecha()};
        return fila; //ID,NOMBRE,DESCRIPCION,CANTIDAD,PRECIO/UNIDAD,PRECIOTOTAL,FECHA,HORA
    }

}
