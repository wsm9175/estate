package com.lodong.spring.wsm9175.estate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Room {
    @Id
    private String id;
    @Column(nullable = false)
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
    private String roomOption;
    private String realDeposit;
    private String realRent;
    private String rent;
    private String roomType;
    private LocalDateTime createAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_image_path")
    private RoomImage roomImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> roomImageList;
}
