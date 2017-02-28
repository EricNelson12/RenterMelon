package main;

import java.sql.Date;

public class Rental {
	
	public enum area {
		BlackMountain,DilworthMountain,Ellison,Glenmore,
		JoeRich,KelownaNorth,KelownaSouth,LowerMission,NorthGlenmore,RutlandNorth,
		RutlandSouth,KelownaSouthEast,SpringfieldSpall,UpperMission
	}
	
	private int Rid;
	private float price;
	private String title, description,address,link;
	
	//Note sql.Date is used instead of util.Date
	private Date date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRid() {
		return Rid;
	}

	public void setRid(int rid) {
		Rid = rid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
