/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.mnc.controller;


import demo.mnc.model.ResultadosBusqueda;
import demo.mnc.service.BusquedaServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author Dell
 */
@RestController
public class BusquedaController {
    @Autowired
    private BusquedaServicio busquedaServicio;
   
    @GetMapping("/Busqueda")
    public List<ResultadosBusqueda> GetAllBusqueda(
            @RequestParam(value = "canciones", defaultValue = "") String canciones,
            @RequestParam(value = "peliculas", defaultValue = "") String peliculas,
            @RequestParam(value = "television", defaultValue = "") String television,
            @RequestParam(value = "personas", defaultValue = "") String personas,
            @RequestParam(value = "todos", defaultValue = "") String todos
            ) 
        {
		return busquedaServicio.getAllContenido(canciones,peliculas,television,personas,todos);
	}   
    
}
