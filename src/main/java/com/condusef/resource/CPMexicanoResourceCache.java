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
    RemoteCache<String, Cp> cache;

    @Mutation
    @Description("Add a CPMexicano to the cache")
    public String addCPMexicanoCache(@Name("key") String key) {

        try {
            Cp cpMexicanoList = service.getAllCPMexicanoCache();
           
            cache.put(key, cpMexicanoList);
            return "CPMexicano added to cache";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add CPMexicano to cache: " + e.getMessage();
        }

    }

    @Query
    @Description("Get all CPMexicano from the cache")
    public Cp getallCPMexicano(@Name("key") String key) {
        return cache.get(key);
    }

    @Query
    @Description("Get CPMexicano by d_codigo")
    public List<CPMexicanoCache> getCPMexicanoByD_codigo(@Name("key") String key, @Name("d_codigo") String d_codigo) {
        Cp data = cache.get(key);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.stream()
                .filter(cp -> cp.d_codigo().equals(d_codigo))
                .collect(Collectors.toList());
    } 
    
    @Query
    @Description("Get CPMexicano by d_asenta")
    public List<CPMexicanoCache> getCPMexicanoByD_asenta(@Name("key") String key, @Name("d_asenta") String d_asenta) {
        Cp data = cache.get(key);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.stream()
                .filter(cp -> cp.d_asenta().equals(d_asenta))
                .collect(Collectors.toList());
    }

    @Query
    @Description("Get CPMexicano by d_estado")
    public List<CPMexicanoCache> getCPMexicanoByD_estado(@Name("key") String key, @Name("d_estado") String d_estado) {
        Cp data = cache.get(key);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.stream()
                .filter(cp -> cp.d_estado().equals(d_estado))
                .collect(Collectors.toList());
    }

    @Query
    @Description("Get CPMexicano by d_ciudad")
    public List<CPMexicanoCache> getCPMexicanoByD_ciudad(@Name("key") String key, @Name("d_ciudad") String d_ciudad) {
        Cp data = cache.get(key);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.stream()
                .filter(cp -> cp.d_ciudad().equals(d_ciudad))
                .collect(Collectors.toList());
    }

    @Query
    @Description("Get CPMexicano by D_mnpio")
    public List<CPMexicanoCache> getCPMexicanoByD_mnpio(@Name("key") String key, @Name("D_mnpio") String D_mnpio) {
        Cp data = cache.get(key);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.stream()
                .filter(cp -> cp.D_mnpio().equals(D_mnpio))
                .collect(Collectors.toList());
    }

    @Query
    @Description("Get Size of CPMexicanoCache")
    public int getSizeCPMexicanoCache(@Name("key") String key) {
        Cp data = cache.get(key);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList.size();
    }
}



// package com.condusef.resource;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.eclipse.microprofile.graphql.Description;
// import org.eclipse.microprofile.graphql.GraphQLApi;
// import org.eclipse.microprofile.graphql.Mutation;
// import org.eclipse.microprofile.graphql.Name;
// import org.eclipse.microprofile.graphql.Query;
// import org.infinispan.client.hotrod.RemoteCache;

// import com.condusef.models.CPMexicanoCache;
// import com.condusef.models.Cp;
// import com.condusef.service.CPMexicanoServiceCache;

// import io.quarkus.infinispan.client.Remote;
// import jakarta.inject.Inject;

// @GraphQLApi
// public class CPMexicanoResourceCache {

//     @Inject
//     CPMexicanoServiceCache service;

//     @Inject
//     @Remote("CpMexicanoF")
//     RemoteCache<String, Cp> cache;

//     @Mutation
//     @Description("Add a CPMexicano to the cache")
//     public String addCPMexicanoCache() {

//         try {
//             List<Cp> cpMexicanoList = service.getAllCPMexicanoCache();
           

//             for (Cp cp : cpMexicanoList) {
//                 cache.put(cp.estado(), cp);
//                 break;
//             }

//             return "CPMexicano added to cache";
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "Failed to add CPMexicano to cache: " + e.getMessage();
//         }

//     }

//     @Query
//     @Description("Get all CPMexicano from the cache")
//     public List<Cp> getAllCPMexicanoCache() {
//         return cache.values().stream().collect(Collectors.toList());
//     }

//     // @Query
//     // @Description("Get CPMexicano by d_codigo")
//     // public List<CPMexicanoCache> getCPMexicanoByD_codigo(@Name("key") String key, @Name("d_codigo") String d_codigo) {
//     //     Cp data = cache.get(key);
//     //     List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
//     //     return cpMexicanoCacheList.stream()
//     //             .filter(cp -> cp.d_codigo().equals(d_codigo))
//     //             .collect(Collectors.toList());
//     // } 
    
//     // @Query
//     // @Description("Get CPMexicano by d_asenta")
//     // public List<CPMexicanoCache> getCPMexicanoByD_asenta(@Name("key") String key, @Name("d_asenta") String d_asenta) {
//     //     Cp data = cache.get(key);
//     //     List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
//     //     return cpMexicanoCacheList.stream()
//     //             .filter(cp -> cp.d_asenta().equals(d_asenta))
//     //             .collect(Collectors.toList());
//     // }

//     // @Query
//     // @Description("Get CPMexicano by d_estado")
//     // public List<CPMexicanoCache> getCPMexicanoByD_estado(@Name("key") String key, @Name("d_estado") String d_estado) {
//     //     Cp data = cache.get(key);
//     //     List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
//     //     return cpMexicanoCacheList.stream()
//     //             .filter(cp -> cp.d_estado().equals(d_estado))
//     //             .collect(Collectors.toList());
//     // }

//     // @Query
//     // @Description("Get CPMexicano by d_ciudad")
//     // public List<CPMexicanoCache> getCPMexicanoByD_ciudad(@Name("key") String key, @Name("d_ciudad") String d_ciudad) {
//     //     Cp data = cache.get(key);
//     //     List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
//     //     return cpMexicanoCacheList.stream()
//     //             .filter(cp -> cp.d_ciudad().equals(d_ciudad))
//     //             .collect(Collectors.toList());
//     // }

//     // @Query
//     // @Description("Get CPMexicano by D_mnpio")
//     // public List<CPMexicanoCache> getCPMexicanoByD_mnpio(@Name("key") String key, @Name("D_mnpio") String D_mnpio) {
//     //     Cp data = cache.get(key);
//     //     List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
//     //     return cpMexicanoCacheList.stream()
//     //             .filter(cp -> cp.D_mnpio().equals(D_mnpio))
//     //             .collect(Collectors.toList());
//     // }

//     // @Query
//     // @Description("Get Size of CPMexicanoCache")
//     // public int getSizeCPMexicanoCache(@Name("key") String key) {
//     //     Cp data = cache.get(key);
//     //     List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
//     //     return cpMexicanoCacheList.size();
//     // }
// }
