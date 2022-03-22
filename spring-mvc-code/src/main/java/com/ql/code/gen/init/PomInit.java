package com.ql.code.gen.init;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author wanqiuli
 * @date 2022/3/22 13:47
 */
@Service
public class PomInit extends Init {

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("table_name", "pom");
        dataMap.put("path", dataMap.get("project_name") + "/");
        dataMap.put("module", "pom.xml");
        dataMap.put("file_path", dataMap.get("path") + "pom.xml");
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("pom.ftl");
    }
}
