// package com.condusef.init;



// import org.infinispan.client.hotrod.RemoteCache;

// import com.condusef.models.Cp;
// import com.condusef.service.CPMexicanoServiceCache;

// import io.quarkus.infinispan.client.Remote;
// import io.quarkus.runtime.StartupEvent;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.enterprise.event.Observes;
// import jakarta.inject.Inject;

// @ApplicationScoped
// public class Init {
    
//     @Inject
//     CPMexicanoServiceCache service;

//     @Inject
//     @Remote("CpMexicano")
//     RemoteCache<String, Cp> cache;


//     void onStart(@Observes StartupEvent ev) {
       
//         System.out.println("La aplicación se esta iniciando...");
//         CargarDatos();
    
//     }
//     private String CargarDatos() {

        
//          if (cache.containsKey("CpMexicano")) {   
//             return "Trayendo datos de la cache...";
//         } 
//         SubirCache();
//         if (!cache.containsKey("CpMexicano")) {
//             return "Error al cargar los datos.";
//         }
//         return "Datos cargados correctamente.";
//     }
//     private void SubirCache() {
 
//         try {
//             Cp cpMexicanoList = service.getAllCPMexicanoCache();
           
//             cache.put("CpMexicano", cpMexicanoList);
            
//         } catch (Exception e) {
//             e.printStackTrace();
//         }


//     }

// }
