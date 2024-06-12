package com.example.Lovelylawnsbe.LL.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnnouncementCont {
    @Autowired
    private  AnnouncementServ announcementServ;

//    @DeleteMapping("/delete/{anncmtID}")
//    public String deleteAnnouncement(@PathVariable("anncmtId") int anncmtId) {
//        announcementServ.deleteAnnouncement(anncmtId);
//        return "redirect:/home/all-announcements";
//    }
    @PostMapping("/save")
    public String saveAnnouncement(@ModelAttribute("announcement") Announcement announcement) {
        announcementServ.createAnnouncement(announcement);
        return "redirect:/home/all-announcements";
    }
    @GetMapping("/announce/post")
    public String addNewAnnouncement(Announcement announcement) {
        announcementServ.createAnnouncement(announcement);
        return "redirect:/home/all-announcements";
    }


}
