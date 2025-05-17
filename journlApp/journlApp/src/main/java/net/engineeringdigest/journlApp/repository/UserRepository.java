package net.engineeringdigest.journlApp.repository;

import net.engineeringdigest.journlApp.entity.JornalEntry;
import net.engineeringdigest.journlApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUserName(String username);

}

//controller ----------> service ------------> repository
