package com.condusef.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.condusef.database.DatabaseConnection;
import com.condusef.models.CPMexicanoCache;
import com.condusef.models.Cp;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CPMexicanoServiceCache {
    

    

    public CPMexicanoServiceCache() {

    }

    public Cp ConsultarCp(String EstadoCp) {


        List<CPMexicanoCache> ListCPMexicanoCache = new ArrayList<>();
        Cp data = null;

        try {

            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM CPMexicano WHERE d_estado = ?");
            statement.setString(1, EstadoCp);
            ResultSet resultSet = statement.executeQuery();

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

            data = new Cp(ListCPMexicanoCache);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                DatabaseConnection.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return data;
    }


}