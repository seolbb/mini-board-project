package org.example.board_project.repository;

import org.example.board_project.domain.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BoardRepository extends CrudRepository<Board, Long>, PagingAndSortingRepository<Board,Long> {
}
