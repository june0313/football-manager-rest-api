package com.jun.fm.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wayne on 2017. 5. 28..
 */
@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public Map<String, String> home() {
		return ImmutableMap.of("message", "hello fm");
	}

}
