package com.disney.graphql.studentservicegraphql.controller;

import com.disney.graphql.studentservicegraphql.service.StudentService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GrapQLControiller {

    private final GraphQL graphQL;

    public GrapQLControiller(StudentService studentService) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(studentService)
                .withValueMapperFactory( new JacksonValueMapperFactory())
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
    }


    @PostMapping(value = "/graphql")
    @ResponseBody
    public Map<String, Object> grapql(@RequestBody Map<String, String> request, HttpServletRequest httpServletRequest) {
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
                .query(request.get("query"))
                .operationName(request.get("operationName"))
                .context(httpServletRequest)
                .build());
        return executionResult.toSpecification();

    }
}
