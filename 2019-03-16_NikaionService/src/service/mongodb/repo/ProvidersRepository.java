package service.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import service.entities.mongodb.ProviderCrud;
@Repository
public interface ProvidersRepository extends MongoRepository<ProviderCrud, String>{

}
