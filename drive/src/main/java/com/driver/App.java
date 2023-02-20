package com.driver;


import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.oauth2.GoogleCredentials;

/**
 * Hello world!
 */
public final class App {

    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException {
        try {
            GoogleCredentials credential = GoogleCredentials.fromStream(new FileInputStream("C:/Users/vinic/Desktop/conectar google/drive/credenciais/client_secret_769812187261-ce4ijjlbs04ctfqrlnnsoh06cdr6cfhd.apps.googleusercontent.com.json"));
            // Usa as credenciais para fazer chamadas à API do Google aqui
            
       
        // Crie uma instância da classe de serviço do Google Drive
        Drive drive = new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), (HttpRequestInitializer) credential)
                .setApplicationName("meu computador pessoal")
                .build();

// Liste todos os arquivos na raiz do seu Google Drive
        FileList result = drive.files().list()
                .setQ("mimeType='application/vnd.google-apps.folder' and trashed=false")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .execute();

        for (File file : result.getFiles()) {
            System.out.printf(file.getName(), file.getId());
        }
    } catch (IOException e) {
        System.out.println("deu erro " + e );
      }
    }
}
