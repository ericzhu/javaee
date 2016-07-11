package com.ejb01.session;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ejb01.entity.Account;

@Stateless(name = "bankService")
public class BankServiceEJB implements BankServiceLocal, BankServiceRemote {

	@PersistenceContext(name = "ejb01-bank")
	private EntityManager entityManager;

	@Override
	public Account createAccount(String owner) {
		Account account = new Account(owner);
		entityManager.persist(account);
		return account;
	}

	@Override
	public Account getAccount(Long accountNumber) {
		Account account = entityManager.find(Account.class, accountNumber);
		if (account == null) {
			throw new RuntimeException("Account [" + accountNumber + "] does not exist");
		}
		return account;
	}

	@Override
	public List<Account> getAllAccounts() {
		TypedQuery<Account> query = entityManager.createQuery("select a from Account a", Account.class);
		return query.getResultList();
	}

	@Override
	public Account deposit(Long accountNumber, BigDecimal amount) {
		Account account = getAccount(accountNumber);
		account.setBalance(account.getBalance().add(amount));
		return account;
	}

	@Override
	public Account withdraw(Long accountNumber, BigDecimal amount) {
		Account account = getAccount(accountNumber);
		account.setBalance(account.getBalance().subtract(amount));
		return account;
	}

	@Override
	public void transfer(Long accountNumberFrom, Long accountNumberTo, BigDecimal amount) {
		withdraw(accountNumberFrom, amount);
		deposit(accountNumberTo, amount);
	}

}
