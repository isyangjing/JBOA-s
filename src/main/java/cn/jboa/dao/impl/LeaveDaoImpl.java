package cn.jboa.dao.impl;

import cn.jboa.dao.BaseDao;
import cn.jboa.dao.EmployeeDao;
import cn.jboa.dao.LeaveDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;
import cn.jboa.util.DateUtil;
import cn.jboa.util.PaginationSupport;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LeaveDaoImpl extends BaseHibernateDaoSupport<Leave> implements LeaveDao {



    @Override
    public List<Leave> findLeave() {
        String hql="from Leave";
        return find(hql);
    }


    private EmployeeDao employeeDao;
    @Override
    public PaginationSupport<Leave> getLeavePage(String startDate, String endDate, int pageNo, int pageSize) throws Exception {
        //通过DetachedCriteria设置查询条件
        DetachedCriteria criteria = DetachedCriteria.forClass(Leave.class);
        Date dStartDate = null;
        Date dEndDate = null;

        if (startDate != null && !startDate.equals("")) {
            dStartDate = DateUtil.strToDate(startDate, "yyyy-MM-dd");
            criteria.add(Restrictions.ge("createTime", dStartDate));
            System.out.println(criteria);
        }

        if (endDate != null && !endDate.equals("")) {
            dEndDate = DateUtil.strToDate(endDate + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
            criteria.add(Restrictions.le("createTime", dEndDate));
            System.out.println(criteria);
        }

        return findPageByCriteria(criteria, pageNo, pageSize);
    }

    @Override
    public List getLevanId(Leave leaveId) {
        System.out.println(">>>>>>>>>>>two");
        return find("from Leave c where c.creator = ? order by c.id",leaveId);

    }

    /*@Override
    public boolean sevaLevan(Leave leave) {
        boolean bRet = false;
        try{

            leave.setCreateTime(new Date());
            save(leave);
            bRet = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return bRet;
    }*/


}
