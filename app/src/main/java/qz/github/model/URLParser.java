package qz.github.model;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {
    public URLParser(){
        
    }
    public List<String> parseURLs(String input) {
        List<String> userList = new ArrayList<>();

        // Pola regex untuk mendeteksi URL dalam string
        String urlPattern = "((https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|])";

        // Membuat pola regex
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(input);

        // Memisahkan input yang bukan URL berdasarkan spasi
        String[] inputs = input.split(" ");

        for (String in : inputs) {
            if (matcher.find()) {
                try {
                    // Mengonversi string input ke objek URL
                    URL url = new URL(in);

                    // Mendapatkan path dari URL
                    String path = url.getPath();

                    // Mengambil bagian terakhir dari path
                    String lastSegment = path.substring(path.lastIndexOf('/') + 1);

                    // Memasukkan input URL yang telah diparse ke dalam List
                    userList.add(lastSegment);
                } catch (Exception e) {
                    // Tangani jika terdapat kesalahan URL
                    e.printStackTrace();
                }
            } else {
                // Menambahkan inputan tanpa URL ke dalam List
                userList.add(in);
            }
        }

        // Mengembalikan List yang berisi inputan URL yang telah diparse
        return userList;
    }
}