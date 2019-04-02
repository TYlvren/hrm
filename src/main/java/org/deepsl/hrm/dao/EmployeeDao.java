package org.deepsl.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.deepsl.hrm.domain.Employee;

import static org.deepsl.hrm.util.common.HrmConstants.EMPLOYEETABLE;

/**   
 * @Description: EmployeeMapper接口
 * @version V1.0   
 */
public interface EmployeeDao {

	// 根据参数查询员工总数
 	Integer count(Map<String, Object> params);
	
	// 根据参数动态查询员工

	List<Employee> selectAllEmployee();
	List<Employee> selectByPage(Map<String, Object> params);
	
	// 动态插入员工
 	void save(Employee employee);

	// 根据id删除员工
 	void deleteById(Integer id);
	
	// 根据id查询员工
  
	Employee selectById(Integer id);
	
	// 动态修改员工
 	void update(Employee employee);

}
