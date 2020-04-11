Exercises: Playing with Smart Contracts using Web3j
In this exercise, we will use Web3j – Lightweight Java and Android library for integration with Ethereum clients.
This exercise will show how to create contract wrappers from native java code, deploy contracts and how to interact with an already deployed contract on the Ethereum Ropsten Testnet.
1.	Set up environment
First, create a new Maven Project:
 
Then, in pom.xml, add web3j as dependency:
 
In order to create a wrapper class of the contract we will need to compile it and create two files with bytecode and the application binary interface.
Install solc:
npm install –g solc
Create a .sol called ArrayOfFacts, which will have 3 functions: adding a fact, get a fact by an index and get how many facts are stored in the contract:
 
To generate the wrapper code, compile the smart contract and create the bytecode and abi in the same directory:
solcjs ArrayOfFacts.sol --bin --abi --optimize -o .
 
Finally, to generate the wrapper code, use web3j’s Command Line Tools. Go to https://github.com/web3j/web3j/releases and download the release appropriate for your OS. Extract it, open a terminal in the bin directory and run:
web3j solidity generate /path/to/<smart-contract>.bin /path/to/<smart-contract>.abi –o /path/to/src/main/java –p com.your.organisation.name
This will create a wrapper class of the contract:
 
Then, create a class called ContractService, which will handle the connection Ropsten and handle the contract's deployment, load and call of it. Create 3 private properties for the Web3, the Credentials (account) and for the Contract itself.
 
Then, create a constructor, which takes 2 parameters: provider of the node and a private key for the credentials:
 
We will need HTTPProvider in order to create our connection to the Ropsten Testnet using Infura.io.
Now let’s get the necessary Infura.io provider. Go to https://infura.io/ and click [Get started for free]:
 
Fill out the form and click [Submit]. Then copy the Ropsten URL:
 
Create a Main.java file and paste in the main:
 
For the example of the exercise, we will take one private key from MetaMask and use it as a signer for the deployment transaction. Keep in mind only the contract owner can add facts to the contract.
 
If you do not have ETHt, use the MetaMask faucet: https://faucet.metamask.io/ 
 
Export the private key:
 
 
 
Go to ContractService.java and create a deploy method, which creates a transaction for the deployment of the contract and then sends it. The method will wait to for the transaction to be mined and then finish:
 
 
In Ropsten Etherscan:
 
Copy the contract address and create a load function, which loads a contract by its address:
 
 

2.	Writing to the Smart Contract
In the ContractService class, create a method, which takes as an argument a fact, calls the contract's add function and sends it. The result will return when the transaction is mined:
 
 
 
In Etherscan:
 

Try adding a fact using another private key as an account.
3.	Reading from the Smart Contract
When reading from a Smart Contract, no wallets or private keys are needed. 
In the ContractService class create a method which gets a fact by a given index and returns a string with the fact. 
 
 
 
Then create a method, which gets how many facts are stored in the contract:
 
 
 
What to Submit?
Create a zip file (e.g. username-playing-smart-contracts-web3j.zip) holding the 3 java files (Contract, ContractService and Main) with the methods, a snapshot of the Ropsten Etherscan contract address and its transactions.
Submit your zip file as homework at the course Website.

NOTES:

I used the STS IDE, not Intellij.
1. Create a new Java Project.
2. Right mouse click on the created project.
3. Configure -> Convert to Maven project.  This creates the initial pom.xml file.

FOR MAC:
 curl -L https://get.web3j.io | sh
 
In the src directory:
1. solcjs ArrayOfFacts.sol --bin --abi --optimize -o .
2. web3j solidity generate -b ArrayOfFacts_sol_ArrayOfFacts.bin -a ArrayOfFacts_sol_ArrayOfFacts.abi -o ./ -p com.arrayoffacts.ArraryOfFacts

Use version 4.5.17:
  <dependencies>
  	<dependency>
  		<groupId>org.web3j</groupId>
  		<artifactId>core</artifactId>
  		<version>4.5.17</version>
<!--   		<version>3.3.1</version> -->
  	</dependency>
  </dependencies>
  
  Contents of the pom.xml file:
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>smart-contracts-web3j</groupId>
  <artifactId>smart-contracts-web3j</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.0</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <dependencies>
  	<dependency>
  		<groupId>org.web3j</groupId>
  		<artifactId>core</artifactId>
  		<version>4.5.17</version>
<!--   		<version>3.3.1</version> -->
  	</dependency>
  </dependencies>
</project>

