package com.kr.jabtang;

public class NaverUserDTO {
	private String email;
    private String nickname;
    private String age;
	
    
    
    public NaverUserDTO(String email, String nickname, String age) {
		this.email = email;
		this.nickname = nickname;
		this.age = age;
	}
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
    
    
}
