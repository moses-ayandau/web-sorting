package com.moses.sorting.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.moses.sorting.model.Movie;
import com.moses.sorting.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/movies/*")
public class MovieServlet extends HttpServlet {
    private final MovieService movieService = new MovieService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getWriter(), movieService.getAllMovies());
        } else {

            int id = parseId(pathInfo);
            movieService.getMovieById(id).ifPresentOrElse(
                    movie -> {
                        try {
                            resp.setContentType("application/json");
                            objectMapper.writeValue(resp.getWriter(), withHateoasLinks(movie, req));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    },
                    () -> resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie movie = objectMapper.readValue(req.getReader(), Movie.class);
        Movie createdMovie = movieService.addMovie(movie);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        objectMapper.writeValue(resp.getWriter(), withHateoasLinks(createdMovie, req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try {
            int id = parseId(pathInfo);
            Movie updatedMovie = objectMapper.readValue(req.getReader(), Movie.class);

            if (movieService.updateMovie(id, updatedMovie)) {
                resp.setContentType("application/json");
                objectMapper.writeValue(resp.getWriter(), withHateoasLinks(updatedMovie, req));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid or missing ID in the request URL.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try {
            int id = parseId(pathInfo);

            if (movieService.deleteMovie(id)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid or missing ID in the request URL.");
        }
    }


    private int parseId(String pathInfo) {
        if (pathInfo == null || pathInfo.split("/").length < 2) {
            throw new IllegalArgumentException("Invalid path information: ID is missing");
        }
        return Integer.parseInt(pathInfo.split("/")[1]);
    }


    private Map<String, Object> withHateoasLinks(Movie movie, HttpServletRequest req) {
        Map<String, Object> response = new HashMap<>();
        response.put("movie", movie);

        String baseUrl = req.getRequestURL().toString();
        String pathInfo = req.getPathInfo();

        if (pathInfo != null) {
            baseUrl = baseUrl.replace(pathInfo, "");
        }

        response.put("links", Map.of(
                "self", baseUrl + "/" + movie.getId(),
                "update", baseUrl + "/" + movie.getId(),
                "delete", baseUrl + "  /" + movie.getId()
        ));

        return response;
    }

}
