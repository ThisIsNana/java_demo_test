package com.example.java_demo_test.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;

@Repository
public interface PersonInfoDAO extends JpaRepository<PersonInfo, String> {
	

	public List<PersonInfo> findByAgeGreaterThan(int age);

	public List<PersonInfo> findByAgeGreaterThanEqual(int age);

	public List<PersonInfo> findByAgeLessThan(int age);
	// 找出小於等於A歲的資料並由小到大排序ASC 由大到小DESC
	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	// 找出低於A年齡"或"高於B年齡的資料
//	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int ageFrom, int ageTo);
	// 找出介於A跟B之間的資料
	public List<PersonInfo> findByAgeBetween(int age1, int age2);

	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int age1, int age2);

	public List<PersonInfo> findByCityContaining(String str);

	public List<PersonInfo> findByAgeLessThanAndCityContaining(int age, String str);
	
	//baseDAO
	
//	public List<PersonInfo> doQueryByAge(int age); 
//	
//	public List<PersonInfo> doQueryByAge(int age, int limitSize); 
//	
//	public List<PersonInfo> doQueryByAge(int age, int limitSize, int startPosition); 
//	
//	@Transactional
//	public int doUpdateAgeByName(int age, String name);
	
	//===========JPQL練習
	
	@Transactional
	@Modifying
	@Query("update PersonInfo p set "
			+ "p.id = :newId, "
			+ "p.name = :newName, "
			+ "p.age = :newAge, "
			+ "p.city = :newCity where p.id = :newId")
	public int updateNameById(
			@Param("newId") String inputId,
			@Param("newName") String inputName,
			@Param("newAge") int inputAge,
			@Param("newCity") String inputCity);
	
	//jpa寫法
	public List<PersonInfo> findByNameContainingOrCityContaining(String name, String city);
	
	//@Query
	@Query(value = "SELECT * FROM person_info AS p "
			+ "WHERE p.city LIKE %:keyword% OR p.name LIKE %:keyword%", nativeQuery = true)
	public List<PersonInfo> searchByNameOrCity(@Param("keyword")String keyword);

	//RegExp
	@Query(value = "SELECT * FROM person_info AS p "
			+ "WHERE p.city REGEXP :keyword OR p.name REGEXP :keyword", nativeQuery = true)
	public List<PersonInfo> searchByNameOrCityRegexp(@Param("keyword")String keyword);

}
