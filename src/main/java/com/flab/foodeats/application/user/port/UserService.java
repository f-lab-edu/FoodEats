package com.flab.foodeats.application.user.port;

import com.flab.foodeats.application.user.DeleteUserTarget;
import com.flab.foodeats.application.user.LoginUserResponse;
import com.flab.foodeats.application.user.LoginUserTarget;
import com.flab.foodeats.application.user.ModifyUserTarget;
import com.flab.foodeats.application.user.RegisterUserTarget;

public interface UserService {

	void registerUserInfo(RegisterUserTarget registerUserTarget);

	LoginUserResponse login(LoginUserTarget loginUserTarget) throws Exception;

	void modifyUserInfo(ModifyUserTarget modifyUserTarget);

	void deleteUserInfo(DeleteUserTarget deleteUserTarget);
}
