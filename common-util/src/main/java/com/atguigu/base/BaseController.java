package com.atguigu.base;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.base
 * @className: BaseController
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 11:45
 * @version: 1.0
 */
public class BaseController {


    //封装分页信息
    protected Map<String,Object> getFilters(HttpServletRequest Request){
        Enumeration parameterNames = Request.getParameterNames();
        Map<String,Object> filters = new TreeMap<>();
        while (parameterNames!=null&&parameterNames.hasMoreElements()){
            String parameterName  =(String) parameterNames.nextElement();
            String[] values=Request.getParameterValues(parameterName);
            if(values!=null&&values.length!=0){
                if(values.length>1){
                    filters.put(parameterName,values);
                }else {
                    filters.put(parameterName,values[0]);
                }
            }
        }
        if(!filters.containsKey("pageNum")){
            filters.put("pageNum",1);
        }
        if(!filters.containsKey("pageSize")){
            filters.put("pageSize",10);
        }
        return filters;
    }
}
