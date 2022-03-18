package ${package_name};

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* ${table_name}
*
* @author echo cow
* @date ${.now?datetime}
*/
@Data
@Table(name = "${table_name}")
@Entity(name = "${table_name}")
public class ${class_name} implements Serializable {
<#list columns as column>

    /**
    * ${column.columnComment}
    */
    private ${column.columnType} ${column.columnName};
</#list>
}