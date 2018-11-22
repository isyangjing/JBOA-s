package cn.jboa.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.jboa.dao.ClaimVoucherDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.entity.Department;
import cn.jboa.util.DateUtil;
import cn.jboa.util.PaginationSupport;

public class ClaimVoucherDaoImpl extends BaseHibernateDaoSupport<ClaimVoucher> implements
		ClaimVoucherDao {



	/**
	 * 
	 * @param createSn-创建人
	 * @param dealSn-下一步审批人
	 * @param status-状态
	 * @param startDate-创建日期：开始时间
	 * @param endDate-创建日期：结束时间
	 * @param pageNo-分页参数：当前显示第几页
	 * @param pageSize-分页参数：页面显示的大小
	 * @return
	 */
	public PaginationSupport<ClaimVoucher> getClaimVoucherPage(String createSn,
			String dealSn, String status,String startDate, String endDate, int pageNo,
			int pageSize) {
		//通过DetachedCriteria设置查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(ClaimVoucher.class);
		if (createSn != null && !createSn.equals("")){
			criteria.add(Restrictions.eq("creator.sn", createSn));
		}
		if (dealSn != null && !dealSn.equals("")){
			criteria.add(Restrictions.eq("nextDeal.sn", dealSn));
		}
		if (status != null && !status.equals("")){
			criteria.add(Restrictions.eq("status", status));
			
		}
		Date dStartDate = null;
		Date dEndDate = null;
		try {
			if(startDate != null && !startDate.equals("")){
				dStartDate = DateUtil.strToDate(startDate,"yyyy-MM-dd");
				criteria.add(Restrictions.ge("createTime", dStartDate));
			}
			if(endDate != null && !endDate.equals("")){
				dEndDate = DateUtil.strToDate(endDate+" 23:59:59","yyyy-MM-dd hh:mm:ss");
				criteria.add(Restrictions.le("createTime", dEndDate));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return findPageByCriteria(criteria, pageNo, pageSize);
	}

	@Override
	public List getClaimVoucherByModifyDate(int year, int month, int departmentId){
		// TODO Auto-generated method stub
		try{
			Date startDate = null;
			Date endDate = null;
			if(month!=0){
				startDate = DateUtil.strToDate(year+"-"+month+"-1 00:00:00", "yyyy-MM-dd hh:mm:ss");
				endDate = DateUtil.strToDate(DateUtil.getLastDayOfMonth(year, month)+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
			}else{
				startDate = DateUtil.strToDate(year+"-1"+"-1 00:00:00", "yyyy-MM-dd hh:mm:ss");
				endDate = DateUtil.strToDate(year+"-12"+"-31 23:59:59", "yyyy-MM-dd hh:mm:ss");
			}
			
			/*Date startDate = DateUtil.strToDate(year+"-"+month+"-1 00:00:00", "yyyy-MM-dd hh:mm:ss");
			Date endDate = DateUtil.strToDate(DateUtil.getLastDayOfMonth(year, month)+" 23:59:59", "yyyy-MM-dd hh:mm:ss");*/
			StringBuffer sb = new StringBuffer();
			sb.append(" select cv.creator.sn, cv.creator.name, sum(cv.totalAccount) ");
			sb.append("from ClaimVoucher cv ");
			sb.append("where cv.modifyTime >=:startDate ");
			sb.append("and cv.modifyTime <=:endDate ");
			if(departmentId!=0){
				sb.append("and cv.creator.sysDepartment.id =:departId ");
			}
			sb.append(" and cv.status = '已付款' ");
			sb.append(" group by cv.creator.sn, cv.creator.name ");
			
			String[] paramNames = new String[]{"startDate","endDate","departId"};
			Object[] values = new Object[]{startDate,endDate,departmentId};
			List list = this.getHibernateTemplate().findByNamedParam(sb.toString(), paramNames, values);
			return list;
		}catch(Exception e){
			return null;
		}
		
		
		/*Criteria criteria = this.getSession().createCriteria(ClaimVoucher.class);
		if(year==0 || month == 0){
			return null;
		}
		try{
			Date startDate = DateUtil.strToDate(year+"-"+month+"-1 00:00:00", "yyyy-MM-dd hh:mm:ss");
			Date endDate = DateUtil.strToDate(DateUtil.getLastDayOfMonth(year, month)+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
			criteria.add(Restrictions.eq("creator.sysDepartment.id", departmentId)); //????为什么报错，因为延迟加载的原因吗？
			
			criteria.add(Restrictions.ge("modifyTime", startDate));
			criteria.add(Restrictions.le("modifyTime", endDate));
		}catch(Exception e){
			
		}
		List<ClaimVoucher> result = criteria.list();
		return result;*/
	}

	@Override
	public List getClaimVoucherByDateAndDept(int year, int month) {
		// TODO Auto-generated method stub
		try{
			Date startDate = null;
			Date endDate = null;
			if(month!=0){
				startDate = DateUtil.strToDate(year+"-"+month+"-1 00:00:00", "yyyy-MM-dd hh:mm:ss");
				endDate = DateUtil.strToDate(DateUtil.getLastDayOfMonth(year, month)+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
			}else{
				startDate = DateUtil.strToDate(year+"-1"+"-1 00:00:00", "yyyy-MM-dd hh:mm:ss");
				endDate = DateUtil.strToDate(year+"-12"+"-31 23:59:59", "yyyy-MM-dd hh:mm:ss");
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append(" select cv.creator.sysDepartment.id, cv.creator.sysDepartment.name, sum(cv.totalAccount) ");
			sb.append("from ClaimVoucher cv ");
			sb.append("where cv.modifyTime >=:startDate ");
			sb.append("and cv.modifyTime <=:endDate ");
			sb.append(" and cv.status = '已付款' ");
			sb.append(" group by cv.creator.sysDepartment.id, cv.creator.sysDepartment.name ");
			
			String[] paramNames = new String[]{"startDate","endDate"};
			Object[] values = new Object[]{startDate,endDate};
			List list = this.getHibernateTemplate().findByNamedParam(sb.toString(), paramNames, values);
			return list;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public List getClaimVoucher(ClaimVoucher main_id) {
		return find("from ClaimVoucherDetail c where c.bizClaimVoucher = ? order by c.id",main_id);
	}
}
