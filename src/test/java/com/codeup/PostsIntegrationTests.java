package com.codeup;
import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostRepository;
import com.codeup.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.HttpSession;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;






@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBlogApplication.class)
@AutoConfigureMockMvc
public class PostsIntegrationTests {
  private User testUser;
  private HttpSession httpSession;

  @Autowired
  private MockMvc mvc;

  @Autowired
  UserRepository userDao;

  @Autowired
  PostRepository postDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Before
  public void setup() throws Exception {

    testUser = userDao.findByUsername("testUser");

    // Creates the test user if not exists
    if(testUser == null){
      User newUser = new User();
      newUser.setUsername("testUser");
      newUser.setPassword(passwordEncoder.encode("pass"));
      newUser.setEmail("testUser@codeup.com");
      testUser = userDao.save(newUser);
    }

    // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
    httpSession = this.mvc.perform(post("/login").with(csrf())
      .param("username", "testUser")
      .param("password", "pass"))
      .andExpect(status().is(HttpStatus.FOUND.value()))
      .andExpect(redirectedUrl("/profile"))
      .andReturn()
      .getRequest()
      .getSession();
  }

  @Test
  public void contextLoads() {
    // Sanity Test, just to make sure the MVC bean is working
    assertNotNull(mvc);
  }

  @Test
  public void testIfUserSessionIsActive() throws Exception {
    // It makes sure the returned session is not null
    assertNotNull(httpSession);
  }

  @Test
  public void testCreatePostResults() throws Exception {
    // Makes a Post request to /ads/create and expect a redirection to the Ad
    this.mvc.perform(
      post("/posts/create").with(csrf())
        .session((MockHttpSession) httpSession)
        // Add all the required parameters to your request like this
        .param("title", "test")
        .param("body", "for sale"))
      .andExpect(status().is3xxRedirection());
  }

  @Test
  public void testPostById() throws Exception {
    Post existingPost = postDao.findAll().get(0);
    this.mvc.perform(get("/posts/" + existingPost.getId()))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString(existingPost.getBody())));
  }

  @Test
  public void testPosts() throws Exception {
    Post existingPost = postDao.findAll().get(0);
    this.mvc.perform(get("/posts"))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("test")))
      .andExpect(content().string(containsString(existingPost.getTitle())));
  }

  @Test
  public void testUpdatePostResults() throws Exception {
    Post existingPost = postDao.findAll().get(0);
    this.mvc.perform(
      post("/posts/update/" + existingPost.getId()).with(csrf())
        .session((MockHttpSession) httpSession)
        .param("title","edited title")
        .param("body","edited body"))
      .andExpect(status().is3xxRedirection());

    this.mvc.perform(get("/posts/update/" + existingPost.getId())
      .session((MockHttpSession) httpSession))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("edited title")))
      .andExpect(content().string(containsString("edited body")));
  }

}
