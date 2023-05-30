package br.com.megasoft.pianalyzerproject.api;


import br.com.megasoft.pianalyzerproject.model.Notice;
import br.com.megasoft.pianalyzerproject.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.megasoft.pianalyzerproject.service.PiService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pi")
@RequiredArgsConstructor
public class PiApis {

    private final PiService piService;

    @GetMapping
    public ResponseEntity<Result> findBestSequence(@RequestParam("file") String fileName){
        log.info("PiApis.findBestSequence - input [{}]", fileName);
        Result result = piService.findBestSequence(fileName);
        log.info("PiApis.findBestSequence - output [{}]", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> getFilesNames(){
        log.info("PiApis.getFilesNames - input [{}]");
        List<String> files = piService.getFilesNames();
        log.info("PiApis.getFilesNames - output [{}]", files);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @PostMapping("/files/upload")
    public ResponseEntity<Notice> uploadFile(@RequestParam MultipartFile file) {
        log.info("PiApis.getFilesNames - input [{}]", file.getOriginalFilename());
        Notice notice = piService.uploadFile(file);
        log.info("PiApis.getFilesNames - output [{}]", notice);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

}
