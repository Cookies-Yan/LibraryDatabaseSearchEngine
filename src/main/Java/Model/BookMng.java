package Model;

public class BookMng {
	private static String SearchTxt;
	public BookMng() {
		super();
	}
	
	public BookMng(String searchTxt)
	{
		super();
		this.SearchTxt=searchTxt;
	}
	public String getSearchTxt() {
		return SearchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		SearchTxt = searchTxt;
	}
}
