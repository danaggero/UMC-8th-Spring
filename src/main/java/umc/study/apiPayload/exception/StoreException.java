package umc.study.apiPayload.exception;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
  public StoreException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
