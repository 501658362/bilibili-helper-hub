package io.cruii.task;

import cn.hutool.core.io.FileUtil;
import io.cruii.component.BilibiliDelegate;
import io.cruii.pojo.po.TaskConfig;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cruii
 * Created on 2021/10/11
 */
@Log4j2
public class PushTask {
    private final String traceId;
    private final BilibiliDelegate delegate;

    public PushTask(String traceId,
                    BilibiliDelegate delegate) {
        this.traceId = traceId;
        this.delegate = delegate;
    }

    public Boolean push() {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
        List<String> logs = FileUtil.readLines(new File("logs/execution/all-" + date + ".log"), StandardCharsets.UTF_8);
        assert traceId != null;
        String content = logs
                .stream()
                .filter(line -> line.contains(traceId) && (line.contains("INFO") || line.contains("ERROR")))
                .map(line -> line.split("\\|\\|")[1])
                .collect(Collectors.joining("\n"));

        return push(delegate.getConfig(), content);
    }

    private boolean push(TaskConfig taskConfig, String content) {
        return false;
        //boolean result = false;
        //if (CharSequenceUtil.isNotBlank(taskConfig.getBarkDeviceKey())) {
        //    BarkPusher barkPusher = new BarkPusher(taskConfig.getBarkDeviceKey());
        //    result = barkPusher.push(content);
        //} else if (!CharSequenceUtil.hasBlank(taskConfig.getCorpId(), taskConfig.getCorpSecret(), taskConfig.getAgentId(), taskConfig.getMediaId())) {
        //    QyWechatPusher pusher = new QyWechatPusher(taskConfig.getCorpId(), taskConfig.getCorpSecret(), taskConfig.getAgentId(), taskConfig.getMediaId());
        //    result = pusher.push(content.replace("\n", "<br>"));
        //} else if (!CharSequenceUtil.hasBlank(taskConfig.getTgBotToken(), taskConfig.getTgBotChatId())) {
        //    TelegramBotPusher pusher = new TelegramBotPusher(taskConfig.getTgBotToken(), taskConfig.getTgBotChatId());
        //    result = pusher.push(content);
        //} else if (CharSequenceUtil.isNotBlank(taskConfig.getScKey())) {
        //    ServerChanPusher pusher = new ServerChanPusher(taskConfig.getScKey());
        //    result = pusher.push(content);
        //} else {
        //    log.info("该账号未配置推送或推送配置异常");
        //}
        //return result;
    }
}
