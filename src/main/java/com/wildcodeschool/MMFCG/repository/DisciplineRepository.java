package com.wildcodeschool.MMFCG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.MMFCG.entity.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long>{
	


}
