package com.monocept.model;

public class CredentialsDTO {

	String userNameDTO;
	String passwordDTO;

	public CredentialsDTO(String userNameDTO, String passwordDTO) {
		this.userNameDTO = userNameDTO;
		this.passwordDTO = passwordDTO;
	}

	public CredentialsDTO() {
	}

	public String getUserNameDTO() {
		return userNameDTO;
	}

	public void setUserNameDTO(String userNameDTO) {
		this.userNameDTO = userNameDTO;
	}

	public String getPasswordDTO() {
		return passwordDTO;
	}

	public void setPasswordDTO(String passwordDTO) {
		this.passwordDTO = passwordDTO;
	}

	@Override
	public String toString() {
		return "CredentialsDTO [userNameDTO=" + userNameDTO + ", passwordDTO=" + passwordDTO + "]";
	}

}