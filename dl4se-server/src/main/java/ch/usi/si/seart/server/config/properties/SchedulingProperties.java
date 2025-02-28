
package ch.usi.si.seart.server.config.properties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.scheduling.support.CronTrigger;

import javax.validation.constraints.NotNull;

@Getter
@ConfigurationProperties(prefix = "platform.scheduling", ignoreUnknownFields = false)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(onConstructor_ = @ConstructorBinding)
public class SchedulingProperties {

    @NotNull
    CronTrigger taskCleanerCron;

    @NotNull
    CronTrigger repoMaintainerCron;

    @NotNull
    CronTrigger viewMaintainerCron;
}