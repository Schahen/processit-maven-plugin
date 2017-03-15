package org.shabunc.maven;

import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.Iterator;

public interface Processor {
  void setProject(MavenProject mavenProject);
  Iterator<File> getFiles();
  void process(File file);
}
