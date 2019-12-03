package com.starsgroup.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
public final class Event {
    private String eventId;
	private String category;
	private String subCategory;
    private String name;
    private String startTime;
	private String displayed;
	private String suspended;
	private List<Market> markets;
}
