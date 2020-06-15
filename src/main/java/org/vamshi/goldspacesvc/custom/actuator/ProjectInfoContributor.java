package org.vamshi.goldspacesvc.custom.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("project_name", "goldspacesvc")
                .withDetail("owner", "Vamshi Krishna")
                .withDetail("email", "v4vamshikrishna@gmail.com");
    }
}
