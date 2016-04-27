package com.cpersicum.modules.mobiles;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobileServlet extends HttpServlet {
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String jse = "request.getSession().getId()";
		out.println(jse);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setContentType("text/xml");

		String requestCode = request.getParameter("requestCode");
		String requestData = request.getParameter("requestData");
		PrintWriter out = response.getWriter();
		MobileOption mobileOption = new MobileOption();
		mobileOption.option(requestCode, requestData,
				"request.getSession().getId()", out);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.mobiles.MobileServlet JD-Core Version: 0.5.3
 */