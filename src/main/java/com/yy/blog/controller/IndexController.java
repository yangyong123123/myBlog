package com.yy.blog.controller;

import com.yy.blog.service.BlogService;
import com.yy.blog.service.TagService;
import com.yy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String index(Model model){
        Pageable pageable = PageRequest.of(0,6);
        model.addAttribute("page",blogService.findAll());
        model.addAttribute("types", typeService.findAll(pageable));
        model.addAttribute("tags", tagService.findAll(pageable));
        model.addAttribute("recommendBlogs", blogService.findRecommendBlog());
        return "index";
    }
    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("page", blogService.findAll("%"+query+"%"));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }
}
