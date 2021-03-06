package me.ahn.management.controller.backend;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ahn.management.annotation.PassToken;
import me.ahn.management.annotation.UserLoginToken;
import me.ahn.management.common.Const;
import me.ahn.management.common.PropertiesConfig;
import me.ahn.management.common.ServerResponse;
import me.ahn.management.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/file/")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @PassToken
    @RequestMapping(value = "/single/upload", method = RequestMethod.POST)
    public ServerResponse file_upload(HttpSession session, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {

        // 0. 파일 사이즈 체크 -->  최대 20MB
        if (file.getSize() > 0 && file.getSize() <= (Const.UPLOAD_IMAGE_MAX_SIZE * 1024)) {
            // 1. 서버에 파일 저장, 외부접근가능한 URL반환
            String targetFileName = fileService.saveSingleImage(file);
            String targetFileFinal = propertiesConfig.getFileServerHttpPrefix() + targetFileName;
            log.info(">>>>>>" + targetFileFinal);

            return ServerResponse.createBySuccess(targetFileFinal);
        } else {
            return ServerResponse.createByErrorMessage("文件大小不能超过20MB");
        }
    }

    @PassToken
    @RequestMapping(value = "/single/upload/url", method = RequestMethod.POST)
    public Map<String, Object> file_upload_return_url(HttpSession session
            , HttpServletRequest request
            , @RequestParam(value = "file", required = false) MultipartFile[] multipartFiles
    ) {
//        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
//        if (currentUser == null) {
//            return ServerResponse.createByErrorMessage(Const.Message.NEED_LOGIN);
//        }
        Map<String, Object> result = new HashMap<>();
        List imgList = new ArrayList();
        log.info(">>>>>>>>>>" + String.valueOf(multipartFiles.length));
        // 0. 파일 사이즈 체크 -->  최대 20MB
        for (MultipartFile file : multipartFiles) {
            if (file.getSize() > 0 && file.getSize() <= (Const.UPLOAD_IMAGE_MAX_SIZE * 1024)) {
                String targetFileName = fileService.saveSingleImage(file);
                String targetFileFinalName = propertiesConfig.getFileServerHttpPrefix() + targetFileName;

                imgList.add(targetFileFinalName);
                result.put("errno", 0);
                result.put("data", imgList);
            } else {
                result.put("errno", 99);
                result.put("data", imgList);
            }
        }
        return result;
    }

}
