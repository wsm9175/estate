package com.lodong.spring.wsm9175.estate.controller;

import com.lodong.spring.wsm9175.estate.dto.BoardDTO;
import com.lodong.spring.wsm9175.estate.responseentity.StatusEnum;
import com.lodong.spring.wsm9175.estate.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lodong.spring.wsm9175.estate.util.MakeResponseEntity.getResponseMessage;

@Slf4j
@RestController
@RequestMapping("rest/v1/estate/board")

public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/list")
    public ResponseEntity<?> getBoardList(int page) {
        try {
            List<BoardDTO> boardDTOS = boardService.getBoardList(page);
            return getResponseMessage(StatusEnum.OK, "게시판" + page, boardDTOS);
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            return getResponseMessage(StatusEnum.BAD_REQUEST, nullPointerException.getMessage());
        }
    }
}
