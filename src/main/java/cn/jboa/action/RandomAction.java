package cn.jboa.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import cn.jboa.util.RandomNumUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 该类主要用于生成验证码
 * 
 */
public class RandomAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private ByteArrayInputStream inputStream;
    String result = null;
    private String code;

    // 生成验证码方法
    public String execute() throws Exception {
        RandomNumUtil rdnu = RandomNumUtil.Instance();
        this.setInputStream(rdnu.getImage());/// 取得带有随机字符串的图片
        ActionContext.getContext().getSession().put("random", rdnu.getString());
        return SUCCESS;
    }

    public String codeIdentifying() throws UnsupportedEncodingException {
        System.out.println("codeIdentifying================"+ code);
        if (ActionContext.getContext().getSession().get("random").equals(code)) {
            result="true";
        } else {
            result="false";
        }
        inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
        return "result";
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }
}
