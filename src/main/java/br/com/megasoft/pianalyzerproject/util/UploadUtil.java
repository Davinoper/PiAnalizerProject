package br.com.megasoft.pianalyzerproject.util;

import br.com.megasoft.pianalyzerproject.exception.InvalidFileException;
import br.com.megasoft.pianalyzerproject.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadUtil {

    @Value("${upload.directory}")
    private String uploadDirectory;

    public void uploadFile(MultipartFile file){
        try {
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File savedFile = new File(directory.getAbsolutePath() + File.separator + originalFilename);
            file.transferTo(savedFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadFailedException();
        }
    }

    public List<String> listFilesNames(){
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("file:" + uploadDirectory + "/*");

            List<String> fileNames = new ArrayList<>();
            for (Resource resource : resources) {
                fileNames.add(resource.getFilename());
            }

            return fileNames;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
