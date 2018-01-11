package com.a2bfit.Model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import com.a2bfit.Repo.GebruikerRepo;

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
        String datum = "04/02/2017";
        Training expectedTraining = new Training(1L, schemaID, datum);
        when(gebruikerRepo.startTraining(schemaID, datum)).thenReturn(expectedTraining);

        Training training = gebruiker.startTraining(schemaID, datum);

        Assert.assertEquals(expectedTraining, training);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startTraining_SchemaBestaatNiet_ThrowException(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        long schemaID = 1;
        String datum = "04/02/2017";
        Training expectedTraining = new Training(1L, schemaID, datum);
        when(gebruikerRepo.startTraining(schemaID, datum)).thenThrow(IllegalArgumentException.class);

        Training training = gebruiker.startTraining(schemaID, datum);
    }

    @Test
    public void vinkOefeningAf(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        Oefening expectedOefening = new Oefening(1L, "Benchpress", "benchpress.png", "omschrijving", 1L);

        TrainingsOefening trainingsOefening = new TrainingsOefening(1,2,10);
        gebruiker.vinkOefeningAf(trainingsOefening);

        verify(gebruikerRepo, times(1)).vinkOefeningAf(trainingsOefening);
    }

    @Test
    public void getSchemas(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);

        List<Schema> expectedSchemas = new ArrayList<Schema>(){{
            add(new Schema(1L, "Borst", new ArrayList<Oefening>() {{
                add(new Oefening(1L, "Benchpress", "benchpress.png", "omschrijving", 1L));
            }}));
        }};
        when(gebruikerRepo.getSchemas()).thenReturn(expectedSchemas);

        List<Schema> schemas = gebruiker.getSchemas();

        Assert.assertEquals(expectedSchemas, schemas);
    }

    @Test
    public void getTrainingen(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);

        List<Training> expectedTrainingen = new ArrayList<Training>() {{
            add(new Training(1L, 1L, "04/02/2017"));
            add(new Training(2L, 1L, "05/02/2017"));
        }};
        when(gebruikerRepo.getTrainingen()).thenReturn(expectedTrainingen);

        List<Training> trainingen = gebruiker.getTrainingen();

        Assert.assertEquals(expectedTrainingen, trainingen);
    }

    @Test
    public void getOefeningenBijTraining(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        final long trainingID = 1;

        List<TrainingsOefening> expectedTrainingsOefeningen = new ArrayList<TrainingsOefening>() {{
            add(new TrainingsOefening(trainingID, 1L, 55));
        }};
        when(gebruikerRepo.getOefeningenBijTraining(trainingID)).thenReturn(expectedTrainingsOefeningen);

        List<TrainingsOefening> trainingsOefeningen = gebruiker.getOefeningenBijTraining(trainingID);

        Assert.assertEquals(expectedTrainingsOefeningen, trainingsOefeningen);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getOefeningenBijTraining_TrainingBestaatNiet_ThenThrowException(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        final long trainingID = 1;

        when(gebruikerRepo.getOefeningenBijTraining(trainingID)).thenThrow(IllegalArgumentException.class);

        gebruiker.getOefeningenBijTraining(trainingID);
    }

    @Test
    public void getSetsEnReps(){
        Gebruiker gebruiker = new Gebruiker(gebruikerRepo);
        int[] expectedSetEnRep = {1, 2};
        long oefeningID = 1;
        when(gebruikerRepo.getSetsEnReps(oefeningID)).thenReturn(expectedSetEnRep);

        int[] setEnRep = gebruiker.getSetsEnReps(oefeningID);

        Assert.assertEquals(expectedSetEnRep, setEnRep);
    }
}
