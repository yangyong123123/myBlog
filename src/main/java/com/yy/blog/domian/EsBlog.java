package com.yy.blog.domian;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "blogs",type = "t_blog")
public class EsBlog {
    private Long id;
    private Long blogId;
    private String title;
    private String content;
    private String description;
}
