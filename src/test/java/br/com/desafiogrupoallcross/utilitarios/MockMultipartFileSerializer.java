package br.com.desafiogrupoallcross.utilitarios;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

public class MockMultipartFileSerializer extends JsonSerializer<MockMultipartFile> {

    @Override
    public void serialize(MockMultipartFile value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeStringField("originalFilename", value.getOriginalFilename());
        gen.writeStringField("contentType", value.getContentType());
        gen.writeStringField("content", new String(value.getBytes())); // Assuming content is a String
        // Add more fields if needed
        gen.writeEndObject();
    }
}
