package com.hamdi.banking.repositories;

import com.hamdi.banking.models.User;
import javax.persistence.criteria.From;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from User where firstname ='asma'
    List<User> findAllByFirstname(String firstname);

    //select * from User where firstname like ='%asma%'
    List<User> findAllByFirstnameContaining(String firstname);

    //select * from User where firstname ilike ='asma'
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    // select * from User u inner join Account a on u.d = a.id_user and a.iban = 'asdffs'

    List<User> findAllByAccountIban(String Iban);

    // select * from User where firstname='%asma%' and email ='hamdi@gmail.com'
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);


    //same methode with Query
    @Query("from User where firstname = :Fn")
    List<User> searchByFirstname(@Param("Fn") String firstname);

    @Query("from User where firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(String firstname);
    @Query("from User u inner join Account a on u.id = a.user.id where a.iban= :iban")
    List<User> searchByIban(String iban);

    // requet native
   // @Query(value = "select * from _user u inner join Account a on u.d = a.id_user and a.iban = :iban", nativeQuery= true)
    // List<User> searchByIbanNative(String iban);

    Optional<User> findByEmail(String email);

}
