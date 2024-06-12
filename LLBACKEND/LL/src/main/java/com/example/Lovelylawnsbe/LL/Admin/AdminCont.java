package com.example.Lovelylawnsbe.LL.Admin;

import com.example.Lovelylawnsbe.LL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class AdminCont{
    @Autowired
    private UserServ userServ;
    @Autowired
    private PostServ postServ;
    @Autowired
    private ForumServ forumServ;
    @Autowired
    private AnnouncementServ announcementServ;

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("forums", forumServ.getAllForums());
        model.addAttribute("announcements", announcementServ.getAllAnnouncements());
        return "home";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServ.getAllUsers());
        return "user-list";
    }

    @GetMapping("/user")
    public String getUserById(@RequestParam(value = "userId") int userId, Model model) {
        try {
            User user = userServ.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            model.addAttribute("user", user);
            return "user-detail";
        }catch(ResourceNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/delete/user/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userServ.deleteUser(userId);
        return "redirect:/home/all-users";
    }
    @GetMapping("/post")
    public String getPostById(@RequestParam("postId") int postId, Model model) {
        try {
            Post post = postServ.getPostById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
            model.addAttribute("post", post);
            return "post-detail";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/all-posts")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postServ.getAllPosts());
        return "post-list";
    }
    @GetMapping("/delete/post/{postId}")
    public String deletePost(@PathVariable int postId) {
        postServ.deletePost(postId);
        return "redirect:/home/all-posts";
    }

    @GetMapping("/all-forums")
    public String getAllForums(Model model) {
        model.addAttribute("forums", forumServ.getAllForums());
        return "forum-list";
    }

    @GetMapping("/delete/forum/{forumId}")
    public String deleteForum(@PathVariable int forumId) {
        forumServ.deleteForum(forumId);
        return "redirect:/home/all-forums";
    }

    @GetMapping("/forum")
    public String getForumById(@RequestParam("forumId") int forumId, Model model) {
        try {
            Forum forum = forumServ.getForumById(forumId).orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
            model.addAttribute("forum", forum);
            return "forum-detail";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/delete/announcement/{anncmtId}")
    public String deleteAnnouncement(@PathVariable int anncmtId) {
        announcementServ.deleteAnnouncement(anncmtId);
        return "redirect:/home/all-announcements";
    }

//    @PostMapping("/save")
//    public String createAnnouncement(@ModelAttribute("announcement") Announcement announcement) {
//        announcementServ.createAnnouncement(announcement);
//        return "redirect:/home/all-announcements";
//    }
    @GetMapping("/announcement")
    public String getAnnouncementById(@RequestParam("anncmtId") int anncmtId, Model model) {
        try {
            Announcement announcement = announcementServ.getAnnouncementById(anncmtId);
            if (announcement == null) {
                throw new ResourceNotFoundException("Announcement not found with id: " + anncmtId);
            }
            model.addAttribute("announcement", announcement);
            return "anncmt-detail";
        } catch (ResourceNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/all-announcements")
    public String getAllAnnouncements(Model model) {
        model.addAttribute("announcements", announcementServ.getAllAnnouncements());
        return "anncmt-list";
    }

//    @GetMapping("/create-page")
//    public String createAnnouncementPage(){
//        return "create-anncmt";
//    }

//    @PostMapping("/announcement")
//    public String createNewAnnouncement(Announcement announcement) {
//        announcementServ.createAnnouncement(announcement);
//        return "redirect:/home/all-announcements";
//    }
    @GetMapping("/create-page")
    public String createAnnouncementPage(Model model) {
        //Announcement announcement = new Announcement();
        model.addAttribute("announcement", new Announcement());
        return "create-anncmt";
    }

    @PostMapping("/save")
    public String createNewAnnouncement(Announcement announcement) {
        announcementServ.createAnnouncement(announcement);
        return "redirect:/home/all-announcements";
    }
}
