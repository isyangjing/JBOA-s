package cn.jboa.service;

import java.util.List;
import java.util.Map;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.util.PaginationSupport;

public interface ClaimVoucherService {
	
	public Map getAllStatusMap();
	
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
	public PaginationSupport<ClaimVoucher> getClaimVoucherPage(String createSn, String dealSn,
                                                               String status, String startDate, String endDate, int pageNo, int pageSize);
	
	public ClaimVoucher findClaimVoucherById(Long id);

	
	public List<ClaimVoucher> getClaimVoucherByModifyDate(int year, int month, int departmentId);
	
//	public List<ClaimVoucherStatistics> getClaimVoucherByDateAndDept(int year, int month);
	
	public boolean deleteClaimVoucherById(Long id);
	
	public boolean saveClaimVoucher(ClaimVoucher claimVoucher);
	public boolean updateClaimVoucher(ClaimVoucher claimVoucher);
	
	public List<ClaimVoucherDetail> getClaimVoucher(ClaimVoucher main_id);
	
}
