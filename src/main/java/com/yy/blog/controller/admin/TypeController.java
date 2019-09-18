package com.yy.blog.controller.admin;

import com.yy.blog.domian.Type;
import com.yy.blog.service.TypeService;
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
public class TypeController {
    @Autowired
    TypeService typeService;

    /**
     * 分类页
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable , Model model){
        model.addAttribute("page",typeService.findAll(pageable));
        return "admin/types";
    }

    /**
     * 新增分类页
     * @return
     */
    @RequestMapping("/types/save")
    public String save(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param name
     * @param model
     * @return
     */
    @PostMapping("/types/saveType")
    public String saveType(String name,Model model){
        Type type = new Type();
        type.setName(name);
        typeService.save(type);
        return "redirect:/admin/types";
    }

    /**
     * 修改标签页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/types/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Type type = typeService.findById(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    /**
     * 修改标签功能
     * @param id
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/types/editType/{id}")
    public String editType(@PathVariable("id") Long id,String name,Model model){
        Type type = typeService.findById(id);
        type.setName(name);
        typeService.save(type);
        return "redirect:/admin/types";
    }

    /**
     * 删除标签功能
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/types/delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        Type type = typeService.findById(id);
        typeService.delete(type);
        return "redirect:/admin/types";
    }
}
