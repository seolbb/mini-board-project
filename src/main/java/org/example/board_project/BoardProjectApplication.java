package org.example.board_project;

import org.example.board_project.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BoardRepository boardRepository){
        return args -> {
//            boardRepository.findAll().forEach(System.out::println);
        };
    }

}
