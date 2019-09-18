package com.yy.blog.dao;

import com.yy.blog.domian.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag,Long> {

}
