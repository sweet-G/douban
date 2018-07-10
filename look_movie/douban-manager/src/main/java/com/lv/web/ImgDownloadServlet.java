package com.lv.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/img/download")
public class ImgDownloadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("file"); // 23232sdsdaa231dfdsf.jpg 服务器存储的文件名称
		String name = req.getParameter("name"); // 我的照片.jpg 下载文件的名称

		fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");

		File file = new File("E:/src/img/upload", fileName);
		if (file.exists()) {
			FileInputStream input = new FileInputStream(file);
			OutputStream output = resp.getOutputStream();

			// 如果name不为空，则下载图片
			if (StringUtils.isNotEmpty(name)) {
				// 设置mime类型
				resp.setContentType("application/octet-stream");
				// 设置文件大小
				resp.setContentLength((int) file.length());
				// 设置下载文件名
				resp.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
			}

			IOUtils.copy(input, output);

			output.flush();
			output.close();
			input.close();

		} else {
			resp.sendError(404, "该资源未找到");
		}

	}

}
