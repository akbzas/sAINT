import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import java.nio.file.attribute.*;
 
/**
 * This Java program demonstrates how to compress a directory in ZIP format.
 *
 * @author www.codejava.net
 * Link: https://www.codejava.net/java-se/file-io/how-to-compress-files-in-zip-format-in-java
 */
public class ZipDir extends SimpleFileVisitor<Path> {
 
    private static ZipOutputStream zos;
 
    private Path sourceDir;
 
    public ZipDir(Path sourceDir) {
        this.sourceDir = sourceDir;
    }
 
    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attributes) {
 
        try {
            Path targetFile = sourceDir.relativize(file);
 
            zos.putNextEntry(new ZipEntry(targetFile.toString()));
 
            byte[] bytes = Files.readAllBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
 
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        return FileVisitResult.CONTINUE;
    }
 
    public static void main(String[] args) {
        String dirPath = "C:\\Users\\arierodr\\Documents\\@LPM_NothingToSeeHere\\ZipJava\\Zipping";//Zip Path folder
        Path sourceDir = Paths.get(dirPath);
 
        try {
            String zipFileName = dirPath.concat(".zip");
            zos = new ZipOutputStream(new FileOutputStream(zipFileName));
 
            Files.walkFileTree(sourceDir, new ZipDir(sourceDir));
 
            zos.close();
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex);
        }
    }
}


        
        
