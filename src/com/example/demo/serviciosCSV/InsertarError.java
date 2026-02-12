package com.example.demo.serviciosCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import com.example.demo.modelo.construccion;
import com.example.demo.modelo.granjero;
import com.example.demo.modelo.granjeroG;
import com.example.demo.modelo.plantacion;
import com.example.demo.modelo.riegos;

public class InsertarError { 
	public void addErrorG(String erroresLog, String error) {
		LocalTime horaActual = LocalTime.now();
	    try {
	        Files.write(
	            Paths.get(erroresLog),
	            ( 	horaActual + " "+
	            	"Granjero: " +
	            		error+
	             System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addErrorGG(String erroresLog, String error) {
		LocalTime horaActual = LocalTime.now();
		System.out.println(erroresLog);
	    try {
	        Files.write(
	            Paths.get(erroresLog),
	            (		horaActual + " "+
	            		"Granjero Granjero: " +
	            		error+
	             System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addErrorC(String erroresLog,  String error) {
		LocalTime horaActual = LocalTime.now();
	    try {
	        Files.write(
	            Paths.get(erroresLog),
	            (horaActual + " "+
	            "Construccion: " +
	            error+
	             System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addErrorP(String erroresLog, String error) {
		LocalTime horaActual = LocalTime.now();
	    try {
	        Files.write(
	            Paths.get(erroresLog),
	            (horaActual+
	            "Plantacion: " +
	            error+
	            System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addErrorR(String erroresLog, String error) {
		LocalTime horaActual = LocalTime.now();
	    try {
	        Files.write(
	            Paths.get(erroresLog),
	            (horaActual+
	            "Riego: " +
	            error +
	            System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addErrorT(String erroresLog, String error) {
		LocalTime horaActual = LocalTime.now();
	    try {
	        Files.write(
	            Paths.get(erroresLog),
	            (horaActual+
	            "Tractor: " +
	            error +
	            System.lineSeparator()).getBytes(),
	            StandardOpenOption.CREATE,
	            StandardOpenOption.APPEND
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
