package com.taokoo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @author Taokoo
 * @date 2021/06/29
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class TaokooException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private int code = 500;
    private String msg;
}
