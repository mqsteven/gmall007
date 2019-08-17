package com.atguigu.gmall.user.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class UmsMemberReceiveAddress {

    private String id;
    private String memberId;
    private String name;
    private String phoneNumber;
    private String defaultStatus;
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;

}
