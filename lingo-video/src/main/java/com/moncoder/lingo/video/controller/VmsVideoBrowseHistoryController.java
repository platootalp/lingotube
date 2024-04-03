package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.VideoBrowseHistoryDTO;
import com.moncoder.lingo.video.domain.vo.VideoBrowseHistoryVO;
import com.moncoder.lingo.video.service.IVmsVideoBrowseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户浏览视频历史记录表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 13:40:30
 */
@Api(tags = "视频浏览历史管理")
@RestController
@RequestMapping("/video/history")
public class VmsVideoBrowseHistoryController {

    @Autowired
    private IVmsVideoBrowseHistoryService videoBrowseHistoryService;

    @ApiOperation("保存浏览历史")
    @PostMapping("/")
    public Result<String> save(@RequestBody @Valid VideoBrowseHistoryDTO videoBrowseHistoryDTO) {
        boolean flag = videoBrowseHistoryService.save(videoBrowseHistoryDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("批量删除浏览历史")
    @DeleteMapping("/s")
    public Result<String> deleteBatch(@RequestParam @NotNull Integer userId,
                                      @RequestParam @NotNull List<Integer> ids) {
        boolean flag = videoBrowseHistoryService.deleteBatch(userId, ids);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户全部浏览历史")
    @GetMapping("/page")
    public Result<LPage<VideoBrowseHistoryVO>> getPage(@RequestParam @NotNull Integer userId,
                                                       @RequestParam(defaultValue = "1") Long pageNum,
                                                       @RequestParam(defaultValue = "5") Long pageSize,
                                                       @RequestParam(required = false) String titleKeyWord) {
        LPage<VideoBrowseHistoryVO> browseHistoryVOS
                = videoBrowseHistoryService.getPageByUserId(userId, pageNum, pageSize,titleKeyWord);
        return Result.success(browseHistoryVOS);
    }
}
