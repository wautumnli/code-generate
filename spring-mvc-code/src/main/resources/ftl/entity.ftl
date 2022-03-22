package ${package};

import lombok.Data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.util.Date;

/**
* ${table_name}
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@Data
@TableName("${table_name}")
public class ${class_name} implements Serializable {

<#list columns as column>

    <#if column.column == '${id}'>
    @TableId(value = "${id}", type =IdType.AUTO)
    private ${column.columnType} ${column.columnName};
    <#elseif column.column == "create_time">
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private ${column.columnType} createTime;
    <#elseif column.column == "update_time">
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private ${column.columnType} updateTime;
    <#elseif column.column == "deleted">
    @TableField(value = "deleted")
    @TableLogic
    private ${column.columnType} deleted;
    <#elseif column.column == "ver">
    @TableField(value = "ver")
    @Version
    private ${column.columnType} ver;
    <#else>
    <#if column.columnComment != "">
    /**
    * ${column.columnComment}
    */
    </#if>
    @TableField(value = "${column.column}")
    private ${column.columnType} ${column.columnName};
    </#if>
</#list>
}