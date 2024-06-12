package com.example.Lovelylawnsbe.LL.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServ {

    @Autowired
    private AnnouncementRep announcementRep;

    public List<Announcement> getAllAnnouncements() {
        return announcementRep.findAll();
    }

    public Announcement getAnnouncementById(int anncmtId) {
        return announcementRep.findById(anncmtId).orElse(null);
    }

    public void createAnnouncement(Announcement announcement) { announcementRep.save(announcement);
    }
    public void deleteAnnouncement(int anncmtId) {
        announcementRep.deleteById(anncmtId);
    }
}
