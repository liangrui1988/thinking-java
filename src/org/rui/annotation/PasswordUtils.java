package org.rui.annotation;

import java.util.List;

public class PasswordUtils {

	// /密码必须包含至少一个数字
	@UseCase(id = 47, description = "passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}

	// 加密口令
	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	// 新密码不能平等之前使用的
	@UseCase(id = 49, description = "new passwords can 't equal previously used ones")
	public boolean checkForNewPassword(List<String> prevPassWords,
			String password) {
		return !prevPassWords.contains(password);
	}

}
