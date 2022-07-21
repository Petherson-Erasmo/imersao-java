import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // busca os top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        // manipula os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i<10; i++) {
            Conteudo conteudo = conteudos.get(i);

            String nomeArquivo = "Alura-Stickers/image/" + conteudo.getTitulo() + ".png";

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(
                "\u001b[30;3;255m \u001b[40;3;255m Nome da Figurinha: \u001b[m"
                + conteudo.getTitulo()
                + ".png"+"\u001b[0m"
            );
            System.out.println();
        }
    }
}
