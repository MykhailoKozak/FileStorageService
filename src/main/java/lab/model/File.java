package lab.model;


import java.io.Serializable;


public class File implements Serializable {

    private String name;
    private String type;
    private String size;

    public File(String name, String type, String size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public File() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        if (getName() != null ? !getName().equals(file.getName()) : file.getName() != null) return false;
        if (getType() != null ? !getType().equals(file.getType()) : file.getType() != null) return false;
        return getSize() != null ? getSize().equals(file.getSize()) : file.getSize() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
