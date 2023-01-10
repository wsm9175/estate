package com.lodong.spring.wsm9175.estate.controller;

import com.lodong.spring.wsm9175.estate.responseentity.StatusEnum;
import com.lodong.spring.wsm9175.estate.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static com.lodong.spring.wsm9175.estate.util.MakeResponseEntity.getResponseMessage;

@Slf4j
@RestController
@RequestMapping("rest/v1/estate/file")

public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/main/{fileName}")
    public ResponseEntity<?> fileMainDownload(@PathVariable String fileName) {
        try {
            String storage = fileService.getFileStorage(fileName);
            File file = new File(storage);
            ResponseEntity<byte[]> result = null;
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
            return result;
        }catch (IOException e){
            e.printStackTrace();
            StatusEnum statusEnum = StatusEnum.BAD_REQUEST;
            String message = "해당 파일이 존재하지 않습니다.";
            return getResponseMessage(statusEnum, message);
        }
    }

    @GetMapping("/room/{fileName}")
    public ResponseEntity<?> fileRoomDownload(@PathVariable String fileName) {
        try {
            String storage = fileService.getFileStorage(fileName);
            File file = new File(storage);
            ResponseEntity<byte[]> result = null;
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            StatusEnum statusEnum = StatusEnum.BAD_REQUEST;
            String message = "해당 파일이 존재하지 않습니다.";
            return getResponseMessage(statusEnum, message);
        }
    }

}
