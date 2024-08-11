package org.example.ProjectTraninng.Core.Components;

import org.example.ProjectTraninng.Core.Servecies.BloodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BloodTypeService bloodTypeService;

    @Override
    public void run(String... args) throws Exception {
        bloodTypeService.initializeBloodTypes();
    }
}