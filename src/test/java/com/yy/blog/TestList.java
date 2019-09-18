package com.yy.blog;

import com.yy.blog.domian.Tag;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestList {

    @Test
    public void tagsToIds() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("测试1");
        Tag tag1 = new Tag();
        tag1.setId(2L);
        tag1.setName("测试2");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        tags.add(tag1);

        if (!tags.isEmpty()) {

            String join = StringUtils.join(tags, ",");
            System.out.println(join);
        }
    }

    @Test
    public void test(){
        Integer a = new Integer(5);
        Integer b = new Integer(5);
        Integer c = 5;
        Integer d = 5;
        Integer e = 128;
        Integer f = 128;
        int g = 128;
        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(c == d);
        System.out.println(e ==f);
        System.out.println(f == g);
    }
}
