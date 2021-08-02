package com.flab.foodeats.user.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.ApiResponse;
import com.flab.foodeats.user.model.UpdateFormDTO;

@Service
public interface UserService {

	ApiResponse insertUserInfo(InsertFormDTO insertFormDTO);

	ApiResponse login(LoginFormDTO loginFormDTO);

	ApiResponse logout();

	ApiResponse findAllUser();

	ApiResponse findInfoById(String id);

	ApiResponse updateUserInfo(UpdateFormDTO updateFormDTO);

	ApiResponse deleteUserInfo(DeleteFormDTO deleteFormDTO);
}
