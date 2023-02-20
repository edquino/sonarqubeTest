/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.sonarqubetest;

/**
 *
 * @author Edqui
 */
public class CursoService {

    public static void main(String[] args) {
        Curso curso = new Curso("Jenkins", 700f);
        float precioFinal = curso.PrecioFinal(30);
        
        System.out.println("Precio Final = $" + precioFinal);
    }
}
