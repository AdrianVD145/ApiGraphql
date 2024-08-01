package com.condusef.resource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.condusef.database.DatabaseConnection;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {


        String Registro = "no hay registros";

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Catalog.CPMexicano LIMIT 1");
            
                while (resultSet.next()) {      
                Registro = resultSet.getString("d_codigo") + " " + 
                resultSet.getString("d_asenta") + " " + 
                resultSet.getString("d_tipo_asenta") + " " + 
                resultSet.getString("D_mnpio") + " " + 
                resultSet.getString("d_estado") + " " + 
                resultSet.getString("d_ciudad") + " " + 
                resultSet.getString("d_CP") + " " + 
                resultSet.getString("c_estado") + " " + 
                resultSet.getString("c_oficina") + " " + 
                resultSet.getString("c_CP") + " " + 
                resultSet.getString("c_tipo_asenta") + " " + 
                resultSet.getString("c_mnpio") + " " + 
                resultSet.getString("id_asenta_cpcons") + " " + 
                resultSet.getString("d_zona") + " " + 
                resultSet.getString("c_cve_ciudad");
            }
        
            
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al conectar a la base de datos :(" + e.getMessage();
        } finally {
            try {
                DatabaseConnection.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return "Error al cerrar la conexión a la base de datos"+ ex.getMessage();
            }
        }
        
        return Registro;
    }

}