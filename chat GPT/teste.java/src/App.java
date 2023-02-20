import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class App {

    public static void main(String[] args) {

        // define o caminho do diretorio
        File diretorio = new File("C:\\Users\\vinic\\Downloads");

        // lista os arquivos
        File[] arquivos = diretorio.listFiles();

        if (arquivos != null) {

            // percorre por todos os arquivos do diretorio
            for (File arquivo : arquivos) {

                // verifica se o arquivo é um pdf

                if (arquivo.isFile() && arquivo.getName().endsWith(".pdf")) {

                    // JOptionPane.showMessageDialog(null,arquivo);

                    if (!arquivo.getName().contains("formato-invalido") ||
                            !arquivo.getName().contains("loader")) {

                        String filePath = arquivo.toString();

                        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
                            PDFTextStripper pdfStripper = new PDFTextStripper();
                            String text = pdfStripper.getText(document);
                            // System.out.println(text);
                            System.out.println(filePath);
                            // Encontra a posição do último caractere de barra invertida na string
                            int lastSlash = filePath.lastIndexOf("\\");

                            // Extrai a parte da string após a posição da última barra invertida
                            String fileName = filePath.substring(lastSlash + 1);

                            // Encontra a posição do primeiro hífen na string do nome do arquivo
                            int firstHyphen = fileName.indexOf("-");

                            // Extrai a parte da string contendo o número "o id do cliente"
                            String idCliente = fileName.substring(0, firstHyphen);

                            System.out.println(idCliente); // Saída: do cliente

                            String[] partes = fileName.split("-");
                            String Sigla = partes[1];

                            System.out.println("o id do cliente é, " + idCliente + ", e a siga  é, " + Sigla); // Saída:
                                                                                                               

                            if (text.toLowerCase().contains("conteudo do pdf".toLowerCase())) {
                                System.out.println("Encontrado!");
                            } else {
                                System.out.println("Não encontrado!");
                            }
                        } catch (IOException e) {
                            System.out.println(e);
                        }

                    }
                }

            }
        }
    }
}