import loadedClasses.LoadingClass;

import java.lang.reflect.InvocationTargetException;

//        for testing pathClassLoader before running, compile the program and
//        delete LoadingClass.class from target folder

public class PathMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        PathClassLoader pathClassLoader = new PathClassLoader(new String[]{"temp/"});
        Class customLoadedClass = Class.forName("loadedClasses.LoadingClass", true, pathClassLoader);
        Class usuallyLoadedClass = Class.forName("loadedClasses.UsuallyLoadingClass", true, pathClassLoader);
        Object customObject = customLoadedClass.getDeclaredConstructor().newInstance();
        Object usuallyObject = usuallyLoadedClass.getDeclaredConstructor().newInstance();
        System.out.println(customObject);
        System.out.println(customLoadedClass.getClassLoader());
        System.out.println(usuallyObject);
        System.out.println(usuallyLoadedClass.getClassLoader());
//        LoadingClass loadingClass = (LoadingClass)customObject;
//        System.out.println(loadingClass);
    }
}
