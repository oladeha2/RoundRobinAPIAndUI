package com.dsp.springboot.rest.tournament;

import com.dsp.springboot.rest.fixture.Fixture;
import com.dsp.springboot.rest.fixture.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.WebServiceException;

@RestController
public class TournamentResource {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    //TODO: Create new tournament POST endpoint

    @GetMapping("/tournaments")
    public List<Tournament> listTournaments() {
        return tournamentRepository.findAll();
    }

    @GetMapping("/tournaments/{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable long id) {
        return tournamentRepository.findById(id)
            .map(tournament -> new ResponseEntity<>(tournament, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // get mapping for getting fixtures for a specific week or a specific team
    @GetMapping("tournaments/{id}/fixturesWeek")
    public List<Fixture> getWeekFixtures(@PathVariable long id, @RequestParam(value = "week", required = true) long week){
        return fixtureRepository.getFixturesByWeek(id, week)
                .orElse(Collections.emptyList());
    }

    @GetMapping("tournaments/{id}/fixturesTeam")
    public List<Fixture> getTeamFixtures(@PathVariable long id, @RequestParam(value = "team", required = true) long teamId){
        return fixtureRepository.getFixturesByTeam(id, teamId)
                .orElse(Collections.emptyList());
    }

    // post mapping for the creation of a tournament entity
    @PostMapping("/tournaments")
    public ResponseEntity<Object> createTournament(@RequestBody Tournament tournament){
        Tournament savedTournament = tournamentRepository.save(tournament);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(savedTournament.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/tournaments/{id}/generateFixtures")
    public List<Fixture> generateFixtures(@PathVariable long id) {
        Tournament tournament = tournamentRepository.findById(id)
            .orElseThrow(() -> new WebServiceException("No Such Tournament"));
        FixtureCalculator fixtureCalculator = new FixtureCalculator(tournament);
        List<Fixture> fixtures = fixtureCalculator.calculateFixtures();
        fixtureRepository.saveAll(fixtures);
        return fixtures;
    }
}
