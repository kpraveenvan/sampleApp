package com.example.employeeService;

import com.example.employeeService.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeServiceITTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void deleteAllBeforeTests() throws Exception {
//        employeeRepository.deleteAll();
    }

    @Test
    public void getAllEmployeesTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$._embedded.employees").exists())
                .andExpect(jsonPath("$._embedded.employees", hasSize(6)))
                .andExpect(jsonPath("$._embedded.employees[0].firstName", is("Praveen")))
                .andExpect(jsonPath("$._embedded.employees[1].firstName", is("Paul")))
                .andExpect(jsonPath("$._embedded.employees[2].firstName", is("Bruce")))
                .andExpect(jsonPath("$._embedded.employees[0].lastName", is("Vanga")))
                .andExpect(jsonPath("$._embedded.employees[1].lastName", is("Rodd")))
                .andExpect(jsonPath("$._embedded.employees[2].lastName", is("Banner")))
                .andExpect(jsonPath("$._embedded.employees[0].departmentId", is("1")))
                .andExpect(jsonPath("$._embedded.employees[1].departmentId", is("1")))
                .andExpect(jsonPath("$._embedded.employees[2].departmentId", is("1")));
    }

    @Test
    public void getOneEmployeesTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$._embedded.employees.firstName", is("Praveen")))
                .andExpect(jsonPath("$._embedded.employees.lastName", is("Vanga")))
                .andExpect(jsonPath("$._embedded.employees.departmentId", is("1")));
    }

    @Test
    public void updateOneEmployee() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Praveen\", \"lastName\": \"Vanga\", \"departmentId\" : 2}")
                ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createNewEmployee() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Praveen2\", \"lastName\": \"Vanga2\", \"departmentId\" : 2}"))
                .andExpect(status().is2xxSuccessful());
    }


}
