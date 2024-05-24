package com.condusef.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.condusef.database.DatabaseConnection;
import com.condusef.models.CPMexicanoCache;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class CPMexicanoServiceCache {

    List<CPMexicanoCache> ListCPMexicanoCache = new ArrayList<>();

    public CPMexicanoServiceCache() {

        try {

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TOP 50 * FROM CPMexicano");

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

    // getters

    public List<CPMexicanoCache> getAllCPMexicanoCache() {
        return ListCPMexicanoCache;
    }

    public CPMexicanoCache getCPMexicanoCacheByCodigo(String d_codigo) {
        for (CPMexicanoCache cpMexicano : ListCPMexicanoCache) {
            if (cpMexicano.d_codigo().equals(d_codigo)) {
                return cpMexicano;
            }
        }
        return null;
    }

    public List<CPMexicanoCache> getCPMexicanoCacheByEstado(String d_estado) {
        List<CPMexicanoCache> cpMexicanoByEstado = new ArrayList<>();
        for (CPMexicanoCache cpMexicano : ListCPMexicanoCache) {
            if (cpMexicano.d_estado().equals(d_estado)) {
                cpMexicanoByEstado.add(cpMexicano);
            }
        }
        return cpMexicanoByEstado;
    }

    public List<CPMexicanoCache> getCPMexicanoCacheByMunicipio(String D_mnpio) {
        List<CPMexicanoCache> cpMexicanoByMunicipio = new ArrayList<>();
        for (CPMexicanoCache cpMexicano : ListCPMexicanoCache) {
            if (cpMexicano.D_mnpio().equals(D_mnpio)) {
                cpMexicanoByMunicipio.add(cpMexicano);
            }
        }
        return cpMexicanoByMunicipio;
    }

    public List<CPMexicanoCache> getCPMexicanoCacheByAsenta(String d_asenta) {
        List<CPMexicanoCache> cpMexicanoByAsenta = new ArrayList<>();
        for (CPMexicanoCache cpMexicano : ListCPMexicanoCache) {
            if (cpMexicano.d_asenta().equals(d_asenta)) {
                cpMexicanoByAsenta.add(cpMexicano);
            }
        }
        return cpMexicanoByAsenta;
    }

    // setters

}