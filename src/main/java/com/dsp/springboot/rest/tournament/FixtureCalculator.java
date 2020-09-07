package com.dsp.springboot.rest.tournament;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.team.Team;

import java.util.*;

public class FixtureCalculator {

    private Tournament tournament;

    public FixtureCalculator(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Fixture> calculateFixtures() {

        List<Fixture> fixtures = new ArrayList<Fixture>();

        // TODO: Implement schedule creation here.
        List<Team> teams = tournament.getTeams();
        int len = teams.size();
        boolean check = false;

        // use one less team if there is an even number of teams
        if(len % 2 == 0){
            len = len - 1;
            check = true;
        }

        // assuming team I is the home team and team j is the away team
        for(int i = 0; i < len; i++){
            int week  = i + 1;
            for(int j = 0; j < len; j ++){
                if(week > len) week = 1;

                // get the home and away teams
                Team home = teams.get(i);
                Team away = teams.get(j);

                if((i == j) && (!check)) {
                    // team set to play itself should not have fixture if odd
                    week += 1;
                    continue;
                } else if ((i == j) && (check)){
                    // team should play extra team if there are an odd number of teams
                    away = teams.get(teams.size() - 1);
                }

                // create add the fixtures to the list ignoring duplicated fixtures in reverse
                // also create the away fixtures
                // add both fixtures to list
                if (i >= j){
                    fixtures.add(new Fixture(tournament, home, away, week));
                    // generate away fixture in second half of season
                    fixtures.add(new Fixture(tournament, away, home, week + len));
                }

                week = week + 1;

            }
        }

        return fixtures;
    }
}
