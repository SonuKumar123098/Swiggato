package com.example.swiggato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SwiggatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggatoApplication.class, args);
		// demo for stream api -> which is pre build api
//		List<Integer>l= List.of(1,2,3,4,2,6,2);
//		List<Integer>newList= l.stream().map(x -> x*x).collect(Collectors.toList());
//		List<Integer> sortedList=l.stream().sorted().collect(Collectors.toList());
//		List<Integer>filtered=l.stream().filter(x -> x%2==0).collect(Collectors.toList());
		//you can explore more operation by yourself
		//just treat this as replacement of for loop
	}

}
