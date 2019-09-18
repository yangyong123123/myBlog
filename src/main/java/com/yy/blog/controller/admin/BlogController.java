package com.yy.blog.controller.admin;

import com.yy.blog.domian.Blog;
import com.yy.blog.domian.BlogQuery;
import com.yy.blog.domian.User;
import com.yy.blog.service.BlogService;
import com.yy.blog.service.TagService;
import com.yy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model){
        model.addAttribute("types",typeService.findAll());
        Page<Blog> blogs = blogService.findAll(pageable);
        model.addAttribute("page",blogs);
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,Model model){
        Page<Blog> blogs = blogService.findAll(blog,pageable);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }
    @GetMapping("/blogs/save")
    public String save(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.findAll());
        model.addAttribute("tags",tagService.findAll());
        return "admin/blogs-input";
    }
    @PostMapping("/blogs/saveBlog")
    public String saveBlog(Blog blog, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setTags(tagService.findById(blog.getTagIds()));
        blog.setCreateTime(new Date());
        blog.setViews(0);
        blogService.save(blog);
        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model){
        model.addAttribute("types",typeService.findAll());
        model.addAttribute("tags",tagService.findAll());
        Blog blog = blogService.findById(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }
    @PostMapping("/blogs/editBlog/{id}")
    public String editBlog(@PathVariable("id")Long id, Blog blog,HttpSession session){
        String tagIds = blog.getTagIds();
        Blog one = blogService.findById(id);
        blog.setCreateTime(one.getCreateTime());
        blog.setUpdateTime(new Date());
        blog.setViews(one.getViews());
        blog.setTags(tagService.findById(tagIds));
        blog.setUser((User) session.getAttribute("user"));
        blogService.save(blog);

        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        blogService.delete(id);
        return "redirect:/admin/blogs";
    }
}
