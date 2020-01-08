package com.wildcodeschool.MMFCG.repository;

import com.wildcodeschool.MMFCG.entity.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//DAO
@Repository
public interface ClubRepository extends JpaRepository<Club, Long>{
	
	@Query("SELECT c FROM Club c WHERE fk_region_id=:region")
	public List<Club> findByRegion(@Param("region")int region);
}
