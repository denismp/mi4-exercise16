/**
 * 
 */
package com.arrayoffacts.ArraryOfFacts;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

/**
 * @author denisputnam
 *
 */
public class ContractService {
	private Web3j web3;
	private Credentials credentials;
	private ArrayOfFacts_sol_ArrayOfFacts contract;

	public ContractService(String provider, String privateKey) throws Exception {
		web3 = Web3j.build(new HttpService(provider));
		credentials = Credentials.create(privateKey);
	}


	public Web3j getWeb3() {
		return web3;
	}

	public Credentials getCredentials() {
		return credentials;
	}


	public ArrayOfFacts_sol_ArrayOfFacts getContract() {
		return contract;
	}

	@SuppressWarnings("deprecation")
	public ArrayOfFacts_sol_ArrayOfFacts deploy() throws Exception {
		contract = ArrayOfFacts_sol_ArrayOfFacts
				.deploy(web3, credentials, ManagedTransaction.GAS_PRICE, BigInteger.valueOf(4600000))
				.send();
		return contract;
	}


	@SuppressWarnings("deprecation")
	public ArrayOfFacts_sol_ArrayOfFacts load(String contractAddress) throws Exception{
		// TODO Auto-generated method stub
		contract = ArrayOfFacts_sol_ArrayOfFacts.load(contractAddress, web3, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
		return contract;
	}


	public TransactionReceipt addFact(String fact) throws Exception{
		// TODO Auto-generated method stub
		TransactionReceipt txInfo = contract.add(fact).send();
		return txInfo;
	}


	public String getFact(BigInteger bigInteger) throws Exception {
		// TODO Auto-generated method stub
		String fact = contract.getFact(bigInteger).send();
		return fact;		
	}


	public BigInteger getFactsCount() throws Exception {
		// TODO Auto-generated method stub
		BigInteger count = contract.count().send();
		return count;
	}
	
}
