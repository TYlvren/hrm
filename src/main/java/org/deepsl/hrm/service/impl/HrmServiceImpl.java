package org.deepsl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.deepsl.hrm.dao.DeptDao;
import org.deepsl.hrm.dao.EmployeeDao;
import org.deepsl.hrm.dao.JobDao;
import org.deepsl.hrm.dao.NoticeDao;
import org.deepsl.hrm.dao.UserDao;
import org.deepsl.hrm.domain.Dept;
import org.deepsl.hrm.domain.Employee;
import org.deepsl.hrm.domain.Job;
import org.deepsl.hrm.domain.Notice;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 人事管理系统服务层接口实现类 
 * @version V1.0   
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;

	@Autowired
	private DeptDao deptDao;

	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private NoticeDao noticeDao;
	
	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		HashMap<String, String> hashMap= new HashMap<>();
		hashMap.put("loginname",  loginname );
		hashMap.put("password",  password );

		
		return userDao.selectByLoginnameAndPassword( hashMap );
	}

	/**
	 * HrmServiceImpl接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user,PageModel pageModel) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("user",user);
		System.out.println(user);
		Integer count = userDao.count(map);
		pageModel.setRecordCount(count);

		map.put("pageModel",pageModel);
		System.out.println(pageModel);
		List<User> users = userDao.selectByPage(map);
		 
		return users;
	}

	/**
	 * HrmServiceImpl接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeUserById(Integer id) {
 		
	}
	
	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
		
	}
	
	/**
	 * HrmServiceImpl接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addUser(User user) {
 		
	}
	
	/*****************部门服务接口实现*************************************/
	@Override
	public List<Dept> findDept(){
		return deptDao.selectAllDept();
	}


 
	/*****************员工服务接口实现*************************************/

	@Override
	public List<Employee> findEmployee() {
		return employeeDao.selectAllEmployee();
	}

	/*****************职位接口实现*************************************/
	@Override
	public List<Job> findJob() {
		return jobDao.selectAllJob();
	}

	/*****************公告接口实现*************************************/
	@Override
	public List<Notice> findNotice() {
		return noticeDao.selectAllNotice();
	}
/*****************文件接口实现*************************************/

 
 
 
	
}
