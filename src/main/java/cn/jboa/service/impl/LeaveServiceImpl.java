package cn.jboa.service.impl;

import cn.jboa.common.Constants;
import cn.jboa.dao.EmployeeDao;
import cn.jboa.dao.LeaveDao;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;
import cn.jboa.util.PaginationSupport;

import java.util.Date;
import java.util.List;

public class LeaveServiceImpl implements LeaveService {


    private LeaveDao leaveDao;


    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public LeaveDao getLeaveDao() {
        return leaveDao;
    }

    public void setLeaveDao(LeaveDao leaveDao) {
        this.leaveDao = leaveDao;
    }



    @Override
    public List<Leave> findLeave() {
        return leaveDao.findLeave();
    }

    @Override
    public PaginationSupport<Leave> getLeavePage(String startDate, String endDate, int pageNo, int pageSize) throws Exception {
        return leaveDao.getLeavePage(startDate,endDate,pageNo,pageSize);
    }

    @Override
    public Leave getLevanId(Long leaveId) {
        System.out.println(">>>>>>>>>>>>>>three");
        return (Leave)leaveDao.get(leaveId);
    }

    @Override
    public boolean saveLeave(Leave leave) {
        // TODO Auto-generated method stub
        boolean bRet = false;
        try{
            leave.setCreateTime(new Date());
            leave.setModifyTime(leave.getCreateTime());
            leave.setStatus(Constants.LEAVESTATUS_APPROVING);
            leaveDao.save(leave);
            bRet = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return bRet;
    }


}
