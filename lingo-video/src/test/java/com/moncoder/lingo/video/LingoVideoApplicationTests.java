package com.moncoder.lingo.video;

import com.moncoder.lingo.entity.VmsUserFavoriteVideo;
import com.moncoder.lingo.video.service.IVmsUserFavoriteVideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LingoVideoApplicationTests {

    @Autowired
    private IVmsUserFavoriteVideoService favoriteVideoService;

    @Test
    void contextLoads() {
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(1);
        ids.add(7);
        List<VmsUserFavoriteVideo> batchByIds = favoriteVideoService.getBatchByIds(ids);
        batchByIds.forEach(System.out::println);
    }

}
