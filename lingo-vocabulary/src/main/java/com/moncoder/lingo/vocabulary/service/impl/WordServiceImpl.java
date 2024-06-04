package com.moncoder.lingo.vocabulary.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.Word;
import com.moncoder.lingo.entity.WordDefinition;
import com.moncoder.lingo.mapper.WordMapper;
import com.moncoder.lingo.vocabulary.domain.dto.WordCreateDTO;
import com.moncoder.lingo.vocabulary.service.IWordDefinitionService;
import com.moncoder.lingo.vocabulary.service.IWordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 单词表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:51:38
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements IWordService {
    @Autowired
    private IWordDefinitionService wordDefinitionService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(WordCreateDTO wordCreateDTO) {
        Assert.notNull(wordCreateDTO, "WordCreateDTO cannot be null");

        // 检查单词是否已存在
        Word existingWord = lambdaQuery().eq(Word::getName, wordCreateDTO.getName()).one();
        Integer wordId;
        if (existingWord == null) {
            // 创建并保存 Word 对象
            Word word = new Word();
            BeanUtils.copyProperties(wordCreateDTO, word);
            save(word);
            wordId = word.getId();
        } else {
            wordId = existingWord.getId();
        }

        // 创建并保存 WordDefinition 对象列表
        Map<String, String> meanings = wordCreateDTO.getMeanings();
        List<WordDefinition> wordDefinitions = meanings.keySet().stream()
                .map(key -> {
                    try {
                        String[] split = key.split(",");
                        if (split.length != 2) {
                            throw new IllegalArgumentException("Invalid key format: " + key);
                        }
                        String posEn = split[0].trim();
                        String posCn = split[1].trim();
                        WordDefinition wordDefinition = new WordDefinition();
                        wordDefinition.setWordId(wordId);
                        wordDefinition.setPosEn(posEn);
                        wordDefinition.setPosCn(posCn);
                        wordDefinition.setMeaning(meanings.get(key));
                        return wordDefinition;
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid key format: " + key);
                    }
                })
                .collect(Collectors.toList());
        wordDefinitionService.saveBatch(wordDefinitions);
    }
}
