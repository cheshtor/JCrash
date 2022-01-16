package io.buyan.jcrash.core;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Gan Pengyu
 * CreateDate 2022/1/16
 */
@Slf4j
public class ApiClassLoader extends URLClassLoader {

    static {
        registerAsParallelCapable();
    }

    private URL[] classpath;

    private List<File> classpathFiles;

    private List<Jar> loadedJars;

    private ReentrantLock jarScanLock = new ReentrantLock();

    public ApiClassLoader(URL[] classpath) {
        super(classpath);
        this.classpath = classpath;
        classpathFiles = new ArrayList<>();
        for (URL cp : classpath) {
            classpathFiles.add(new File(cp.getFile()));
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        List<Jar> allJars = getAllJars();
        String path = name.replace('.', '/').concat(".class");
        for (Jar jar : allJars) {
            JarEntry entry = jar.jarFile.getJarEntry(path);
            if (entry == null) {
                continue;
            }
            try {
                URL classFileUrl = new URL("jar:file:" + jar.sourceFile.getAbsolutePath() + "!/" + path);
                byte[] data;
                try (final BufferedInputStream is = new BufferedInputStream(
                        classFileUrl.openStream()); final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    int ch;
                    while ((ch = is.read()) != -1) {
                        baos.write(ch);
                    }
                    data = baos.toByteArray();
                }
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                log.error("find class {} fail.", name, e);
            }
        }
        throw new ClassNotFoundException("Can't find " + name);
    }

    private List<Jar> getAllJars() {
        if (loadedJars == null) {
            jarScanLock.lock();
            try {
                if (loadedJars == null) {
                    loadedJars = doGetJars();
                }
            } finally {
                jarScanLock.unlock();
            }
        }

        return loadedJars;
    }

    private LinkedList<Jar> doGetJars() {
        LinkedList<Jar> jars = new LinkedList<>();
        for (File path : classpathFiles) {
            if (path.exists() && path.isDirectory()) {
                String[] jarFileNames = path.list((dir, name) -> name.endsWith(".jar"));
                for (String fileName : jarFileNames) {
                    try {
                        File file = new File(path, fileName);
                        Jar jar = new Jar(new JarFile(file), file);
                        jars.add(jar);
                        log.info("{} jar file loaded.", file.getName());
                    } catch (IOException e) {
                        log.error("{} jar file can't be resolved", fileName, e);
                    }
                }
            }
        }
        return jars;
    }

    private static class Jar {
        private final JarFile jarFile;
        private final File sourceFile;

        public Jar(JarFile jarFile, File sourceFile) {
            this.jarFile = jarFile;
            this.sourceFile = sourceFile;
        }
    }
}

