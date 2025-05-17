package net.engineeringdigest.journlApp.service;

import net.engineeringdigest.journlApp.entity.JornalEntry;
import net.engineeringdigest.journlApp.entity.User;
import net.engineeringdigest.journlApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JornalEntry jornalEntry, String userName) {
        User user = userService.findByUserName(userName);
        try {
            jornalEntry.setDate(LocalDateTime.now());
            JornalEntry saved = journalEntryRepository.save(jornalEntry);
             user.getJornalEntryList().add(saved);
           //  user.setUserName(null);
             userService.saveEntry(user);
        }
        catch (Exception e) {
            System.out.println("Exception"+e);
        }

    }
    public void saveEntry(JornalEntry jornalEntry) {
            journalEntryRepository.save(jornalEntry);

    }


    public List<JornalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JornalEntry> findById(ObjectId id) {//Optional<> means result will be Optional.empty() if no match found
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJornalEntryList().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);//so that update  user is going to be saved
        journalEntryRepository.deleteById(id);
    }

}
//controller ----------> service ------------> repository