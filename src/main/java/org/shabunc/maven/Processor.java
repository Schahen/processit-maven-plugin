package org.shabunc.maven;

import java.io.File;
import java.util.Iterator;

public interface Processor {
  Iterator<File> getFiles();
  void process(File file);
}
