package com.condusef.init;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import com.condusef.models.Cp;
import com.condusef.service.CPMexicanoServiceCache;

import io.quarkus.infinispan.client.Remote;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class Init {

    @Inject
    CPMexicanoServiceCache service;

    @Inject
    @Remote("CpMexicano")
    RemoteCache<Integer, Cp> Remotecache;
    
    @Inject
    RemoteCacheManager cacheManager;


    void onStart(@Observes StartupEvent ev) {

        System.out.println("La aplicación se esta iniciando...");
        CargarDatos();

    }

    private void CargarDatos() {

        String[] EstadosCp = { "Ciudad de México", "Aguascalientes", "Baja California", "Baja California Sur",
                "Campeche" , "Coahuila de Zaragoza" , "Colima" , "Chiapas", "Chihuahua", "Durango", "Guanajuato", "Guerrero",
                "Hidalgo", "Jalisco", "México", "Michoacán de Ocampo", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", 
                "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala",
                "Veracruz de Ignacio de la Llave", "Yucatán", "Zacatecas"
        };

        // alcaldias rangos maximos
        int[] KeysEstados = { 16 , 20, 22 , 23 , 24, 27, 28, 30, 33, 35, 38, 41, 43, 49, 57,
             61, 62, 63, 67, 71, 75, 76, 77, 79, 82, 85, 86, 89, 90, 96, 97, 99
        };

        for (int i = 0; i < EstadosCp.length; i++) {
            String EstadoCp = EstadosCp[i];
            int KeyEstado = KeysEstados[i];

            if (Remotecache.containsKey(KeyEstado)) {
                System.out.println("Datos existentes en cache de " + EstadoCp);
                continue;
            }

            System.out.println("Cargando datos de " + EstadoCp + "...");
            SubirCache(EstadoCp, KeyEstado);
            

            if (!Remotecache.containsKey(KeyEstado)) {
                System.out.println("Error al cargar los datos de :" + EstadoCp);
            }
                      
        }  

    }

    private void SubirCache(String EstadoCp, int KeyEstado) {

        try {
            Cp cpMexicanoList = service.ConsultarCp(EstadoCp);

            Remotecache.put(KeyEstado, cpMexicanoList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
