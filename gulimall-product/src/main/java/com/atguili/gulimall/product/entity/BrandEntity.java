package com.atguili.gulimall.product.entity;

import com.atguili.common.valid.AddGroup;
import com.atguili.common.valid.ListValue;
import com.atguili.common.valid.UpdateGroup;
import com.atguili.common.valid.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 *
 *	分组校验：方式
 *		1、在校验注解上加 “groups” ， groups 是需要 interface Class 类型的参数。
 *		2、建立相应的 Class （如：AddGroup.class， UpdateGroup.class）
 *		3、在相应的 controller 的方法上加上注解和参数（如：@Validated({AddGroup.class}) 或 @Validated({AddGroup.class, UpdateGroup.class})）
 *		4、默认没有指定分组的 校验注解 只有在	@Validated 后面不指定任何组的时候生效
 *	自定义校验
 *		1、编写自定义校验注解
 *		2、编写自定义校验器
 *		3、用校验器 校验 注解
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 *
	 * 分组校验
	 * 不加 groups 而且没有相应的参数  默认是不校验的
	 */
	@TableId
	@Null(groups = {AddGroup.class}, message = "添加品牌的时候不需要制定品牌id") // 添加的时候不需要指定品牌id
	@NotNull(groups = {UpdateGroup.class, UpdateStatusGroup.class}, message = "修改品牌的时候需要制定品牌id")
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
	@NotBlank(groups = {AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(groups = {AddGroup.class, UpdateGroup.class, UpdateStatusGroup.class})
	@ListValue(value={0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class}) // 自定义验证注解
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
//	@Pattern(regexp = "d{0,10}$", message = "0到10之间", groups = {AddGroup.class, UpdateGroup.class} )
	@NotEmpty
	private String firstLetter;
	/**
	 * 排序
	 */

	@Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, UpdateGroup.class} )
	@NotNull
	private Integer sort;

}
