/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

    

public class Mesa {
    private int numero_mesa;
    private String estado;

    // Constructor vacío
    public Mesa() {}

    // Constructor con parámetros
    public Mesa(int numero, String estado) {
        this.numero_mesa = numero;
        this.estado = estado;
    }

    // Getters y Setters
    public int getNumero() {
        return numero_mesa;
    }

    public void setNumero(int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}