package cn.jboa.service.impl;

import cn.jboa.dao.ClaimVoucherDetailDao;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.ClaimVoucherDetailService;

public class ClaimVoucherDetailServiceImpl implements ClaimVoucherDetailService {

	private ClaimVoucherDetailDao claimVoucherDetailDao;

	public ClaimVoucherDetailDao getClaimVoucherDetailDao() {
		return claimVoucherDetailDao;
	}

	public void setClaimVoucherDetailDao(ClaimVoucherDetailDao claimVoucherDetailDao) {
		this.claimVoucherDetailDao = claimVoucherDetailDao;
	}
	
	public boolean deleteDetail(Long biz_claim_voucher_detail_id) {
		boolean bRet = true;
		try{
			ClaimVoucherDetail claimVoucherDetail = claimVoucherDetailDao.get(biz_claim_voucher_detail_id);
			System.out.println(">>>>>>>>>>>>> " + claimVoucherDetail.getId());
			claimVoucherDetailDao.delete(claimVoucherDetail);
			bRet = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return bRet;
	}
}
