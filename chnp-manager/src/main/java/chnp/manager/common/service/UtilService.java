package chnp.manager.common.service;

import chnp.manager.annotatoion.Column;
import chnp.manager.common.entity.DataTables;
import chnp.manager.mvc.model.domain.TsDataItem;
import chnp.manager.mvc.service.TsDataService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilService {

    @Autowired
    private TsDataService tsDataService;

    public void interpret(DataTables dataTables, Class<?> clazz) {
        dataTables.setData(interpret(dataTables.getData(), clazz));
    }

    public JSONArray interpret(List dataset, Class<?> clazz) {
        return interpret((JSONArray) JSONArray.toJSON(dataset), clazz);
    }

    public JSONArray interpret(JSONArray dataset, Class<?> clazz) {
        Field[] at = clazz.getDeclaredFields();
        for (Field fd : at) {
            fd.setAccessible(true);
            if (fd.isAnnotationPresent(Column.class)) {
                Column d = fd.getAnnotation(Column.class);
                if (d.display()) {
                    if (!"".equals(d.dataType().trim())) {
                        // TODO:查询指定字典
                        List<TsDataItem> tsDataItems = tsDataService.findValuesByKey(d.dataType().trim());
                        Map<String, String> mapping = new HashMap<>();
                        if (null != tsDataItems) {
                            for (TsDataItem item : tsDataItems) {
                                mapping.put(item.getItemKey(), item.getItemValue());
                            }
                        }

                        for(int i = 0; i < dataset.size(); i++) {
                            JSONObject json = dataset.getJSONObject(i);
                            Object value = json.get(fd.getName());
                            if (null != value && mapping.containsKey(value.toString())) {
                                json.put(fd.getName() + "Name", mapping.get(value.toString()));
                            }
                        }
                    }
                }else {
                    for (int i = 0; i < dataset.size(); i++) {
                        JSONObject json = dataset.getJSONObject(i);
                        if (String.class == fd.getType() && null != json.get(fd.getName())) {
                            json.put(fd.getName(), "******");
                        }
                    }
                }

            }
        }
        return dataset;
    }

    public List<Map<String, Object>> list2SelectTree(List<Map<String, Object>> list, int pId, Integer level) {
        List<Map<String, Object>> tree = new ArrayList<>();
        for(Map<String, Object> map : list) {
            if (null != map.get("pId") && map.get("pId").equals(pId)){
                Map<String, Object> parent = new HashMap<>();
                parent.put("id", map.get("id"));
                parent.put("name", null == map.get("name") ? "" : createTabs(level) + map.get("name").toString());
                tree.add(parent);
                tree.addAll(list2SelectTree(list, (int) map.get("id"), level + 1));
            }
        }
        return tree;
    }


    public String createTabs(int level){
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i <= level - 1; i++){
            tab.append("&nbsp;&nbsp;");
        }
        return tab.toString();

    }

    public Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        if (null == subject) return null;
        else return subject.getSession();
    }

}