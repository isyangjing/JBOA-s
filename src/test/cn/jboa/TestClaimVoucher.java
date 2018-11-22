package cn.jboa;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.ClaimVoucherDetailService;
import cn.jboa.service.ClaimVoucherService;
import cn.jboa.util.PaginationSupport;

public class TestClaimVoucher {

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@Test
	public void test() {
		PaginationSupport pageSupport;
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ClaimVoucherService claimVoucherService = (ClaimVoucherService) ctx
				.getBean("claimVoucherService");
		/**
		 * 分页参数说明
		 * @param createSn-创建人
		 * @param dealSn-下一步审批人
		 * @param status-状态
		 * @param startDate-创建日期：开始时间
		 * @param endDate-创建日期：结束时间
		 * @param pageNo-分页参数：当前显示第几页
		 * @param pageSize-分页参数：页面显示的大小
		 * @return
		 */
		PaginationSupport paginationSupport = claimVoucherService
				.getClaimVoucherPage(null, null, null,
				null, null, 1, 5);
		System.out.println("表中所有记录总数>>>>>" + paginationSupport.getTotalCount());
		List<ClaimVoucher> list =  paginationSupport.getItems();
		System.out.println(list.size());
	}
	
	
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ClaimVoucherService claimVoucherService = (ClaimVoucherService) ctx
				.getBean("claimVoucherService");
		ClaimVoucher c = new ClaimVoucher();
		c.setId(Long.parseLong("332"));
		
		List<ClaimVoucherDetail> list = claimVoucherService.getClaimVoucher(c);
		for (ClaimVoucherDetail claimVoucherDetail : list) {
			System.out.println(claimVoucherDetail.getId());
		}
	}
}
