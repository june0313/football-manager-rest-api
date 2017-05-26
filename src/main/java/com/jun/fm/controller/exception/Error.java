package com.jun.fm.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@AllArgsConstructor(staticName = "of")
@Getter
public class Error {
	private Integer code;
	private String message;
}
