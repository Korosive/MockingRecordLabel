package com.app.MockingRecordLabel.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID artist_id;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "register_date")
    private Date register_date;

    public Artist() {
    }

    public Artist(UUID artist_id, byte[] image, String name, String description, Date register_date) {
        this.artist_id = artist_id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.register_date = register_date;
    }

    public UUID getArtist_id() {
        return artist_id;
    }

    public byte[] getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getRegister_date() {
        return register_date;
    }
}
