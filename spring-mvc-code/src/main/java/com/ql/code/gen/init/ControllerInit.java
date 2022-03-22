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
 * @date 2022/3/22 15:31
 */
@Service
public class ControllerInit extends Init{
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("module", "controller");
        dataMap.put("package", dataMap.get("base_package") + "." + "controller");
        dataMap.put("path", dataMap.get("project_name") + "/src/main/java/" +
                dataMap.get("package").toString().replaceAll("\\.", "/") + "/");
        dataMap.put("file_path", dataMap.get("path") +
                GenUtil.underlineToHump((String) dataMap.get("table_name"), true) + "Controller" + GenUtil.SUFFIX);
        List<String> importList = new ArrayList<>();
        importList.add(dataMap.get("base_package") + "." + "service." + dataMap.get("class_name") + "Service");
        dataMap.put("import", importList);
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("controller.ftl");
    }
}
