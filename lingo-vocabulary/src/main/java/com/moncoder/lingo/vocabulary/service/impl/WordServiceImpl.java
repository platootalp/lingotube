package com.moncoder.lingo.vocabulary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.Word;
import com.moncoder.lingo.mapper.WordMapper;
import com.moncoder.lingo.vocabulary.service.IWordService;
import org.springframework.stereotype.Service;

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

}
