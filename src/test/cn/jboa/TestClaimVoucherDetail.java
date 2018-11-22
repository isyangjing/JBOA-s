package cn.jboa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jboa.service.ClaimVoucherDetailService;

public class TestClaimVoucherDetail {

	@Test
	public void test2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ClaimVoucherDetailService claimVoucherDetailService = (ClaimVoucherDetailService) ctx
				.getBean("claimVoucherDetailService");
		claimVoucherDetailService.deleteDetail(new Long(323));
		
	}

}
