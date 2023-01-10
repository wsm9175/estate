package com.lodong.spring.wsm9175.estate.controller;

import com.lodong.spring.wsm9175.estate.dto.CreateBoardDTO;
import com.lodong.spring.wsm9175.estate.responseentity.StatusEnum;
import com.lodong.spring.wsm9175.estate.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.lodong.spring.wsm9175.estate.util.MakeResponseEntity.getResponseMessage;

@Slf4j
@RestController
@RequestMapping("rest/v1/estate/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> writeReview(@RequestPart(value = "room", required = false) List<MultipartFile> roomImages,
                                         @RequestPart(value = "main", required = false) MultipartFile mainImage,
                                         @RequestPart(value = "boardInfo") CreateBoardDTO createBoardDTO) {
        try {
            adminService.saveBoard(roomImages, mainImage, createBoardDTO);
            return getResponseMessage(StatusEnum.OK, "리뷰 작성 성공", null);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return getResponseMessage(StatusEnum.BAD_REQUEST, e.getMessage());
        }
    }
}
