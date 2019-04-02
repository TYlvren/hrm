package org.deepsl.hrm.dao;

import java.util.List;
import java.util.Map;


import org.deepsl.hrm.domain.Dept;

import static org.deepsl.hrm.util.common.HrmConstants.DEPTTABLE;

/**   
 * @Description: DeptMapper接口  
 * @date 2016年7月11日 上午10:53:32 
 * @version V1.0   
 */
public interface DeptDao {

	// 动态查询
	List<Dept> selectByPage(Map<String, Object> params);
	
	Integer count(Map<String, Object> params);
	
	List<Dept> selectAllDept();
	
	Dept selectById(int id);

	// 根据id删除部门
	void deleteById(Integer id);
	
	// 动态插入部门
	void save(Dept dept);
	
	// 动态修改用户
	void update(Dept dept);
}
