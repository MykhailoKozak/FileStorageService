package lab.model;


import java.io.Serializable;
import java.util.Objects;


public class File implements Serializable {

    private String name;
    private String path = "src/main/resources/myResources";

    public File(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public File() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(name, file.name) &&
                Objects.equals(path, file.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
