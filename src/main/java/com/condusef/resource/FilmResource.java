package com.condusef.resource;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import com.condusef.models.Ally;
import com.condusef.models.Film;
import com.condusef.models.Hero;
import com.condusef.service.GalaxyService;

import jakarta.inject.Inject;

@GraphQLApi
public class FilmResource {

    @Inject
    GalaxyService service;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();

    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    @Query
    public List<Ally> Allies() {
        return service.getAllAllies();
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    

}
