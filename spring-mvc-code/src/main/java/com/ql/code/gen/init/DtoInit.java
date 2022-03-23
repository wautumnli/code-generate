package com.ql.code.gen.init;

import com.ql.code.util.GenUtil;
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
 * @date 2022/3/23 20:54
 */
@Service
public class DtoInit extends Init {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("module", "dto");
        dataMap.put("package", dataMap.get("base_package") + "." + "dto");
        dataMap.put("path", dataMap.get("project_name") + "/src/main/java/" +
                dataMap.get("package").toString().replaceAll("\\.", "/") + "/");
        dataMap.put("file_path", dataMap.get("path") + GenUtil.underlineToHump((String) dataMap.get("table_name"), true)
                + "Dto" + GenUtil.SUFFIX);
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("dto.ftl");
    }
}
