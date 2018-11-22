package cn.jboa.action;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;

public class LeaveAction extends BaseAction {

    private LeaveService leaveService;

    private String startDate;
    private String endDate;

    private Leave leave;



    public String lsearch() throws Exception {
        pageSupport = leaveService.getLeavePage(startDate, endDate, pageNo, pageSize);
        return "list";
    }

    public String into(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>one");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+leave.getId());
        leave = leaveService.getLevanId(leave.getId());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>结束");
        return "view";
    }


    public String edit(){
        Map<String,String> leaveTypeMap=new HashMap<>();
        leaveTypeMap.put("1",Constants.LEAVE_ANNUAL);
        leaveTypeMap.put("2",Constants.LEAVE_CASUAL);
        leaveTypeMap.put("3",Constants.LEAVE_MARRIAGE);
        leaveTypeMap.put("4",Constants.LEAVE_MATERNITY);
        leaveTypeMap.put("5",Constants.LEAVE_SICK);
        ActionContext.getContext().getValueStack().set("leaveTypeMap", leaveTypeMap);
        System.out.println(leaveTypeMap.size());
        return "endi";
    }

    public String saveLeave()throws Exception {
        System.out.println("method  saveLeave");
        leave.setCreator(getLoginEmployee());
        leave.setNextDeal((Employee)getSession().get(Constants.AUTH_EMPLOYEE_MANAGER));
        boolean bSave = leaveService.saveLeave(leave);
        if(bSave){
            System.out.println("=============true");
            this.addActionMessage("添加报销单成功！");
            return lsearch();
        }else{
            System.out.println("=============false");
            this.addActionMessage("添加报销单失败！");
            return edit();
        }

    }


    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public LeaveService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
}
