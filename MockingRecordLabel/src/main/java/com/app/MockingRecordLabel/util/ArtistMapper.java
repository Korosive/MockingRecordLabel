package com.app.MockingRecordLabel.util;

import com.app.MockingRecordLabel.model.Artist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ArtistMapper implements RowMapper<Artist> {
    @Override
    public Artist mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Artist(
                resultSet.getObject("artist_id", UUID.class),
                resultSet.getBytes("image"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDate("register_date")
        );
    }
}
