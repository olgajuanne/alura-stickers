import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import components.JsonParser;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão http e buscar os top 250 filmes
        String url ="https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
        // extrair só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.size());
       // System.out.println(listaDeFilmes.get(0));
        // exibir e manipular os dados
         for (Map<String,String> filme : listaDeFilmes) {
             System.out.print("\u001b[36m Titulo:\u001b[m ");
             System.out.println(filme.get("title"));
             System.out.print("\u001b[36m Poster:\u001b[m ");
             System.out.println(filme.get("image"));
             System.out.print("\u001b[37m\u001b[46m Classificação:\u001b[m ");
             System.out.println(filme.get("imDbRating"));
             System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐");
             System.out.println();
         }
    }
}
