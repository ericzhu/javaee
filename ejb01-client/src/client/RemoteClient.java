package client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb01.session.BankServiceRemote;

public class RemoteClient {

	public static void main(String[] args) throws Exception {
		
		
		String appName=""; String moduleName="ejb01-bank";
		String distinctName="bankService"; 
		String viewClassName=BankServiceRemote.class.getName();
		String ejbRemoteJNDIName="ejb:"+appName+"/"+moduleName+"/"+distinctName+"!"+viewClassName;
		
		Context context = new InitialContext();
		
		BankServiceRemote bankService = (BankServiceRemote)context.lookup(ejbRemoteJNDIName);
		// bankService.createAccount("John");
		// bankService.deposit(1L, BigDecimal.valueOf(1000));
		// bankService.transfer(1L, 2L, BigDecimal.valueOf(-700));
		System.out.println(bankService.getAllAccounts().size());
		
	}
}
