package com.starsgroup.service;

import com.starsgroup.entity.Event;
import com.starsgroup.entity.RawFeed;
import com.starsgroup.util.FeedType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeedProprietaryDataServiceTest {

    private FeedProprietaryDataService feedProprietaryDataService;

    @Mock
    Socket socketMocked;

    @Mock
    InputStream inputStreamMocked;

    @Before
    public void setUp() throws IOException {
        feedProprietaryDataService = new FeedProprietaryDataService();
        feedProprietaryDataService.setClientSocket(socketMocked);
        when(socketMocked.getInputStream()).thenReturn(inputStreamMocked);
    }

    @Test
    public void receiveData() throws IOException {
        feedProprietaryDataService.receiveData();
        verify(socketMocked).getInputStream();
    }

    @Test
    public void abortFeedProcess() throws IOException {
        feedProprietaryDataService.abortFeedProcess();
        assertTrue(true);
    }

    @Test
    public void parseFeed() {
        String inputLine = "|2054|create|event|1497359166352|ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2|Football|Sky Bet League Two|\\|Accrington\\| vs \\|Cambridge\\||1497359216693|0|1|";
        RawFeed rawFeed = new RawFeed();
        Event expectedEvent = Event.builder()
                .eventId("ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2")
                .category("Football")
                .subCategory("Sky Bet League Two")
                .name("\\|Accrington\\| vs \\|Cambridge\\|")
                .startTime("1497359216693")
                .displayed("0")
                .suspended("1")
                .build();

        feedProprietaryDataService.setRawFeed(rawFeed);
        feedProprietaryDataService.parseFeed(inputLine, FeedType.event.name());

        assertEquals(expectedEvent, rawFeed.getEvents().get(0));
    }

}