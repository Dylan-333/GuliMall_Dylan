package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest extends TestCase {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void testQueryPage() {
    }

    @Test
    public void testListWithTree() {
        List<CategoryEntity> categoryEntities = categoryService.listWithTree();
//        categoryEntities.forEach(System.out::println);
    }

}