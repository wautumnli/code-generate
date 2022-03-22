package ${package};

<#list import as imp>
import ${imp};
</#list>

/**
* 接口层
*
* @author wanqiuli
* @date ${.now?datetime}
*/
public interface ${class_name}Dao extends BaseMapper<${class_name}> {


}