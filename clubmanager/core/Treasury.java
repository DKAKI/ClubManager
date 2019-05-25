/**
 * 
 */
package clubmanager.core;

/**
 * @author NAHED
 *
 */
public class Treasury {

	int id_treasury;
	int year;
	int sum;
	String state_of_treasury;
	String responsible_treasury;	
	
	/**
	 * @param id_treasury
	 * @param year
	 * @param sum
	 * @param state_of_treasury
	 * @param responsible_treasury
	 */
	public Treasury(int id_treasury, int year, int sum, String state_of_treasury, String responsible_treasury) {
		super();
		this.id_treasury = id_treasury;
		this.year = year;
		this.sum = sum;
		this.state_of_treasury = state_of_treasury;
		this.responsible_treasury = responsible_treasury;
	}
	public Treasury(int year, int sum, String state_of_treasury, String responsible_treasury) {
		this(0, year,  sum,  state_of_treasury,  responsible_treasury );
	}
		/**
	 * @return the id_treasury
	 */
	public int getId_treasury() {
		return id_treasury;
	}
	/**
	 * @param id_treasury the id_treasury to set
	 */
	public void setId_treasury(int id_treasury) {
		this.id_treasury = id_treasury;
	}


	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
		//setId_treasury() setYear() setSum() setState_of_treasury() setResponsible_treasury()
	}
	/**
	 * @return the sum
	 */
	public int getSum() {
		return sum;
	}
	/**
	 * @param sum the sum to set
	 */
	public void setSum(int sum) {
		this.sum = sum;
	}
	/**
	 * @return the state_of_treasury
	 */
	public String getState_of_treasury() {
		return state_of_treasury;
	}
	/**
	 * @param state_of_treasury the state_of_treasury to set
	 */
	public void setState_of_treasury(String state_of_treasury) {
		this.state_of_treasury = state_of_treasury;
	}
	/**
	 * @return the responsible_treasury
	 */
	public String getResponsible_treasury() {
		return responsible_treasury;
	}
	/**
	 * @param responsible_treasury the responsible_treasury to set
	 */
	public void setResponsible_treasury(String responsible_treasury) {
		this.responsible_treasury = responsible_treasury;
	}
		
	/**
	 * 
	 */
	public Treasury() {
		// TODO Auto-generated constructor stub
	}
	@Override
public String toString() {
	return "Treasury [id_treasury=" + id_treasury + ", year=" + year + ", sum=" + sum + ", state_of_treasury="
			+ state_of_treasury + ", responsible_treasury=" + responsible_treasury + "]";
}

}
