/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.mnc.model;

/**
 *
 * @author Dell
 */
public class ResultadosBusqueda {
    private String tipo;
    private String artista;
    private String nombre;
    private String fuente;

    public ResultadosBusqueda() {
    }

    
    public ResultadosBusqueda(String tipo, String artista, String nombre, String fuente) {
        this.tipo = tipo;
        this.artista = artista;
        this.nombre = nombre;
        this.fuente = fuente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    
}
