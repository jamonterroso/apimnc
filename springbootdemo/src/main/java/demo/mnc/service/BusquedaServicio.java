/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.mnc.service;

import demo.mnc.model.ResultadosBusqueda;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface BusquedaServicio {
    public List<ResultadosBusqueda> getAllContenido(String canciones,String peliculas,String television,String personas,String todos);
    
}
