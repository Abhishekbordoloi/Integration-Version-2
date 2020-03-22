package until.enumeration;

public enum CategoryType {
	SOFTLINES(1, "softlines"), HARDLINES(2, "hardlines"), CONSUMABLES(3, "consumables"), MEDIA(4, "media products"),
	OTHERS(5, "Others (If you are NOT sure about your product please choose this)");

	CategoryType(int index, String categoryType) {
		this.categoryType = categoryType;
	}

	private String categoryType;

	public String getCategoryType() {
		return this.categoryType;
	}

	public static CategoryType getCategoryType(int index) {
		switch (index) {
		case 1:
			return SOFTLINES;
		case 2:
			return HARDLINES;
		case 3:
			return CONSUMABLES;
		case 4:
			return MEDIA;
		default:
			return OTHERS;
		}
	}
}
