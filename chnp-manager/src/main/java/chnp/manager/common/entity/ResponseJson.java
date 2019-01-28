package chnp.manager.common.entity;

import com.alibaba.fastjson.JSONObject;

public class ResponseJson extends JSONObject {

    public void success(String msg) {
        this.put("returnCode", 1);
        this.put("msg", msg);
    }

    public void error(String msg) {
        this.put("returnCode", 0);
        this.put("msg", msg);
    }

}