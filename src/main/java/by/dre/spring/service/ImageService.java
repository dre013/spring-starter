package by.dre.spring.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
public class ImageService {

    @Value("${app.image.bucket}")
    private String bucket;

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path fullImagesPath = Path.of(bucket, imagePath);

        try(content) {
            Files.createDirectories(fullImagesPath.getParent());
            Files.write(fullImagesPath, content.readAllBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String imagePath) {
        Path fullImagesPath = Path.of(bucket, imagePath);

        return Files.exists(fullImagesPath)
                ? Optional.of(Files.readAllBytes(fullImagesPath))
                : Optional.empty();
    }
}
