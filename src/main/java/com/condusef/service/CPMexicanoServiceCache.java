package com.condusef.service;

import java.sql.Connection;
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
    Cp data;

    public CPMexicanoServiceCache() {

        try {

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TOP 2000 * FROM CPMexicano");

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
                    resultSet.getString("c_cve_ciudad")


                );

                ListCPMexicanoCache.add(cpMexicano);

                
            }
            data = new Cp( ListCPMexicanoCache );

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