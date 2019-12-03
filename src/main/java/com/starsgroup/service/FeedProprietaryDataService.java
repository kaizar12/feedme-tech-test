package com.starsgroup.service;

import com.starsgroup.entity.FeedHeader;
import com.starsgroup.util.FeedParser;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Service
@Setter
public class FeedProprietaryDataService {

    private Socket clientSocket;
    private BufferedReader in;
    @Value("${provider.service.host}")
    private String providerHost;
    @Value("${provider.service.port}")
    private int providerPort;

    public void receiveData() throws IOException {
        try {
            startConnection(providerHost, providerPort);
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                FeedHeader feedHeader = FeedParser.parseHeader(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopConnection();
        }
    }

    public void abortFeedProcess() {

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
