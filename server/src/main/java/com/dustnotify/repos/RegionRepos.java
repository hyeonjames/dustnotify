package com.dustnotify.repos;

import com.dustnotify.data.Region;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepos extends JpaRepository<Region,Long> {
}