package com.ql.code.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GenUtil {
    private static final String UNDERLINE = "_";
    private static final Map<String, String> MYSQL_TO_JAVA = new HashMap<>();
    public static final String SUFFIX = ".java";

    static {
        MYSQL_TO_JAVA.put("VARCHAR", "java.lang.String");
        MYSQL_TO_JAVA.put("BIGINT", "java.lang.Long");
        MYSQL_TO_JAVA.put("DATE", "java.time.LocalDate");
        MYSQL_TO_JAVA.put("FLOAT", "java.lang.Float");
        MYSQL_TO_JAVA.put("TINYINT", "java.lang.Integer");
        MYSQL_TO_JAVA.put("INT", "java.lang.Integer");
        MYSQL_TO_JAVA.put("BINARY", "java.lang.Byte");
        MYSQL_TO_JAVA.put("SMALLINT", "java.lang.Short");
        MYSQL_TO_JAVA.put("DATETIME", "java.time.LocalDateTime");
        MYSQL_TO_JAVA.put("BIT", "java.lang.Boolean");
    }


    /**
     * 下划线命名转驼峰式命名
     *
     * @param para 下划线命名
     * @return 驼峰式命名
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        for (String s : para.split(UNDERLINE)) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 下划线命名转驼峰式命名
     *
     * @param para            下划线命名
     * @param firstCharChange 首字母是否转换
     * @return 驼峰式命名
     */
    public static String underlineToHump(String para, boolean firstCharChange) {
        String result = underlineToHump(para);
        return firstCharChange ? result.substring(0, 1).toUpperCase() + result.substring(1) : result;
    }

    /**
     * 数据库字段转换
     *
     * @param mysqlDataType 数据库字段类型
     * @return 转换结果
     */
    public static String fieldConversion(String mysqlDataType) {
        return MYSQL_TO_JAVA.getOrDefault(mysqlDataType, "Object");
    }
}