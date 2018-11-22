package cn.jboa;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jboa.entity.Department;
import cn.jboa.entity.Employee;
import cn.jboa.service.EmployeeService;

public class TestEmployee {
	

	/**
	 * 调用getHibernateTemplate().findByExample(emp)
	 * findByExample方法不能根据ID查询
	 */
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeService employeeService = (EmployeeService) ctx.getBean("empService");
		Employee emp = new Employee();
		emp.setName("叶宁");
		emp.setPassword("202cb962ac59075b964b07152d234b70");
		List<Employee> list = employeeService.findEmployee(emp);
		System.out.println(">>>>>>>>>> " + list.size());
	}
	
	
	/**
	 * 测试登录，用户名和密码必须正确
	 */
	@Test
	public void testLogin() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeService employeeService = (EmployeeService) ctx.getBean("empService");
		Employee emp = new Employee();
		emp.setSn("017");
		emp.setPassword("202cb962ac59075b964b07152d234b70");
		Employee emp_return =  employeeService.login(emp);
		System.out.println(">>>>>>>>>" + emp_return.getName());
	}
	
	/**
	 * 查询部门id为2的部门经理员工
	 */
	@Test
	public void testGetManager(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeService employeeService = (EmployeeService) ctx.getBean("empService");
		Employee emp = new Employee();
		Department department = new Department();
		department.setId(2);
		emp.setSysDepartment(department);
		Employee emp_return = employeeService.getManager(emp);
		System.out.println(">>>>>>>" + emp_return.getName());
	}

	@Test
	public void testLeaveList(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		LeaveService leaveService=(LeaveService)ctx.getBean("leaveService");
		List<Leave> leaves= leaveService.findLeave();
		for (Leave leave: leaves) {
			System.out.println(leave.getId());
		}

	}
}
