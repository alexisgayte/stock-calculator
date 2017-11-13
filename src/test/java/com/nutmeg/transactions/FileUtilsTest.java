package com.nutmeg.transactions;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.nutmeg.transactions.FileUtils.fileFromResources;
import static com.nutmeg.transactions.FileUtils.transactionFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

  @Test
  public void with() throws IOException {
    String fileName = "data/all.csv";

    Map<String, List<Transaction>> transactions = transactionFromFile(fileFromResources(fileName), LocalDate.now());

    assertThat(transactions)
        .isNotEmpty()
        .containsOnlyKeys("NEAA0000","NEAB0001") ;

  }


}