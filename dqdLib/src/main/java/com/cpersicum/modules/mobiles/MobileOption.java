package com.cpersicum.modules.mobiles;

import com.alibaba.fastjson.JSON;
import com.cpersicum.modules.mobiles.bean.RequestBean;
import com.cpersicum.modules.mobiles.bean.ResponseBean;
import com.cpersicum.modules.mobiles.config.Config;
import com.cpersicum.modules.mobiles.config.PlaceholderConfigurer;
import com.cpersicum.modules.utils.Exceptions;
import com.cpersicum.modules.utils.spring.SpringContextHolder;
import com.cpersicum.modules.utils.validator.ValidatorHolder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.dozer.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobileOption {
	private static Logger logger = LoggerFactory.getLogger(MobileOption.class);

	private static String SIGN_BEGIN = "127308823~!$#@^%asqwc中方向";
	private RequestBean requestBean;
	private MobileServer serverBean;
	private Config requestConfig;
	private ResponseBean resultData;
	private String xml;

	public void option(String requestCode, String jsonData, String sessionId,
			Writer writer) {
		ResponseBean responseBean;
		try {
			init(requestCode, jsonData);

			xmlToBean(requestCode, sessionId);

			String message = validatorBean();
			if (!(StringUtils.isEmpty(message))) {
				responseBean = new ResponseBean();
				responseBean.setHeadData(false, "-100", message);

				responseData(responseBean, writer);
				return;
			}

			setServerBean();
			this.serverBean.setRequesData(this.requestBean);

			Method method = ReflectionUtils.getMethod(this.serverBean,
					this.requestConfig.getMethodName());

			this.resultData = ((ResponseBean) ReflectionUtils.invoke(method,
					this.serverBean, null));

			responseData(this.resultData, writer);
		} catch (Exception e) {
			logger.error("errorCode=9001,系统出错", e);
			responseBean = new ResponseBean();
			responseBean.setHeadData(false, "9001", "服务器出现异常，无法响应您的请求！");
			responseData(responseBean, writer);
		}
	}

	private String validatorBean() {
		return ValidatorHolder.convertMessage(
				ValidatorHolder.validate(this.requestBean, new Class[0]), ",");
	}

	private void init(String requestCode, String jsonData) {
		getRequestConfig(requestCode);
		unzipXml(jsonData);
	}

	private void getRequestConfig(String requestCode) {
		this.requestConfig = PlaceholderConfigurer.getConfig(requestCode);
	}

	private void unzipXml(String xmlData) {
		this.xml = xmlData;
	}

	private void xmlToBean(String requestCode, String sessionId) {
		this.requestBean = ((RequestBean) JSON.parseObject(this.xml,
				this.requestConfig.getXmlBeanClass()));
	}

	private void setServerBean() {
		this.serverBean = ((MobileServer) SpringContextHolder
				.getBean(this.requestConfig.getServerName()));
	}

	private void responseData(ResponseBean responseBean, Writer writer) {
		try {
			writer.write(JSON.toJSONString(responseBean));
		} catch (IOException e) {
			e.printStackTrace();
			Exceptions.unchecked(e);
		}
	}

	private String getMD5(byte[] source) {
		String s = null;
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte[] tmp = md.digest();

			char[] str = new char[32];

			int k = 0;
			for (int i = 0; i < 16; ++i) {
				byte byte0 = tmp[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}

			s = new String(str);
			return s;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.mobiles.MobileOption JD-Core Version: 0.5.3
 */