package ru.otus.l061.atm.exception;
/**
 * Исключение, выбрасываем когда операция банкомата не может быть завершена в штатном режиме.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @version 1.0
 */
public class OperationAtmCanNotCompleteException extends Exception {
    /**
     * Конструктор.
     * @param message сообщение передаваемое в исключение.
     */
    public OperationAtmCanNotCompleteException(String message) {
        super(message);
    }
}
