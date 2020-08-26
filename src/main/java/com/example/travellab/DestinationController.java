package com.example.travellab;

import com.example.travellab.mainJava.main;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {

	main start = new main();

	@RequestMapping("/result")
	public String[] sayHi(){
		return start.mainCall();
	}
}
