package com.example.demo.serviciosCSV;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.modelo.construccion;
import com.example.demo.modelo.granjero;
import com.example.demo.modelo.granjeroG;
import com.example.demo.modelo.plantacion;
import com.example.demo.modelo.riegos;
import com.example.demo.modelo.tractores;

public class leercsv {
	private static InsertarError instantiationError = new InsertarError();
	private static final Logger logger = LoggerFactory.getLogger(leercsv.class);
	public ArrayList<granjero> leerCSVG(String pathG,String pathError) throws IOException {
	    ArrayList<granjero> granjerosLista = new ArrayList<>();
	    String[] HEADERS = {"id","nombre","descripcion","dinero","puntos","nivel"};

	    try (Reader in = new FileReader(pathG)) {
	        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
	                .setHeader(HEADERS)
	                .setSkipHeaderRecord(true)
	                .build();

	        for (CSVRecord record : csvFormat.parse(in)) {
	        	try {
					granjero g = new granjero();
					g.setId(Integer.parseInt(record.get("id")));
					g.setNombre(record.get("nombre"));
					g.setDescripcion(record.get("descripcion"));
					g.setDinero(Float.parseFloat(record.get("dinero")));
					g.setPuntos(Integer.parseInt(record.get("puntos")));
					g.setNivel(Integer.parseInt(record.get("nivel")));
				
					granjerosLista.add(g);
				} catch (Exception e) {
					 logger.error("granjeros "+e);
	        		 instantiationError.addErrorG(pathError.toString(),e.getMessage());
	 				 return granjerosLista; 
				}
	        }
	            
	        }
	    return granjerosLista;
	}
	
	public ArrayList<construccion> leerCSVC(String pathC,String pathError) throws IOException {
    ArrayList<construccion> construccionesLista = new ArrayList<>();
    String[] HEADERS = {"id","nombre","precio","id_granjero"};

    try (Reader in = new FileReader(pathC)) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        
        for (CSVRecord record : csvFormat.parse(in)) {
        	try {
        		construccion c = new construccion();
                c.setId(Integer.parseInt(record.get("id")));
                c.setNombre(record.get("nombre"));
                c.setPrecio(Float.parseFloat(record.get("precio")));
                c.setId_granjero(Integer.parseInt(record.get("id_granjero")));
                construccionesLista.add(c);
             } catch (Exception e) {
            	 logger.error("construcciones "+e);
        		 instantiationError.addErrorC(pathError.toString(),e.getMessage());
            	 return construccionesLista;
     			}
        	
        	} 
        	return construccionesLista;
		}
	}
	
	public ArrayList<granjeroG> leerCSVGG(String pathGG, String pathError) throws IOException {
    ArrayList<granjeroG> granjeros_granjeros_Lista = new ArrayList<>();
    String[] HEADERS = {"id_granjero","id_vecino","puntos_compartidos"};

    try (Reader in = new FileReader(pathGG)) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
       

        for (CSVRecord record : csvFormat.parse(in)) {
        	try {	
        		granjeroG gg = new granjeroG();
            	gg.setId_granjero(Integer.parseInt(record.get("id_granjero")));
            	gg.setId_vecino(Integer.parseInt(record.get("id_vecino")));
            	gg.setPuntos_compartidos(Integer.parseInt(record.get("puntos_compartidos")));
				granjeros_granjeros_Lista.add(gg);
        	 } catch (Exception e) {
        		 logger.error("Granjero granjero "+e);
        		 instantiationError.addErrorGG(pathError.toString(),e.getMessage());
 				 return granjeros_granjeros_Lista; 
        	 }
		}
       
    }

    return granjeros_granjeros_Lista;
	}
	
	public ArrayList<plantacion> leerCSVP(String pathP, String pathError) throws IOException {
    ArrayList<plantacion> plantacionLista = new ArrayList<>();
    String[] HEADERS = {"id","nombre","precio_compra","precio_venta","proxima_cosecha","id_granjero"};

    try (Reader in = new FileReader(pathP)) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        for (CSVRecord record : csvFormat.parse(in)) {
        	try {
				plantacion p = new plantacion();
				p.setId(Integer.parseInt(record.get("id")));
				p.setNombre(record.get("nombre"));
				p.setPrecio_compra(Float.parseFloat(record.get("precio_compra")));
				p.setPrecio_venta(Float.parseFloat(record.get("precio_venta")));
				p.setProxima_cosecha(Timestamp.valueOf(record.get("proxima_cosecha")));
				p.setId_granjero(Integer.parseInt(record.get("id_granjero")));
				plantacionLista.add(p);
			} catch (Exception e) {
				logger.error("plantacionLista "+e);
        		 instantiationError.addErrorP(pathError.toString(),e.getMessage());
 				 return plantacionLista; 
				}
            
        	}
    	}

    	return plantacionLista;
	}
	
	public ArrayList<riegos> leerCSVR(String pathR, String pathError) throws IOException {
    ArrayList<riegos> riegoLista = new ArrayList<>();
    String[] HEADERS = {"id","tipo","velocidad","id_plantacion"};

    try (Reader in = new FileReader(pathR)) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        for (CSVRecord record : csvFormat.parse(in)) {
        	try {
        		riegos r = new riegos();
                r.setId(Integer.parseInt(record.get("id")));
                r.setTipo(record.get("tipo"));
                r.setVelocidad(Integer.parseInt(record.get("velocidad")));
                r.setId_plantacion(Integer.parseInt(record.get("id_plantacion")));
                riegoLista.add(r);
			}catch (Exception e) {
				logger.error("riegoLista "+e);
       		 	instantiationError.addErrorR(pathError.toString(),e.getMessage());
       		 	return riegoLista; 
				}
            
        }
    }

    return riegoLista;
	}
	
	public ArrayList<tractores> leerCSVT(String pathT, String pathError) throws IOException {
	    ArrayList<tractores> Tractores_Lista = new ArrayList<>();
	    String[] HEADERS = {"id","modelo","velocidad","precio_venta","id_construccion"};

	    try (Reader in = new FileReader(pathT)) {
	        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
	                .setHeader(HEADERS)
	                .setSkipHeaderRecord(true)
	                .build();
	       

	        for (CSVRecord record : csvFormat.parse(in)) {
	        	try {	
	        		tractores t = new tractores();
	            	t.setId(Integer.parseInt(record.get("id")));
	            	t.setModelo(record.get("modelo"));
	            	t.setVelocidad(Integer.parseInt(record.get("velocidad")));
	            	t.setPrecio_venta(Float.parseFloat(record.get("precio_venta")));
	            	t.setId_construccion(Integer.parseInt(record.get("id_construccion")));
	            	Tractores_Lista.add(t);
	        	 } catch (Exception e) {
	        		 logger.error("Tracores "+e);
	        		 instantiationError.addErrorT(pathError.toString(),e.getMessage());
	 				 return Tractores_Lista; 
	        	 }
			}
	       
	    }

	    return Tractores_Lista;
	}

}
