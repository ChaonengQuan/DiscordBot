package com.chaonengquan;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class TranslateCommand implements Command{

    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage();

    }

}
