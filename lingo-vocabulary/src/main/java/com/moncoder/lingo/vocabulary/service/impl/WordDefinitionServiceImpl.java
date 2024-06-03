package com.moncoder.lingo.vocabulary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.WordDefinition;
import com.moncoder.lingo.mapper.WordDefinitionMapper;
import com.moncoder.lingo.vocabulary.service.IWordDefinitionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单词释义表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:51:38
 */
@Service
public class WordDefinitionServiceImpl extends ServiceImpl<WordDefinitionMapper, WordDefinition> implements IWordDefinitionService {

}
