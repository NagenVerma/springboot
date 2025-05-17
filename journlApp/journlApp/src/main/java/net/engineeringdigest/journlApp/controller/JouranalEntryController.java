//package net.engineeringdigest.journlApp.controller;
//
//import net.engineeringdigest.journlApp.entity.JornalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JouranalEntryController {
//
//    private Map<Long,JornalEntry> jornalEntries = new HashMap<>();
//    @GetMapping//method inside a controller should be public so that they can be accesed and invoked by the
//    //spring framework or external http request
//    public List<JornalEntry> getAll() {
//        return new ArrayList<>(jornalEntries.values());
//
//    }
//    @PostMapping//("id/{myId}")
//    public boolean createEntry(@RequestBody JornalEntry myEntry) { //it is like saying ,"Hey Spring,Please take the data from the request and
//        //turn it into a java object that i can use in my code
//        jornalEntries.put(myEntry.getId(),myEntry);
//        return true;
//
//    }
//    @GetMapping("id/{myId}")
//    public JornalEntry getJornalEntryById(@PathVariable Long myId) {
//        return jornalEntries.get(myId);
//    }
//    @DeleteMapping("id/{myId}")
//    public JornalEntry deleteJournalEntryById(@PathVariable Long myId) {
//        return jornalEntries.remove(myId);
//    }
//
//    @PutMapping("/id/{id}")
//    public JornalEntry updateJournalEntryById(@PathVariable Long id,@RequestBody JornalEntry myId) {
//        return jornalEntries.put(id,myId);
//    }
//}
