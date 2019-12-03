package com.starsgroup.util;

import com.starsgroup.entity.FeedHeader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeedParserTest {

    @Test
    public void parseHeader() {
        String inputLine = "|2054|create|event|1497359166352|ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2|Football|Sky Bet League Two|\\|Accrington\\| vs \\|Cambridge\\||1497359216693|0|1|";
        FeedHeader feedHeader = FeedParser.parseHeader(inputLine);
        assertEquals("2054", feedHeader.getMsgId());
        assertEquals("create", feedHeader.getOperation());
        assertEquals("event", feedHeader.getType());
        assertEquals("1497359166352", feedHeader.getTimestamp());
    }

}