/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.piesat.project.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;
import com.piesat.project.common.Result;
import com.piesat.project.common.utils.ShiroUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected HttpSession session;

	@Autowired
	protected ServletContext application;

	/**
	 * 用户ID
	 */
	protected String getCurrentUserId() {
		return ShiroUtils.getUserId();
	}

	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 */
	protected <T> Page<T> getPage() {
		return getPage(10);
	}

	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 * 
	 * @param size
	 *            每页显示数量
	 * @return
	 */
	protected <T> Page<T> getPage(int size) {
		int _size = size, _index = 1;
		if (request.getParameter("_size") != null) {
			_size = Integer.parseInt(request.getParameter("_size"));
		}
		if (request.getParameter("_index") != null) {
			_index = Integer.parseInt(request.getParameter("_index"));
		}
		return new Page<T>(_index, _size);
	}

	/**
	 * 重定向至地址 url
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	protected String redirectTo(String url) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 * @return
	 */
	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 *            序列化特点
	 * @return
	 */
	protected String toJson(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}

	protected <T> T toBean(String json) {
		return JSON.parseObject(json, new TypeReference<T>() {});
	}

	/**
	 * <p>
	 * 自动判定是否有跨域操作,转成字符串并返回
	 * </p>
	 *
	 * @param object
	 * @return 跨域或不跨域的字符串
	 */
	protected String callback(Result object) {
		return callback(object, null);
	}

	protected String callback(Result object, String format) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			/**
			 * 非 JSONP 请求
			 */
			return toJson(object, format);
		}
		StringBuffer json = new StringBuffer();
		json.append(callback);
		json.append("(").append(toJson(object, format)).append(")");
		return json.toString();
	}

	protected String callbackSuccess(Object obj) {
		return callback(new Result(Result.SUCCESS,obj));
	}

	protected String callbackFail(String message) {
		return callback(new Result(Result.ERROR, message));
	}

}
