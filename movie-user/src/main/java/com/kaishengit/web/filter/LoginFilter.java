package com.kaishengit.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.User;


public class LoginFilter extends AbstractFilter{

	private List<String> uriList = null;
	@Override
	public void init(FilterConfig config) throws ServletException {
		String  validateUrl = config.getInitParameter("validateUrl");
		String[] urls = validateUrl.split(",");
		uriList = Arrays.asList(urls);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取请求路径
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI(); 
		
		// 如果isFilter的结果式true，则过滤，如果式false则不过滤
		if(isFilter(uri)) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if(user == null) {
				resp.sendRedirect("/login?callback=" + uri);
			} else {
				chain.doFilter(req, resp);
			}
		} else {
			chain.doFilter(req, resp);
		}
	}

	private boolean isFilter(String uri) {
		// 如果uri在uriList中，则需要过滤，如果不在uriList中，则不需要过滤
		if(uriList == null) {
			return false;
		}
		
		for(String str : uriList) {
			if(uri.startsWith(str)) {
				return true;
			}
		}
		return false;
	}

}
