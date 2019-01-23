package chnp.spring.controller;

import chnp.spring.common.entity.ResponseJson;
import chnp.spring.common.service.LocalCacheUtils;
import chnp.spring.model.domain.ModuleInfo;
import chnp.spring.service.ModuleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/service")
public class ServiceController {
	private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	private LocalCacheUtils localCacheUtils;
	@Autowired
	private ModuleInfoService moduleInfoService;


	@ResponseBody
	@RequestMapping("/register")
	public String register(@RequestParam(name = "moduleNo")String moduleNo) {
		ResponseJson responseJson = new ResponseJson();

		ModuleInfo moduleInfo = moduleInfoService.getByModuleNo(moduleNo);
		if (null == moduleInfo) {
			log.info("模块{}不存在", moduleNo);
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("指定模块不存在");
		}else if (1 == moduleInfo.getModuleStatus()) {
			log.info("模块{}不可用", moduleNo);
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("指定模块不可用");
		}else {
			if (1 == moduleInfo.getServiceStatus())
				moduleInfoService.register(moduleInfo.getModuleId());
			log.info("注册模块{}", moduleNo);

			// 返回下次心跳的间隔时长。默认：10秒
			String heartInterval = localCacheUtils.getConfig("cmServiceHeartInterval");
			responseJson.put("heartInterval", StringUtils.isEmpty(heartInterval.trim()) ? 10 : Integer.valueOf(heartInterval.trim()));

			responseJson.setResultCode(ResponseJson.RETURN_CODE_SUCC);
		}
		return responseJson.toJSONString();
	}

	@ResponseBody
	@RequestMapping("/heart")
	public String heart(@RequestParam(name = "moduleNo")String moduleNo) {
		ResponseJson responseJson = new ResponseJson();

		ModuleInfo moduleInfo = moduleInfoService.getByModuleNo(moduleNo);
		if (null == moduleInfo) {
			log.info("模块{}不存在", moduleNo);
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("指定模块不存在");
		}else if (1 == moduleInfo.getModuleStatus()) {
			log.info("模块{}不可用", moduleNo);
			localCacheUtils.delService(moduleInfo.getModuleId());
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("指定模块不可用");
		}else if (1 == moduleInfo.getServiceStatus()) {
			log.info("模块{}已下线", moduleNo);
			localCacheUtils.delService(moduleInfo.getModuleId());
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("指定模块已下线");
		}else {
			if (1 == moduleInfo.getHeartStatus()) {
				moduleInfoService.startHeart(moduleInfo.getModuleId());
				log.info("模块" + moduleInfo.getModuleNo() + "心跳复苏");
			}
			localCacheUtils.addService(moduleInfo);

			// 返回下次心跳的间隔时长。默认：10秒
			String heartInterval = localCacheUtils.getConfig("cmServiceHeartInterval");
			responseJson.put("heartInterval", StringUtils.isEmpty(heartInterval.trim()) ? 10 : Integer.valueOf(heartInterval.trim()));

			responseJson.setResultCode(ResponseJson.RETURN_CODE_SUCC);
		}
		return responseJson.toJSONString();
	}

	@ResponseBody
	@RequestMapping("/cancel")
	public String cancel(@RequestParam(name = "moduleNo")String moduleNo) {
		ResponseJson responseJson = new ResponseJson();

		ModuleInfo moduleInfo = moduleInfoService.getByModuleNo(moduleNo);
		if (null == moduleInfo) {
			log.info("模块{}不存在", moduleNo);
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("指定模块不存在");
		}else {
			moduleInfoService.cancel(moduleInfo.getModuleId());
			localCacheUtils.delService(moduleInfo.getModuleId());
			log.info("模块{}被注销", moduleNo);

			responseJson.setResultCode(ResponseJson.RETURN_CODE_SUCC);
		}
		return responseJson.toJSONString();
	}

	@ResponseBody
	@RequestMapping("/query")
	public String query(@RequestParam(name = "moduleType")Integer type) {
		ResponseJson responseJson = new ResponseJson();

		List<ModuleInfo> moduleInfos = localCacheUtils.findService(type, 0);
		if (0 < moduleInfos.size()) {
			responseJson.put("modules", moduleInfos);

			// 返回负载策略。默认：按终端ID分配
			String as = localCacheUtils.getConfig("cmServerLoadStrategy");
			responseJson.put("accessStrategy", StringUtils.isEmpty(as) ? 1 : Integer.valueOf(as));

			// 返回终端刷新接口地址的间隔时长。默认：10秒
			String rf = localCacheUtils.getConfig("cmClientRefreshTime");
			responseJson.put("refreshTime", StringUtils.isEmpty(rf) ? 10 : Integer.valueOf(rf));

			// 返回同步的间隔时长。默认：10秒
			String si = localCacheUtils.getConfig("cmServerSynchInterval");
			responseJson.put("synchInterval", StringUtils.isEmpty(si) ? 10 : Integer.valueOf(si));

			responseJson.setResultCode(ResponseJson.RETURN_CODE_SUCC);
			log.info("可用服务查询结果：{}", responseJson.toJSONString());
		}else {
			log.info("服务查询失败！没有可用的服务。");
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("没有可用的服务");
		}
		return responseJson.toJSONString();
	}

	@ResponseBody
	@RequestMapping("/query_debug")
	public String queryDebug(@RequestParam(name = "moduleType")Integer type) {
		ResponseJson responseJson = new ResponseJson();

		List<ModuleInfo> moduleInfos = localCacheUtils.findService(type, 2);
		if (0 < moduleInfos.size()) {
			ModuleInfo moduleInfo = moduleInfos.get(0);

			// 返回调试状态的地址
			responseJson.put("intranetUrl", moduleInfo.getIntranetUrl());

			// 返回同步的间隔时长。默认：10秒
			String si = localCacheUtils.getConfig("cmServerSynchInterval");
			responseJson.put("synchInterval", null == si ? 10 : Integer.valueOf(si));

			// 返回用于调试的终端列表
			Set<String> ds = new HashSet<>();
			String debugStbids = localCacheUtils.getConfig("cmDebugStbIds");
			if (!StringUtils.isEmpty(debugStbids)) {
				String[] stbIds = debugStbids.split(",");
				Collections.addAll(ds, stbIds);
			}
			responseJson.put("stbIds", ds);

			responseJson.setResultCode(ResponseJson.RETURN_CODE_SUCC);
		}else {
			log.info("没有可用的调试服务！");
			responseJson.setResultCode(ResponseJson.RETURN_CODE_FAIL);
			responseJson.setErrorInfo("没有可用的调试服务");
		}
		return responseJson.toJSONString();
	}

}