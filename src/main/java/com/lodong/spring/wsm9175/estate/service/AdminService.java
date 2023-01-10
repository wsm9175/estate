package com.lodong.spring.wsm9175.estate.service;

import com.lodong.spring.wsm9175.estate.domain.Room;
import com.lodong.spring.wsm9175.estate.domain.RoomImage;
import com.lodong.spring.wsm9175.estate.dto.CreateBoardDTO;
import com.lodong.spring.wsm9175.estate.repo.RoomImageRepository;
import com.lodong.spring.wsm9175.estate.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final RoomRepository roomRepository;
    private final RoomImageRepository roomImageRepository;
    private final String STORAGE_ROOT_PATH = "/file/";
    private final String ROOM_PATH = "room/";

    //private final String STORAGE_ROOT_PATH = "C:\\Users\\seongminWoo\\Desktop\\main\\";
    //private final String MAIN_PATH = "";
    //private final String ROOM_PATH = "";

    @Transactional
    public void saveBoard(List<MultipartFile> roomImages, MultipartFile mainImage, CreateBoardDTO createBoardDTO) throws RuntimeException {
        String boardId = UUID.randomUUID().toString();
        log.info(createBoardDTO.toString());
        Room room = Room.builder()
                .id(boardId)
                .address(createBoardDTO.getAddress())
                .addressThai(createBoardDTO.getAddressThai())
                .contact(createBoardDTO.getContact())
                .contract(createBoardDTO.getContract())
                .date(createBoardDTO.getDate())
                .deposit(createBoardDTO.getDeposit())
                .elevator(createBoardDTO.getElevator())
                .floor(createBoardDTO.getFloor())
                .frontMemo(createBoardDTO.getFrontMemo())
                .memo(createBoardDTO.getMemo())
                .roomOption(createBoardDTO.getOption())
                .realDeposit(createBoardDTO.getRealDeposit())
                .realRent(createBoardDTO.getRealRent())
                .rent(createBoardDTO.getRent())
                .roomType(createBoardDTO.getRoomType())
                .createAt(LocalDateTime.now())
                .build();

        roomRepository.save(room);
        List<RoomImage> roomImageList = new ArrayList<>();
        Optional.of(mainImage).ifPresent(file -> {
            String fileName = file.getOriginalFilename();
            String storage = STORAGE_ROOT_PATH + ROOM_PATH + fileName;
            RoomImage rooomImage = RoomImage
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .room(room)
                    .imgPath(storage)
                    .name(fileName)
                    .build();
            try {
                saveFile(file, storage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            roomImageList.add(rooomImage);
            room.setRoomImage(rooomImage);
        });
        Optional.ofNullable(roomImages).orElseGet(Collections::emptyList).forEach(file -> {
            String fileName = file.getOriginalFilename();
            String storge = STORAGE_ROOT_PATH + ROOM_PATH + fileName;
            RoomImage rooomImage = RoomImage
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .room(room)
                    .imgPath(storge)
                    .name(fileName)
                    .build();
            try {
                saveFile(file, storge);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            roomImageList.add(rooomImage);
        });
        roomImageRepository.saveAll(roomImageList);
        room.setRoomImageList(roomImageList);
        roomRepository.save(room);
    }

    private void saveFile(MultipartFile file, String storage) throws IOException {
        File saveFile = new File(storage);
        file.transferTo(saveFile);
    }
}
