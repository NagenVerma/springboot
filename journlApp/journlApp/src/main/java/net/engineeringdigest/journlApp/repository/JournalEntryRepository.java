package net.engineeringdigest.journlApp.repository;

import net.engineeringdigest.journlApp.entity.JornalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JornalEntry, ObjectId>{


}

//controller ----------> service ------------> repository
