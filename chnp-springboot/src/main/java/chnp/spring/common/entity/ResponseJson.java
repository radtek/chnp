package chnp.spring.common.entity;

import com.alibaba.fastjson.JSONObject;

public class ResponseJson extends JSONObject {
	public static final int RETURN_CODE_SUCC = 1;
	public static final int RETURN_CODE_FAIL = 0;

	public void setResultCode(int code) {
		this.put("resultCode", code);
	}

	public void setErrorInfo(String error) {
		this.put("errorInfo", error);
	}

}