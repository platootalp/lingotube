package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.Word;
import com.moncoder.lingo.mapper.WordMapper;
import org.springframework.stereotype.Service;
import service.IWordService;

/**
 * <p>
 * 单词表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:50:03
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements IWordService {

}
