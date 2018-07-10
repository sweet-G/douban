package com.kaishengit.web.movie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.commons.io.IOUtils;

import com.kaishengit.entity.Cofig;
import com.kaishengit.web.BaseServlet;

@WebServlet("/img/upload")
@MultipartConfig
public class ImageUploadServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			// 2.获得文件元素
			Part part = req.getPart("file");
			
			// 2.1 原始文件名称
			String contentDisposition = part.getHeader("Content-Disposition");
			
			String fileName = contentDisposition.split(";")[2].split("\"")[1];
			fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
			
			// 2.2 文件的二进制流
			InputStream input = part.getInputStream();
			OutputStream output = new FileOutputStream(new File(Cofig.get("upload.path"),fileName));
			
			IOUtils.copy(input, output);
			
			output.flush();
			output.close();
			input.close();
			
			Map<String,Object> urlMap = new HashMap<>();

			urlMap.put("success", true);
			urlMap.put("file_path", Cofig.get("download.prex") + fileName);
			urlMap.put("fileName", fileName);
			sendJson(urlMap, resp);
		}
		
	
	}
