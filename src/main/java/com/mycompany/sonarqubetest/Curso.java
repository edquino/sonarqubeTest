/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sonarqubetest;

/**
 *
 * @author Edqui
 */
public class Curso {
    private String nombre;
    private float precio;
    private float descuento;
    private static final float impuesto = 13f;

    public Curso() {
    }

    public Curso(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
    public float PrecioFinal(float descuento) {
        float descuentoBase = precio;
        
        if(descuento < 0 || descuento > 100)
            throw new RuntimeException("Descuento erroneo");
        
        if(precio >= 700)
            descuentoBase = this.precio - (precio * descuento/100);
        
        return descuentoBase + (descuentoBase * this.impuesto/100f);
        
        
    }
}
