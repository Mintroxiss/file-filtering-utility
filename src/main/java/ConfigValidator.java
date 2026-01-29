public class ConfigValidator {
    public static void validate(Config config) {
        if (config.getFiles().isEmpty()) {
            throw new IllegalArgumentException("Не указаны файлы с входными данными.");
        }
        if (config.isShortStatMode() && config.isFullStatMode()) {
            System.out.println("Запрошены конфликтующие виды статистики. Будет выведена полная.");
        }
    }
}
