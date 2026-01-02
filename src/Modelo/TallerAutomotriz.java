/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author JEFFERSON
 */
public abstract class TallerAutomotriz {

    private int id;
    private String cod;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String hora;
    private String ver = "";
    private String ver2 = "";


    public TallerAutomotriz() {
    }

    public TallerAutomotriz(int id, String cod, String nombre, String descripcion, String fecha, String hora, String ver, String ver2) {
        this.id = id;
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.ver = ver;
        this.ver2 = ver2;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVer2() {
        return ver2;
    }

    public void setVer2(String ver2) {
        this.ver2 = ver2;
    }

    
    
    public abstract Object[] Registro();


}
