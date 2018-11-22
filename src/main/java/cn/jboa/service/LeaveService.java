package cn.jboa.service;

import cn.jboa.entity.Leave;
import cn.jboa.util.PaginationSupport;

import java.util.List;

public interface LeaveService {



    public List<Leave> findLeave();

    public PaginationSupport<Leave> getLeavePage(String startDate, String endDate, int pageNo, int pageSize) throws Exception;

    public Leave getLevanId(Long leaveId);

    public boolean saveLeave(Leave leave);

}
