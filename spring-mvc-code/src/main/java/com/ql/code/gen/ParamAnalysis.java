package com.ql.code.gen;

import com.ql.code.config.GenConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数解析
 *
 * @author wanqiuli
 * @date 2022/3/22 12:58
 */
@Component
public class ParamAnalysis {

    @Resource
    private GenConfig genConfig;

    public Map<String, Object> getBaseMap() {
        HashMap<String, Object> baseMap = new HashMap<>();
        baseMap.put("id", genConfig.getId());
        // 路径
        baseMap.put("base_package", genConfig.getPackageRoute());
        baseMap.put("project_name", genConfig.getProjectName());
        return baseMap;
    }
}
