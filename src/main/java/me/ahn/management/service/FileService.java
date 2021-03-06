package me.ahn.management.service;

import me.ahn.management.common.ServerResponse;
import me.ahn.management.model.TB_FILE;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {

    String saveSingleImage(MultipartFile multipartFile);
    Map<String, Object> saveSingleImage2(MultipartFile multipartFile);
    TB_FILE saveFileVO(TB_FILE tbFile);
    ServerResponse mainFileList();

}
