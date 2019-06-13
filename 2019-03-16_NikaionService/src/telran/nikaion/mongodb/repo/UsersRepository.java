package telran.nikaion.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import telran.nikaion.entities.mongodb.UserCrud;
@Repository
public interface UsersRepository extends MongoRepository<UserCrud, String>{

}
