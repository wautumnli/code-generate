package com.ql.code.gen.init;

import com.ql.code.util.GenUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author wanqiuli
 * @date 2022/3/22 13:02
 */
@Slf4j
public abstract class Init {

    abstract Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException;

    /**
     * function is init
     *
     * @param template the template
     * @param dataMap  the dataMap
     * @throws TemplateException the TemplateException
     * @throws IOException       the IOException
     */
    void init(Template template, Map<String, Object> dataMap) throws IOException, TemplateException {
        String tableName = (String) dataMap.get("table_name");
        String path = (String) dataMap.get("path");
        String module = (String) dataMap.get("module");
        String filePath = (String) dataMap.get("file_path");
        File file = new File(filePath);
        if (!file.exists()) {
            if (!new File(path).mkdirs()) {
                log.error("{} 创建文件夹失败", path);
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", tableName);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        template.process(dataMap, fileWriter);
        log.info("table: {}, {} module init success!", tableName, module);
    }

    public void run(Map<String, Object> dataMap) throws TemplateException, IOException {
        Template template = prepare(dataMap);
        init(template, dataMap);
    }
}
