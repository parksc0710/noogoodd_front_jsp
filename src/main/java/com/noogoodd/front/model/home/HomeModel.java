package com.noogoodd.front.model.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeModel {
    private Long id;

    private String notice;

    private boolean act_flg;

    private LocalDateTime reg_dt;

    private LocalDateTime chg_dt;
}
