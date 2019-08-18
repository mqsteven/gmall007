package com.igeekhome.gmall.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UmsMember implements Serializable {


    private String id;
    private String         memberLevelId;
    private String username;
    private String         password;
    private String nickname;
    private String         phone;
    private int status;
    private Date createTime;
    private String icon;
    private int         gender;
    private Date birthday;
    private String        city;
    private String job;
    private String         personalizedSignature;
    private int sourceType;
    private int         integration;
    private int growth;
    private int         luckeyCount;
    private int historyIntegration;

    }
