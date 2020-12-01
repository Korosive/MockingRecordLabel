package com.app.MockingRecordLabel.util;

import com.app.MockingRecordLabel.model.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Session(
                resultSet.getObject("session_id", UUID.class),
                resultSet.getString("name"),
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date"),
                resultSet.getString("description"),
                resultSet.getDate("request_date"),
                resultSet.getBoolean("accepted"),
                resultSet.getBoolean("completed")
        );
    }
}
