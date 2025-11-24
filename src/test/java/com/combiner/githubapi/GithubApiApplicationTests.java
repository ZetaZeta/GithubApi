package com.combiner.githubapi;

import com.combiner.githubapi.userdata.UserDataController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class GithubApiApplicationTests {
    @Autowired
    UserDataController userDataController;

    @Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
        assertThat(userDataController).isNotNull();
	}

    // This is a full end-to-end test for simplicity in a short assignment;
    // usually I would put unit tests here that mock up the client and don't make actual outgoing API calls.
    // This also assumes that octocat is a static test repo whose response won't change.
    @Test
    void onValidRequestReturnHappycase() throws Exception {
        // Arrange

        // Act
        ResultActions resultAction = this.mockMvc.perform(get("/api/userdata/octocat"));
        resultAction = mockMvc.perform(asyncDispatch(resultAction.andReturn()));

        // Assert
        resultAction.andExpect(status().isOk());
        resultAction.andExpect(content().json(TestUtils.getOctocatJson()));
    }

    @Test
    void onInvalidCharacterReturn400() throws Exception {
        // Arrange

        // Act
        ResultActions resultAction = this.mockMvc.perform(get("/api/userdata/a$a"));

        // Assert
        resultAction.andExpect(status().is(400));
    }

    @Test
    void onNonexistentAccountReturn404() throws Exception {
        // Arrange

        // Act
        ResultActions resultAction = this.mockMvc.perform(get("/api/userdata/-test"));
        resultAction = mockMvc.perform(asyncDispatch(resultAction.andReturn()));

        // Assert
        resultAction.andExpect(status().is(404));
    }
}
