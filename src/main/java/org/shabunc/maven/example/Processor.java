package org.shabunc.maven.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.Iterator;

public class Processor implements org.shabunc.maven.Processor {
  @Override
  public Iterator<File> getFiles() {
    File dir = new File("src/main/java");

    String[] extensions = new String[] {".xml"};
    IOFileFilter filter = new SuffixFileFilter(extensions, IOCase.INSENSITIVE);
    Iterator iter = FileUtils.iterateFiles(dir, filter, TrueFileFilter.INSTANCE);
    return iter;
  }

  @Override
  public void process(File file) {
    System.out.println("BINGO BONGO!!!! =>" + file.getName());
  }
}
