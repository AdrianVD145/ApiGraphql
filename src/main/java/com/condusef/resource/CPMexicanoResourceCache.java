package com.condusef.resource;

import java.util.List;
import java.util.concurrent.CompletionStage;

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
    public String addCPMexicanoCache(@Name("key") String key, @Name("cpMexicano") CPMexicanoCache cpMexicano) {

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

    @Query("allCPMexicanoCache")
    @Description("Get all CPMexicano from Mexico")
    public List<CPMexicanoCache> getAllCPMexicanoCache() {
        return service.getAllCPMexicanoCache();
    }

}
