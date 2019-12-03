package com.starsgroup.service;

import com.starsgroup.entity.Event;
import com.starsgroup.entity.FeedHeader;
import com.starsgroup.entity.RawFeed;
import com.starsgroup.util.FeedParser;
import com.starsgroup.util.FeedType;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Setter
public class FeedProprietaryDataService {

    private Socket clientSocket;
    private BufferedReader in;
    private RawFeed rawFeed;
    @Value("${provider.service.host}")
    private String providerHost;
    @Value("${provider.service.port}")
    private int providerPort;

    public void receiveData() throws IOException {
        try {
            rawFeed = new RawFeed();
            startConnection(providerHost, providerPort);
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                FeedHeader feedHeader = FeedParser.parseHeader(inputLine);
                parseFeed(inputLine, feedHeader.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopConnection();
        }
    }

    public void abortFeedProcess() {

    }

    protected void parseFeed(String inputline, String feedType) {
        switch (FeedType.valueOf(feedType)) {
            case event:
                rawFeed.getEvents().add(FeedParser.parseEvent(inputline));
                break;
            case market:
                rawFeed.getMarkets().add(FeedParser.parseMarket(inputline));
                break;
            case outcome:
                rawFeed.getOutcomes().add(FeedParser.parseOutcome(inputline));
                break;
        }
    }

    protected List<Event> transformFeeds() {
        rawFeed.getMarkets().forEach(market -> market.setOutcomes(
                rawFeed.getOutcomes().stream().filter(outcome -> outcome.getMarketId().equals(market.getMarketId())).collect(toList())
        ));
        rawFeed.getEvents().forEach(event -> event.setMarkets(
                rawFeed.getMarkets().stream().filter(market -> market.getEventId().equals(event.getEventId())).collect(toList())
        ));
        return Collections.unmodifiableList(rawFeed.getEvents());
    }

    private void startConnection(String ip, int port) throws IOException {
        if (clientSocket == null) clientSocket = new Socket(ip, port);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void stopConnection() throws IOException {
        if (in != null) in.close();
        clientSocket.close();
    }

}
