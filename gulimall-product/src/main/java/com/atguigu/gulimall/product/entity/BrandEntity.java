package com.atguigu.gulimall.product.entity;

import com.atguigu.common.valid.AddGroup;
import com.atguigu.common.valid.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌
 * 
 * @author Dylan
 * @email Dylanquq@gmail.com
 * @date 2024-04-19 23:04:34
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@NotNull(message="修改必须指定品牌id",groups = { UpdateGroup.class})
	@Null(message="新增不能指定id",groups = { AddGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名必须提交",groups = { AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "log必须是合法地址",groups = { AddGroup.class,UpdateGroup.class})
	@NotEmpty(groups = { AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(groups = { AddGroup.class})
	@Pattern(regexp = "/[a-zA-Z]",message = "检索首字母必须是一个字母",groups = { AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull
	@Min(value=0,message = "排序必须大于等于0",groups = { AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
