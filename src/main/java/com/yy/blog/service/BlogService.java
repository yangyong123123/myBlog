package com.yy.blog.service;

import com.yy.blog.dao.BlogDao;
import com.yy.blog.dao.TagDao;
import com.yy.blog.dao.TypeDao;
import com.yy.blog.domian.Blog;
import com.yy.blog.domian.BlogQuery;
import com.yy.blog.domian.Tag;
import com.yy.blog.domian.Type;
import com.yy.blog.utils.MarkdownUtils;
import com.yy.blog.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@Transactional
public class BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    TypeDao typeDao;
    @Autowired
    TagDao tagDao;

    public Blog findById(Long id) {
        return blogDao.getOne(id);
    }

    public List<Blog> findAll(){
        return blogDao.findAll();
    }

    public List<Blog> findAllByType(Long id) {
        Type type = typeDao.getOne(id);
        return blogDao.findBlogsByType(type);
    }
    public List<Blog> findAllByTag(Long id) {
        return blogDao.findBlogsByTag("%"+id+"%");
    }

    public List<Blog> findAll(String query){
        return blogDao.findByLike(query);
    }
    public Page<Blog> findAll(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    public List<Blog> findRecommendBlog(){
        boolean b = true;
        return blogDao.findByRecommend(b);
    }

    public Page<Blog> findAll(BlogQuery blog, Pageable pageable) {

        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }

        }, pageable);
    }

    public void delete(Long id) {
        blogDao.deleteById(id);
    }

    public Blog save(Blog blog) {
        return blogDao.save(blog);
    }
    public Blog update(Long id ,Blog blog){
        Blog one = blogDao.getOne(id);
        BeanUtils.copyProperties(blog,one, MyBeanUtils.getNullPropertyNames(blog));
        one.setUpdateTime(new Date());
        return blogDao.save(one);
    }

    public Blog getAndConvert(Long id) {
        Blog blog = blogDao.getOne(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogDao.updateViews(id);
        return b;
    }

    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            System.out.println(year);
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    public Long countBlog() {
        return blogDao.count();
    }
}
