package com.yy.blog.service;

import com.yy.blog.dao.TypeDao;
import com.yy.blog.domian.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TypeService {
    @Autowired
    TypeDao typeDao;

    public List<Type> findAll(){
        return typeDao.findAll();
    }

    public Page<Type> findAll(Pageable pageable){
        return typeDao.findAll(pageable);
    }

    public void save(Type type) {
        typeDao.save(type);
    }

    public Type findById(Long id) {
        return typeDao.getOne(id);
    }

    public void delete(Type type) {
        typeDao.delete(type);
    }
}
