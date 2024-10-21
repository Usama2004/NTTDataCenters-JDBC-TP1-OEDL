package com.nttdata.nttdatacenters_jdbc_t1_OEDL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Connection conexion = conectarBaseDatos();
		if (conexion != null) {
			System.out.println("Conexión establecida con éxito.");
			try {
				// Realizar una consulta con PreparedStatement
				String sql = "SELECT id_employee, name, rol, id_service FROM nttdata_t1mysql_employees";
				PreparedStatement pstmt = conexion.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				// Mostrar los resultados de la consulta
                while (rs.next()) {
                    int id_employee = rs.getInt("id_employee");
                    String name = rs.getString("name");
                    String rol = rs.getString("rol");
                    int id_service = rs.getInt("id_service");
                    System.out.println("{ID: " + id_employee + ", Nombre: " + name + ", Puesto: " + rol + "ID Del Servicio: "+id_service);
                }
                
             // Cerrar recursos
                rs.close();
                pstmt.close();
                conexion.close(); // Cierra la conexión cuando hayas terminado
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e.getMessage());
			}
		} else {
			System.out.println("Error al establecer la conexión.");
		}
	}

	private static Connection conectarBaseDatos() {
		String url = "jdbc:mysql://localhost:3306/nttdata_hibernate_taller1";
		String usuario = "root";
		String clave = "";

		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, usuario, clave);
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
		return conexion;
	}
}
