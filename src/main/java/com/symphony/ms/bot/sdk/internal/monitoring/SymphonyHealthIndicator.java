package com.symphony.ms.bot.sdk.internal.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import com.symphony.ms.bot.sdk.internal.symphony.SymphonyService;
import com.symphony.ms.bot.sdk.internal.symphony.model.HealthCheckInfo;

/**
 * Retrieves health details for Symphony components (e.g. POD, agent)
 *
 * @author Marcus Secato
 *
 */
public class SymphonyHealthIndicator implements HealthIndicator {
  private static final Logger LOGGER = LoggerFactory.getLogger(SymphonyHealthIndicator.class);

  private SymphonyService symphonyService;

  public SymphonyHealthIndicator(SymphonyService symphonyService) {
    this.symphonyService = symphonyService;
  }

  @Override
  public Health health() {
    LOGGER.debug("Performing health check for Symphony components");
    HealthCheckInfo symphonyHealthResponse = symphonyService.healthCheck();

    Health.Builder healthBuilder = Health.up();
    if (!symphonyHealthResponse.checkOverallStatus()) {
      healthBuilder = Health.down();
    }

    return healthBuilder.withDetail("Symphony",
        symphonyHealthResponse).build();
  }

}