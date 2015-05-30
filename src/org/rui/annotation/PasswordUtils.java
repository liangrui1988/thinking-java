package org.rui.annotation;

import java.util.List;

public class PasswordUtils {

	// /��������������һ������
	@UseCase(id = 47, description = "passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}

	// ���ܿ���
	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	// �����벻��ƽ��֮ǰʹ�õ�
	@UseCase(id = 49, description = "new passwords can 't equal previously used ones")
	public boolean checkForNewPassword(List<String> prevPassWords,
			String password) {
		return !prevPassWords.contains(password);
	}

}
