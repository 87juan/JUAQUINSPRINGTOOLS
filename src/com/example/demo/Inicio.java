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
		// TODO Auto-generated method stub
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
       System.out.println("=======================Granjero=======================");
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
           			System.out.println("Duplicado ====");
           		}else {
           			sqlFuncion.updateGranjero(granjeros, conn,erroresLog);
           			conn.commit();
           			System.out.println("Update:"+ granjeros );
				}
           	}else {
           		sqlFuncion.addGrangero(granjeros, conn, erroresLog);
           		conn.commit();
           		System.out.println("Add:"+ granjeros );
			}
   			
    	}
		
       } catch (Exception e) {
    	   System.out.println(e);
    	   conn.rollback(spGranjeros);
       }
       
       Savepoint spPlantacion = conn.setSavepoint();
       System.out.println("=======================Plantaciones =======================");
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
           			System.out.println("Duplicado ====");
           		}else {
           			sqlFuncion.updatePlantacion(p, conn, duplicadosLog);
           			conn.commit();
           			System.out.println("Update:"+ p );
				}
           	}else {
           		sqlFuncion.addPlantacion(p, conn, duplicadosLog);
           		conn.commit();
           		System.out.println("Add:"+ p);
			}
   			
    	}
		
       } catch (Exception e) {
    	   System.out.println(e);
    	   conn.rollback(spPlantacion);
       }
       
       Savepoint spRiego = conn.setSavepoint();
       System.out.println("=======================Riegos =======================");
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
           			System.out.println("Duplicado ====");
           		}else {
           			sqlFuncion.updateRiego(r, conn, duplicadosLog);
           			conn.commit();
           			System.out.println("Update:"+ r );
				}
           	}else {
           		sqlFuncion.addRiego(r, conn, duplicadosLog);
           		conn.commit();
           		System.out.println("Add:"+ r);
			}
   			
    	}
		
       } catch (Exception e) {
    	   System.out.println(e);
    	   conn.rollback(spRiego);
       }
       
       Savepoint spConstrucciones = conn.setSavepoint();
       System.out.println("=======================Construcciones=======================");
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
           			System.out.println("Duplicado ====");
           		}else {
           			sqlFuncion.updateConstruccion(c, conn, duplicadosLog);
           			conn.commit();
           			System.out.println("Update:"+ c);
				}
           	}else {
           		sqlFuncion.addConstruccion(c, conn, duplicadosLog);
           		conn.commit();
           		System.out.println("Add:"+ c);
			}
   			
    	}
		
       } catch (Exception e) {
    	   System.out.println(e);
    	   conn.rollback(spConstrucciones);
       }
       
       Savepoint spTractores = conn.setSavepoint();
       System.out.println("=======================Tractores=======================");
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
           			System.out.println("Duplicado ====");
           		}else {
           			sqlFuncion.updateTractor(t, conn, duplicadosLog);
           			conn.commit();
           			System.out.println("Update:"+ t);
				}
           	}else {
           		sqlFuncion.addTractor(t, conn, duplicadosLog);
           		conn.commit();
           		System.out.println("Add:"+ t);
			}
   			
    	}
		
       } catch (Exception e) {
    	   System.out.println(e);
    	   conn.rollback(spTractores);
       }
       
       Savepoint spGranjeroG = conn.setSavepoint();
       System.out.println("=======================Granjero Granjero =======================");
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
           			System.out.println("Duplicado ====");
           		}else {
           			sqlFuncion.updateGranjeroGranjero(granjerosG, conn, erroresLog);
           			conn.commit();
           			System.out.println("Update:"+ granjerosG );
				}
           	}else {
           		sqlFuncion.addGranjeroGranjero(granjerosG, conn, erroresLog);
           		conn.commit();
           		System.out.println("Add:"+ granjerosG);
			}
   			
    	}
		
       } catch (Exception e) {
    	   System.out.println(e);
    	   conn.rollback(spGranjeroG);
       }
        
	}

}
