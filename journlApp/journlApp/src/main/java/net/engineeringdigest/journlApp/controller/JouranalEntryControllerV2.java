package net.engineeringdigest.journlApp.controller;

import net.engineeringdigest.journlApp.entity.JornalEntry;
import net.engineeringdigest.journlApp.entity.User;
import net.engineeringdigest.journlApp.service.JournalEntryService;
import net.engineeringdigest.journlApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JouranalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEnteriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JornalEntry> all = user.getJornalEntryList();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JornalEntry> createEntry(@RequestBody JornalEntry myEntry,@PathVariable String userName) {
        try {

            //myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JornalEntry> getJornalEntryById(@PathVariable ObjectId myId) {
        Optional<JornalEntry> jornalEntry =journalEntryService.findById(myId);
        if(jornalEntry.isPresent()) {
            return new ResponseEntity<>(jornalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    @DeleteMapping("id/{myId}")
//    public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
//        journalEntryService.deleteById(myId);
//        return true;
//    }
@DeleteMapping("id/{userName}/{myId}")
public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {
    journalEntryService.deleteById(myId,userName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
//similary i can do for the remaining
    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JornalEntry newEntry,@PathVariable String userName) {
        JornalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);

        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
