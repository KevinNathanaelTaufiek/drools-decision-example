package com.kevinnathanaeltaufiek.drools_decision_example.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public KieSession customerKieSession() {
        KieServices kieServices = KieServices.Factory.get();

        // Load Rule from decision rule contained file
        Resource dt = ResourceFactory.newClassPathResource("rules/Discount.drl.xls", getClass());
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(dt);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieRepository kieRepository = kieServices.getRepository();

        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);

        KieSession kieSession = kieContainer.newKieSession();

        return kieSession;
    }


    @Bean
    public KieSession orderKieSession() {
        KieServices kieServices = KieServices.Factory.get();

        // Load Rule from decision rule contained file
        Resource dt = ResourceFactory.newClassPathResource("rules/offer.drl", getClass());
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(dt);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieRepository kieRepository = kieServices.getRepository();

        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);

        KieSession kieSession = kieContainer.newKieSession();

        return kieSession;
    }

}
