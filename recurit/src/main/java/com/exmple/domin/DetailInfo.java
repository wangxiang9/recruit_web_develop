package com.exmple.domin;

import lombok.Data;

import java.util.List;

@Data
public class DetailInfo {
    private Integer iid;//id
    private String iname;//职位名称
    private String salary;//待遇
    private String detail;//详细说明
    private String iflag;//是否被收藏
    private String deadline;//截止日期
    private String updateTime;//更新时间
    private Integer cid;//类别
    private String sourceId;//资源地址
    private String company;//公司名
    private String address;//工作地点
    private String request;//要求
    private String contactInformation;//联系信息
    private String degree;//学历
    private String experience;//经验

}
