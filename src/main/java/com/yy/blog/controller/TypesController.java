package com.yy.blog.controller;

import com.yy.blog.domian.Type;
import com.yy.blog.service.BlogService;
import com.yy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TypesController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @RequestMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model){
        List<Type> types = typeService.findAll();
        if (id == -1){
            id = types.get(0).getId();
        }
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.findAllByType(id));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
