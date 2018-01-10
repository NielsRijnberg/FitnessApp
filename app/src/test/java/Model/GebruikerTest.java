package Model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import Repo.GebruikerRepo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GebruikerTest {
    @Mock
    GebruikerRepo gebruikerRepo;

    @Test
    public void getPrestaties(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        List<Prestatie> expectedPrestaties = new ArrayList<Prestatie>(){{
            add(new Prestatie(1L, "miljoen", "Til een miljoen kilo"));
            add(new Prestatie(2L, "bodybuilder", "Voer 100 trainingen uit"));
        }};
        when(gebruikerRepo.getPrestaties()).thenReturn(expectedPrestaties);

        List<Prestatie> prestaties = gebruiker.getPrestaties();

        Assert.assertEquals(expectedPrestaties, prestaties);
    }

    @Test
    public void getOefeningenVoorSchema(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        List<Oefening> expectedOefeningen = new ArrayList<Oefening>(){{
            add(new Oefening(1L, "Benchpress", "benchpress.png", "omschrijving", 1L));
            add(new Oefening(2L, "Incline Benchpress", "inclinebenchpress.png", "omschrijving", 1L));
            add(new Oefening(3L, "Dumbbell press", "dumbbellpress.png", "omschrijving", 1L));
        }};
        long schemaID = 1;
        when(gebruikerRepo.getOefeningenVoorSchema(schemaID)).thenReturn(expectedOefeningen);

        List<Oefening> oefeningen = gebruiker.getOefeningenVoorSchema(schemaID);

        Assert.assertEquals(expectedOefeningen, oefeningen);
    }

    @Test
    public void startTraining(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        long schemaID = 1;
        Training expectedTraining = new Training(1L, schemaID, "04/02/2017");
        when(gebruikerRepo.startTraining(schemaID)).thenReturn(expectedTraining);

        Training training = gebruiker.startTraining(schemaID);

        Assert.assertEquals(expectedTraining, training);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startTraining_SchemaBestaatNiet_ThrowException(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        long schemaID = 1;
        Training expectedTraining = new Training(1L, schemaID, "04/02/2017");
        when(gebruikerRepo.startTraining(schemaID)).thenThrow(IllegalArgumentException.class);

        Training training = gebruiker.startTraining(schemaID);
    }

    @Test
    public void vinkOefeningAf(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        Oefening expectedOefening = new Oefening(1L, "Benchpress", "benchpress.png", "omschrijving", 1L);

        TrainingsOefening trainingsOefening = new TrainingsOefening(1,2,10);
        gebruiker.vinkOefeningAf(trainingsOefening);

        verify(gebruikerRepo, times(1)).vinkOefeningAf(trainingsOefening);
    }
}
