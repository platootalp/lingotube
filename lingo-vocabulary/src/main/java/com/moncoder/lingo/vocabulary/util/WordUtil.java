package com.moncoder.lingo.vocabulary.util;

import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.vocabulary.domain.dto.WordCreateDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Moncoder
 * @version 1.0
 * @description 单词读取工具
 * @date 2024/6/4 13:39
 */
public class WordUtil {

    public static List<WordCreateDTO> readTxt(String path) throws IOException {
        List<WordCreateDTO> wordCreateDTOList = new ArrayList<>();
        InputStream is = WordUtil.class.getClassLoader().getResourceAsStream(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                WordCreateDTO wordCreateDTO = new WordCreateDTO();
                String[] s = line.split("\\s{3,}");
                if(s.length < 3){
                    System.out.println(count);
                    throw new ApiException("文件格式错误！");
                }
                wordCreateDTO.setName(s[0].trim());
                wordCreateDTO.setPhoneticUk(s[1].trim());
                wordCreateDTO.setPhoneticUs(s[1]);
                String[] definition = s[2].split(" ");
                if(definition.length %2 !=0){
                    System.out.println(count);
                    throw new ApiException("文件格式错误！");
                }
                Map<String, String> meanings = new HashMap<>();
                for (int i = 0; i < definition.length - 1; i+=2) {
                    String key = definition[i];
                    String value = definition[i+1];
                    meanings.put(key,value);
                }
                wordCreateDTO.setMeanings(meanings);
                wordCreateDTOList.add(wordCreateDTO);
                count++;
            }
        }
        return wordCreateDTOList;
    }
}
