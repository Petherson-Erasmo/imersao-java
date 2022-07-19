import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // busca os top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
    
        URI endreco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest geRequest = HttpRequest.newBuilder(endreco).GET().build();
        HttpResponse<String> response = client.send(geRequest, BodyHandlers.ofString());
        String body = response.body();

        // Extrai os dados
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // manipula os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();

        }
    }
}
