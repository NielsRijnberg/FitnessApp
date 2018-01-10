package Model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import Repo.FitnessRepo;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FitnessTest {
    @Mock
    FitnessRepo fitnessRepo;

    @Test
    public void getOefeningen(){
        Fitness fitness = new Fitness(fitnessRepo);
        List<Oefening> expectedOefeningen = new ArrayList<Oefening>(){{
            add(new Oefening(1L, "","","", 2L));
            add(new Oefening(3L, "","","", 4L));
        }};
        String spiergroepNaam = "Borst";
        when(fitnessRepo.getOefeningen(spiergroepNaam)).thenReturn(expectedOefeningen);

        List<Oefening> oefeningen = fitness.getOefeningen(spiergroepNaam);

        Assert.assertEquals(expectedOefeningen, oefeningen);
    }

    @Test
    public void getSpiergroepen(){
        Fitness fitness = new Fitness(fitnessRepo);
        List<Spiergroep> expectedSpiergroepen = new ArrayList<Spiergroep>(){{
            add(new Spiergroep(1L, "Borst"));
            add(new Spiergroep(2L, "Biceps"));
            add(new Spiergroep(3L, "Triceps"));
        }};
        when(fitnessRepo.getSpiergroepen()).thenReturn(expectedSpiergroepen);

        List<Spiergroep> spiergroepen = fitness.getSpiergroepen();

        Assert.assertEquals(expectedSpiergroepen, spiergroepen);
    }
}
