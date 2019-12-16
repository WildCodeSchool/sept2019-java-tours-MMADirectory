package com.wildcodeschool.MMFCG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.MMFCG.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{

}
