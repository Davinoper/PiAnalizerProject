package br.com.megasoft.pianalyzerproject.service;

import br.com.megasoft.pianalyzerproject.constant.Message;
import br.com.megasoft.pianalyzerproject.model.Notice;
import br.com.megasoft.pianalyzerproject.util.PiManager;
import br.com.megasoft.pianalyzerproject.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import br.com.megasoft.pianalyzerproject.model.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PiService {

    private final PiManager piManager;
    private final UploadUtil uploadUtil;

    public Result findBestSequence(String fileName){
        log.info("PiService.findBestSequence - input [{}]");
        String piNumber = piManager.readPiFile(fileName);
        List<BigInteger> primeNumbers = piManager.findPrimeNumbers();
        String primeSequence = piManager.findBestSequence(piNumber,primeNumbers);
        Result result = new Result(Message.FIND_BEST_SEQUENCE, primeSequence);
        log.info("UserService.findBestSequence - output [{}]", primeSequence);
        return result;
    }

    public Notice uploadFile(MultipartFile file){
        log.info("PiService.uploadFile - input [{}]",file.getOriginalFilename());
        uploadUtil.uploadFile(file);
        log.info("UserService.uploadFile - output [{}]");
        return new Notice(Message.FILE_UPLOADED);
    }

    public List<String> getFilesNames(){
        log.info("PiService.getFilesNames - input [{}]");
        List<String> filesNames = uploadUtil.listFilesNames();
        log.info("UserService.getFilesNames - output [{}]", filesNames);
        return filesNames;
    }


}
