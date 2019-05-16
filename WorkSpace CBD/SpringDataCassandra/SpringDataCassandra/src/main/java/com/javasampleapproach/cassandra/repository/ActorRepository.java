package com.javasampleapproach.cassandra.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.javasampleapproach.cassandra.model.Actor;

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