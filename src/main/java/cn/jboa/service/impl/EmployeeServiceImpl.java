package cn.jboa.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import cn.jboa.dao.EmployeeDao;
import cn.jboa.entity.Employee;
import cn.jboa.exception.JboaException;
import cn.jboa.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	private EmployeeDao empDao;

	

	@Override
	public Employee getEmployeeBySN(String sn) {
		// TODO Auto-generated method stub
		return empDao.get(sn);
	}

	@Override
	public boolean saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
			empDao.saveOrUpdate(employee);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public Employee login(Employee emp) throws JboaException {
		// TODO Auto-generated method stub
		Employee employee = empDao.findEmployeeBySn(emp.getSn());
		if(employee != null && employee.getPassword().equals(emp.getPassword())){
			return employee;
		}else{
			System.out.println("Invalid sn or password>>>>>>>>>>.");
			throw new JboaException("Invalid sn or password!");
		}
	}
	

	@Override
	public Employee getManager(Employee employee) {
		return empDao.getManager(employee);
	}

	@Override
	public Employee getGeneralManager() {
		// TODO Auto-generated method stub
		return empDao.getGeneralManager();
	}

	@Override
	public Employee getCashier() {
		// TODO Auto-generated method stub
		return empDao.getCashier();
	}
	
	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	@Override
	public List<Employee> findEmployee(Employee emp) {
		List<Employee> list = empDao.findEmployee(emp);
		return list;
	}
	

}
