package com.condusef.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.condusef.database.DatabaseConnection;
import com.condusef.models.CPMexicanoCache;
import com.condusef.models.Cp;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CPMexicanoServiceCache {

    List<CPMexicanoCache> ListCPMexicanoCache = new ArrayList<>();
    List<Cp> ListCpEstados = new ArrayList<>();

    String[] estados = {"Aguascalientes", 
    "Baja California", 
    "Baja California Sur", 
    "Campeche", 
    "Chiapas", 
    "Chihuahua", 
    "Ciudad de México", 
    "Coahuila de Zaragoza", 
    "Colima", 
    "Durango", 
    "Guanajuato", 
    "Guerrero", 
    "Hidalgo", 
    "Jalisco", 
    "México", 
    "Michoacán de Ocampo", 
    "Morelos", 
    "Nayarit", 
    "Nuevo León", 
    "Oaxaca", 
    "Puebla", 
    "Querétaro", 
    "Quintana Roo", 
    "San Luis Potosí", 
    "Sinaloa", 
    "Sonora", 
    "Tabasco", 
    "Tamaulipas", 
    "Tlaxcala", 
    "Veracruz de Ignacio de la Llave", 
    "Yucatán", 
    "Zacatecas"};

    public CPMexicanoServiceCache() {

        try {

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            
            for (String estado : estados) {
                String query = "SELECT * FROM CPMexicano WHERE d_estado = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, estado);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    

                    CPMexicanoCache cpMexicano = new CPMexicanoCache( 
                        resultSet.getString("d_codigo"),
                        resultSet.getString("d_asenta"),
                        resultSet.getString("d_tipo_asenta"),
                        resultSet.getString("D_mnpio"),
                        resultSet.getString("d_estado"),
                        resultSet.getString("d_ciudad"),
                        resultSet.getString("d_CP"),
                        resultSet.getString("c_estado"),
                        resultSet.getString("c_oficina"),
                        resultSet.getString("c_CP"),
                        resultSet.getString("c_tipo_asenta"),
                        resultSet.getString("c_mnpio"),
                        resultSet.getString("id_asenta_cpcons"),
                        resultSet.getString("d_zona"),
                        resultSet.getString("c_cve_ciudad") );

                ListCPMexicanoCache.add(cpMexicano);

                
            }

            ListCpEstados.add(new Cp(ListCPMexicanoCache));
        }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

  //getters
    
        public Cp getAllCPMexicanoCache() {
            return data;
        }

        

  //setters

}