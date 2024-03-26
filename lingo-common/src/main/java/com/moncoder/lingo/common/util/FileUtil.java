package com.moncoder.lingo.common.util;

import com.moncoder.lingo.common.constant.SystemConstant;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 文件处理工具类
 * @date 2024/3/26 14:07
 */
public class FileUtil {


    /**
     * 将文件保存到指定目录下
     *
     * @param file    文件
     * @param dirName 目录
     * @return
     */
    public static String saveFile(MultipartFile file, String dirName, String filePrefix) throws IOException {
        // 1.目录不存在则先创建
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 2.生成唯一文件名
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = now.atZone(zoneId);
        // 手动构建日期时间字符串，不包含冒号
        String formattedDateTime = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .replace(":", "-")
                + zonedDateTime.getOffset().getId().replace(":", "-");
        String fileName = filePrefix + formattedDateTime + "-" + file.getOriginalFilename();

        // 3.保存文件到指定路径
        Path filePath = Paths.get(SystemConstant.LINGO_FILE_PATH + dirName).resolve(fileName);
        file.transferTo(filePath.toFile());

        // 4.返回文件uri（绝对路径）
        String fileUri = SystemConstant.LINGO_FILE_PATH + dirName + "/" + fileName;
        return fileUri;
    }

}
