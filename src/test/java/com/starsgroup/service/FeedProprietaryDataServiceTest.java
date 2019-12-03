package com.starsgroup.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertTrue;
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
        assertTrue(true);
    }

    @Test
    public void abortFeedProcess() throws IOException {
        feedProprietaryDataService.abortFeedProcess();
        assertTrue(true);
    }

}