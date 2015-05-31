package com.leonid.crudtask.options;

import com.leonid.crudtask.model.User;
import com.leonid.crudtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Transactional
@Service
public class FillingDb {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        for (int i = 0; i <100 ; i++) {
            User user = new User();
            user.setName(getRandomName());
            user.setAge(getRandomAge());
            user.setIsAdmin(getRandomIsAdmin());
            user.setCreatedDate(getRandomDate());
            userRepository.save(user);
        }
    }

    public String getRandomName(){
        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrst";
        Random r = new Random();
        final Set<String> identifiers = new HashSet<String>();
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = r.nextInt(5)+5;
            for(int i = 0; i < length; i++)
                builder.append(lexicon.charAt(r.nextInt(lexicon.length())));
            if(identifiers.contains(builder.toString())){
                builder = new StringBuilder();}
        }
        return builder.toString();
    }

    public int getRandomAge(){
        Random r = new Random();
        int result = r.nextInt(130-1) +1;
        return result;
    }

    public boolean getRandomIsAdmin(){
        return Math.random()<0.05;//There can't be too many admins
    }

    public Date getRandomDate(){
        Date date = new Date();
        Random r = new Random();
        int day = r.nextInt(27-1)+1;
        int month = r.nextInt(11-1)+1;
        int year = r.nextInt(114-100)+100;
        date.setDate(day);
        date.setMonth(month);
        date.setYear(year);
        return date;
    }

}
