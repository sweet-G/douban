package com.lv.web.filter;

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

public class LoginFilter extends AbstractFilter {

	private List<String> uriList = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		uriList = Arrays.asList(config.getInitParameter("paths").split(","));
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String getUri = req.getRequestURI();
		
		if(isFilter(getUri)) {
			if(req.getSession().getAttribute("user") == null) {
				resp.sendRedirect("/login?path=" + getUri);
			} else {
				chain.doFilter(req, resp);
			}
		} else {
			chain.doFilter(req, resp);
		}
	}

	private boolean isFilter(String getUri) {
		if(uriList == null) {
			return false;
		}
		for(String uri : uriList) {
			if(getUri.startsWith(uri)) {
				return true;
			}
		}
		return false;
	}
	
}
