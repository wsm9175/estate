package com.lodong.spring.wsm9175.estate.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RoomImage {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    @Column(nullable = false)
    private String imgPath;
    @Column(unique = true)
    private String name;
}
