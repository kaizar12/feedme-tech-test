package com.starsgroup.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RawFeed {
    private final List<Event> events;
    private final List<Market> markets;
    private final List<Outcome> outcomes;

    public RawFeed() {
        events = new ArrayList<>();
        markets = new ArrayList<>();
        outcomes = new ArrayList<>();
    }
}
