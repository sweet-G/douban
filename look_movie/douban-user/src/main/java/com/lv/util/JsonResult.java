package com.lv.util;

public class JsonResult {

	
	private String state;
	private Object data;
	private String message;

	public static JsonResult success() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setState(Config.JSON_STATE_SUCCESS);
		return jsonResult;
	}

	public static JsonResult success(Object data) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setState(Config.JSON_STATE_SUCCESS);
		jsonResult.setData(data);
		return jsonResult;
	}

	public static JsonResult error(String message) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setState(Config.JSON_STATE_ERROR);
		jsonResult.setMessage(message);
		return jsonResult;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
