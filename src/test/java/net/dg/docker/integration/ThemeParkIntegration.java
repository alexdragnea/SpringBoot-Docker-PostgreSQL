package net.dg.docker.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ThemeParkIntegration {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void init() throws Exception {

        String ride1 = "{\"name\":\"Ride1\",\"description\":\"Description Ride1 ride.\",\"thrillFactor\":1}";
        String ride2 = "{\"name\":\"Ride2\",\"description\":\"Description Ride2 ride.\",\"thrillFactor\":1}";
        String ride3 = "{\"name\":\"Ride3\",\"description\":\"Description Ride3 ride.\",\"thrillFactor\":1}";
        String ride4 = "{\"name\":\"Ride4\",\"description\":\"Description Ride4 ride.\",\"thrillFactor\":1}";
        List<String> themeParkRideList = new ArrayList<>();
        themeParkRideList.add(ride1);
        themeParkRideList.add(ride2);
        themeParkRideList.add(ride3);
        themeParkRideList.add(ride4);

        for(String list: themeParkRideList) {
            mockMvc.perform(MockMvcRequestBuilders.post("/ride")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(list)
                            .accept(MediaType.APPLICATION_JSON));
        }
    }

    @Test
    public void getsAllRides() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ride")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getsSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ride/3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void returnsNotFoundForInvalidSingleRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ride/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void addsNewRide() throws Exception {
        String newRide = "{\"name\":\"Ride5\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/ride")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRide)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateExistingRide() throws Exception {
        String updatedRide = "{\"name\":\"Monorail\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2}";
        mockMvc.perform(MockMvcRequestBuilders.put("/ride/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedRide)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteExistingRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/ride/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void returnNotFoundForDeletingNonExistingRide() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/ride/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}