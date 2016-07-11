package com.ejb01.session;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import com.ejb01.entity.Account;

@Remote
public interface BankServiceRemote {

	public Account createAccount(String owner);

	public Account getAccount(Long accountNumber);

	public List<Account> getAllAccounts();

	public Account deposit(Long accountNumber, BigDecimal amount);

	public Account withdraw(Long accountNumber, BigDecimal amount);

	public void transfer(Long accountNumberFrom, Long accountNumberTo, BigDecimal amount);

}
