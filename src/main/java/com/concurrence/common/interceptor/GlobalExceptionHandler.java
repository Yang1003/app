package com.concurrence.common.interceptor;


import com.concurrence.common.exception.BizException;
import com.concurrence.common.exception.BizResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import static com.concurrence.common.constants.MessageConstants.RESPONSE_COMMON_PARAMETER_ERROR_CODE;
import static com.concurrence.common.constants.MessageConstants.RESPONSE_COMMON_PARAMETER_ERROR_MSG;
import static com.concurrence.common.constants.MessageConstants.RESPONSE_FAIL_CODE;


/**
 * @author pengli
 * @date 2022/7/22 15:57
 * <p>
 * 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理BizException, 封装成BizResponse的统一格式
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResponseEntity errorHandler(HttpServletRequest request, BizException exception) {
        BizResponse response = BizResponse.failure(exception.getCode(), exception.getMsg(), exception.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 处理JSR303 验证异常 exception,API入参和出参的数据验证时发生的异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity errorHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        log.error("In GlobalExceptionHandler, MethodArgumentNotValidException error", exception);
        List<String> errorMsg = new ArrayList<>();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                errorMsg.add(fieldError.getDefaultMessage());
            } else {
                errorMsg.add(RESPONSE_COMMON_PARAMETER_ERROR_MSG);
                break;
            }
        }
        BizResponse response = BizResponse.failure(RESPONSE_COMMON_PARAMETER_ERROR_CODE, errorMsg.toArray(new String[0]));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 枚举类型不能匹配的/参数类型不匹配
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity errorHandler(HttpServletRequest request, HttpMessageNotReadableException exception) {
        log.error("In GlobalExceptionHandler, HttpMessageNotReadableException error", exception);
        BizResponse response = BizResponse.failure(RESPONSE_COMMON_PARAMETER_ERROR_CODE, RESPONSE_COMMON_PARAMETER_ERROR_MSG);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity errorHandler(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        log.error("In GlobalExceptionHandler, MethodArgumentTypeMismatchException error", exception);
        BizResponse response = BizResponse.failure(RESPONSE_COMMON_PARAMETER_ERROR_CODE, RESPONSE_COMMON_PARAMETER_ERROR_MSG);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 没有catch的到 exception统一处理，系统的最后防线
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity errorHandler(HttpServletRequest request, Exception exception) {
        log.error("In GlobalExceptionHandler, unknown exception error", exception);

        Throwable cause = exception.getCause();
        if (cause != null) {
            if (cause instanceof BizException) {
                return buildBizResponse((BizException) cause);
            } else {
                cause = cause.getCause();
                if (cause instanceof BizException) {
                    return buildBizResponse((BizException) cause);
                }
            }
        }

        BizResponse response = BizResponse.failure(RESPONSE_FAIL_CODE, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<BizResponse> buildBizResponse(BizException cause) {
        final BizResponse response = BizResponse.failure(cause.getCode(), cause.getMsg());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
