package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class conversorApi {

    private static final String API_KEY = "e4d44c1c0380dab72a219179";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws IOException,
            InterruptedException {
        String url = BASE_URL + monedaOrigen;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();

        // Procesar la respuesta JSON para obtener la tasa de cambio
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        double tasaCambio = jsonObject.get("conversion_rates").getAsJsonObject().get(monedaDestino).getAsDouble();
        return tasaCambio;
    }

}
