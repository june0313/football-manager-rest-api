package com.jun.fm.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wayne on 2017. 5. 27..
 */
@Getter
@AllArgsConstructor
public class ClubNotFoundException extends RuntimeException {
	private Long clubId;
}
