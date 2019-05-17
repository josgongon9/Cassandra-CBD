package com.cbd.cassandra.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cbd.cassandra.model.Actor;
import com.google.common.eventbus.AllowConcurrentEvents;

public interface ActorRepository extends CrudRepository<Actor, String> {

	
	@Query(value="SELECT * FROM actor WHERE fullname= ?0 ALLOW FILTERING")
	public List<Actor> findByName(String fullName);
	
	@Query(value="SELECT * FROM actor WHERE movie=?0 ALLOW FILTERING")
	public List<Actor> findByFilm(String filmName);

	@Query("SELECT * FROM actor WHERE age < ?0 ALLOW FILTERING")
	public List<Actor> findActorHasAgeLessThan(int age);
	
	@Query("SELECT * FROM actor WHERE year < ?0 ALLOW FILTERING")
	public List<Actor> findActorYearLessThan(int year);
	
	@Query("SELECT * FROM actor WHERE year > ?0 ALLOW FILTERING")
	public List<Actor> findActorYearGreaterThan(int year);
}