package com.jun.fm.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@AllArgsConstructor
@Getter
public class PlayerNotFoundException extends RuntimeException {
	private Long playerId;
}
