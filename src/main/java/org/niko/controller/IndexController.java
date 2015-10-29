package org.niko.controller;

import org.niko.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired CrawlerService crawlerService;

    @RequestMapping(value = "", method = GET)
    public String index() {
        return "indexing.form";
    }
    
    @ResponseStatus(NO_CONTENT)
    @RequestMapping(value = "", method = POST)
    public void indexUrl(@RequestParam String q, @RequestParam(required = false) Integer depth) {
        crawlerService.load(q, depth);
    }

}
