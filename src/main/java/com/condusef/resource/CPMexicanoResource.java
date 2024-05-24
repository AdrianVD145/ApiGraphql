package com.condusef.resource;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.condusef.models.CPMexicano;
import com.condusef.service.CPMexicanoService;

import jakarta.inject.Inject;

@GraphQLApi
public class CPMexicanoResource {
    
    @Inject
    CPMexicanoService service;

    @Query("allCPMexicano")
    @Description("Get all CPMexicano from Mexico")
    public List<CPMexicano> getAllCPMexicano() {
        return service.getAllCPMexicano();
    }

    @Query
    @Description("Get a CPMexicano by Codigo from Mexico")
    public CPMexicano getCPMexicanoByCodigo(@Name("Codigo") String codigo) {
        return service.getCPMexicanoByCodigo(codigo);
    }

    @Query
    @Description("Get a CPMexicano by Estadofrom Mexico")
    public List<CPMexicano> getCPMexicanoByEstado(@Name("Estado") String estado) {
        return service.getCPMexicanoByEstado(estado);
    }

    @Query
    @Description("Get a CPMexicano by Municipio from Mexico")
    public List<CPMexicano> getCPMexicanoByMunicipio(@Name("Municipio") String municipio) {
        return service.getCPMexicanoByMunicipio(municipio);
    }

    @Query
    @Description("Get a CPMexicano by Asenta from Mexico")
    public List<CPMexicano> getCPMexicanoByAsenta(@Name("Asenta") String asenta) {
        return service.getCPMexicanoByAsenta(asenta);
    }
    



}
