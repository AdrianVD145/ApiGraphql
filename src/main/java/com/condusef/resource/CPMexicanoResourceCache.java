package com.condusef.resource;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.infinispan.client.hotrod.RemoteCache;

import com.condusef.models.CPMexicanoCache;
import com.condusef.service.CPMexicanoServiceCache;

import io.quarkus.infinispan.client.Remote;
import jakarta.inject.Inject;

@GraphQLApi
public class CPMexicanoResourceCache {

    @Inject
    CPMexicanoServiceCache service;

    @Inject
    @Remote("CpMexicano")
    RemoteCache<String, CPMexicanoCache> cache;

    @Mutation
    @Description("Add a CPMexicano to the cache")
    public String addCPMexicanoCache(@Name("key") String key) {

        try {
            List<CPMexicanoCache> cpMexicanoList = service.getAllCPMexicanoCache();
            if (cpMexicanoList.isEmpty()) {
                return "la lista esta vacía";
            }
            cache.put(key, cpMexicanoList.get(0));
            return "CPMexicano added to cache";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add CPMexicano to cache: " + e.getMessage();
        }

    }
    @Query
    @Description("Get a CPMexicano from the cache by key")
    public CPMexicanoCache getCPMexicanoByKey(@Name("key") String key) {
        return cache.get(key);
    }

    @Query
    @Description("Get all CPMexicano from the cache")
    public List<CPMexicanoCache> getAllCPMexicanoCache() {
        return (List<CPMexicanoCache>) cache.values();
    }

    @Query
    @Description("Get a CPMexicano from the d_codigo")
    public CPMexicanoCache getCPMexicanoByCodigo(@Name("d_codigo") String d_codigo) {
        return cache.values().stream().filter(cp -> cp.d_codigo().equals(d_codigo)).findFirst().orElse(null);
    }

    @Query
    @Description("Get a CPMexicano from the d_asenta")
    public List<CPMexicanoCache> getCPMexicanoByAsenta(@Name("d_asenta") String d_asenta) {
        return cache.values().stream().filter(cp -> cp.d_asenta().equals(d_asenta)).toList();
    }


    @Query
    @Description("Get a CPMexicano from the D_mnpio")
    public CPMexicanoCache getCPMexicanoByMnpio(@Name("D_mnpio") String D_mnpio) {
        return cache.values().stream().filter(cp -> cp.D_mnpio().equals(D_mnpio)).findFirst().orElse(null);
    }

    @Query
    @Description("Get a CPMexicano from the d_estado")
    public List<CPMexicanoCache> getCPMexicanoByEstado(@Name("d_estado") String d_estado) {
        return cache.values().stream().filter(cp -> cp.d_estado().equals(d_estado)).toList();
    }

    @Query
    @Description("Get the size of the cache")
    public int getCacheSize() {
        return cache.size();
    }



}
