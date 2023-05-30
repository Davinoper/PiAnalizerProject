package br.com.megasoft.pianalyzerproject.util;

import br.com.megasoft.pianalyzerproject.exception.FileReadFailedException;
import br.com.megasoft.pianalyzerproject.exception.InvalidFileException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class PiManager {

    public String readPiFile(String fileName) {
        StringBuilder piNumber = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("files/" + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                piNumber.append(line.replace(".", ""));
            }
        } catch (IOException e) {
            throw new FileReadFailedException();
        }
        return piNumber.toString();
    }

    public boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.TWO) < 0) {
            return false;
        }
        for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(number) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }

    public List<BigInteger> findPrimeNumbers() {
        List<BigInteger> primeNumbers = new ArrayList<>();
        BigInteger limit = BigInteger.valueOf(9973);

        for (BigInteger i = BigInteger.TWO; i.compareTo(limit) <= 0; i = i.add(BigInteger.ONE)) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public String findBestSequence(String piNumber, List<BigInteger> primeNumbers) {
        var bestSequence = "";
        var sequenceEnd = false ;
        int i = 0;
        String sequence ="";
        for (int j = i+1;(j <=  piNumber.length() & !sequenceEnd & i <= piNumber.length() - 1);) {
            String subsequence = piNumber.substring(i, j);
            BigInteger number;
            try{
                number = new BigInteger(subsequence);
            }catch (NumberFormatException e){
                throw new InvalidFileException();
            }
            if (primeNumbers.contains(number)) {
                sequence +=  subsequence;
                i=j;
                j = i+1;
            }else if (sequence.length() == 0){
                i++;
                j = i+1;
            }else if (j-i==3 & sequence.length() > bestSequence.length()){
                bestSequence = sequence;
                sequence= "";
                i++;
                j = i+1;
            }else if(j-i < 3 &  j <=  piNumber.length() ){
                j++;
            }else if(j-i == 3 &  j <=  piNumber.length() &  i <= piNumber.length() - 1){
                i++;
                j = i+1;
                sequence= "";
            }else{
                sequenceEnd = true;
            }
        }
        return bestSequence;
    }


}
