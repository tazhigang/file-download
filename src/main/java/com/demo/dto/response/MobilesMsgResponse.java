package com.demo.dto.response;

import java.util.List;
/**
 * @Author: ittzg
 * @CreateDate: 2018/12/30 12:00
 * @Description:
 */
public class MobilesMsgResponse {
    private List<String> moblesMsgs;
    private List<String> errorMsgs;

    public List<String> getMoblesMsgs() {
        return moblesMsgs;
    }

    public void setMoblesMsgs(List<String> moblesMsgs) {
        this.moblesMsgs = moblesMsgs;
    }

    public List<String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setErrorMsgs(List<String> errorMsgs) {
        this.errorMsgs = errorMsgs;
    }
}
