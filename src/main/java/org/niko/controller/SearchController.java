package org.niko.controller;

import org.niko.dao.PagesDao;
import org.niko.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class SearchController {

    @Autowired PagesDao pagesDao;

    @RequestMapping(value = "/", method = GET)
    public String index() throws Exception {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = GET)
    public List<Page> search(@RequestParam String q) throws Exception {
        return pagesDao.search(q);
    }

}
