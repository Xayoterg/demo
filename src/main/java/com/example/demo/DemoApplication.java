package com.example.demo;

import com.example.demo.controller.CurrencyParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
	@RestController
	public class DemoApplication {


		public static void main(String[] args) {
			SpringApplication.run(DemoApplication.class, args);
		}

		@GetMapping("/hello")
		public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
			String urlGif= "";
			try {
				 urlGif= CurrencyParser.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "<img src='"+urlGif+"'>";
		}

	}

