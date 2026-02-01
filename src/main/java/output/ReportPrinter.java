package output;

import config.Config;
import core.Statistic;

import java.io.File;
import java.io.PrintStream;

public class ReportPrinter {
    private final PrintStream out;

    public ReportPrinter(PrintStream out) {
        this.out = out;
    }

    private static final String USE_CURRENT_DIRECTORY = "Будет использована текущая рабочая директория.";

    public void statistics(Config config, Statistic statistic) {
        if (config.isShortStatMode() && config.isFullStatMode()) {
            out.println("Запрошены оба типа статистики. Будет выведена полная.");
            dividerMessage();
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
                                - Максимальная длина: %d""".formatted(
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
                            - строк: %d""".formatted(
                            statistic.getIntegerCounter().toPlainString(),
                            statistic.getFloatCounter().toPlainString(),
                            statistic.getSentenceCounter()
                    )
            );
        } else {
            return;
        }
        out.println(message);
        dividerMessage();
    }

    public void fileError(File file) {
        out.printf("Файла по пути \"%s\" не существует.%n", file.getPath());
        dividerMessage();
    }

    public void optionRequiresError(String option, String requirement) {
        out.printf("Опция \"%s\" требует указания %s.%n", option, requirement);
        dividerMessage();
    }

    public void illegalArgumentsError(String error) {
        out.printf("Ошибка недопустимых аргументов: %s", error);
    }

    public void notDirectoryError(File directory) {
        out.printf(
                "Путь \"%s\" существует, но это не директория. %s%n",
                directory.getPath(),
                USE_CURRENT_DIRECTORY
        );
        dividerMessage();
    }

    public void createDirectoryError(File directory) {
        out.printf("Не удалось создать директорию \"%s\". %s%n", directory.getPath(), USE_CURRENT_DIRECTORY);
        dividerMessage();
    }

    public void filledFileMessage(File file) {
        out.printf("Файл \"%s\" успешно заполнен.%n", file.getAbsolutePath());
    }

    public void writeFileError(File file) {
        out.printf("Возникла ошибка при работе с файлом \"%s\".%n", file.getName());
    }

    public void readFileError(File file) {
        out.printf("Возникла ошибка при чтении файла \"%s\".%n", file.getName());
        dividerMessage();
    }

    public void unknownOptionError(String arg) {
        out.printf("Неизвестная опция: \"%s\"%n", arg);
        dividerMessage();
    }

    public void dividerMessage() {
        out.println("--------------------------------------------------------------------------");
    }
}
