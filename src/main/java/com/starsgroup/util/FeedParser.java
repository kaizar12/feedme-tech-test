package com.starsgroup.util;

import com.starsgroup.entity.FeedHeader;

public class FeedParser {

    public static FeedHeader parseHeader (String feedString) {
        String[] feed = tokenise(feedString);
        return FeedHeader.builder()
                .msgId(feed[1])
                .operation(feed[2])
                .type(feed[3])
                .timestamp(feed[4])
                .build();
    }

    private static String[] tokenise(String feedString) {
        return feedString.split("\\|");
    }
}
