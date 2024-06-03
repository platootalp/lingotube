package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.WordDefinition;
import com.moncoder.lingo.mapper.WordDefinitionMapper;
import org.springframework.stereotype.Service;
import service.IWordDefinitionService;

/**
 * <p>
 * 单词释义表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:50:03
 */
@Service
public class WordDefinitionServiceImpl extends ServiceImpl<WordDefinitionMapper, WordDefinition> implements IWordDefinitionService {

}
