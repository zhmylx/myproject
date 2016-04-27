package com.cpersicum.modules.utils;

import com.alibaba.fastjson.JSON;
import com.cpersicum.modules.entity.vo.Page;
import com.cpersicum.modules.entity.vo.ShiroUser;
import com.cpersicum.modules.orm.QueryCondition;
import com.cpersicum.modules.utils.security.SystemUserInfo;
import com.cpersicum.modules.utils.web.WebUtil;
import com.cpersicum.modules.utils.web.struts2.Struts2Utils;
import javax.servlet.http.HttpServletRequest;

public class DataSearchUtils {
	public static <T> Page<T> BuildPage(QueryCondition parameter) {
		HttpServletRequest request = Struts2Utils.getRequest();

		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		++pageIndex;
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		String sortField = request.getParameter("sortField");
		String sortOrder = request.getParameter("sortOrder");
		Page page = new Page();
		page.setPageNo(pageIndex);
		page.setPageSize(pageSize);
		page.setOrderBy(sortField);
		page.setOrder(sortOrder);

		parameter = (QueryCondition) WebUtil.requestParam2Bean(request,
				parameter);
		parameter.setOrderBy(sortField);
		parameter.setOrder(sortOrder);
		parameter.setPageNo(pageIndex);
		parameter.setPageSize(pageSize);
		setCurrnetUserInfo(parameter);
		page.setParameter(parameter);
		return page;
	}

	private static void setCurrnetUserInfo(QueryCondition parameter) {
		ShiroUser shiroUser = SystemUserInfo.getCurrnetUser();
		parameter.setCurrnetUserLoginName(shiroUser.getLoginName());
		parameter.setCurrnetUserDeptNo(shiroUser.getDeptNo());
	}

	public static String BuildPage2Json(Page page) {
		MinPage minPage = new MinPage();
		minPage.setTotal(page.getTotalItems());
		minPage.setData(page.getResult());
		String json = JSON.toJSONString(minPage);
		return json;
	}
}
