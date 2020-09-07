package com.dsp.springboot.rest.tournament;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.team.Team;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FixtureCalculatorTest {

    @Test
    public void testFixtureCalculatorEvenTeams(){

        Tournament tournament = new Tournament();
        List<Team> teams = new ArrayList<Team>();

        // add teams to team list
        teams.add(new Team(1L, "Team One", 2010, 100));
        teams.add(new Team(2L, "Team Two", 2010, 100));
        teams.add(new Team(3L, "Team Three", 2010, 100));
        teams.add(new Team(4L, "Team Four", 2010, 100));

        tournament.setTeams(teams);

        FixtureCalculator fixtureCalculator = new FixtureCalculator(tournament);
        List<Fixture> fixtures = fixtureCalculator.calculateFixtures();

        // check the correct number of features have been created and check the first and the last fixture
        assertEquals(fixtures.size(), 12);
        assertEquals(fixtures.get(0).getHomeTeam().getName(), "Team One");
        assertEquals(fixtures.get(0).getAwayTeam().getName(), "Team Four");
        assertEquals(fixtures.get(0).getWeek(), 1);

        assertEquals(fixtures.get(fixtures.size() - 1).getHomeTeam().getName(), "Team Four");
        assertEquals(fixtures.get(fixtures.size() - 1).getAwayTeam().getName(), "Team Three");
        assertEquals(fixtures.get(fixtures.size() - 1).getWeek(), 5);

    }

    @Test
    public void TestFixtureCalculatorOddTeams(){

        Tournament tournament = new Tournament();
        List<Team> teams = new ArrayList<Team>();

        // add teams to team list
        teams.add(new Team(1L, "Team One", 2010, 100));
        teams.add(new Team(2L, "Team Two", 2010, 100));
        teams.add(new Team(3L, "Team Three", 2010, 100));

        tournament.setTeams(teams);

        FixtureCalculator fixtureCalculator = new FixtureCalculator(tournament);
        List<Fixture> fixtures = fixtureCalculator.calculateFixtures();

        // check the correct number of fixtures have been created and check the first fixture and the last fixture has been created correctly
        assertEquals(fixtures.size(), 6);
        assertEquals(fixtures.get(0).getHomeTeam().getName(), "Team Two");
        assertEquals(fixtures.get(0).getAwayTeam().getName(), "Team One");
        assertEquals(fixtures.get(0).getWeek(), 2);

        assertEquals(fixtures.get(fixtures.size() - 1).getHomeTeam().getName(), "Team Two");
        assertEquals(fixtures.get(fixtures.size() - 1).getAwayTeam().getName(), "Team Three");
        assertEquals(fixtures.get(fixtures.size() - 1).getWeek(), 4);

    }
}