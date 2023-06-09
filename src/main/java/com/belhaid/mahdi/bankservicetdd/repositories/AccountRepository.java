package com.belhaid.mahdi.bankservicetdd.repositories;

import com.belhaid.mahdi.bankservicetdd.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account,String> {
}
