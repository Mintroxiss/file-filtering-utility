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
                    - Количество: %s
                    """.formatted(statistic.getIntegerCounter().toPlainString())
            );
            if (statistic.isHasIntegers()) {
                message.append("""
                                - Минимальное: %s
                                - Максимальное: %s
                                - Сумма: %s
                                - Среднее арифметическое: %s
                                """.formatted(
                                statistic.getIntegerMin().toPlainString(),
                                statistic.getIntegerMax().toPlainString(),
                                statistic.getIntegerSum().toPlainString(),
                                statistic.getIntegerAverage().toPlainString()
                        )
                );
            }
            message.append("""
                    
                    ВЕЩЕСТВЕННЫЕ ЧИСЛА
                    - Количество: %s
                    """.formatted(statistic.getFloatCounter().toPlainString())
            );
            if (statistic.isHasFloats()) {
                message.append("""
                                - Минимальное: %s
                                - Максимальное: %s
                                - Сумма: %s
                                - Среднее арифметическое: %s
                                """.formatted(
                                statistic.getFloatMin().toPlainString(),
                                statistic.getFloatMax().toPlainString(),
                                statistic.getFloatSum().toPlainString(),
                                statistic.getFloatAverage().toPlainString()
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
                            - целых чисел: %s
                            - вещественных чисел: %s
                            - строк: %d
                            
                            """.formatted(
                            statistic.getIntegerCounter().toPlainString(),
                            statistic.getFloatCounter().toPlainString(),
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

    public static void writeFileError(File file) {
        System.out.printf("Возникла ошибка при работе с файлом \"%s\".%n", file.getName());
    }

    public static void readFileError(File file) {
        System.out.printf("Возникла ошибка при чтении файла \"%s\"%n", file.getName());
    }
}
