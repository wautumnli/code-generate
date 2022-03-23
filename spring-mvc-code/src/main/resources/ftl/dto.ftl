package ${package};

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
* ${table_name} Dto
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@Data
public class ${class_name}Dto implements Serializable {

<#list columns as column>
    <#if column.columnComment != "">
        /**
        * ${column.columnComment}
        */
    </#if>
    private ${column.columnType} ${column.columnName};
</#list>
}