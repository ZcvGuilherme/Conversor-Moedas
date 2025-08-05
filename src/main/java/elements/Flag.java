package elements;

public enum Flag {
	USD("USD", "🇺🇸", "Dólar Americano"),
    EUR("EUR", "🇪🇺", "Euro"),
    GBP("GBP", "🇬🇧", "Libra Britânica"),
    JPY("JPY", "🇯🇵", "Iene"),
    AUD("AUD", "🇦🇺", "Dólar Australiano"),
    CHF("CHF", "🇨🇭", "Franco Suíço"),
    CAD("CAD", "🇨🇦", "Dólar Canadense"),
    BRL("BRL", "🇧🇷", "Real Brasileiro"),
    ARS("ARS", "🇦🇷", "Peso Argentino"),
	RUB("RUB", "🇷🇺", "Rublo Russo");
	
	private final String code;
	private final String flag;
	private final String name;
	
	Flag(String code, String flag, String name) {
		this.code = code;
		this.flag = flag;
		this.name = name;
		
	}
	
	public String getCode() {
		return code;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public String getName() {
		return name;
	}
	

	
	public static Flag searchByCode(String code) {
		for (Flag m : Flag.values()) {
			if (m.code.equalsIgnoreCase(code)) {
				return m;
			}
		}
		return null;
	}
	public static String getFlagByCode(String code) {
		for (Flag m : Flag.values()) {
			if (m.code.equalsIgnoreCase(code)) {
				return m.getFlag();
			}
		}
		return null;
	}
	
	public static String getNameByCode(String code) {
		for (Flag m : Flag.values()) {
			if (m.code.equalsIgnoreCase(code)) {
				return m.getName();
			}	
		}
		return null;
	}
}
