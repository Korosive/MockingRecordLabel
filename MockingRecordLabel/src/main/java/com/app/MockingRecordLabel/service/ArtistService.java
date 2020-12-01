package com.app.MockingRecordLabel.service;

import com.app.MockingRecordLabel.model.Artist;
import com.app.MockingRecordLabel.util.ArtistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArtistService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(ArtistService.class);

    public List<Artist> getArtistList() {
        String sql = "SELECT * FROM artists";
        List<Artist> listArtist = new ArrayList<>();

        try {
            listArtist = jdbcTemplate.query(sql, new Object[]{}, new ArtistMapper());
            log.info("Retrieved list of artists at {}.", new Date());
        } catch (DataAccessException accessException) {
            log.warn("Failed to retrieve list of artists at {}.", new Date());
            log.warn(accessException.getMessage());
            log.warn(accessException.getLocalizedMessage());
        }

        return listArtist;
    }

    public List<Artist> getExampleList() {
        String sql = "SELECT * FROM artists LIMIT 3";
        List<Artist> listArtist = new ArrayList<>();

        try {
            listArtist = jdbcTemplate.query(sql, new Object[]{}, new ArtistMapper());
            log.info("Retrieved example list of artists at {}.", new Date());
        } catch (DataAccessException accessException) {
            log.warn("Failed to retrieve example list of artists at {}.", new Date());
            log.warn(accessException.getMessage());
        }

        return listArtist;
    }

    public Artist getArtistById(UUID artist_id) {
        String sql = "SELECT * FROM artists WHERE artist_id = ?";
        Artist targetArtist;

        try {
            targetArtist = jdbcTemplate.queryForObject(sql, new Object[]{artist_id}, new ArtistMapper());
            log.info("Retrieved {} at {}.", artist_id, new Date());
        } catch (DataAccessException accessException) {
            targetArtist = null;
            log.warn("Failed to retrieve {} at {}.", artist_id, new Date());
            log.warn(accessException.getMessage());
        }

        return targetArtist;
    }

    public HashMap<String, Object> addArtist(Artist newArtist) {
        String sql = "INSERT INTO artists VALUES (?, ?, ?, ?, ?)";
        HashMap<String, Object> response = new HashMap<>();
        byte[] image = newArtist.getImage();
        UUID id = UUID.randomUUID();
        String name = newArtist.getName();
        String description = newArtist.getDescription();
        Date register_date = new Date();

        try {
            jdbcTemplate.update(sql, id, image, name, description, register_date);
            response.put("success", Boolean.TRUE);
            response.put("message", name + " added.");
            log.info("Added {} at {}.", name, register_date);
        } catch (DataAccessException accessException) {
            response.put("success", Boolean.FALSE);
            response.put("message", "Failed to add " + name + ".");
            log.warn("Failed to add {} at {}.", name, register_date);
            log.warn(accessException.getMessage());
        }

        return response;
    }

    public HashMap<String, Object> updateArtist(Artist updateArtist) {
        String sql = "UPDATE artists SET image = ?, name = ?, description = ?, register_date = ? WHERE artist_id = ?";
        HashMap<String, Object> response = new HashMap<>();
        UUID id = updateArtist.getArtist_id();
        byte[] image = updateArtist.getImage();
        String name = updateArtist.getName();
        String description = updateArtist.getDescription();
        Date register_date = updateArtist.getRegister_date();

        try {
            jdbcTemplate.update(sql, image, name, description, register_date, id);
            response.put("success", Boolean.TRUE);
            response.put("message", name + " updated.");
            log.info("Updated {} at {}.", name, new Date());
        } catch (DataAccessException accessException) {
            response.put("success", Boolean.FALSE);
            response.put("message", "Failed to update " + name + ".");
            log.warn("Failed to update {} at {}.", name, new Date());
            log.warn(accessException.getMessage());
        }

        return response;
    }

    public HashMap<String, Object> deleteArtist(UUID artist_id) {
        String sql = "DELETE FROM artists WHERE artist_id = ?";
        HashMap<String, Object> response = new HashMap<>();

        try {
            jdbcTemplate.update(sql, artist_id);
            response.put("success", Boolean.TRUE);
            response.put("message", artist_id + " deleted.");
            log.info("Deleted {} at {}.", artist_id, new Date());
        } catch (DataAccessException accessException) {
            response.put("success", Boolean.FALSE);
            response.put("message", "Failed to delete " + artist_id + ".");
            log.warn("Failed to delete {} at {}.", artist_id, new Date());
            log.warn(accessException.getMessage());
        }

        return response;
    }
}
