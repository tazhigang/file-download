package com.demo.config;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 * @Author: ittzg
 * @CreateDate: 2018/12/30 11:54
 * @Description:
 */
public class ReadTxtFile {
    public static Map<String,List<String>> getMobilesMsg(InputStream inputStream) throws IOException {
        HashMap<String, List<String>> mobilesMsgMap = new HashMap<String, List<String>>();
        List<String> mobileMsgs = new ArrayList<>();
        List<String> errorMsgs = new ArrayList<>();
        String mobileMsg = "";
        String errorMsg = "";
        int i = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        while(true){
            String mobile = br.readLine();
            if(mobile == null) break;
            mobileMsg = mobile.trim();
            if(mobileMsg.length()==11){
                try {
                    mobileMsg = ""+Long.parseLong(mobileMsg);
                    mobileMsgs.add(mobileMsg);
                    i++;
                } catch (NumberFormatException e) {
                    errorMsg = "第"+i+"行电话号码不合法：电话号码必须是纯数字";
                    errorMsgs.add(errorMsg);
                    i++;
                }
            }else{
                errorMsg = "第"+i+"行电话号码不合法：格式错误";
                errorMsgs.add(errorMsg);
                i++;
            }
        }
        br.close();
        mobilesMsgMap.put("mobileMsgs",mobileMsgs);
        mobilesMsgMap.put("errorMsgs",errorMsgs);
        return mobilesMsgMap;
    }
}
