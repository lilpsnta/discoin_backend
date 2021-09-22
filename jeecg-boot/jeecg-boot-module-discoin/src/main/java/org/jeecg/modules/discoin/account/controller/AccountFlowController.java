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
import org.jeecg.modules.discoin.account.entity.AccountFlow;
import org.jeecg.modules.discoin.account.service.IAccountFlowService;

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
 * @Description: 账户流水Controller组件
 * @Author: lufer
 * @Date: 2021-09-07
 * @Version: V1.0
 */
@Api(tags="账户流水")
@RestController
@RequestMapping("/accountFlow")
@Slf4j
public class AccountFlowController extends JeecgController<AccountFlow, IAccountFlowService> {

    /**
     *账户流水Service组件
     */
	@Autowired
	private IAccountFlowService accountFlowService;
	
	/**
     * 分页列表查询
     * @param accountFlow
     * @param pageNo 页码（从第几页开始查询，默认从第1页开始查询）
     * @param pageSize 每页条目（每页显示多少条数据，默认显示10条）
     * @param request 请求对象
     * @return accountFlow分页列表集合
     */
	@AutoLog(value = "账户流水-分页列表查询")
	@ApiOperation(value="账户流水-分页列表查询", notes="账户流水-分页列表查询")
	@GetMapping("/list")
	public Result<?> queryPageList(AccountFlow accountFlow,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AccountFlow> queryWrapper = QueryGenerator.initQueryWrapper(accountFlow, req.getParameterMap());
		Page<AccountFlow> page = new Page<AccountFlow>(pageNo, pageSize);
		IPage<AccountFlow> pageList = accountFlowService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 * @param accountFlow
	 * @return
	 */
	@AutoLog(value = "账户流水-添加")
	@ApiOperation(value="账户流水-添加", notes="账户流水-添加")
	@PostMapping("/add")
	public Result<?> add(@RequestBody AccountFlow accountFlow) {
		accountFlowService.save(accountFlow);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param accountFlow
	 * @return
	 */
	@AutoLog(value = "账户流水-编辑")
	@ApiOperation(value="账户流水-编辑", notes="账户流水-编辑")
	@PutMapping("/edit")
	public Result<?> edit(@RequestBody AccountFlow accountFlow) {
		accountFlowService.updateById(accountFlow);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户流水-通过id删除")
	@ApiOperation(value="账户流水-通过id删除", notes="账户流水-通过id删除")
	@DeleteMapping("/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		accountFlowService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "账户流水-批量删除")
	@ApiOperation(value="账户流水-批量删除", notes="账户流水-批量删除")
	@DeleteMapping("/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.accountFlowService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户流水-通过id查询")
	@ApiOperation(value="账户流水-通过id查询", notes="账户流水-通过id查询")
	@GetMapping("/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AccountFlow accountFlow = accountFlowService.getById(id);
		if(accountFlow==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(accountFlow);
	}

    /**
     * 导出excel
     * @param request
     * @param accountFlow
     */
    @RequestMapping("/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AccountFlow accountFlow) {
        return super.exportXls(request, accountFlow, AccountFlow.class, "账户流水");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AccountFlow.class);
    }

}
