package com.softStore.softStore.Class;
import com.softStore.softStore.Class.NotificationHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
public class JsonMonitorService {
    private final NotificationHandler notificationHandler;
    private final ObjectMapper objectMapper;
    private int lastArticleCount = 0;

    public JsonMonitorService(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
        this.objectMapper = new ObjectMapper();

        // Inicia el hilo
        new Thread(this::monitorJson).start();
    }

    public void monitorJson() {
        while (true) {
            try {
                // Ruta al archivo JSON
                File jsonFile = Paths.get("src/main/resources/articles.json").toFile();
                JsonNode jsonNode = objectMapper.readTree(jsonFile);

                // Suponiendo que los artículos están en un array "articles"
                JsonNode articles = jsonNode.get("articles");
                if (articles != null && articles.size() > lastArticleCount) {
                    // Notificar sobre los nuevos artículos
                    int newArticles = articles.size() - lastArticleCount;
                    notificationHandler.notifyClients("Se agregaron " + newArticles + " artículos nuevos.");
                    lastArticleCount = articles.size();
                }

                // Pausa de 5 segundos
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
