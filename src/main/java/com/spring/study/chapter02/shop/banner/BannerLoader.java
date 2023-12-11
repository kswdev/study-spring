package com.spring.study.chapter02.shop.banner;

import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BannerLoader {
    private Resource banner;

    public void setBanner(Resource banner) {
        this.banner = banner;
    }
    @PostConstruct
    public void showBanner() throws IOException {
        Path path = Paths.get(banner.getURI());

        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
