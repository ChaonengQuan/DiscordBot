package com.chaonengquan;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Mono;

public class TranslateCommand implements Command {

    @Override
    public void execute(MessageCreateEvent event) {
        Mono<MessageChannel> channel = event.getMessage().getChannel();

        String content = event.getMessage().getContent().split("!tl")[1];

        /*-----Google Translate-----*/
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translate.translate(content);
        String translatedText = translation.getTranslatedText();

        channel.block().createMessage(translatedText).block();
    }

}
