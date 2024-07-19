package com.Condusef;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class CPMexicanoResourceCacheTest {

    @Test
    @TestSecurity(user = "user", roles = {"admin"})
    void testAddCPMexicanoCache() {
        given()
            .contentType("application/json")
            .body("{ \"query\": \"mutation { addCPMexicanoCache }\" }")
            .when().post("/graphql")
            .then()
            .statusCode(200)
            .body("data.addCPMexicanoCache", is("Nada por ahora"));
    }

    @Test
    @TestSecurity(user = "user", roles = {"user", "admin"})
    void testGetCPMexicanoByD_codigo_valid() {
        String codigoPostal = "67117";

        given()
            .contentType("application/json")
            .body("{ \"query\": \"query { getCPMexicanoByD_codigo(d_codigo: \\\"" + codigoPostal + "\\\") { d_codigo } }\" }")
            .when().post("/graphql")
            .then()
            .statusCode(200); // Ajustar segun los datos esperados
    }

    @Test
    @TestSecurity(user = "user", roles = {"admin"})
    void testGetAllEntrysCache() {
        given()
            .contentType("application/json")
            .body("{ \"query\": \"query { getAllEntrysCache }\" }")
            .when().post("/graphql")
            .then()
            .statusCode(200); // Ajustar segun los datos esperados
    }

    @Test
    @TestSecurity(user = "user", roles = {"admin"})
    void testGetSizeCPMexicanoCache() {
        given()
            .contentType("application/json")
            .body("{ \"query\": \"query { getSizeCPMexicanoCache }\" }")
            .when().post("/graphql")
            .then()
            .statusCode(200); // Ajustar segun los datos esperados
    }

    @Test
    @TestSecurity(user = "user", roles = {"user", "admin"})
    void testGetCPMexicanoByAlcaldia_valid() {
        int alcaldia = 16;

        given()
            .contentType("application/json")
            .body("{ \"query\": \"query { getCPMexicanoByAlcaldia(alcaldia: " + alcaldia + ") { d_codigo } }\" }")
            .when().post("/graphql")
            .then()
            .statusCode(200); // Ajustar segun los datos esperados
    }
}
