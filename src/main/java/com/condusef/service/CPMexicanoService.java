package com.condusef.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.condusef.database.DatabaseConnection;
import com.condusef.models.CPMexicano;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class CPMexicanoService {

    List<CPMexicano> ListCPMexicano = new ArrayList<>();

    public CPMexicanoService() {

        try {

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TOP 50 * FROM CPMexicano");

            while (resultSet.next()) {
                CPMexicano cpMexicano = new CPMexicano();
                cpMexicano.d_codigo = resultSet.getString("d_codigo");
                cpMexicano.d_asenta = resultSet.getString("d_asenta");
                cpMexicano.d_tipo_asenta = resultSet.getString("d_tipo_asenta");
                cpMexicano.D_mnpio = resultSet.getString("D_mnpio");
                cpMexicano.d_estado = resultSet.getString("d_estado");
                cpMexicano.d_ciudad = resultSet.getString("d_ciudad");
                cpMexicano.d_CP = resultSet.getString("d_CP");
                cpMexicano.c_estado = resultSet.getString("c_estado");
                cpMexicano.c_oficina = resultSet.getString("c_oficina");
                cpMexicano.c_CP = resultSet.getString("c_CP");
                cpMexicano.c_tipo_asenta = resultSet.getString("c_tipo_asenta");
                cpMexicano.c_mnpio = resultSet.getString("c_mnpio");
                cpMexicano.id_asenta_cpcons = resultSet.getString("id_asenta_cpcons");
                cpMexicano.d_zona = resultSet.getString("d_zona");
                cpMexicano.c_cve_ciudad = resultSet.getString("c_cve_ciudad");

                ListCPMexicano.add(cpMexicano);
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

    public List<CPMexicano> getAllCPMexicano() {
        return ListCPMexicano;
    }

    public CPMexicano getCPMexicanoByCodigo(String d_codigo) {
        for (CPMexicano cpMexicano : ListCPMexicano) {
            if (cpMexicano.d_codigo.equals(d_codigo)) {
                return cpMexicano;
            }
        }
        return null;
    }

    public List<CPMexicano> getCPMexicanoByEstado(String d_estado) {
        List<CPMexicano> cpMexicanoByEstado = new ArrayList<>();
        for (CPMexicano cpMexicano : ListCPMexicano) {
            if (cpMexicano.d_estado.equals(d_estado)) {
                cpMexicanoByEstado.add(cpMexicano);
            }
        }
        return cpMexicanoByEstado;
    }

    public List<CPMexicano> getCPMexicanoByMunicipio(String D_mnpio) {
        List<CPMexicano> cpMexicanoByMunicipio = new ArrayList<>();
        for (CPMexicano cpMexicano : ListCPMexicano) {
            if (cpMexicano.D_mnpio.equals(D_mnpio)) {
                cpMexicanoByMunicipio.add(cpMexicano);
            }
        }
        return cpMexicanoByMunicipio;
    }

    public List<CPMexicano> getCPMexicanoByAsenta(String d_asenta) {
        List<CPMexicano> cpMexicanoByAsenta = new ArrayList<>();
        for (CPMexicano cpMexicano : ListCPMexicano) {
            if (cpMexicano.d_asenta.equals(d_asenta)) {
                cpMexicanoByAsenta.add(cpMexicano);
            }
        }
        return cpMexicanoByAsenta;
    }

    // setters



}