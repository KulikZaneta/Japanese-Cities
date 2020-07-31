package com.japan.demo.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config configHazelcast() {
        return new Config()
                .setInstanceName("japanese-city")
                .addMapConfig(new MapConfig()
                        .setName("mapCity")
                        .setEvictionConfig(new EvictionConfig()
                                .setEvictionPolicy(EvictionPolicy.LFU)
                                .setSize(1000)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_NATIVE_MEMORY_SIZE))
                        .setTimeToLiveSeconds(10000))
                .setInstanceName("attractions")
                .addMapConfig(new MapConfig()
                        .setName("mapAttractions")
                        .setEvictionConfig(new EvictionConfig()
                                .setEvictionPolicy(EvictionPolicy.LFU)
                                .setSize(1000)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_NATIVE_MEMORY_SIZE))
                        .setTimeToLiveSeconds(10000));
    }
}
