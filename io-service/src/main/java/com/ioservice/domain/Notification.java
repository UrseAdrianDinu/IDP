package com.ioservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", unique = true)
    private Long notification_id;

    @Column(name = "category")
    private String category;

    @Column(name = "details")
    private String content;

    @ManyToOne
    @JsonIgnoreProperties("notifications")
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Notification(Long notification_id, String category, String content, User user) {
        this.notification_id = notification_id;
        this.category = category;
        this.content = content;
        this.user = user;
    }

    public Notification(String category, String content, User user) {
        this.notification_id = notification_id;
        this.category = category;
        this.content = content;
        this.user = user;
    }

    public Notification() {
    }

    public Long getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Long notification_id) {
        this.notification_id = notification_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
