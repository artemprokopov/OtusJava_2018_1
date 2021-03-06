package ru.otus.l061.atm.exception;
/**
 * Исключение, выбрасываем когда отсутствуют купюры в слотах хранилища банкомата.
 * @author Artem Prokopov
 * @since 30/03/2018
 * @version 1.0
 */
public class NotEnoughMoneyException extends Exception {
    /**
	 * Серийный номер.
	 */
	private static final long serialVersionUID = -2829340574254066034L;

	/**
     * Конструктор.
     * @param message строка сообщения передаваемая в исключение.
     */
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
