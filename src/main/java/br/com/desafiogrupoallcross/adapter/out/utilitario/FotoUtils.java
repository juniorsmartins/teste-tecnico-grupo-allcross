package br.com.desafiogrupoallcross.adapter.out.utilitario;

import br.com.desafiogrupoallcross.config.exception.http_404.MultipartFileNaoEncontradoException;
import br.com.desafiogrupoallcross.config.exception.http_500.FalhaNaCompactacaoDeImagemException;
import br.com.desafiogrupoallcross.config.exception.http_500.FalhaNaDescompactacaoDeImagemException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FotoUtils {

    public static byte[] compactarFoto(byte[] imagem) {
        Deflater deflater = new Deflater(); // criado um objeto Deflater, que é uma classe em Java usada para realizar a compressão de dados usando o algoritmo DEFLATE.
        deflater.setLevel(Deflater.BEST_COMPRESSION); // Define o nível de compressão para BEST_COMPRESSION, indicando que o algoritmo de compressão deve ser aplicado com a máxima eficiência possível.
        deflater.setInput(imagem); // Define o array de bytes imagem como entrada para o processo de compressão.
        deflater.finish(); // Informa ao Deflater que a entrada está completa e que ele deve concluir o processo de compressão.

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imagem.length); // Cria um ByteArrayOutputStream que será usado para armazenar os dados comprimidos. O tamanho inicial do buffer é definido como o comprimento da imagem.

        byte[] tmp = new byte[4*1024]; // Um loop é utilizado para realizar a compressão em partes. O método deflate do Deflater é chamado para comprimir parte dos dados e o resultado é escrito no ByteArrayOutputStream.
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        try {
            outputStream.close();
        } catch (Exception ex) {
            throw new FalhaNaCompactacaoDeImagemException();
        }

        return outputStream.toByteArray(); // Retorna o array de bytes que representa a imagem comprimida.
    }

    public static byte[] descompactarImagem(byte[] imagem) {
        Inflater inflater = new Inflater(); // Inflater é criado. A classe Inflater em Java é usada para realizar a descompressão de dados que foram comprimidos usando o algoritmo DEFLATE.
        inflater.setInput(imagem); // Define o array de bytes imagem como entrada para o processo de descompressão.

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imagem.length); // Cria um ByteArrayOutputStream que será usado para armazenar os dados descomprimidos. O tamanho inicial do buffer é definido como o comprimento da imagem comprimida.

        byte[] tmp = new byte[4*1024]; // Um loop é utilizado para realizar a descompressão em partes. O método inflate do Inflater é chamado para descomprimir parte dos dados e o resultado é escrito no ByteArrayOutputStream.
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ex) {
            throw new FalhaNaDescompactacaoDeImagemException();
        }

        return outputStream.toByteArray(); // Retorna o array de bytes que representa a imagem descomprimida.
    }

    public static byte[] converterMultipartFileEmArrayDeByte(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new MultipartFileNaoEncontradoException();
        }
    }
}

