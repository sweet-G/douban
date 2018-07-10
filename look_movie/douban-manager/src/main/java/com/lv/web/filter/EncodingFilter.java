package com.lv.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter extends AbstractFilter{

	String encoding = "UTF-8";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String encoding = config.getInitParameter("encoding");
		if(encoding == null) {
			this.encoding = encoding;
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}

}
