package com.moncoder.lingo.video;

import com.moncoder.lingo.video.service.IVmsVideoService;
import com.moncoder.lingo.video.service.impl.VmsVideoServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LingoVideoApplicationTests {

    @Autowired
    IVmsVideoService videoService;

    @Test
    void contextLoads() {
        videoService.saveTrendingVideos(4);
        videoService.saveLatestVideos(4);
        videoService.saveRecommendedVideos(4);
    }

}
