package com.lodong.spring.wsm9175.estate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardDTO {
    private String address;
    private String addressThai;
    private String contact;
    private String contract;
    private LocalDate date;
    private String deposit;
    private String elevator;
    private String floor;
    private String frontMemo;
    private String memo;
    private String option;
    private String realDeposit;
    private String realRent;
    private String rent;
    private String roomType;
}
