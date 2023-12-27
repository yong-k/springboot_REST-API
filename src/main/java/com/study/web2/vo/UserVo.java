package com.study.web2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.dto.user.CreateUserReqDto;
import com.study.web2.dto.user.UpdateUserReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo {
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String website;
    private String company;

    public UserVo(CreateUserReqDto createUserReqDto) {
        this.name = createUserReqDto.getName();
        this.username = createUserReqDto.getUsername();
        this.email = createUserReqDto.getEmail();
        this.password = createUserReqDto.getPassword();
        this.address = createUserReqDto.getAddress();
        this.phone = createUserReqDto.getPhone();
        this.website = createUserReqDto.getWebsite();
        this.company = createUserReqDto.getCompany();
    }

    public UserVo(UpdateUserReqDto updateUserReqDto) {
        this.name = updateUserReqDto.getName();
        this.username = updateUserReqDto.getUsername();
        this.email = updateUserReqDto.getEmail();
        this.password = updateUserReqDto.getPassword();
        this.address = updateUserReqDto.getAddress();
        this.phone = updateUserReqDto.getPhone();
        this.website = updateUserReqDto.getWebsite();
        this.company = updateUserReqDto.getCompany();
    }
}