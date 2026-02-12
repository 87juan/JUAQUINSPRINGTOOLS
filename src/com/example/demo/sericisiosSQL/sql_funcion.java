package com.example.demo.sericisiosSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.modelo.construccion;
import com.example.demo.modelo.granjero;
import com.example.demo.modelo.granjeroG;
import com.example.demo.modelo.plantacion;
import com.example.demo.modelo.riegos;
import com.example.demo.modelo.tractores;
import com.example.demo.serviciosCSV.InsertarError;
import com.google.protobuf.TextFormat.ParseException;

public class sql_funcion {
	private static InsertarError instantiationError = new InsertarError();
    //====================SELECT===============================================
	
	public boolean selecExistGranjero(int id,Connection conn,String errorlog) {
		String select="SELECT id FROM granjeros where id like ?";
		
		try(PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1,id);
			ResultSet resultado= ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        }

	    } catch (SQLException e) {
	        if (e.getErrorCode() == 1146) { 
	            // Error MySQL: tabla no existe
	            System.out.println("Tabla 'granjeros' no existe.");
	            instantiationError.addErrorGG(errorlog, "Tabla no existe: " + e.getMessage());
	            return false;  // <--- devolvemos null si la tabla no existe
	        } else {
	            // Otro error de SQL
	            System.out.println("Error al consultar la BD: " + e.getMessage());
	            instantiationError.addErrorGG(errorlog, e.getMessage());
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	        instantiationError.addErrorGG(errorlog, e.getMessage());
	        return false;
	    }
	}
	
	public boolean selecExistGranjeroGranjero(int id_granjero,int id_vecino,Connection conn,String errorlog) {
		 String select = "SELECT * FROM granjero_granjero WHERE id_granjero = ? AND id_vecino = ? LIMIT 1";

		    try (PreparedStatement ps = conn.prepareStatement(select)) {
		        ps.setInt(1, id_granjero);
		        ps.setInt(2, id_vecino);

		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		                // El registro existe
		                return true;
		            } else {
		                // La tabla existe pero el registro no
		                return false;
		            }
		        }

		    } catch (SQLException e) {
		        if (e.getErrorCode() == 1146) { 
		            // Error MySQL: tabla no existe
		            System.out.println("Tabla 'granjero_granjero' no existe.");
		            instantiationError.addErrorGG(errorlog, "Tabla no existe: " + e.getMessage());
		            return false;  // <--- devolvemos null si la tabla no existe
		        } else {
		            // Otro error de SQL
		            System.out.println("Error al consultar la BD: " + e.getMessage());
		            instantiationError.addErrorGG(errorlog, e.getMessage());
		            return false;
		        }
		    } catch (Exception e) {
		        System.out.println("Error inesperado: " + e.getMessage());
		        instantiationError.addErrorGG(errorlog, e.getMessage());
		        return false;
		    }
	}
	
	public boolean selecExistConstruccion(int id,Connection conn, String errorlog) {
		String select="SELECT id FROM construcciones where id like ?";
		
		try(PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1,id);
			ResultSet resultado= ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        }

	    } catch (SQLException e) {
	        if (e.getErrorCode() == 1146) { 

	            System.out.println("Tabla 'construcciones' no existe.");
	            instantiationError.addErrorGG(errorlog, "Tabla no existe: " + e.getMessage());
	            return false;  
	        } else {
	            // Otro error de SQL
	            System.out.println("Error al consultar la BD: " + e.getMessage());
	            instantiationError.addErrorGG(errorlog, e.getMessage());
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	        instantiationError.addErrorGG(errorlog, e.getMessage());
	        return false;
	    }
	}

	public boolean selecExistPlantaciones(int id,Connection conn, String errorlog) {
		String select="SELECT id FROM plantaciones where id like ?";
		

		try(PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1,id);
			ResultSet resultado= ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        }

	    } catch (SQLException e) {
	        if (e.getErrorCode() == 1146) { 

	            System.out.println("Tabla 'plantaciones' no existe.");
	            instantiationError.addErrorGG(errorlog, "Tabla no existe: " + e.getMessage());
	            return false;  
	        } else {
	            // Otro error de SQL
	            System.out.println("Error al consultar la BD: " + e.getMessage());
	            instantiationError.addErrorGG(errorlog, e.getMessage());
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	        instantiationError.addErrorGG(errorlog, e.getMessage());
	        return false;
	    }	
	}
	
	public boolean selecExistRiegos(int id,Connection conn, String errorlog) {
		String select="SELECT id FROM riegos where id like ?";
		
		try(PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1,id);
			ResultSet resultado= ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        }

	    } catch (SQLException e) {
	        if (e.getErrorCode() == 1146) { 

	            System.out.println("Tabla 'Riegos' no existe.");
	            instantiationError.addErrorGG(errorlog, "Tabla no existe: " + e.getMessage());
	            return false;  
	        } else {
	            // Otro error de SQL
	            System.out.println("Error al consultar la BD: " + e.getMessage());
	            instantiationError.addErrorGG(errorlog, e.getMessage());
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	        instantiationError.addErrorGG(errorlog, e.getMessage());
	        return false;
	    }		
	}
	
	public boolean selecExistTractores(int id,Connection conn, String errorlog) {
		String select="SELECT id FROM tractores where id like ?";
		
		try(PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1,id);
			ResultSet resultado= ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return true;
	            } else {
	                return false;
	            }
	        }

	    } catch (SQLException e) {
	        if (e.getErrorCode() == 1146) { 

	            System.out.println("Tabla 'tractores' no existe.");
	            instantiationError.addErrorGG(errorlog, "Tabla no existe: " + e.getMessage());
	            return false;  
	        } else {
	            // Otro error de SQL
	            System.out.println("Error al consultar la BD: " + e.getMessage());
	            instantiationError.addErrorGG(errorlog, e.getMessage());
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	        instantiationError.addErrorGG(errorlog, e.getMessage());
	        return false;
	    }		
	}

    //====================ADD===============================================
	
	public void addGrangero(granjero granjero,Connection conn,String pathError) throws SQLException{
		 String sql = "INSERT INTO granjero (id, nombre, descripcion, dinero, puntos, nivel) VALUES (?, ?, ?, ?, ?, ?)";
		 try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1,granjero.getId());
	            stmt.setString(2, granjero.getNombre());
	            stmt.setString(3, granjero.getDescripcion());
	            stmt.setDouble(4,granjero.getDinero());
	            stmt.setInt(5, granjero.getPuntos());
	            stmt.setInt(6, granjero.getNivel());
	            stmt.executeUpdate();
	        }catch (Exception e) {
	        	System.out.println("AddGranjeroGramjero "+e);
	        	instantiationError.addErrorG(pathError.toString(),e.getMessage());
			}
		 
	}
	
	public void addGranjeroGranjero(granjeroG granjerogranjero, Connection conn, String pathError) throws SQLException {
        String sql = "INSERT INTO farmville.granjero_granjero (id_granjero, id_vecino, puntos_compartidos) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, granjerogranjero.getId_granjero());
            stmt.setInt(2,	granjerogranjero.getId_vecino());
            stmt.setInt(3, granjerogranjero.getPuntos_compartidos());
            stmt.executeUpdate();
        } catch (Exception e) {
        	System.out.println("AddGranjeroGramjero "+e);
        	instantiationError.addErrorGG(pathError.toString(),e.getMessage());
		}
    }
	
	public void addConstruccion(construccion construccion, Connection conn,String pathError) throws SQLException {

	    String sql = "INSERT INTO construcciones (id, nombre, precio, id_granjero) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, construccion.getId());
	        stmt.setString(2, construccion.getNombre());
	        stmt.setDouble(3, construccion.getPrecio());
	        stmt.setInt(4, construccion.getId_granjero());
	        stmt.executeUpdate();
	    } catch (Exception e) {
        	instantiationError.addErrorC(pathError.toString(),e.getMessage());
		}
	}

	public void addPlantacion(plantacion plantacion, Connection conn, String pathError)
	        throws SQLException {

	    String sql = """
	        INSERT INTO plantaciones
	        (id, nombre, precio_compra, precio_venta, proxima_cosecha, id_granjero)
	        VALUES (?, ?, ?, ?, ?, ?)
	        """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, plantacion.getId());
	        stmt.setString(2, plantacion.getNombre());
	        stmt.setDouble(3, plantacion.getPrecio_compra());
	        stmt.setDouble(4, plantacion.getPrecio_venta());
	        stmt.setTimestamp(5, plantacion.getProxima_cosecha());
	        stmt.setInt(6, plantacion.getId_granjero());
	        stmt.executeUpdate();
	    }catch (Exception e) {
        	instantiationError.addErrorP(pathError.toString(),e.getMessage());
		}
	}

	public void addRiego(riegos riego, Connection conn, String pathError) throws SQLException {

	    String sql = "INSERT INTO riegos (id, tipo, velocidad, id_plantacion) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, riego.getId());
	        stmt.setString(2, riego.getTipo());
	        stmt.setDouble(3, riego.getVelocidad());
	        stmt.setInt(4, riego.getId_plantacion());
	        stmt.executeUpdate();
	    }catch (Exception e) {
        	instantiationError.addErrorR(pathError.toString(),e.getMessage());
		}
	}

	public void addTractor(tractores tractor, Connection conn, String pathError) throws SQLException {

	    String sql = "INSERT INTO tractores (id,modelo,velocidad,precio_venta,id_construccion) VALUES (?, ?, ?, ?,?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, tractor.getId());
	        stmt.setString(2, tractor.getModelo());
	        stmt.setDouble(3, tractor.getVelocidad());
	        stmt.setFloat(4, tractor.getPrecio_venta());
	        stmt.setInt(5, tractor.getId_construccion());
	        stmt.executeUpdate();
	    }catch (Exception e) {
        	instantiationError.addErrorR(pathError.toString(),e.getMessage());
		}
	}
    
    //====================UPDATE===============================================
    
	public void updateGranjero(granjero granjero, Connection conn,String pathError) throws SQLException {

	    String sql = """
	        UPDATE granjeros
	        SET nombre = ?, descripcion = ?, dinero = ?, puntos = ?, nivel = ?
	        WHERE id = ?
	        """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, granjero.getNombre());
	        stmt.setString(2, granjero.getDescripcion());
	        stmt.setDouble(3, granjero.getDinero());
	        stmt.setInt(4, granjero.getPuntos());
	        stmt.setInt(5, granjero.getNivel());
	        stmt.setInt(6, granjero.getId());
	        stmt.executeUpdate();
	    }catch (Exception e) {
	    	System.out.println("Error en Update la BD: " + e.getMessage());
        	instantiationError.addErrorG(pathError.toString(),e.getMessage());
		}
	}

    
	public void updateGranjeroGranjero(granjeroG relacion, Connection conn, String pathError) throws SQLException {

	    String sql = """
	        UPDATE granjero_granjero
	        SET puntos_compartidos = ?
	        WHERE id_granjero = ? AND id_vecino = ?
	        """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, relacion.getPuntos_compartidos());
	        stmt.setInt(2, relacion.getId_granjero());
	        stmt.setInt(3, relacion.getId_vecino());
	        stmt.executeUpdate();
	    }catch (Exception e) {
	    	System.out.println("Error en Update la BD: " + e.getMessage());
        	instantiationError.addErrorGG(pathError.toString(),e.getMessage());
		}
	}

    
	public void updateConstruccion(construccion construccion, Connection conn, String pathError) throws SQLException {

	    String sql = """
	        UPDATE construcciones
	        SET nombre = ?, precio = ?, id_granjero = ?
	        WHERE id = ?
	        """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, construccion.getNombre());
	        stmt.setDouble(2, construccion.getPrecio());
	        stmt.setInt(3, construccion.getId_granjero());
	        stmt.setInt(4, construccion.getId());
	        stmt.executeUpdate();
	    }catch (Exception e) {
	    	System.out.println("Error en Update la BD: " + e.getMessage());
        	instantiationError.addErrorC(pathError.toString(),e.getMessage());
		}
	}


	public void updatePlantacion(plantacion plantacion, Connection conn, String pathError) throws SQLException {

	    String sql = """
	        UPDATE plantaciones
	        SET nombre = ?, precio_compra = ?, precio_venta = ?, proxima_cosecha = ?, id_granjero = ?
	        WHERE id = ?
	        """;
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, plantacion.getNombre());
	        stmt.setDouble(2, plantacion.getPrecio_compra());
	        stmt.setDouble(3, plantacion.getPrecio_venta());
	        stmt.setTimestamp(4, plantacion.getProxima_cosecha());
	        stmt.setInt(5, plantacion.getId_granjero());
	        stmt.setInt(6, plantacion.getId());
	        stmt.executeUpdate();
	    }catch (Exception e) {
	    	System.out.println("Error en Update la BD: " + e.getMessage());
        	instantiationError.addErrorP(pathError.toString(),e.getMessage());
		}
	    System.out.println("Id: " +plantacion.getId());
	}

    
	public void updateRiego(riegos riego, Connection conn, String pathError) throws SQLException {

	    String sql = """
	        UPDATE riegos
	        SET tipo = ?, velocidad = ?, id_plantacion = ?
	        WHERE id = ?
	        """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, riego.getTipo());
	        stmt.setDouble(2, riego.getVelocidad());
	        stmt.setInt(3, riego.getId_plantacion());
	        stmt.setInt(4, riego.getId());
	        stmt.executeUpdate();
	    }catch (Exception e) {
	    	System.out.println("Error en Update la BD: " + e.getMessage());
        	instantiationError.addErrorR(pathError.toString(),e.getMessage());
		}
	}
	
	public void updateTractor(tractores tractor, Connection conn, String pathError) throws SQLException {

	    String sql = """
	        UPDATE riegos
	        SET modelo =?, velocidad=? ,precio_venta=? ,id_construccion=?
	        WHERE id = ?
	        """;

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, tractor.getModelo());
	        stmt.setDouble(2, tractor.getVelocidad());
	        stmt.setFloat(3, tractor.getPrecio_venta());
	        stmt.setInt(4, tractor.getId_construccion());
	        stmt.setInt(5, tractor.getId());
	        stmt.executeUpdate();
	    }catch (Exception e) {
	    	System.out.println("Error en Update la BD: " + e.getMessage());
        	instantiationError.addErrorT(pathError.toString(),e.getMessage());
		}
	}

    
  //====================GET SQL===============================================
	public granjero getGranjeroById(int id, Connection conn,String pathError) throws SQLException {
		
	    String sql = "SELECT * FROM granjeros WHERE id = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                granjero g = new granjero();
	                g.setId(rs.getInt("id"));
	                g.setNombre(rs.getString("nombre"));
	                g.setDescripcion(rs.getString("descripcion"));
	                g.setDinero(rs.getFloat("dinero"));
	                g.setPuntos(rs.getInt("puntos"));
	                g.setNivel(rs.getInt("nivel"));
	                return g;
	            }
	        }catch (Exception e) {
	        	instantiationError.addErrorG(pathError.toString(),e.getMessage());
	        	System.out.println(e);
	        }
	    }catch (Exception e) {
        	instantiationError.addErrorG(pathError.toString(),e.getMessage());
        	System.out.println(e);
        }
	    return null;
	}
    
    public granjeroG getGranjeroGranjeroById(int idGranjero, int idVecino, Connection conn, String pathError) throws SQLException {
    String sql = "SELECT * FROM granjero_granjero WHERE id_granjero = ? AND id_vecino = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idGranjero);
        stmt.setInt(2, idVecino);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
            	granjeroG gg = new granjeroG();
                gg.setId_granjero(rs.getInt("id_granjero"));
                gg.setId_vecino(rs.getInt("id_vecino"));
                gg.setPuntos_compartidos(rs.getInt("puntos_compartidos"));
                return gg;
            }
        }catch (Exception e) {
	        	instantiationError.addErrorGG(pathError.toString(),e.getMessage());
	        	System.out.println(e);
	        }
	    }catch (Exception e) {
        	instantiationError.addErrorGG(pathError.toString(),e.getMessage());
        	System.out.println(e);
        }
    return null;
    }

    public construccion getConstruccionById(int id, Connection conn, String pathError) throws SQLException {

        String sql = "SELECT * FROM construcciones WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    construccion c = new construccion();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setPrecio(rs.getFloat("precio"));
                    c.setId_granjero(rs.getInt("id_granjero"));
                    return c;
                }
            }catch (Exception e) {
	        	instantiationError.addErrorC(pathError.toString(),e.getMessage());
	        	System.out.println(e);
	        }
	    }catch (Exception e) {
        	instantiationError.addErrorC(pathError.toString(),e.getMessage());
        	System.out.println(e);
        }
        return null;
    }

    public plantacion getPlantacionById(int id, Connection conn, String pathError) throws SQLException {

        String sql = "SELECT * FROM plantaciones WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    plantacion p = new plantacion();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio_compra(rs.getFloat("precio_compra"));
                    p.setPrecio_venta(rs.getFloat("precio_venta"));
                    p.setProxima_cosecha(rs.getTimestamp("proxima_cosecha"));
                    p.setId_granjero(rs.getInt("id_granjero"));
                    return p;
                }
            }catch (Exception e) {
	        	instantiationError.addErrorP(pathError.toString(),e.getMessage());
	        	System.out.println(e);
	        }
	    }catch (Exception e) {
        	instantiationError.addErrorP(pathError.toString(),e.getMessage());
        	System.out.println(e);
        }
        return null;
    }

    public riegos getRiegoById(int id, Connection conn, String pathError) throws SQLException {

        String sql = "SELECT * FROM riegos WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    riegos r = new riegos();
                    r.setId(rs.getInt("id"));
                    r.setTipo(rs.getString("tipo"));
                    r.setVelocidad(rs.getInt("velocidad"));
                    r.setId_plantacion(rs.getInt("id_plantacion"));
                    return r;
                }
            }catch (Exception e) {
	        	instantiationError.addErrorR(pathError.toString(),e.getMessage());
	        	System.out.println(e);
	        }
	    }catch (Exception e) {
        	instantiationError.addErrorR(pathError.toString(),e.getMessage());
        	System.out.println(e);
        }
        return null;
    }
    
    public tractores getTractorById(int id, Connection conn, String pathError) throws SQLException {

        String sql = "SELECT * FROM tractores WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tractores r = new tractores();
                    r.setId(rs.getInt("id"));
                    r.setModelo(rs.getString("modelo"));
                    r.setVelocidad(rs.getInt("velocidad"));
                    r.setPrecio_venta(rs.getFloat("precio_venta"));
                    r.setId_construccion(rs.getInt("id_construccion"));
                    return r;
                }
            }catch (Exception e) {
	        	instantiationError.addErrorT(pathError.toString(),e.getMessage());
	        	System.out.println(e);
	        }
	    }catch (Exception e) {
        	instantiationError.addErrorT(pathError.toString(),e.getMessage());
        	System.out.println(e);
        }
        return null;
    }

 
}
