package com.ecuex.ornekproje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Component
public class InitializeData {
    @Autowired
    private DataSource dataSource;

    @EventListener(ApplicationStartedEvent.class)
    public void loadData() throws IOException {
        if (new ClassPathResource("data.sql").exists()) {
            ResourceDatabasePopulator resourceDatabasePopulator =
                    new ResourceDatabasePopulator(
                            true,
                            false,
                            "UTF-8",
                            new ClassPathResource("data.sql"));
            resourceDatabasePopulator.execute(dataSource);
            deleteDataSql();
        }
    }

    private void deleteDataSql() throws IOException {
//        File file = new ClassPathResource("data.sql").getFile();

//        if (file.delete()) {
//            System.out.println("File deleted successfully");
//        } else {
//            System.out.println("Failed to delete the file");
//        }
    }
}