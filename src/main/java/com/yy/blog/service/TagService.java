package com.yy.blog.service;

import com.yy.blog.dao.TagDao;
import com.yy.blog.domian.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TagService {
    @Autowired
    TagDao tagDao;

    public Page<Tag> findAll(Pageable pageable){
        return tagDao.findAll(pageable);
    }
    public List<Tag> findAll(){
        return tagDao.findAll();
    }
    public void save(Tag tag) {
        tagDao.save(tag);
    }

    public Tag findById(Long id) {
        return tagDao.getOne(id);
    }
    public List<Tag> findById(String id) {
        return tagDao.findAllById(convertToList(id));
    }
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    public void delete(Tag tag) {
        tagDao.delete(tag);
    }
}
