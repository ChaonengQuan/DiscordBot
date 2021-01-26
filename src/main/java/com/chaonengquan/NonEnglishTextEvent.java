package com.chaonengquan;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class NonEnglishTextEvent {
    public void execute(ReactionAddEvent event) {
        ReactionEmoji emoji = event.getEmoji();
        if (emoji.asUnicodeEmoji().get().getRaw().equals("🇺🇸")) { //same as "\uD83C\uDDFA\uD83C\uDDF8"

            event.getMessage().subscribe(message -> {

                Translate translate = TranslateOptions.getDefaultInstance().getService();
                Translation translation = translate.translate(message.getContent());
                String translatedText = translation.getTranslatedText();

                event.getChannel().block().createMessage(translatedText).block();
            });

        }
    }
}
