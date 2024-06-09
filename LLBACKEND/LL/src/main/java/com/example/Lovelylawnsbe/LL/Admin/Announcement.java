package com.example.Lovelylawnsbe.LL.Admin;

import jakarta.persistence.*;

@Entity
@Table(name="announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int anncmtId;
    private String title;
    private String content;


    public int getAnncmtId() {
        return anncmtId;
    }

    public void setAnncmtId(int anncmtId) {
        this.anncmtId = anncmtId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}