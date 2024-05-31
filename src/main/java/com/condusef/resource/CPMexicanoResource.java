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
    @Description("Obtener todos los CP de Mexico")
    public List<CPMexicano> getAllCPMexicano() {
        return service.getAllCPMexicano();
    }

    @Query
    @Description("Obtener un CP de Mexico por Codigo")
    public CPMexicano getCPMexicanoByCodigo(@Name("Codigo") String codigo) {
        return service.getCPMexicanoByCodigo(codigo);
    }

    @Query
    @Description("Obtener un CP de Mexico por Estado")
    public List<CPMexicano> getCPMexicanoByEstado(@Name("Estado") String estado) {
        return service.getCPMexicanoByEstado(estado);
    }

    @Query
    @Description("Obtener un CP de Mexico por Municipio")
    public List<CPMexicano> getCPMexicanoByMunicipio(@Name("Municipio") String municipio) {
        return service.getCPMexicanoByMunicipio(municipio);
    }

    @Query
    @Description("Obtener un CP de Mexico por Asenta")
    public List<CPMexicano> getCPMexicanoByAsenta(@Name("Asenta") String asenta) {
        return service.getCPMexicanoByAsenta(asenta);
    }
    



}
