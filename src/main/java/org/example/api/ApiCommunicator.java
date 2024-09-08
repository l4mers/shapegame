package org.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Player;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiCommunicator {
    private final String baseUrl = "http://localhost:8080/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Player getPlayer(UserInfo userInfo) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+"getPlayer"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userInfo)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } else {
            throw new IOException("Unexpected status code: " + response.statusCode());
        }
    }

    public List<HighScore> getHighScore(int page, int size, String sort) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String uri = String.format("%shighScore?page=%d&size=%d&sort=%s", baseUrl, page, size, sort);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } else {
            throw new IOException("Unexpected status code: " + response.statusCode());
        }
    }

    public HighScore postHighScore(Player player) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+"highScore"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(player)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } else {
            throw new IOException("Unexpected status code: " + response.statusCode());
        }
    }


}
