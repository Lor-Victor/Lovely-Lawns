package com.example.Lovelylawnsbe.LL.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnnouncementCont {
    @Autowired
    private  AnnouncementServ announcementServ;

    @GetMapping("/delete/announcement/{anncmtID}")
    public String deleteAnnouncement(@RequestParam("anncmtId") int anncmtId) {
        announcementServ.deleteAnnouncement(anncmtId);
        return "redirect:/home/all-announcements";
    }
    @PostMapping("/save")
    public String saveAnnouncement(@ModelAttribute("announcement") Announcement announcement) {
        announcementServ.saveAnnouncement(announcement);
        return "redirect:/home/announcements/list";
    }
}
