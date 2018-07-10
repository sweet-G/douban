package com.lv.util;

public class JsonResult {

	public static final String JSON_STATE_SUCCESS = "success";
	public static final String JSON_STATE_ERROR = "error";

	private String state;
	private Object obj;
	private String message;

	public static JsonResult success() {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setState(JsonResult.JSON_STATE_SUCCESS);
		return jsonResult;
	}
	
	public static JsonResult success(Object data) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setState(JsonResult.JSON_STATE_SUCCESS);
		jsonResult.setObj(data);
		return jsonResult;
	}
	public static JsonResult error(String message) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setState(JsonResult.JSON_STATE_ERROR);
		jsonResult.setMessage(message);
		return jsonResult;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
