package pact.consumer;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import br.ce.wcaquino.tasksfrontend.repository.TaskRepository;
import org.junit.Rule;
import org.junit.Test;

public class UpdateTaskTest {
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("Tasks", this);

    @Pact(consumer = "TasksFront")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        DslPart requestbody = new PactDslJsonBody()
                .numberType("id", 1)
                .stringType("task", "task update")
                .array("duedate").numberType(2020).numberType(9).numberType(31).closeArray();

        return builder
                .given("there is a task with id=1")
                .uponReceiving("update a task")
                .path("/tod/1")
                .method("put")
                .body(requestbody)
                .willRespondWith()
                .status(200)
                .toPact();

    }

    @Test
    @PactVerification
    public void shouldUpdateTask(){
        //TaskRepository repo = new TaskRepository(mockProvider.getUrl());
        //repo.update

    }
}
