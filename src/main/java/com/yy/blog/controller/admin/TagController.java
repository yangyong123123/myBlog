package com.yy.blog.controller.admin;

import com.yy.blog.domian.Tag;
import com.yy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    /**
     * 分类页
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable , Model model){
        model.addAttribute("page",tagService.findAll(pageable));
        return "admin/tags";
    }

    /**
     * 新增分类页
     * @return
     */
    @RequestMapping("/tags/save")
    public String save(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
     * 新增分类
     * @param name
     * @return
     */
    @PostMapping("/tags/saveTag")
    public String saveTag(String name){
        Tag tag = new Tag();
        tag.setName(name);
        tagService.save(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 修改标签页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/tags/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Tag tag = tagService.findById(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    /**
     * 修改标签功能
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/tags/editTag/{id}")
    public String editTag(@PathVariable("id") Long id,String name){
        Tag tag = tagService.findById(id);
        tag.setName(name);
        tagService.save(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 删除标签功能
     * @param id
     * @return
     */
    @RequestMapping("/tags/delete/{id}")
    public String delete(@PathVariable Long id){
        Tag tag = tagService.findById(id);
        tagService.delete(tag);
        return "redirect:/admin/tags";
    }
}
