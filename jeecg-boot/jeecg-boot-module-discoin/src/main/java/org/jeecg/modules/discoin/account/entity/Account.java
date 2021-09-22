package org.jeecg.modules.discoin.account.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 账户实体类
 * @Author: lufer
 * @Date: 2021-09-06
 * @Version: V1.0
 */
@Data
@TableName("zl_account")
@ApiModel(value="zl_account对象", description="账户")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;

	/**
	 *创建人
	 */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;

	/**
	 *创建日期
	 */
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;

	/**
	 *更新人
	 */
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;

	/**
	 *更新日期
	 */
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;

	/**
	 *所属部门
	 */
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;

	/**
	 *账户地址
	 */
	@Excel(name = "账户地址", width = 15)
    @ApiModelProperty(value = "账户地址")
    private java.lang.String token;

	/**
	 *账户名称
	 */
	@Excel(name = "账户名称", width = 15)
    @ApiModelProperty(value = "账户名称")
    private java.lang.String userId;

	/**
	 *账户余额
	 */
	@Excel(name = "账户余额", width = 15)
    @ApiModelProperty(value = "账户余额")
    private java.math.BigDecimal balance;

	/**
	 *冻结金额
	 */
	@Excel(name = "冻结金额", width = 15)
    @ApiModelProperty(value = "冻结金额")
    private java.math.BigDecimal lockedAmount;

	/**
	 *累计金额
	 */
	@Excel(name = "累计金额", width = 15)
    @ApiModelProperty(value = "累计金额")
    private java.math.BigDecimal totalAmount;

	/**
	 *今日收益
	 */
	@Excel(name = "今日收益", width = 15)
    @ApiModelProperty(value = "今日收益")
    private java.math.BigDecimal todayProfit;

	/**
	 *预计收益
	 */
	@Excel(name = "预计收益", width = 15)
    @ApiModelProperty(value = "预计收益")
    private java.math.BigDecimal predictProfit;

	/**
	 *收益周期
	 */
	@Excel(name = "收益周期", width = 15)
    @ApiModelProperty(value = "收益周期")
    private java.lang.Integer profitTerm;

	/**
	 *收益类型
	 */
	@Excel(name = "收益类型", width = 15)
    @ApiModelProperty(value = "收益类型")
    private java.lang.String profitType;

	/**
	 *收益占比
	 */
	@Excel(name = "收益占比", width = 15)
    @ApiModelProperty(value = "收益占比")
    private java.math.BigDecimal profitRadio;

	/**
	 *固定收益
	 */
	@Excel(name = "固定收益", width = 15)
    @ApiModelProperty(value = "固定收益")
    private java.math.BigDecimal fixedProfit;

	/**
	 *每日提币上限
	 */
	@Excel(name = "每日提币上限", width = 15)
    @ApiModelProperty(value = "每日提币上限")
    private java.math.BigDecimal dayLimitAmount;

	/**
	 *管理费
	 */
	@Excel(name = "管理费", width = 15)
    @ApiModelProperty(value = "管理费")
    private java.math.BigDecimal managementFee;

	/**
	 *手续费
	 */
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
    private java.math.BigDecimal tradeFee;

}
