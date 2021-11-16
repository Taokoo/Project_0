
package com.taokoo.common.exception;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.taokoo.common.util.R;


/**
  * 全局异常处理器
 * @author Taokoo
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(TaokooException.class)
	public R handleTaokooException(TaokooException e){
		return R.error(e.getCode(),e.getMsg());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public R handleNullPointerException(NullPointerException e){
		logger.error(e.getMessage(), e);
		return R.error(500,"在程序处理中指向了一个不存在的位置，该条数据可能已不存在");
	}
	
	@ExceptionHandler(TooManyResultsException.class)
	public R handleTooManyResultsException(TooManyResultsException e){
		logger.error(e.getMessage(), e);
		return R.error(500,"在数据库中查询出了多余的数据，请联系管理员检查原因");
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public R hadleUsernameNotFoundException(UsernameNotFoundException e){
		logger.error(e.getMessage(), e);
		return R.error(404, "用户不存在");
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error(500,"数据库中已存在该记录");
	}

	@ExceptionHandler(AuthenticationException.class)
	public R handleAuthenticationException(AuthenticationException  e){
		logger.error(e.getMessage(), e);
		return R.error(401,"用户未登录，请先登录");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public R handleAccessDeniedException(AccessDeniedException e){
		logger.error(e.getMessage(), e);
		return R.error(403,"无访问权限");
	}
	
	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
}
