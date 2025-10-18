/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.enums.TipoAula;

/**
 *
 * @author alexm
 */
public class Aula {
    private String id;
    private String nombre;
    private int capacidad;
    private TipoAula tipoAula;

    public Aula(String id, String nombre, int capacidad, TipoAula tipoAula) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoAula = tipoAula;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public TipoAula getTipoAula() { return tipoAula; }
    public void setTipoAula(TipoAula tipoAula) { this.tipoAula = tipoAula; }

    @Override
    public String toString() {
        return "Aula [ID=" + id + ", Nombre='" + nombre + "', Capacidad=" + capacidad + ", Tipo=" + tipoAula + "]";
    }
    
}
