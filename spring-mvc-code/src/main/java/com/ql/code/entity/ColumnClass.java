package com.ql.code.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库信息获取
 *
 * @author wanqiuli
 * @date 2022/3/18 17:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnClass {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列大小
     */
    private Integer columnSize;
    /**
     * 列的类型
     */
    private String columnType;
    /**
     * 列的注释
     */
    private String columnComment;
    /**
     * 是否能为空值
     */
    private Boolean nullAble;
}