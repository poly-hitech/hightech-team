package com.example.test.Model;

public class HostAdress {
	private String host;
	private String address = "https://wookportfolio.duckdns.org:83/";

	public String getHost() {
		return host;
	}
	public void setHost() {
		this.host = this.address;
	}
}