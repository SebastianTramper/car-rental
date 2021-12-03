package nl.sog.carrental.restapi.controllers;

import nl.sog.carrental.restapi.entities.Rental;
import nl.sog.carrental.restapi.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Tests that the endpoint should return a User object which has all the
     * properties set and also has an id that was set by the database.
     *
     * @throws Exception
     */
    @Test
    public void createShouldReturnUserObject() throws Exception {
        // url endpoint
        String url = "http://localhost:" + port + "/user/create";

        // create header instance and set contenttype to formdata
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // add form data to a hashmap to be inserted into the body
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("firstName", "bas");
        map.add("lastName", "baas");
        map.add("address", "baslaan");
        map.add("zipCode", "6546bas");
        map.add("country", "basland");
        map.add("email", "bas@gmail.com");
        map.add("phone", "06bas");

        // make new request and insert body (map) and header
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        // make new post request and insert end point url, http request and return user instance
        ResponseEntity<User> response = restTemplate.postForEntity(url, request , User.class);

        // check http 200 request, check not if response is not null, check if email is equal to bas@gmail.com,
        // check if id exist in the body
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmail()).isEqualTo("bas@gmail.com");
        assertThat(response.getBody().getId()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void createShouldNotAllowDuplicateEmail() throws Exception {
        String url = "http://localhost:" + port + "/user/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("firstName", "joep");
        map.add("lastName", "jaap");
        map.add("address", "joeplaan");
        map.add("zipCode", "6546joep");
        map.add("country", "joepland");
        map.add("email", "joep@gmail.com");
        map.add("phone", "06joep");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // First user should succeed. Email does not yet exist
        ResponseEntity<User> response = restTemplate.postForEntity(url, request , User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Second user should fail. Email already exists.
        ResponseEntity<User> duplicatedResponse = restTemplate.postForEntity(url, request , User.class);
        assertThat(duplicatedResponse.getStatusCode()).isEqualTo(HttpStatus.IM_USED);
    }

    @Test
    public void createShouldReturnErrorOnMissingParam() throws Exception {
        String url = "http://localhost:" + port + "/user/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("lastName", "jaap");
        map.add("address", "joeplaan");
        map.add("zipCode", "6546joep");
        map.add("country", "joepland");
        map.add("email", "joep@gmail.com");
        map.add("phone", "06joep");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // First user should succeed. Email does not yet exist
        ResponseEntity<User> response = restTemplate.postForEntity(url, request , User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }



}