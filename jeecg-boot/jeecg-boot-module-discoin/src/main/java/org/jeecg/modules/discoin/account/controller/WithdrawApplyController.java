package org.jeecg.modules.discoin.account.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.discoin.account.entity.WithdrawApply;
import org.jeecg.modules.discoin.account.service.IWithdrawApplyService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 提币申请Controller组件
 * @Author: lufer
 * @Date: 2021-09-07
 * @Version: V1.0
 */
@Api(tags="提币申请")
@RestController
@RequestMapping("/withdrawApply")
@Slf4j
public class WithdrawApplyController extends JeecgController<WithdrawApply, IWithdrawApplyService> {

    /**
     *提币申请Service组件
     */
	@Autowired
	private IWithdrawApplyService withdrawApplyService;
	
	/**
     * 分页列表查询
     * @param withdrawApply
     * @param pageNo 页码（从第几页开始查询，默认从第1页开始查询）
     * @param pageSize 每页条目（每页显示多少条数据，默认显示10条）
     * @param request 请求对象
     * @return withdrawApply分页列表集合
     */
	@AutoLog(value = "提币申请-分页列表查询")
	@ApiOperation(value="提币申请-分页列表查询", notes="提币申请-分页列表查询")
	@GetMapping("/list")
	public Result<?> queryPageList(WithdrawApply withdrawApply,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WithdrawApply> queryWrapper = QueryGenerator.initQueryWrapper(withdrawApply, req.getParameterMap());
		Page<WithdrawApply> page = new Page<WithdrawApply>(pageNo, pageSize);
		IPage<WithdrawApply> pageList = withdrawApplyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 * @param withdrawApply
	 * @return
	 */
	@AutoLog(value = "提币申请-添加")
	@ApiOperation(value="提币申请-添加", notes="提币申请-添加")
	@PostMapping("/add")
	public Result<?> add(@RequestBody WithdrawApply withdrawApply) {
		withdrawApplyService.save(withdrawApply);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param withdrawApply
	 * @return
	 */
	@AutoLog(value = "提币申请-编辑")
	@ApiOperation(value="提币申请-编辑", notes="提币申请-编辑")
	@PutMapping("/edit")
	public Result<?> edit(@RequestBody WithdrawApply withdrawApply) {
		withdrawApplyService.updateById(withdrawApply);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "提币申请-通过id删除")
	@ApiOperation(value="提币申请-通过id删除", notes="提币申请-通过id删除")
	@DeleteMapping("/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		withdrawApplyService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "提币申请-批量删除")
	@ApiOperation(value="提币申请-批量删除", notes="提币申请-批量删除")
	@DeleteMapping("/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.withdrawApplyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "提币申请-通过id查询")
	@ApiOperation(value="提币申请-通过id查询", notes="提币申请-通过id查询")
	@GetMapping("/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WithdrawApply withdrawApply = withdrawApplyService.getById(id);
		if(withdrawApply==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(withdrawApply);
	}

    /**
     * 导出excel
     * @param request
     * @param withdrawApply
     */
    @RequestMapping("/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WithdrawApply withdrawApply) {
        return super.exportXls(request, withdrawApply, WithdrawApply.class, "提币申请");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, WithdrawApply.class);
    }

}
