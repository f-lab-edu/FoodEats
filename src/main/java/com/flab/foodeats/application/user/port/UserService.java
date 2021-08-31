package com.flab.foodeats.application.user.port;

import com.flab.foodeats.application.user.DeleteUserTarget;
import com.flab.foodeats.application.user.LoginUserTarget;
import com.flab.foodeats.application.user.ModifyUserTarget;
import com.flab.foodeats.application.user.RegisterUserTarget;

public interface UserService {

	void registerUserInfo(RegisterUserTarget registerUserTarget);

	Long login(LoginUserTarget loginUserTarget);

	void modifyUserInfo(ModifyUserTarget modifyUserTarget);

	void deleteUserInfo(DeleteUserTarget deleteUserTarget);
}
