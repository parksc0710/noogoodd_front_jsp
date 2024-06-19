package com.noogoodd.front.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String uuid;
    private String nickname;
    private boolean disability_yn;
    private String disability_type;
    private String aid_type;
    private String address_area;
    private String gender;
    private String birth_day;
    private String sign_type;
    private boolean act_flg;
    private LocalDateTime reg_dt;
    private LocalDateTime chg_dt;

}
