package com.yy.blog.controller;

import com.yy.blog.domian.Tag;
import com.yy.blog.service.BlogService;
import com.yy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TagsController {
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;

    @RequestMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model){
        List<Tag> tags = tagService.findAll();
        if (id == -1){
            id = tags.get(0).getId();
        }

        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.findAllByTag(id));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
