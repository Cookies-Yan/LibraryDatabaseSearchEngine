package Model;

public class BrwerMng
{
	private static int Card_id;
	private static String Ssn;
	private static String Bname;
	private static String Address;
	private static String Phone;
	
	public BrwerMng() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BrwerMng(String ssn, String bname, String address, String phone)
	{
		super();
		this.Ssn=ssn;
		this.Bname=bname;
		this.Address=address;
		this.Phone =phone;
	}
    public static void setCard_id(int i) {
    	Card_id=i;
	}
	public static int getCard_id() {
		return Card_id;
	}
	public static String getSsn() {
		return Ssn;
	}
	public void setSsn(String ssn) {
		Ssn = ssn;
	}
	public static String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public static String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public static String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}   
	    
}
