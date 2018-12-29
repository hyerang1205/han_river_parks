package com.example.open_api;

public class Contact {
	private int park_id;
	private String park_name;
	private String park_info1;
	private String park_info2;
	private String park_url;
	private String park_url_2;
	private String park_facebook;

	private int fac_id;
	private String fac_name;
	private String fac_info;
	private double fac_locax;
	private double fac_locay;

	private String food_name;
	private String food_category;
	private String food_call;

	public int getPId() {
		return park_id;
	}

	public void setPId(int park_id) {
		this.park_id = park_id;
	}

	public String getPName() {
		return park_name;
	}

	public void setPName(String park_name) {
		this.park_name = park_name;
	}

	public String getPInfo1() {
		return park_info1;
	}

	public void setPInfo1(String park_info1) {
		this.park_info1 = park_info1;
	}

	public String getPInfo2() {
		return park_info2;
	}

	public void setPInfo2(String park_info2) {
		this.park_info2 = park_info2;
	}

	public String getPUrl() {
		return park_url;
	}

	public void setPUrl(String park_url) {
		this.park_url = park_url;
	}

	public String getPUrl2() {
		return park_url_2;
	}

	public void setPUrl2(String park_url_2) {
		this.park_url_2 = park_url_2;
	}

	public String getPFacebook() {
		return park_facebook;
	}

	public void setPFaceBook(String park_facebook) {
		this.park_facebook = park_facebook;
	}

	public double getFx() {
		return fac_locax;
	}

	public void setFx(double fac_locax) {
		this.fac_locax = fac_locax;
	}

	public double getFy() {
		return fac_locay;
	}

	public void setFy(double fac_locay) {
		this.fac_locay = fac_locay;
	}

	// facility
	public int getFId() {
		return fac_id;
	}

	public void setFId(int fac_id) {
		this.fac_id = fac_id;
	}

	public String getFName() {
		return fac_name;
	}

	public void setFName(String fac_name) {
		this.fac_name = fac_name;
	}

	public String getFInfo() {
		return fac_info;
	}

	public void setFInfo(String fac_info) {
		this.fac_info = fac_info;
	}

	// food
	public String getFOName() {
		return food_name;
	}

	public void setFOName(String food_name) {
		this.food_name = food_name;
	}

	public String getFOCategory() {
		return food_category;
	}

	public void setFOCategory(String food_category) {
		this.food_category = food_category;
	}

	public String getFOCall() {
		return food_call;
	}

	public void setFOCall(String food_call) {
		this.food_call = food_call;
	}

}