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
 * @Description: 账户流水实体类
 * @Author: lufer
 * @Date: 2021-09-07
 * @Version: V1.0
 */
@Data
@TableName("zl_account_flow")
@ApiModel(value="zl_account_flow对象", description="账户流水")
public class AccountFlow implements Serializable {
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
	 *账户ID
	 */
	@Excel(name = "账户ID", width = 15)
    @ApiModelProperty(value = "账户ID")
    private java.lang.Integer accountId;

	/**
	 *账户变动类型
	 */
	@Excel(name = "账户变动类型", width = 15)
    @ApiModelProperty(value = "账户变动类型")
    private java.lang.String changeType;

	/**
	 *变动金额
	 */
	@Excel(name = "变动金额", width = 15)
    @ApiModelProperty(value = "变动金额")
    private java.math.BigDecimal changeAmount;

	/**
	 *变动前余额
	 */
	@Excel(name = "变动前余额", width = 15)
    @ApiModelProperty(value = "变动前余额")
    private java.math.BigDecimal beforeAmount;

	/**
	 *变动后余额
	 */
	@Excel(name = "变动后余额", width = 15)
    @ApiModelProperty(value = "变动后余额")
    private java.math.BigDecimal afterAmount;

	/**
	 *备注
	 */
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;

}
