package ${package_name};

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

    @TableId(value = "id", type =IdType.ASSIGN_ID)
    private Long id;
<#list columns as column>

    <#if column.columnComment != "">
    /**
    * ${column.columnComment}
    */
    </#if>
    @TableField(value = "${column.column}")
    private ${column.columnType} ${column.columnName};
</#list>

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_user")
    private String updateUser;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @TableField(value = "ver")
    @Version
    private String ver;
}