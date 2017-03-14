package org.shabunc.maven;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

@Mojo(name = "run", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Plugin extends AbstractMojo {

  @Parameter(property = "processors")
  private String[] processors;

  public void execute() throws MojoExecutionException {

    for (String processorName : processors) {
      try {
        Class<?> clazz = Class.forName(processorName);
        Constructor<?> ctr = clazz.getConstructor();
        Processor processor = (Processor) ctr.newInstance();

        Iterator<File> it = processor.getFiles();
        while (it.hasNext()) {
          File file = (File) it.next();
          getLog().info(String.format("processing %s", file.toString()));
          processor.process(file);
        }
        System.out.println(processor);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }

  }
}