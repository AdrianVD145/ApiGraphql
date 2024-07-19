package com.condusef.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import com.condusef.models.CPMexicanoCache;
import com.condusef.models.Cp;
import com.condusef.service.CPMexicanoServiceCache;

import io.quarkus.infinispan.client.Remote;
import io.quarkus.security.ForbiddenException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

@GraphQLApi
public class CPMexicanoResourceCache {

    @Inject
    CPMexicanoServiceCache service;

    @Inject
    @Remote("CpMexicano")
    RemoteCache<Integer, Cp> Remotecache;

    @Inject
    RemoteCacheManager cacheManager;

    @Inject
    SecurityIdentity identity;

    int[] KeysEstados = { 16, 20, 22, 23, 24, 27, 28, 30, 33, 35, 38, 41, 43,
            49, 57, 61, 62, 63, 67, 71, 75, 76, 77, 79, 82, 85, 86, 89, 90, 96, 97, 99 };

    private void VerificarAuth() {
        if (identity.isAnonymous()) {
            System.out.println("No esta autenticado");
            throw new NotAuthorizedException("No está autenticado"); // 401
        }
    }

    private void VerificarRolAdminOUser() {
        if (!identity.hasRole("admin") && !identity.hasRole("user")) {
            System.out.println("No tiene permisos para realizar esta acción");
            throw new ForbiddenException("No tiene permisos para realizar esta acción"); // 403
        }
    }

    private String sanitizarCodigoPostal(String codigoPostal) {
        // Validar que solo contenga dígitos y tenga una longitud específica de 5
        if (codigoPostal == null || !codigoPostal.matches("\\d{5}")) {
            System.out.println("Codigo postal invalido");
            throw new IllegalArgumentException("Código postal inválido");
        }
        return codigoPostal.trim();
    }

    private int sanitizarAlcaldia(int alcaldia) {
        // Validar que la alcaldía esté dentro de un rango válido (por ejemplo, entre 0
        // y 99)
        if (alcaldia < 0 || alcaldia > 99) {
            throw new IllegalArgumentException("Alcaldía inválida");
        }
        return alcaldia;
    }

    @Mutation
    @Description("Add a CPMexicano to the cache")
    @RolesAllowed("admin")
    public String addCPMexicanoCache() {

        return "Nada por ahora";

    }

    @Query
    @Description("Obtener CPMexicano con codigo postal")
    @RolesAllowed({ "user", "admin" })
    public List<CPMexicanoCache> getCPMexicanoByD_codigo(@Name("d_codigo") String d_codigoP) {

        try {
            VerificarAuth();
            String d_codigo = sanitizarCodigoPostal(d_codigoP);

            int alcaldia = Integer.parseInt(d_codigo.substring(0, 2));

            // busqueda lineal de la key
            for (int i = 0; i < KeysEstados.length; i++) {
                if (alcaldia <= KeysEstados[i]) {
                    alcaldia = KeysEstados[i];
                    break;
                }
            }

            Cp data = Remotecache.get(alcaldia);

            List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
            return cpMexicanoCacheList.stream()
                    .filter(cp -> cp.d_codigo().equals(d_codigo))
                    .collect(Collectors.toList());

        } catch (ForbiddenException e) {            
            return null;
        }
        catch (IllegalArgumentException e) {
            return null;
        }
        catch (NotAuthorizedException e) {
            return null;
        }
        
    }

    @Query
    @Description("Obtener entradas del cache")
    @RolesAllowed("admin")
    public int getAllEntrysCache() {
        VerificarAuth();
        VerificarRolAdminOUser();

        return Remotecache.values().size();
    }

    @Query
    @Description("Tamaño de los datos de CPMexicano")
    @RolesAllowed("admin")
    public int getSizeCPMexicanoCache() {
        VerificarAuth();
        VerificarRolAdminOUser();

        // obtener size de cada entrada y sumarla
        int size = 0;
        for (int i = 0; i < 32; i++) {
            Cp data = Remotecache.get(KeysEstados[i]);
            List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
            size += cpMexicanoCacheList.size();
        }
        return size;
    }

    @Query
    @Description("Obtener CPMexicano por alcaldia")
    @RolesAllowed({ "user", "admin" })
    public List<CPMexicanoCache> getCPMexicanoByAlcaldia(@Name("alcaldia") int alcaldia) {
        VerificarAuth();
        VerificarRolAdminOUser();
        sanitizarAlcaldia(alcaldia);

        Cp data = Remotecache.get(alcaldia);
        List<CPMexicanoCache> cpMexicanoCacheList = data.cpMexicanoCacheList();
        return cpMexicanoCacheList;
    }

    // @Query
    // @Description("Obtener valores de identity")
    // public String getIdentity() {
    // return identity.hasRole("admin") ? "admin" : "user";
    // }

}
