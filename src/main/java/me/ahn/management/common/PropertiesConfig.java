package me.ahn.management.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:/config.properties")
public class PropertiesConfig {

    @Value("${cunho.passwordSalt}")
    private String passwordSalt;

    @Value("${cunho.miniAppId}")
    private String miniAppId;

    @Value("${cunho.miniAppSecret}")
    private String miniAppSecret;

    @Value("${cunho.mpAppId}")
    private String mpAppId;

    @Value("${cunho.mpAppSecret}")
    private String mpAppSecret;
    //
//    @Value("${cunho.ftpServerIp}")
//    private String ftpServerIp;
//
//    @Value("${cunho.ftpUsername}")
//    private String ftpUsername;
//
//    @Value("${cunho.ftpPassword}")
//    private String ftpPassword;
//
    @Value("${cunho.fileServerHttpPrefix}")
    private String fileServerHttpPrefix;

    @Value("${cunho.filePath}")
    private String filePath;

}
