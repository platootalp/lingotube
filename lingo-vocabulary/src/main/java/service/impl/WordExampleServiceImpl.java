package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.WordExample;
import com.moncoder.lingo.mapper.WordExampleMapper;
import org.springframework.stereotype.Service;
import service.IWordExampleService;

/**
 * <p>
 * 单词例句表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:50:03
 */
@Service
public class WordExampleServiceImpl extends ServiceImpl<WordExampleMapper, WordExample> implements IWordExampleService {

}
