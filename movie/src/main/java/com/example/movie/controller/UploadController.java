package com.example.movie.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.movie.dto.UploadReaultDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/upload")
@Log4j2
@Controller
public class UploadController {

    // application.properties 에 작성한 값 불러오기

    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    @GetMapping("/create")
    public String getUploadForm() {
        return "/upload/test";
    }

    @PreAuthorize("isAuthrnticated()")
    @PostMapping("/files")
    public ResponseEntity<List<UploadReaultDTO>> postUpload(MultipartFile[] uploadFiles) {

        List<UploadReaultDTO> uploadReaultDTOs = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            // String fileName = oriName.substring(oriName.lastIndexOf("\\") + 1);
            // log.info("oriName {}", oriName);
            // log.info("fileName {}", fileName);

            // 이미지가 아닌 다른 파일이 첨부된다면 돌려보내기
            if (!uploadFile.getContentType().startsWith("image")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // 첨부파일이 이미지라면
            String oriName = uploadFile.getOriginalFilename();
            String saveFolderPath = makeFolder();

            // 고유 식별자 생성
            String uuid = UUID.randomUUID().toString();
            // upload/2025/05/15/308d028c-6737-4326-b90c-607798dbbc2d_파일명.jpg
            String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + oriName;
            Path savePath = Paths.get(saveName);

            try {
                // 특정 폴더에 저장
                uploadFile.transferTo(savePath);
                // 썸네일 저장
                String thumbnailSavedName = uploadPath + File.separator + saveFolderPath + File.separator + "s_" + uuid
                        + "_" + oriName;
                File thumFile = new File(thumbnailSavedName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumFile, 100, 100);

            } catch (Exception e) {
                e.printStackTrace();
            }
            uploadReaultDTOs.add(new UploadReaultDTO(oriName, uuid, saveFolderPath));
        }
        return new ResponseEntity<>(uploadReaultDTOs, HttpStatus.OK);

    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, String size) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "utf-8");
            File file = new File(uploadPath + File.separator + srcFileName);

            if (size != null && size.equals("1")) {
                // s_ 제거
                file = new File(file.getParent(), file.getName().substring(2));
            }

            HttpHeaders headers = new HttpHeaders();
            // Content-type : 브라우저에게 보내는 파일 타입이 무엇인지 제공할 때 사용
            // Content-type : "application/json", "image/jepg" ==> MIME 확인
            headers.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PreAuthorize("isAuthrnticated()")
    @PostMapping("/removeFile")
    public ResponseEntity<String> postMethodName(String fileName) {
        log.info("파일 삭제 요청 {}", fileName);

        // 2025/05/16/~~~~~~~
        String oriFilename;
        try {
            oriFilename = URLDecoder.decode(fileName, "utf-8");
            // 원본 파일 삭제
            File file = new File(uploadPath + File.separator + oriFilename);
            file.delete();
            // 썸네일 삭제
            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            thumbnail.delete();

            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 폴더생성
    private String makeFolder() {
        // LocalDate.now() : 2025-05-15 => format : 2025/05/15
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        // File.separator : 운영체제에 맞는 파일 구분 기호 (/ or \)
        String folderPath = dateStr.replace("/", File.separator);

        File uploadPathFolder = new File(uploadPath, folderPath);
        if (!uploadPathFolder.exists())
            uploadPathFolder.mkdirs(); // 디렉토리 생성
        return folderPath;

    }

}
