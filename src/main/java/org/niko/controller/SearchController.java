package org.niko.controller;

import org.niko.repository.PagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class SearchController {

    @Autowired
    PagesRepository pagesRepository;

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam String q, Model model) {
        model.addAttribute("result", pagesRepository.search(q));
        model.addAttribute("q", q);
        return "search";
    }

}
