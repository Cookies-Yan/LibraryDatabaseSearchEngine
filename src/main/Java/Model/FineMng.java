package Model;

public class FineMng {
	private static int Loan_id;
	private static int Card_id;
	private static String Date_out;
	private static String Date_in;
	private static int Paid;
	private static float Fine_amt;
	
	public FineMng() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FineMng(int card_id, String date_out, String date_in)
	{
		super();
		this.Card_id=card_id;
		this.Date_out=date_out;
		this.Date_in=date_in;
	}
	public FineMng(int loan_id, float fine_amt)
	{
		super();
		this.Loan_id=loan_id;
		this.Fine_amt=fine_amt;
	}
	
	public static int getLoan_id() {
		return Loan_id;
	}
	public static void setLoan_id(int loan_id) {
		Loan_id = loan_id;
	}
	public static int getCard_id() {
		return Card_id;
	}
	public void setCard_id(int card_id) {
		Card_id = card_id;
	}
	public static String getDate_out() {
		return Date_out;
	}
	public static void setDate_out(String date_out) {
		Date_out = date_out;
	}
	public static String getDate_in() {
		return Date_in;
	}
	public static void setDate_in(String date_in) {
		Date_in = date_in;
	}
//	public static int getPaid() {
//		return paid;
//	}
//	public static void setPaid(int paid) {
//		FineMng.paid = paid;
//	}
	public static float getFine_amt() {
		return Fine_amt;
	}
	public static void setFine_amt(float fine_amt) {
		FineMng.Fine_amt = fine_amt;
	}
	
}
