package com.dsp.springboot.rest.fixture;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {

    @Query(value = "SELECT * FROM FIXTURE WHERE HOME_TEAM_ID = ?1 OR AWAY_TEAM_ID = ?1", nativeQuery = true)
    Optional<List<Fixture>> getFixturesByTeamId(long teamId);

    @Query(value = "SELECT * FROM FIXTURE WHERE TOURNAMENT_ID = ?1 AND WEEK = ?2", nativeQuery = true)
    Optional<List<Fixture>> getFixturesByWeek(long tournamentId, long week);

    @Query(value = "SELECT * FROM FIXTURE WHERE (AWAY_TEAM_ID = ?2 OR HOME_TEAM_ID = ?2) AND TOURNAMENT_ID = ?1", nativeQuery = true)
    Optional<List<Fixture>> getFixturesByTeam(long tournamentId, long teamId);
 }
