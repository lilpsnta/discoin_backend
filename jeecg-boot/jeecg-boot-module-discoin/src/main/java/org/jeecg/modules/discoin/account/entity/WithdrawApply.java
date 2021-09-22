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
 * @Description: 提币申请实体类
 * @Author: lufer
 * @Date: 2021-09-07
 * @Version: V1.0
 */
@Data
@TableName("zl_withdraw_apply")
@ApiModel(value="zl_withdraw_apply对象", description="提币申请")
public class WithdrawApply implements Serializable {
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
	 *订单号
	 */
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
    private java.lang.String orderNo;

	/**
	 *账户ID
	 */
	@Excel(name = "账户ID", width = 15)
    @ApiModelProperty(value = "账户ID")
    private java.lang.Integer accountId;

	/**
	 *收币地址
	 */
	@Excel(name = "收币地址", width = 15)
    @ApiModelProperty(value = "收币地址")
    private java.lang.String toAddress;

	/**
	 *提现金额
	 */
	@Excel(name = "提现金额", width = 15)
    @ApiModelProperty(value = "提现金额")
    private java.math.BigDecimal withdrawAmount;

	/**
	 *状态
	 */
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String orderStatus;

	/**
	 *审核人ID
	 */
	@Excel(name = "审核人ID", width = 15)
    @ApiModelProperty(value = "审核人ID")
    private java.lang.String approveUserId;

	/**
	 *审核时间
	 */
	@Excel(name = "审核时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "审核时间")
    private java.util.Date approveTime;

	/**
	 *备注
	 */
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;

}
