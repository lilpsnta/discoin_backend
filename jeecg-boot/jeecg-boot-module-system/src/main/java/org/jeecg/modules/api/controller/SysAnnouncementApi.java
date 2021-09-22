package org.jeecg.modules.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.CommonSendStatus;
import org.jeecg.common.constant.WebsocketConst;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.message.websocket.WebSocket;
import org.jeecg.modules.system.entity.SysAnnouncement;
import org.jeecg.modules.system.entity.SysAnnouncementSend;
import org.jeecg.modules.system.service.ISysAnnouncementSendService;
import org.jeecg.modules.system.service.ISysAnnouncementService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Controller
 * @Description: 系统通告表
 * @Author: jeecg-boot
 * @Date: 2019-01-02
 * @Version: V1.0
 */
@Api(tags="系统通知公告APP通用接口")
@RestController
@RequestMapping("/sys/api/annountCement")
@Slf4j
public class SysAnnouncementApi {
	@Autowired
	private ISysAnnouncementService sysAnnouncementService;

	/**
	  * 分页列表查询
	 * @return
	 */
	@ApiOperation("系统通知公告APP通用接口-分页查询系统公告")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<?> queryPageList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		JSONObject result = new JSONObject();
		Page<SysAnnouncement> page = new Page<SysAnnouncement>(pageNo,pageSize);
		IPage<SysAnnouncement> pageList = sysAnnouncementService.page(page);
		return Result.OK(pageList);
	}


	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@ApiOperation("系统通知公告APP通用接口-查询公告详情")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) Integer id) {
		SysAnnouncement sysAnnouncement = sysAnnouncementService.getById(id);
		return Result.OK(sysAnnouncement);
	}

}
