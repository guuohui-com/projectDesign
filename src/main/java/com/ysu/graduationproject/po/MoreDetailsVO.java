package com.ysu.graduationproject.po;

import org.springframework.stereotype.Component;

@Component
public class MoreDetailsVO {

    private Moredetails moredetails;

    private String tableId;

    public Moredetails getMoredetails() {
        return moredetails;
    }

    public void setMoredetails(Moredetails moredetails) {
        this.moredetails = moredetails;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
