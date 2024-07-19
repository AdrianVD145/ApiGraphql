package com.condusef.resource;

import com.condusef.models.User;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/Login")
public class AuthResource {
    

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(User credentials) {
        if (isValidUser(credentials)) {
            String token = Jwt.issuer("https://example.com/issuer")
                              .upn(credentials.getUsuario())
                              .groups(credentials.getRol())
                              .sign();
            return Response.ok(token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    private boolean isValidUser(User credentials) {

        // Aqui se validarian si el usuario y la constraseña existen en la base de datos: 
        
        return "user".equals(credentials.getUsuario()) && "password".equals(credentials.getContraseña());
    }
}

