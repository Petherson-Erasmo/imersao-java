
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            String nomeArquivo = "Alura-Stickers/image/" + titulo + ".png";

            InputStream inputStream = new URL(urlImagem).openStream();

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[30;3;255m \u001b[40;3;255m Título: \u001b[m" + titulo + "\u001b[0m");
            System.out.println("\u001b[30;3;255m \u001b[40;3;255m Ano: \u001b[m" + filme.get("year") + "\u001b[0m");
            System.out.println("\u001b[37;3;255m \u001b[45;3;255m Classificação: " + filme.get("imDbRating") + "\u001b[0m" );
            System.out.println();
        }
    }
}
