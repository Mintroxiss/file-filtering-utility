package config;

public class ConfigValidator {
    public static void validate(Config config) throws IllegalArgumentException {
        if (config.getFiles().isEmpty()) {
            throw new IllegalArgumentException("Не указаны файлы с входными данными.");
        }
    }
}
