package ua.goit.alg;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.Paths.get;
import static java.util.Arrays.copyOf;

public class Arrays {

  public static void mergeSort(File file) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      List<File> tempFileList;
      tempFileList = divideLargeFile(bufferedReader, file.getParent());

      File sortedTempFile = mergeTempFile(tempFileList);
      Files.copy(get(sortedTempFile.getPath()), get(file.getParent() + "\\sorted_" + file.getName()));
      sortedTempFile.delete();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static List<File> divideLargeFile(BufferedReader bufferedReader, String dir) throws IOException {
    List<File> tempFileList = new ArrayList<File>();
    int blockSize = estimateArrayMaxSize();
    int[] array = new int[blockSize];
    int count = 0;
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      array[count] = Integer.parseInt(line);
      count++;
      if (count == blockSize) {
        MergeSort.sort(array);
        File newTempFile = createTempFile(array, dir);
        tempFileList.add(newTempFile);
        array = new int[blockSize];
        count = 0;
      }
    }
    if (count > 1) {
      array = copyOf(array, count);
      File newTempFile = createTempFile(array, dir);
      tempFileList.add(newTempFile);
    }
    return tempFileList;
  }

  public static File createTempFile(int[] array, String dir) throws IOException {
    File tempFile = File.createTempFile("temp", ".txt", new File(dir));
    FileWriter fileWriter = new FileWriter(tempFile);
    for (int anArray : array) {
      fileWriter.write(anArray + "\n");
    }
    fileWriter.close();
    return tempFile;
  }

  private static File mergeTempFile(List<File> tempFileList) throws IOException {
    if (tempFileList.isEmpty()) return null;
    if (tempFileList.size() == 1) return tempFileList.get(0);

    List<File> files = new ArrayList<File>();
    while (tempFileList.size() >= 2) {
      File tempFileFirst = tempFileList.get(0);
      File tempFileSecond = tempFileList.get(1);
      File mergedFile = mergeTwoTempFile(tempFileFirst, tempFileSecond);
      tempFileList.remove(1);
      tempFileList.remove(0);
      removeFile(tempFileFirst, tempFileSecond);
      files.add(mergedFile);
    }

    if (tempFileList.size() == 1) {
      files.add(tempFileList.get(0));
    }
    return mergeTempFile(files);
  }

  private static void removeFile(File... files) {
    for (File file:files) {
      file.delete();
    }
  }

  private static File mergeTwoTempFile(File firstFile, File secondFile) throws IOException {
    File newTempFile = File.createTempFile("temp", ".txt", new File(firstFile.getParent()));

    try (BufferedReader bufferedReaderFirst = new BufferedReader(new FileReader(firstFile));
         BufferedReader bufferedReaderSecond = new BufferedReader(new FileReader(secondFile));
         FileWriter fileWriter = new FileWriter(newTempFile)) {

      String lineFirst = bufferedReaderFirst.readLine();
      String lineSecond = bufferedReaderSecond.readLine();
      while (true) {
        if (lineFirst == null || lineSecond == null) {
          break;
        }
        int first = Integer.parseInt(lineFirst);
        int second = Integer.parseInt(lineSecond);

        if (first <= second) {
          fileWriter.write(first + "\n");
          lineFirst = bufferedReaderFirst.readLine();
        } else {
          fileWriter.write(second + "\n");
          lineSecond = bufferedReaderSecond.readLine();
        }
      }

      if (lineFirst != null) {
        fileWriter.write(Integer.parseInt(lineFirst) + "\n");
        while ((lineFirst = bufferedReaderFirst.readLine()) != null) {
          fileWriter.write(Integer.parseInt(lineFirst) + "\n");
        }
      } else if (lineSecond != null) {
        fileWriter.write(Integer.parseInt(lineSecond) + "\n");
        while ((lineSecond = bufferedReaderSecond.readLine()) != null) {
          fileWriter.write(Integer.parseInt(lineSecond) + "\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return newTempFile;
  }

  private static int estimateArrayMaxSize() {
    int freeMemory = (int) Runtime.getRuntime().freeMemory() / 10;
    return freeMemory;
  }

}
