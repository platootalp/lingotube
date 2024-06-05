package com.moncoder.lingo.vocabulary.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.entity.Word;
import com.moncoder.lingo.entity.WordDefinition;
import com.moncoder.lingo.mapper.WordMapper;
import com.moncoder.lingo.vocabulary.dao.WordDao;
import com.moncoder.lingo.vocabulary.domain.dto.WordCreateDTO;
import com.moncoder.lingo.vocabulary.domain.dto.WordViewPageDTO;
import com.moncoder.lingo.vocabulary.domain.vo.WordVO;
import com.moncoder.lingo.vocabulary.domain.vo.WordViewVO;
import com.moncoder.lingo.vocabulary.service.IWordDefinitionService;
import com.moncoder.lingo.vocabulary.service.IWordService;
import com.moncoder.lingo.vocabulary.util.WordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
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
    @Autowired
    private WordDao wordDao;

    private static final HashMap<String, String> POS_EN_CN_MAP;

    static {
        POS_EN_CN_MAP = new HashMap<>();
        // 名词
        POS_EN_CN_MAP.put("n.", "名词");
        // 动词
        POS_EN_CN_MAP.put("v.", "动词");
        POS_EN_CN_MAP.put("vt.", "及物动词");
        POS_EN_CN_MAP.put("vi.", "不及物动词");
        // 形容词
        POS_EN_CN_MAP.put("a.", "形容词");
        POS_EN_CN_MAP.put("adj.", "形容词");
        // 副词
        POS_EN_CN_MAP.put("ad.", "副词");
        POS_EN_CN_MAP.put("adv.", "副词");
    }

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
                        WordDefinition wordDefinition = new WordDefinition();
                        wordDefinition.setWordId(wordId);
                        wordDefinition.setPosEn(key);
                        wordDefinition.setPosCn(POS_EN_CN_MAP.get(key));
                        wordDefinition.setMeaning(meanings.get(key));
                        return wordDefinition;
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid key format: " + key);
                    }
                })
                .collect(Collectors.toList());
        wordDefinitionService.saveBatch(wordDefinitions);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importList(String path){
        List<WordCreateDTO> wordCreateDTOList;
        try {
            wordCreateDTOList = WordUtil.readTxt(path);
        } catch (IOException e) {
            throw new ApiException("文件读取失败！");
        }
        wordCreateDTOList.forEach(this::create);
    }

    @Override
    public LPage<WordViewVO> getPageWord(WordViewPageDTO wordViewDTO) {
        // 1.设置分页参数
        Integer currentPage = wordViewDTO.getCurrentPage();
        Integer pageSize = wordViewDTO.getPageSize();

        // 2.分页查询数据库
        String keyword = wordViewDTO.getKeyword();
        Integer sortBy = wordViewDTO.getSortBy();
        List<WordVO> wordVOS = wordDao.selectWordList(keyword, sortBy);

        // 3.组装 WordViewVO 列表
        Map<Integer, WordViewVO> map = new LinkedHashMap<>();
        wordVOS.forEach(wordVO -> {
            Integer key = wordVO.getId();
            WordViewVO wordViewVO = map.computeIfAbsent(key, k -> {
                WordViewVO newWordViewVO = new WordViewVO();
                BeanUtils.copyProperties(wordVO, newWordViewVO);
                newWordViewVO.setDefinition(new HashMap<>());
                return newWordViewVO;
            });
            wordViewVO.getDefinition().put(wordVO.getPosEn(), wordVO.getMeaning());
        });

        // 4.转换 Map 到 List
        List<WordViewVO> wordViewVOList = new ArrayList<>(map.values());

        // 5.进行分页处理
        int total = wordViewVOList.size();
        int totalPage = (int) Math.ceil((double) total / pageSize);
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, total);
        List<WordViewVO> pagedWordViewVOList;
        if (start >= total) {
            pagedWordViewVOList = Collections.emptyList();
        } else {
            pagedWordViewVOList = wordViewVOList.subList(start, end);
        }

        // 6.返回分页结果
        LPage<WordViewVO> result = new LPage<>();
        result.setPageNum((long) currentPage);
        result.setPageSize((long) pageSize);
        result.setTotalPage((long) totalPage);
        result.setTotal((long) total);
        result.setList(pagedWordViewVOList);

        return result;
    }


}
