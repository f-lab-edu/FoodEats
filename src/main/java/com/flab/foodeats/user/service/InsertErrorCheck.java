package com.flab.foodeats.user.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.code.ErrorUserCode;

@Service
public class InsertErrorCheck {

	public void idAlreadyExistCheck(InsertFormDTO insertFormDTO) {
		if (insertFormDTO != null) {
			throw new DuplicateKeyException(ErrorUserCode.ID_EXIST.getMessage());
		}
	}
}
