package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1、查询出所有分类
        List<CategoryEntity> categoryEntities = list();
        // 2、组装成父子的树形结构
        List<CategoryEntity> result = categoryEntities.stream().filter(u->u.getCatLevel().equals(1)).collect(Collectors.toList());
        return result.stream().map((menu)-> {
            menu.setChildren(dfs(menu,categoryEntities));
            return menu;
        }).sorted(Comparator.comparingInt(CategoryEntity::getSort))
        .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        //TODO 1、检查当前删除的菜单，是否被别的地方引用。

        //逻辑删除
        baseMapper.deleteBatchIds(catIds);
    }

    private List<CategoryEntity> dfs(CategoryEntity x,List<CategoryEntity> allList) {
        List<CategoryEntity> children = new ArrayList<>();
        allList.stream().forEach((u)->{
            if(u.getParentCid().equals(x.getCatId())){
                children.add(u);
                u.setChildren(dfs(u,allList));
            }
        });
        return children;
    }

}