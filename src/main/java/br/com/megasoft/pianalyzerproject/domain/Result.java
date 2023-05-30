package br.com.megasoft.pianalyzerproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result {
    private String message;
    private String sequence;
}
