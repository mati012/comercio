package com.example.consumidorpromociones.util;

import com.example.consumidorpromociones.dto.PromocionDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonWriter {

    public static void escribirJson(PromocionDto dto, String directorioSalida) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            File directorio = new File(directorioSalida);
            if (!directorio.exists()) directorio.mkdirs();

            File archivo = new File(directorio, "promocion_" + timestamp + ".json");
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(json);
            }

            System.out.println("✅ Archivo generado: " + archivo.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("❌ Error al escribir archivo JSON");
            e.printStackTrace();
        }
    }
}
