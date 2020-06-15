package com.web.controller;

import com.web.enums.ResponseCodeEnum;
import com.web.utils.StringUtils;

/**
 * controller通用父类，用于封装通用参数和函数的
 */
public class BaseController {

	public String ResponseJson(String status, ResponseCodeEnum responseCodeEnum, Object data) {

		return StringUtils.jsonUtils
				.toJson(new Response(status, responseCodeEnum.getCode(), responseCodeEnum.getMessage(), data));
	}
}



