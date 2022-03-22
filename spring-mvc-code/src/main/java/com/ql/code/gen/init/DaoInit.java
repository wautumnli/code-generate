package com.ql.code.gen.init;

import com.ql.code.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wanqiuli
 * @date 2022/3/22 13:37
 */
@Service
public class DaoInit extends Init {

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("module", "dao");
        dataMap.put("package", dataMap.get("base_package") + "." + "dao");
        dataMap.put("path", dataMap.get("project_name") + "/src/main/java/" +
                dataMap.get("package").toString().replaceAll("\\.", "/") + "/");
        List<String> importList = new ArrayList<>();
        importList.add("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        importList.add(dataMap.get("base_package") + "." + "entity." + dataMap.get("class_name"));
        dataMap.put("import", importList);
        dataMap.put("file_path", dataMap.get("path") + GenUtil.underlineToHump((String) dataMap.get("table_name"), true) + "Dao" + GenUtil.SUFFIX);
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("dao.ftl");
    }
}
