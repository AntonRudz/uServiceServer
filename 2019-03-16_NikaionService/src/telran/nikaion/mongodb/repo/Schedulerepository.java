package telran.nikaion.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.nikaion.entities.mongodb.SchedulePatternCrud;

public interface Schedulerepository extends MongoRepository<SchedulePatternCrud, String>{

}
