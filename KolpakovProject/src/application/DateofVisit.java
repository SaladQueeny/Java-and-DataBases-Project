package application;
//Колпаков Егор
public class DateofVisit {//класс для сохранения информации о дате визита

	private int yyyy;
	private int mm;
	private int dd;
	private int hh;
	private int mi;
	private int ss;
	
	public DateofVisit(int yyyy, int mm, int dd, int hh, int mi, int ss) {
		this.yyyy = yyyy;
		this.mm = mm;
		this.dd = dd;
		this.hh = hh;
		this.mi = mi;
		this.ss = ss;
	}
	public int getYyyy() {
		return yyyy;
	}

	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public int getHh() {
		return hh;
	}

	public void setHh(int hh) {
		this.hh = hh;
	}

	public int getMi() {
		return mi;
	}

	public void setMi(int mi) {
		this.mi = mi;
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
	}

}
