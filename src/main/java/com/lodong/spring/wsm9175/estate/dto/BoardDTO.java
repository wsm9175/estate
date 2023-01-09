package com.lodong.spring.wsm9175.estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private String address;
    private String addressThai;
    private String contact;
    private String contract;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년MM월dd일", timezone = "Asia/Seoul")
    private LocalDate date;
    private String deposit;
    private String elevator;
    private String floor;
    private String frontMemo;
    private String mainImage_path;
    private String memo;
    private String option;
    private List<String> picPath;
    private String real_deposit;
    private String real_rent;
    private String rent;
    private String room_type;
}
