package service.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Phone implements Serializable{
	private String number;
	private boolean telegram;
	private boolean viber;
	private boolean whatsapp;
public Phone() {
}
public Phone(String number, boolean telegram, boolean viber, boolean whatsapp) {
	super();
	this.number = number;
	this.telegram = telegram;
	this.viber = viber;
	this.whatsapp = whatsapp;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public boolean isTelegram() {
	return telegram;
}
public void setTelegram(boolean telegram) {
	this.telegram = telegram;
}
public boolean isViber() {
	return viber;
}
public void setViber(boolean viber) {
	this.viber = viber;
}
public boolean isWhatsapp() {
	return whatsapp;
}
public void setWhatsapp(boolean whatsapp) {
	this.whatsapp = whatsapp;
}


}
