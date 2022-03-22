package com.ql.code.run;

import com.ql.code.entity.ColumnClass;
import com.ql.code.gen.ParamAnalysis;
import com.ql.code.gen.init.Init;
import com.ql.code.util.GenDatabaseUtil;
import com.ql.code.util.GenUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 启动类
 *
 * @author wanqiuli
 * @date 2022/3/21 12:21
 */
@Slf4j
@Component
public class GenEntity implements ApplicationRunner {

    @Resource
    private GenDatabaseUtil genDatabaseUtil;
    @Resource
    private ParamAnalysis paramAnalysis;
    @Resource
    private List<Init> initList;
    @Resource
    private List<Init> firstInitList;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("begin generate code..");
        // 获取所有表
        List<String> tables = genDatabaseUtil.getTables();
        if (CollectionUtils.isEmpty(tables)) {
            log.error("database don't have tables!");
            return;
        }
        // 存放模板变量
        Map<String, Object> data = paramAnalysis.getBaseMap();
        for (Init init : firstInitList) {
            init.run(data);
        }
        for (String table : tables) {
            // 工具类将下划线命名转化为驼峰
            List<ColumnClass> columns = genDatabaseUtil.getColumns(table);
            String className = GenUtil.underlineToHump(table, true);
            data.put("table_name", table);
            data.put("class_name", className);
            data.put("low_class_name", GenUtil.firstLow(className));
            data.put("columns", columns);
            for (Init init : initList) {
                init.run(data);
            }
        }
    }
}
