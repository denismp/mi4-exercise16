/**
 * 
 */
package com.arrayoffacts.ArraryOfFacts;

import java.math.BigInteger;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author denisputnam
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String provider = "https://ropsten.infura.io/v3/2c64faa795c242d083706a5e3105e830";
		String privateKey = "0x7EB2255581AED1C929A291B65BC3A37FB70BA8C6783FFFABE18D8C6EC5DCFFC1";
		ArrayOfFacts_sol_ArrayOfFacts contract = null;
		String contractAddress = null;

		try {
			ContractService service = new ContractService(provider, privateKey);
			System.out.println("Deploying the contract...");
			contract = service.deploy();
			System.out.println("Contract deployed.");
			
			contractAddress = contract.getContractAddress();
			System.out.println("Contract Address=" + contractAddress);
			
			System.out.println("Loading the deployed contract...");
			contract = service.load(contractAddress);
			System.out.println("Deployed contract loaded.");
			
			System.out.println("Add a fact to the deployed contract...");
			String fact = "The Times 03/Jan/2009 Chancellor on brink of second bailout for banks";
			TransactionReceipt trxInfo = service.addFact(fact);
			System.out.println("Fact added to the deployed contract.");
			System.out.println("Transaction Receipt Info\n" + trxInfo);
			
			System.out.println("Retrieve the first fact...");
			String result = service.getFact(new BigInteger("0"));
			System.out.println("result=" + result);
			
			System.out.println("Get the facts count...");
			BigInteger count = service.getFactsCount();
			System.out.println("Count=" + count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
