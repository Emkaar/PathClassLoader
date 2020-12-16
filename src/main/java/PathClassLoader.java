import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PathClassLoader extends ClassLoader{

    private String[] loadingPaths;

    public PathClassLoader(String[] loadingPaths) {
        this.loadingPaths = loadingPaths;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class result;
        File classFile = findFile(name.replace('.' ,File.separatorChar));
        if(classFile == null){
            throw new ClassNotFoundException("Class in path not found.");
        }
        byte[] classBytes = new byte[0];
        try {
            classBytes = loadFileAsBytes(classFile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        result = defineClass(name, classBytes, 0, classBytes.length);
        return result;
    }

    private File findFile(String name) {
        File f;
        for (int k=0; k <loadingPaths.length; k++) {
            f = new File((new File(loadingPaths[k])).getPath()
                    + File.separatorChar
                    + name
                    + ".class");
            if (f.exists())
                return f;
        }
        return null;
    }
    private byte[] loadFileAsBytes(File classFile) throws IOException {
        byte[] result = new byte[(int)classFile.length()];
        FileInputStream f = new FileInputStream(classFile);
        try {
            f.read(result,0,result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {}
        }
        return result;
    }
}
