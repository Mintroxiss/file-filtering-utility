package output;

import config.Config;
import core.Statistic;

import java.io.File;

public class ReportPrinter {
    private static final String USE_CURRENT_DIRECTORY = "Будет использована текущая рабочая директория";

    public static void statistics(Config config, Statistic statistic) {
        if (config.isShortStatMode() && config.isFullStatMode()) {
            System.out.println("Запрошены оба типа статистики. Будет выведена полная.");
        }
        StringBuilder message = new StringBuilder();
        if (config.isFullStatMode()) {
            message.append("""
                    
                    ЦЕЛЫЕ ЧИСЛА
                    - Количество: %d
                    """.formatted(statistic.getIntegerCounter())
            );
            if (statistic.isHasIntegers()) {
                message.append("""
                                - Минимальное: %d
                                - Максимальное: %d
                                - Сумма: %d
                                - Среднее арифметическое: %f
                                """.formatted(
                                statistic.getIntegerMin(),
                                statistic.getIntegerMax(),
                                statistic.getIntegerSum(),
                                statistic.getIntegerAverage()
                        )
                );
            }
            message.append("""
                    
                    ВЕЩЕСТВЕННЫЕ ЧИСЛА
                    - Количество: %d
                    """.formatted(statistic.getFloatCounter())
            );
            if (statistic.isHasFloats()) {
                message.append("""
                                - Минимальное: %f
                                - Максимальное: %f
                                - Сумма: %f
                                - Среднее арифметическое: %f
                                """.formatted(
                                statistic.getFloatMin(),
                                statistic.getFloatMax(),
                                statistic.getFloatSum(),
                                statistic.getFloatAverage()
                        )
                );
            }
            message.append("""
                    
                    СТРОКИ
                    - Количество: %d
                    """.formatted(statistic.getSentenceCounter())
            );
            if (statistic.isHasSentences()) {
                message.append("""
                                - Минимальная длина: %d
                                - Максимальная длина: %d
                                
                                """.formatted(
                                statistic.getSentenceMinLength(),
                                statistic.getSentenceMaxLength()
                        )
                );
            }
        } else if (config.isShortStatMode()) {
            message.append("""
                            
                            КОЛИЧЕСТВО
                            - целых чисел: %d
                            - вещественных чисел: %d
                            - строк: %d
                            
                            """.formatted(
                            statistic.getIntegerCounter(),
                            statistic.getFloatCounter(),
                            statistic.getSentenceCounter()
                    )
            );
        } else {
            return;
        }
        System.out.println(message);
    }

    public static void fileError(File file) {
        System.out.printf("Файла по пути \"%s\" не существует", file.getPath());
    }

    public static void optionRequiresError(String option, String requirement) {
        System.out.printf("Опция \"%s\" требует указания %s%n", option, requirement);
    }

    public static void illegalArgumentsError(String error) {
        System.out.printf("Ошибка недопустимых аргументов: %s", error);
    }

    public static void notDirectoryError(File directory) {
        System.out.printf(
                "Путь \"%s\" существует, но это не директория. %s%n",
                directory.getPath(),
                USE_CURRENT_DIRECTORY
        );
    }

    public static void createDirectoryError(File directory) {
        System.out.printf("Не удалось создать директорию \"%s\". %s%n", directory.getPath(), USE_CURRENT_DIRECTORY);
    }

    public static void filledFileMessage(File file) {
        System.out.printf("Файл \"%s\" успешно заполнен.%n", file.getAbsolutePath());
    }
}
