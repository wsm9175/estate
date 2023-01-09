package com.lodong.spring.wsm9175.estate.service;

import com.lodong.spring.wsm9175.estate.domain.Room;
import com.lodong.spring.wsm9175.estate.domain.RoomImage;
import com.lodong.spring.wsm9175.estate.dto.BoardDTO;
import com.lodong.spring.wsm9175.estate.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class BoardService {
    private final String mainFileDownloadUrl = "http://210.99.223.38:13405/rest/v1/estate/file/main/";
    private final String roomFileDownloadUrl = "http://210.99.223.38:13405/rest/v1/estate/file/room/";
    private final RoomRepository roomRepository;

    public List<BoardDTO> getBoardList(int page) throws NullPointerException{
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createAt").descending());
        Page<Room> roomPageList = roomRepository.findAll(pageRequest);
        List<Room> roomList = Optional.of(roomPageList.getContent()).orElseThrow(()-> new NullPointerException("게시물이 존재하지 않습니다."));
        List<BoardDTO> boardDTOS = new ArrayList<>();
        for(Room room : roomList){
            List<RoomImage> roomImageList = Optional.ofNullable(room.getRoomImageList()).orElseGet(Collections::emptyList);
            List<String> roomImageNameList = roomImageList.stream().map(roomImage -> roomFileDownloadUrl+roomImage.getName()).toList();
            BoardDTO boardDTO = new BoardDTO(room.getAddress(), room.getAddressThai(), room.getContact(), room.getContract(),
                    room.getDate(), room.getDeposit(), room.getElevator(), room.getFloor(), room.getFrontMemo(),
                    mainFileDownloadUrl+Optional.ofNullable(room.getRoomImage().getName()).orElse(""), room.getMemo(), room.getOption(), roomImageNameList ,room.getRealDeposit(), room.getRealRent(), room.getRent(), room.getRoomType());
            boardDTOS.add(boardDTO);
        }

        return boardDTOS;
    }
}
