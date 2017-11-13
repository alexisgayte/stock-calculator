package com.nutmeg.transactions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class FileUtils {

  public static Map<String, List<Transaction>> transactionFromFile(File transactionFile, LocalDate date) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get((transactionFile.getAbsolutePath())))) {
      return lines
          .map(line -> line.split(","))
          //.filter(LocalDate.parse(array[1].trim(), BASIC_ISO_DATE) )

          .collect(
              groupingBy(
                  array -> array[0].trim(),
                  mapping(array -> new TransactionBuilder(array[0].trim(), array[1].trim())
                      .withTransactionType(array[2].trim())
                      .hasUnit(array[3].trim())
                      .hasPrice(array[4].trim())
                      .hasAsset(array[5].trim())
                      .build(), toList())
              )
          );

    } catch (IOException e) {
      throw new IOException();
    }
  }

  public static File fileFromResources(String fileName){
    ClassLoader classLoader = FileUtils.class.getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    return file;
  }

}
