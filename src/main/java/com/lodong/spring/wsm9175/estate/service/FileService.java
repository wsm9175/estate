package com.lodong.spring.wsm9175.estate.service;

import com.lodong.spring.wsm9175.estate.domain.RoomImage;
import com.lodong.spring.wsm9175.estate.repo.RoomImageRepository;
import com.lodong.spring.wsm9175.estate.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {
   /* private final String STORAGE_ROOT_PATH = "C:\\Users\\seongminWoo\\Desktop\\outsourcing\\supermandiary\\supermandiary\\src\\main\\resources\\static";
    private final String BUSINESSLICENSE_PATH = "\\";*/
    private final RoomRepository roomRepository;
    private final RoomImageRepository roomImageRepository;

    private final String STORAGE_ROOT_PATH = "/file/";
    private final String MAIN_PATH = "main/";
    private final String ROOM_PATH = "room/";

    public String getFileStorage(String name){
        RoomImage roomImage = roomImageRepository
                .findByName(name)
                .orElseThrow(()->new NullPointerException("해당 이미지는 존재하지 않습니다."));
        return roomImage.getImgPath();
    }
    private void saveFile(MultipartFile file, String storage) throws IOException {
        File saveFile = new File(storage);
        file.transferTo(saveFile);
    }
    private String getNowTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formatedNow = now.format(formatter);
        return formatedNow;
    }
}
