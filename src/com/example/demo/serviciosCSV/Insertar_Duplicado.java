package com.example.demo.serviciosCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.example.demo.modelo.construccion;
import com.example.demo.modelo.granjero;
import com.example.demo.modelo.granjeroG;
import com.example.demo.modelo.plantacion;
import com.example.demo.modelo.riegos;
import com.example.demo.modelo.tractores;

public class Insertar_Duplicado {
	public void addDuplicadoG(String duplicadosLog, granjero granjero) {
		try {
			Files.write(
            Paths.get(duplicadosLog),
            	("Granjero: " +
            		granjero.getId() + " " +
             	granjero.getNombre() + " " +
             	granjero.getDescripcion() + " " +
             	granjero.getDinero() + " " +
             	granjero.getPuntos() + " " +
             	granjero.getNivel() +
             	System.lineSeparator()).getBytes(),
            	StandardOpenOption.CREATE,
           		StandardOpenOption.APPEND
        		);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	public void addDuplicadoGG(String duplicadosLog, granjeroG granjero_granjero) {
    try {
        Files.write(
            Paths.get(duplicadosLog),
            ("Granjero Granjero: " +
             granjero_granjero.getId_granjero() + " " +
             granjero_granjero.getId_vecino() + " " +
             granjero_granjero.getPuntos_compartidos() +
             System.lineSeparator()).getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
	
	public void addDuplicadoC(String duplicadosLog, construccion construccione) {
	    try {
	        Files.write(
	            Paths.get(duplicadosLog),
	            ("Construccion: " +
	             construccione.getId() + " " +
	             construccione.getNombre() + " " +
	             construccione.getPrecio() + " " +
	             construccione.getId_granjero() +
	             System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addDuplicadoP(String duplicadosLog, plantacion plantacion) {
	    try {
	        Files.write(
	            Paths.get(duplicadosLog),
	            ("Plantacion: " +
	             plantacion.getId() + " " +
	             plantacion.getNombre() + " " +
	             plantacion.getPrecio_compra() + " " +
	             plantacion.getPrecio_venta() + " " +
	             plantacion.getProxima_cosecha() + " " +
	             plantacion.getId_granjero() +
	             System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addDuplicadoR(String duplicadosLog, riegos riego) {
		try {
        Files.write(
            Paths.get(duplicadosLog),
            ("Riego: " +
             riego.getId() + " " +
             riego.getTipo() + " " +
             riego.getVelocidad() + " " +
             riego.getId_plantacion() +
             System.lineSeparator()).getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        		);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public void addDuplicadoT(String duplicadosLog, tractores tractor) {
		try {
        Files.write(
            Paths.get(duplicadosLog),
            ("Tractor: " +
            		tractor.getId() + " " +
            		tractor.getModelo() + " " +
            		tractor.getVelocidad() + " " +
            		tractor.getPrecio_venta() + " " +
            		tractor.getId_construccion() +
             System.lineSeparator()).getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        		);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}
