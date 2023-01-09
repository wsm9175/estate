package com.lodong.spring.wsm9175.estate.repo;

import com.lodong.spring.wsm9175.estate.domain.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomImageRepository extends JpaRepository<RoomImage, String> {
    public Optional<RoomImage> findByName(String name);
}
