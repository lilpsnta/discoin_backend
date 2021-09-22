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
import org.jeecg.modules.discoin.account.entity.Account;
import org.jeecg.modules.discoin.account.service.IAccountService;

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
 * @Description: 账户Controller组件
 * @Author: lufer
 * @Date: 2021-09-06
 * @Version: V1.0
 */
@Api(tags="账户")
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController extends JeecgController<Account, IAccountService> {

    /**
     *账户Service组件
     */
	@Autowired
	private IAccountService accountService;
	
	/**
     * 分页列表查询
     * @param account
     * @param pageNo 页码（从第几页开始查询，默认从第1页开始查询）
     * @param pageSize 每页条目（每页显示多少条数据，默认显示10条）
     * @param req 请求对象
     * @return account分页列表集合
     */
	@AutoLog(value = "账户-分页列表查询")
	@ApiOperation(value="账户-分页列表查询", notes="账户-分页列表查询")
	@GetMapping("/list")
	public Result<?> queryPageList(Account account,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Account> queryWrapper = QueryGenerator.initQueryWrapper(account, req.getParameterMap());
		Page<Account> page = new Page<Account>(pageNo, pageSize);
		IPage<Account> pageList = accountService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 * @param account
	 * @return
	 */
	@AutoLog(value = "账户-添加")
	@ApiOperation(value="账户-添加", notes="账户-添加")
	@PostMapping("/add")
	public Result<?> add(@RequestBody Account account) {
		accountService.save(account);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param account
	 * @return
	 */
	@AutoLog(value = "账户-编辑")
	@ApiOperation(value="账户-编辑", notes="账户-编辑")
	@PutMapping("/edit")
	public Result<?> edit(@RequestBody Account account) {
		accountService.updateById(account);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户-通过id删除")
	@ApiOperation(value="账户-通过id删除", notes="账户-通过id删除")
	@DeleteMapping("/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		accountService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "账户-批量删除")
	@ApiOperation(value="账户-批量删除", notes="账户-批量删除")
	@DeleteMapping("/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.accountService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "账户-通过id查询")
	@ApiOperation(value="账户-通过id查询", notes="账户-通过id查询")
	@GetMapping("/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Account account = accountService.getById(id);
		if(account==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(account);
	}

    /**
     * 导出excel
     * @param request
     * @param account
     */
    @RequestMapping("/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Account account) {
        return super.exportXls(request, account, Account.class, "账户");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Account.class);
    }

}
