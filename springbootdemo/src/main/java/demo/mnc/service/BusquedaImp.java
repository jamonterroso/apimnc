/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.mnc.service;

import demo.mnc.SpringBootDemoApplication;

import demo.mnc.model.ResultadosBusqueda;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.xml.sax.InputSource;

/**
 *
 * @author Dell
 */
@Service("BusquedaServicio")
public class BusquedaImp implements BusquedaServicio {
private static List<ResultadosBusqueda> resultadosbusqueda = new ArrayList<ResultadosBusqueda>();
    @Override
    public List<ResultadosBusqueda> getAllContenido(String canciones,String peliculas,String television,String personas,String todos) {
        resultadosbusqueda = new ArrayList<ResultadosBusqueda>();
 
        canciones = canciones.replace(" ", "+");
        peliculas = peliculas.replace(" ", "+");
        television = television.replace(" ", "+");
        personas = personas.replace(" ", "+");
        todos = todos.replace(" ", "+");
        if(!todos.equals("")){
            canciones=todos;
            peliculas=todos;
            television=todos;
            personas=todos;
        }
        final String uriCanciones = "https://itunes.apple.com/search?term="+canciones+"&entity=song";
        final String uriPeliculas = "https://itunes.apple.com/search?term="+peliculas+"&entity=movie";
        final String uriTelevisionI = "https://itunes.apple.com/search?term="+television+"&entity=tvEpisode";
        final String uriTelevisionTV = "http://api.tvmaze.com/search/shows?q="+television;
        final String uriPersonas = "http://www.crcind.com/csp/samples/SOAP.Demo.cls?soap_method=GetByName&name="+personas;
        
        if(!canciones.equals("")){
            ParseUrl(uriCanciones,"Canciones");
        }
        if(!peliculas.equals("")){
            ParseUrl(uriPeliculas,"Peliculas");
        }
        if(!television.equals("")){
            ParseUrl(uriTelevisionI,"Television");
            ParseUrlTV(uriTelevisionTV,"Television");
        }
        if(!personas.equals("")){
            ParseUrlPersons(uriPersonas,"Personas");
        }
        return resultadosbusqueda;
    }
     
    private  void ParseUrl(String url,String tipo){
    try {
        RestTemplate restTemplate = new RestTemplate();
        String result =restTemplate.getForObject(url, String.class);
        JSONObject obj2 = new JSONObject(result);
        JSONArray res = obj2.getJSONArray("results");
        for (int i = 0; i < res.length(); i++) {
            ResultadosBusqueda r= new ResultadosBusqueda();
            JSONObject object = res.getJSONObject(i);
            r.setArtista(object.getString("artistName"));
            r.setNombre(object.getString("trackName"));
            r.setTipo(tipo);
            r.setFuente("https://itunes.apple.com");
            resultadosbusqueda.add(r);
        }
    } catch (JSONException ex) {
        java.util.logging.Logger.getLogger(BusquedaImp.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
        
    private  void ParseUrlTV(String url,String tipo){
    try {
        RestTemplate restTemplate = new RestTemplate();
        String result =restTemplate.getForObject(url, String.class);
        JSONArray obj2 = new JSONArray(result);
        for (int i = 0; i < obj2.length(); i++) {
            ResultadosBusqueda r= new ResultadosBusqueda();
            JSONObject object = obj2.getJSONObject(i);
            JSONObject res = object.getJSONObject("show");
            for (int j = 0; j < res.length(); j++) {
                r.setArtista(res.getString("name"));
                r.setNombre(res.getString("summary"));
                r.setTipo(tipo);
                r.setFuente(res.getString("url"));
            }
            resultadosbusqueda.add(r);
        }
    } catch (JSONException ex) {
        java.util.logging.Logger.getLogger(BusquedaImp.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }   
    private  void ParseUrlPersons(String url,String tipo){
       
    try {
        String str = url;
        URL url1 = new URL(str);
        InputStream is = url1.openStream();
        int ptr = 0;
        StringBuilder builder = new StringBuilder();
        while ((ptr = is.read()) != -1) {
            builder.append((char) ptr);
        }
        String xml = builder.toString();
        Document doc = DocumentBuilderFactory.newInstance()
                                         .newDocumentBuilder()
                                         .parse(new InputSource(new StringReader(xml)));
        NodeList errNodes = doc.getElementsByTagName("SQL");
        if (errNodes.getLength() > 0) {
                for(int i=0;i<errNodes.getLength();i++){
                    ResultadosBusqueda r= new ResultadosBusqueda();
                    Element err = (Element)errNodes.item(i);
                    r.setArtista(err.getElementsByTagName("DOB").item(0).getTextContent());
                    r.setNombre(err.getElementsByTagName("Name").item(0).getTextContent());
                    r.setTipo(tipo);
                    r.setFuente("http://www.crcind.com/csp/samples/SOAP.Demo.cls?soap_method=GetByName");
                    resultadosbusqueda.add(r);    
                    }
                    
                }

             else {  
                }
            }

       
        catch (IOException ex) {
        java.util.logging.Logger.getLogger(BusquedaImp.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException ex) {
        java.util.logging.Logger.getLogger(BusquedaImp.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
        java.util.logging.Logger.getLogger(BusquedaImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        
    }
}

