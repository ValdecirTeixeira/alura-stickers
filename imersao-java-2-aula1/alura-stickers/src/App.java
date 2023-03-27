import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[42mTitle: \u001b[m "+"\u001b[1m"+filme.get("title")+"\u001b[m");
            System.out.println("\u001b[43mImage: \u001b[m "+"\u001b[3m"+filme.get("image")+"\u001b[m");
            System.out.println("\u001b[44mRating:\u001b[m "+ "\u001b[37m"+"\u001b[47m" + " "+filme.get("imDbRating")+ " "+"\u001b[m");
            
            int imDbRating = (int) Math.round(Double.parseDouble(filme.get("imDbRating")));
            for (int i = 0; i <= 10 && i < imDbRating; i++) {
                System.out.print("⭐");
            }
            System.out.println();
            System.out.println();   
        }
    }
}
