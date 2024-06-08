package com.condusef.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.infinispan.client.hotrod.RemoteCache;

import com.condusef.models.CPMexicanoCache;
import com.condusef.models.Cp;
import com.condusef.service.CPMexicanoServiceCache;

import io.quarkus.infinispan.client.Remote;
import jakarta.inject.Inject;

@GraphQLApi
public class CPMexicanoResourceCache {

    @Inject
    CPMexicanoServiceCache service;

    @Inject
    @Remote("CpMexicano")
    RemoteCache<Integer, Cp> cache;

    
    int[] KeysEstados = { 16 , 20, 22 , 23 , 24, 27, 28, 30, 33, 35, 38, 41, 43, 
        49, 57, 61, 62, 63, 67, 71, 75, 76, 77, 79, 82, 85, 86, 89, 90, 96, 97, 99 };
    

    @Mutation
    @Description("Add a CPMexicano to the cache")
    public String addCPMexicanoCache(@Name("key") String key) {

       return "Nada por ahora";

    }

    @Query
    @Description("Obtener CPMexicano con codigo postal")
    public List<CPMexicanoCache> getCPMexicanoByD_codigo(@Name("d_codigo") String d_codigo) {

        int alcaldia = Integer.parseInt(d_codigo.substring(0, 2));

        //busqueda lineal de la key
        for (int i = 0; i < KeysEstados.length; i++) {
            if (alcaldia <= KeysEstados[i]) {
                alcaldia = KeysEstados[i];
                break;
            }
        }
    
        Cp data = cache.get(alcaldia);

        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.stream()
                .filter(cp -> cp.d_codigo().equals(d_codigo))
                .collect(Collectors.toList());
    } 

    @Query
    @Description("Obtener entradas del cache")
    public int getAllEntrysCache() {
        return cache.values().size();
    }

    @Query
    @Description("Tamaño de los datos de CPMexicano")
    public int getSizeCPMexicanoCache(){
        //obtener size de cada entrada y sumarla
        int size = 0;
        for (int i = 0; i < 32; i++) {
            Cp data = cache.get(KeysEstados[i]);
            List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
            size += cpMexicanoCacheList.size();
        }
        return size;
    }


    @Query
    @Description("Obtener CPMexicano por alcaldia")
    public List<CPMexicanoCache> getCPMexicanoByAlcaldia(@Name("alcaldia") int alcaldia) {
        Cp data = cache.get(alcaldia);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList;
    }


}
