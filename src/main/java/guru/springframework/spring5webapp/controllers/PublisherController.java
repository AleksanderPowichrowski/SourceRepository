package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.PublisherV2Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublisherController {

    private final PublisherV2Repository publisherV2Repository;

    public PublisherController(PublisherV2Repository publisherV2Repository) {
        this.publisherV2Repository = publisherV2Repository;
    }

    @RequestMapping("/publishers")
    public String getPublishers(Model model){
        model.addAttribute("publishers",publisherV2Repository.findAll());
        return "publishers/list";
    }
}
