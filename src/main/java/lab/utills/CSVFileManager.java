package lab.utills;

import lab.model.File;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
    public static String[] HEADERS = {"Name", "Type", "Size"};

    /**
     * Method is handel csv file encoding issue
     */
    private static InputStreamReader newReader(final InputStream inputStream) {
        return new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
    }

    public static List<lab.model.File> readFile(java.io.File file) {
        List<lab.model.File> list = new ArrayList<>();
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            Iterable<CSVRecord> parser = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord(true).parse(newReader(in));

            for (CSVRecord record : parser) {
                list.add(new lab.model.File(record.get("Name"), record.get("Type"), record.get("Size")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static void writeFiles(List<File> fileList, java.io.File file) {
        Writer out = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withNullString("");

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            csvFilePrinter = new CSVPrinter(out, csvFileFormat);

            csvFilePrinter.printRecord(HEADERS);

            for (lab.model.File files : fileList) {
                csvFilePrinter.printRecord(files.getName(), files.getType(), files.getSize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
