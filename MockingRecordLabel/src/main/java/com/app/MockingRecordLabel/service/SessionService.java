package com.app.MockingRecordLabel.service;

import com.app.MockingRecordLabel.model.Session;
import com.app.MockingRecordLabel.util.SessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger log = LoggerFactory.getLogger(SessionService.class);

    public HashMap<String, Object> requestSession(Session newSession) {
        String sql = "INSERT INTO sessions (session_id, name, start_date, end_date, description, request_date, accepted, completed) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        HashMap<String, Object> response = new HashMap<>();
        UUID session_id = UUID.randomUUID();
        String name = newSession.getName();
        Date start_date = newSession.getStart_date();
        Date end_date = newSession.getEnd_date();
        String description = newSession.getDescription();
        Date request_date = new Date();

        try {
            jdbcTemplate.update(sql, session_id, name, start_date, end_date, description, request_date, false, false);
            response.put("success", Boolean.TRUE);
            response.put("message", "Successfully requested session.");
            log.info("Session requested of {} at {}.", name, new Date());
        } catch (DataAccessException accessException) {
            response.put("success", Boolean.FALSE);
            response.put("message", "Failed to request session.");
            log.warn("Failed to request session of {} at {}.", name, new Date());
            log.warn(accessException.getMessage());
        }

        return response;
    }

    public List<Session> getSessionList() {
        String sql = "SELECT * FROM sessions ORDER BY request_date DESC";
        List<Session> listSession = new ArrayList<>();

        try {
            listSession = jdbcTemplate.query(sql, new SessionMapper());
            log.info("Retrieved list of sessions at {}.", new Date());
        } catch (DataAccessException accessException) {
            log.warn("Failed to retrieve the list of sessions at {}.", new Date());
            log.warn(accessException.getMessage());
        }

        return listSession;
    }

    public List<Session> getNameSession(String name_id) {
        String sql = "SELECT * FROM sessions WHERE name_id = ?";
        List<Session> listSessions = new ArrayList<>();

        try {
            listSessions = jdbcTemplate.query(sql, new Object[]{name_id}, new SessionMapper());
            log.info("Retrieved list of {} sessions at {}.", name_id, new Date());
        } catch (DataAccessException accessException) {
            log.warn("Failed to retrieve list of {} sessions at {}.", name_id, new Date());
            log.warn(accessException.getMessage());
        }

        return listSessions;
    }

    public HashMap<String, Object> updateSession(Session updateSession) {
        String sql = "UPDATE sessions SET name = ?, start_date = ?, end_date = ?, description = ?, accepted = ?, completed = ? WHERE session_id = ?";
        HashMap<String, Object> response = new HashMap<>();
        UUID session_id = updateSession.getSession_id();
        String name = updateSession.getName();
        Date start_date = updateSession.getStart_date();
        Date end_date = updateSession.getEnd_date();
        String description = updateSession.getDescription();
        boolean accepted = updateSession.isAccepted();
        boolean completed = updateSession.isCompleted();

        try {
            jdbcTemplate.update(sql, name, start_date, end_date, description, accepted, completed, session_id);
            response.put("success", Boolean.TRUE);
            response.put("message", "Successfully updated session");
            log.info("Successfully updated session at {}.", new Date());
        } catch (DataAccessException accessException) {
            response.put("success", false);
            response.put("message", "Failed to update session.");
            log.warn("Failed to update session at {}.", new Date());
            log.warn(accessException.getMessage());
        }

        return response;
    }

    public HashMap<String, Object> deleteSession(UUID session_id) {
        String sql = "DELETE FROM sessions WHERE session_id = ?";
        HashMap<String, Object> response = new HashMap<>();

        try {
            jdbcTemplate.update(sql, session_id);
            response.put("success", true);
            response.put("message", "Successfully deleted session.");
            log.info("Successfully deleted session with id ({}) at {}.", session_id, new Date());
        } catch (DataAccessException accessException) {
            response.put("success", false);
            response.put("message", "Failed to delete session.");
            log.warn("Failed to delete success {} at {}.", session_id, new Date());
            log.warn(accessException.getMessage());
        }

        return response;
    }
}
