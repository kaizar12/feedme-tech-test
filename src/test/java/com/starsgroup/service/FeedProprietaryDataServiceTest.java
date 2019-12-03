package com.starsgroup.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FeedProprietaryDataServiceTest {

    private FeedProprietaryDataService feedProprietaryDataService;

    @Before
    public void setUp() throws IOException {
        feedProprietaryDataService = new FeedProprietaryDataService();
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