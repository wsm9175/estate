package com.lodong.spring.wsm9175.estate.repo;

import com.lodong.spring.wsm9175.estate.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    public Page<Room> findAll(Pageable pageable);
}
