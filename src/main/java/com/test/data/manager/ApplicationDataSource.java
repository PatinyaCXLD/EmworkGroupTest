package com.test.data.manager;

import com.test.data.services.DataSource;
import com.test.models.Application;
import com.test.models.ApplicationList;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationDataSource implements DataSource<ApplicationList> {

    private String directoryName;
    private String fileName;
    private String filePath;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private DateTimeFormatter toFromDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyy");

    public ApplicationDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    @Override
    public ApplicationList readData() {
        ApplicationList applications = new ApplicationList();
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while ( (line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Application application = new Application(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim()
                );
                application.setAppealTime(LocalDateTime.parse(data[6].trim(),dateFormat));
                application.setStatus(data[7].trim());
                application.setFromDate(LocalDate.parse(data[8].trim(),toFromDateFormat));
                application.setToDate(LocalDate.parse(data[9].trim(),toFromDateFormat));
                applications.addApplication(application);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return applications;
    }

    @Override
    public void writeData(ApplicationList applicationList) {
        File file = new File(filePath);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (Application application : applicationList.getAllApplications()){
                String line = application.getFullName() + "," + application.getPosition() + "," + application.getEmail() + ',' + application.getPhone()+ ',' + application.getType() + ',' + application.getReason() + ',' + application.getAppealTime().format(dateFormat) + ',' + application.getStatus() + ',' + application.getFromDate().format(toFromDateFormat) + ',' + application.getToDate().format(toFromDateFormat);
                System.out.println(line);
                buffer.append(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.filePath = directoryName + File.separator + fileName;
        file = new File(this.filePath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return filePath;
    }
}
