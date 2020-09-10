package Model;


public class LoanMng
{
	private static int Loan_id;
	private static String Isbn;
	private static int Card_id;
	private static String Date_out;
	private static String Due_date;
	private static String Date_in;
	private static String SearchTxt;
	
	
	public LoanMng() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoanMng(String isbn, int card_id, String date_out, String due_date)
	{
		super();
		this.Isbn=isbn;
		this.Card_id=card_id;
		this.Date_out=date_out;
		this.Due_date=due_date;
	}
	public LoanMng(String isbn, int card_id, String date_in)
	{
		super();
		this.Isbn=isbn;
		this.Card_id=card_id;
		this.Date_in =date_in;
	}

	public String getIsbn() {
		return Isbn;
	}
	public static void setIsbn(String isbn) {
		Isbn = isbn;
	}
	public int getCard_id() {
		return Card_id;
	}
	public static void setCard_id(int card_id) {
		Card_id = card_id;
	}
	public String getDate_out() {
		return Date_out;
	}
	public static void setDate_out(String date_out) {
		Date_out = date_out;
	}
	public String getDue_date() {
		return Due_date;
	}
	public static void setDue_date(String due_date) {
		Due_date = due_date;
	}
	public String getDate_in() {
		return Date_in;
	}
	public static void setDate_in(String date_in) {
		Date_in = date_in;
	}
	public String getSearchTxt() {
		return SearchTxt;
	}
	public static void setSearchTxt(String searchTxt) {
		SearchTxt = searchTxt;
	}
}
