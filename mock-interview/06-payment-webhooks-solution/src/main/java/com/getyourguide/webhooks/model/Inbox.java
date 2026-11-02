package com.getyourguide.webhooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbox")
public class Inbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dedup_key")
    private String dedupKey;
    @Column(name = "event_code")
    private String eventCode;
    @Column(name = "raw_body")
    private String rawBody;

    public static Inbox of(String dedupKey, String eventCode, String rawBody) {
        Inbox inbox = new Inbox();
        inbox.dedupKey = dedupKey;
        inbox.eventCode = eventCode;
        inbox.rawBody = rawBody;
        return inbox;
    }
}
