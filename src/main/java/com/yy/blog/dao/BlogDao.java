package com.yy.blog.dao;

import com.yy.blog.domian.Blog;
import com.yy.blog.domian.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor {

    List<Blog> findByRecommend(boolean b);

    List<Blog> findBlogsByType(Type type);

    @Query(value = "SELECT * FROM t_blog WHERE tag_ids LIKE ?", nativeQuery = true)
    List<Blog> findBlogsByTag(String id);

    @Query(value = "SELECT * FROM t_blog WHERE title LIKE ?1 OR content LIKE ?1 OR description LIKE ?1", nativeQuery = true)
    List<Blog> findByLike(String query);

    @Modifying
    @Query(value = "UPDATE t_blog SET views = views + 1 WHERE id = ?", nativeQuery = true)
    void updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

}