package cn.jboa.dao;

import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.entity.Leave;
import cn.jboa.util.PaginationSupport;

import java.util.List;

public interface LeaveDao  extends BaseDao<Leave>{


    public List<Leave> findLeave();

    public PaginationSupport<Leave> getLeavePage(String startDate, String endDate, int pageNo, int pageSize) throws Exception;

    public List getLevanId(Leave leaveId);

   /* public boolean sevaLevan(Leave leave);*/
}
