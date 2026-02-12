package com.example.demo;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.example.demo.modelo.construccion;
import com.example.demo.modelo.granjero;
import com.example.demo.modelo.granjeroG;
import com.example.demo.modelo.plantacion;
import com.example.demo.modelo.riegos;
import com.example.demo.modelo.tractores;
import com.example.demo.sericisiosSQL.sql_funcion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import com.example.demo.serviciosCSV.leercsv;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.example.demo.serviciosCSV.InsertarError;
import com.example.demo.serviciosCSV.Insertar_Duplicado;
public class Inicio {
	private static sql_funcion sqlFuncion = new sql_funcion();
	private static leercsv leerCSV = new leercsv();
	private static Insertar_Duplicado insertarDuplicado = new Insertar_Duplicado();
	private static InsertarError instantiationError = new InsertarError();
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args)throws Exception {
		final Logger logger = LoggerFactory.getLogger(Inicio.class);
		String configFilePath ="C://Users/juan.lopez.de.la.pla/Documents/JuaquinTareaFarmville/config.properties";
		Properties props = new Properties();
		
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
        }

        String csvPath = props.getProperty("csv.path");
        Path csvG = Paths.get(csvPath, "granjeros.csv");
        Path csvGG = Paths.get(csvPath, "granjero_granjero.csv");
        Path csvC = Paths.get(csvPath, "construcciones.csv");
        Path csvP = Paths.get(csvPath, "plantaciones.csv");
        Path csvR = Paths.get(csvPath, "riegos.csv");
        Path csvT = Paths.get(csvPath, "tractores.csv");
        String erroresLog = props.getProperty("log.errores");
        String duplicadosLog = props.getProperty("log.duplicados");
        
        Connection conn = DriverManager.getConnection(
        	    props.getProperty("db.url"),
        	    props.getProperty("db.user"),
        	    props.getProperty("db.password")
        	);

       
      
       

       ArrayList<granjeroG> gGCSV = leerCSV.leerCSVGG(csvGG.toString(),erroresLog); 
       ArrayList<granjero> gCSV =leerCSV.leerCSVG(csvG.toString(),erroresLog);
       ArrayList<construccion> cCSV =leerCSV.leerCSVC(csvC.toString(),erroresLog);
       ArrayList<plantacion> pCSV =leerCSV.leerCSVP(csvP.toString(),erroresLog);
       ArrayList<riegos> rCSV =leerCSV.leerCSVR(csvR.toString(),erroresLog);
       ArrayList<tractores> tCSV =leerCSV.leerCSVT(csvT.toString(),erroresLog);
       
       Savepoint spGranjeros = conn.setSavepoint();
       logger.info("=======================Granjero=======================");
       try {
    	conn.setAutoCommit(false);
   
    	for (granjero granjeros : gCSV) {
           	if(sqlFuncion.selecExistGranjero(granjeros.getId(), conn, erroresLog)) {
           		granjero g2 = sqlFuncion.getGranjeroById(granjeros.getId(), conn,erroresLog);
           		if(
           				granjeros.getDinero().equals(g2.getDinero()) &&
           				granjeros.getNombre().equals(g2.getNombre()) &&
           				granjeros.getPuntos()==(g2.getPuntos()) &&
           				granjeros.getNivel()==(g2.getNivel()) &&
           				granjeros.getDescripcion().equals(g2.getDescripcion())
           				
           				) {
           			insertarDuplicado.addDuplicadoG(duplicadosLog, granjeros);
           			logger.info("Duplicado: "+granjeros);
           		}else {
           			sqlFuncion.updateGranjero(granjeros, conn,erroresLog);
           			conn.commit();
           			logger.info("Update:"+ granjeros );
				}
           	}else {
           		sqlFuncion.addGrangero(granjeros, conn, erroresLog);
           		conn.commit();
           		logger.info("Add:"+ granjeros );
			}
   			
    	}
		
       } catch (Exception e) {
    	   logger.error(e.getMessage());
    	   conn.rollback(spGranjeros);
       }
       
       Savepoint spPlantacion = conn.setSavepoint();
       logger.info("=======================Plantaciones =======================");
       try {
    	conn.setAutoCommit(false);
    	for (plantacion p : pCSV) {
           	if(sqlFuncion.selecExistPlantaciones(p.getId(), conn, erroresLog)) {
           		plantacion p2 = sqlFuncion.getPlantacionById(p.getId(), conn,erroresLog);
           		if(
           			p.getId()==(p2.getId()) &&
                   	p.getId_granjero()==(p2.getId_granjero()) &&
                    p.getNombre().equalsIgnoreCase(p2.getNombre())&&
                    p.getPrecio_compra().equals(p2.getPrecio_compra())&&
                    p.getPrecio_venta().equals(p2.getPrecio_venta())&&
                    p.getProxima_cosecha().equals(p2.getProxima_cosecha())) 
           		{
           			insertarDuplicado.addDuplicadoP(duplicadosLog, p);
           			logger.info("Duplicado: "+p);
           		}else {
           			sqlFuncion.updatePlantacion(p, conn, duplicadosLog);
           			conn.commit();
           			logger.info("Update:"+ p );
				}
           	}else {
           		sqlFuncion.addPlantacion(p, conn, duplicadosLog);
           		conn.commit();
           		logger.info("Add:"+ p);
			}
   			
    	}
		
       } catch (Exception e) {
    	   logger.error(e.getMessage());
    	   conn.rollback(spPlantacion);
       }
       
       Savepoint spRiego = conn.setSavepoint();
       logger.info("=======================Riegos =======================");
       try {
    	conn.setAutoCommit(false);
    	for (riegos r : rCSV) {
           	if(sqlFuncion.selecExistRiegos(r.getId(), conn, erroresLog)) {
           		riegos r2 = sqlFuncion.getRiegoById(r.getId(), conn,erroresLog);
           		if(
           			r.getId()==(r2.getId()) &&
                   	r.getTipo().equals(r2.getTipo()) &&
                    r.getId_plantacion()==(r2.getId_plantacion())&&
                    r.getVelocidad()==(r2.getVelocidad())) 
           		{
           			insertarDuplicado.addDuplicadoR(duplicadosLog, r);
           			logger.info("Duplicado: "+r);
           		}else {
           			sqlFuncion.updateRiego(r, conn, duplicadosLog);
           			conn.commit();
           			logger.info("Update:"+ r );
				}
           	}else {
           		sqlFuncion.addRiego(r, conn, duplicadosLog);
           		conn.commit();
           		logger.info("Add:"+ r);
			}
   			
    	}
		
       } catch (Exception e) {
    	   logger.error(e.getMessage());
    	   conn.rollback(spRiego);
       }
       
       Savepoint spConstrucciones = conn.setSavepoint();
       logger.info("=======================Construcciones=======================");
       try {
    	conn.setAutoCommit(false);
    	for (construccion c : cCSV) {
           	if(sqlFuncion.selecExistConstruccion(c.getId(), conn, erroresLog)) {
           		construccion c2 = sqlFuncion.getConstruccionById(c.getId(), conn,erroresLog);
           		if(
           			c.getId()==(c2.getId()) &&
                   	c.getNombre().equals(c2.getNombre()) &&
                    c.getPrecio().equals(c2.getPrecio())&&
                    c.getId_granjero()==(c2.getId_granjero())) 
           		{
           			insertarDuplicado.addDuplicadoC(duplicadosLog, c);
           			logger.info("Duplicado: "+c);
           		}else {
           			sqlFuncion.updateConstruccion(c, conn, duplicadosLog);
           			conn.commit();
           			logger.info("Update:"+ c);
				}
           	}else {
           		sqlFuncion.addConstruccion(c, conn, duplicadosLog);
           		conn.commit();
           		logger.info("Add:"+ c);
			}
   			
    	}
		
       } catch (Exception e) {
    	   logger.error(e.getMessage());
    	   conn.rollback(spConstrucciones);
       }
       
       Savepoint spTractores = conn.setSavepoint();
       logger.info("=======================Tractores=======================");
       try {
    	conn.setAutoCommit(false);
    	for (tractores t : tCSV) {
           	if(sqlFuncion.selecExistTractores(t.getId(), conn, erroresLog)) {
           		tractores t2 = sqlFuncion.getTractorById(t.getId(), conn,erroresLog);
           		if(
           			t.getId()==(t2.getId()) &&
                   	t.getModelo().equals(t2.getModelo()) &&
                    t.getVelocidad()==(t2.getVelocidad())&&
                    t.getId_construccion()==(t2.getId_construccion())&&
                    t.getPrecio_venta().equals(t2.getPrecio_venta())) 
           		{
           			insertarDuplicado.addDuplicadoT(duplicadosLog, t);
           			logger.info("Duplicado: "+t);
           		}else {
           			sqlFuncion.updateTractor(t, conn, duplicadosLog);
           			conn.commit();
           			logger.info("Update:"+ t);
				}
           	}else {
           		sqlFuncion.addTractor(t, conn, duplicadosLog);
           		conn.commit();
           		logger.info("Add:"+ t);
			}
   			
    	}
		
       } catch (Exception e) {
    	   logger.error(e.getMessage());
    	   conn.rollback(spTractores);
       }
       
       Savepoint spGranjeroG = conn.setSavepoint();
       logger.info("=======================Granjero Granjero =======================");
       try {
    	conn.setAutoCommit(false);
    	
    	for (granjeroG granjerosG : gGCSV) {
    		if(sqlFuncion.selecExistGranjeroGranjero(granjerosG.getId_granjero(),granjerosG.getId_vecino(), conn, erroresLog)) {
           		granjeroG g2 = sqlFuncion.getGranjeroGranjeroById(granjerosG.getId_granjero(),granjerosG.getId_vecino(), conn,erroresLog);
           		if(
           				granjerosG.getId_granjero()==(g2.getId_granjero()) &&
           				granjerosG.getId_vecino()==(g2.getId_vecino()) &&
           				granjerosG.getPuntos_compartidos()==(g2.getPuntos_compartidos())
           				) {
           			insertarDuplicado.addDuplicadoGG(duplicadosLog, granjerosG);
           			logger.info("Duplicado"+ granjerosG);
           		}else {
           			sqlFuncion.updateGranjeroGranjero(granjerosG, conn, erroresLog);
           			conn.commit();
           			logger.info("Update:"+ granjerosG);
				}
           	}else {
           		sqlFuncion.addGranjeroGranjero(granjerosG, conn, erroresLog);
           		conn.commit();
           		logger.info("Add:"+ granjerosG);
			}
   			
    	}
		
       } catch (Exception e) {
    	   logger.error(e.getMessage());
    	   conn.rollback(spGranjeroG);
       }
        
	}

}
