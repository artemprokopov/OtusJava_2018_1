//CHECKSTYLE:OFF

public class StringTutor  {
	public static void log(String s) {
		System.out.println(s);
	}


	
	/**
	 *  Убедитесь, что приветствие greeting имеет вид
	 *  Привет, Иван Иванов!
	 *  или
	 *  Привет,Петр Первый!
	 *  т.е. начинается на Привет, заканчивается на !
	 *  и содержит 2 слова для имени и фамилии,
	 *  причем имя и фамилия не короче 3 букв
	 *  и начинаются с большой буквы
	 */
	public boolean checkGreeting(String greeting) {
		String reg = "Привет,\\s*[А-Я][а-я]{2,}\\s+[А-Я][а-я]{2,}\\s*[!]";
		return greeting.matches(reg);
	}
}
//CHECKSTYLE:ON