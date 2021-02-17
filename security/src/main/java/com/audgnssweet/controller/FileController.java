package com.audgnssweet.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/members")
public class FileController {

    @GetMapping("/uploadform")
    public String uploadForm() {
        return "members/uploadform";
    }

    //업로드 파일이 여러개일 경우 MultipartFile[] files
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println("파일 이름 : " + file.getOriginalFilename());
        System.out.println("파일 크기 : " + file.getSize());

        try (
            FileOutputStream fos = new FileOutputStream("/Temp/" + file.getOriginalFilename());
            InputStream is = file.getInputStream();
        ) {
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0 , readCount);
            }
        } catch (Exception e) {
            throw new RuntimeException("file save error");
        }
        return "members/uploadok";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {

        // 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
        // 원래 DB와 연동하면, 파일 저장시 파일 정보를 DB에 담고
        // 다운로드 요청이 있으면 DB에서 파일 정보를 넘겨주어서 찾아가게 하는 식으로 해야한다.
        String fileName = "jpaosiv.jpg";
        String saveFileName = "/Temp/jpaosiv.jpg"; // 맥일 경우 "/tmp/connect.png" 로 수정
        String contentType = "image/jpg";
        int fileLength = 374580;

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", "" + fileLength);
        response.setHeader("Pragma", "no-cache;");  //브라우저가 캐시를 읽지 못하도록.
        response.setHeader("Expires", "-1;");

        try (
            FileInputStream fis = new FileInputStream(saveFileName);
            OutputStream out=response.getOutputStream();
        ) {
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while ((readCount = fis.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            throw new RuntimeException("file save error");
        }
    }
}
