package com.app.MockingRecordLabel.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID session_id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "description")
    private String description;

    @Column(name = "request_date")
    private Date request_date;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "completed")
    private boolean completed;


    public Session(UUID session_id, String name, Date start_date, Date end_date, String description, Date request_date, boolean accepted, boolean completed) {
        this.session_id = session_id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.request_date = request_date;
        this.accepted = accepted;
        this.completed = completed;
    }

    public UUID getSession_id() {
        return session_id;
    }

    public String getName() {
        return name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getDescription() {
        return description;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isCompleted() {
        return completed;
    }

}
