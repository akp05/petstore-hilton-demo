# Petstore Hilton Demo - CLAUDE.md

## Project Overview
This is a **Spring Boot 3.3.0 + OpenAPI 3.0 Petstore Demo** project that demonstrates modern Java development practices using OpenAPI code generation, comprehensive testing, and Spring Boot features. Built as part of the Hilton-Reno team's AI agent evaluation process.

## Quick Start Commands

### Essential Build & Test and Development Commands
```bash
# Run tests (use this frequently for validation)
mvn test

# Clean and compile
mvn clean compile

# Clean rebuild if dependencies are conflicting
mvn clean install -U

# Run the application (starts on port 8080)
mvn spring-boot:run

# Generate OpenAPI code from YAML specifications
mvn clean generate-sources -Pgenerate-swagger-versions

# Check for dependency updates
mvn versions:display-dependency-updates

# Build Docker image (if Dockerfile exists)
docker build -t petstore-demo 
```

## Project Architecture

### Technology Stack
- **Java 21** - Modern LTS Java version
- **Spring Boot 3.3.0** - Web framework with embedded Tomcat
- **OpenAPI 3.0** - API specification and code generation
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework for tests
- **Jackson** - JSON serialization with RFC3339 date format
- **Maven 3.9.11** - Build and dependency management

### Project Structure
```
src/
├── main/java/org/openapitools/
│   ├── api/                               # Generated API interfaces & controllers
│   ├── model/                            # Generated model classes  
│   ├── service/                          # Business logic services
│   ├── validator/                        # Input validation components
│   └── configuration/                    # Spring configuration
├── main/resources/
└── test/java/org/openapitools/          # Comprehensive unit tests
    ├── model/
    └── api/
```


## Development Guidelines

### Code Generation Workflow

#### Swagger Regeneration Workflow
1. After swagger regen to the latest version the generated code appears in `target/generated-sources/openapi-v*`
2. Incorporates these regenerated Swagger changes. Keep all original method bodies and implementations intact. Only update API method signatures, models, and annotations where they differ according to the diff. Do not remove any custom logic from implementation classes

### Testing Strategy

#### Integration Test Strategy

1. Generate a Cucumber/Gherkin `.feature` file for a given endpoint

2. Requirements:
   - Follow EXACTLY this style and formatting:
       - Feature: Short, business-oriented statement of the functionality.
       - Background: Common setup for all scenarios (endpoint path).
       - Scenario: Numbered, descriptive, covering success/failure paths.
       - Given: Define path params, query params, and mocked external service calls in tabular format:
         | url | method | responseCode | responseBodyFile | responseHeaders | queryParams |
       - When: Describe HTTP method and endpoint call.
       - Then: Verify HTTP status code, JSON response match, and that certain external requests were made (also in table format).
   - Use WireMock-style mocking for external service calls.
   - Use realistic mock JSON file names (e.g., `response_<id>.json`, `expected_response_<id>.json`). Create wiremock request/response seed data under wire-mock.files folder
   - Include brief `# comments` in scenarios explaining assumptions and setup.

3. Match formatting exactly as in the provided example:
    ````
    Example snippet:
    Feature: Retrieve Pet Details
    
    # This feature tests the retrieval of Pet details from the Petstore service.
    # It covers success, related entity presence, partial/no data, and error scenarios.
    # External service calls are mocked using WireMock to ensure deterministic results.
    
    Background:
    Given an endpoint of "/v2/pet/{petId}"
    
    Scenario: [1] A GET request is successful when a pet with complete details exists
    # This scenario assumes petId 1001 exists in the datastore with full details.
    Given path param "petId" = "1001"
    And query param "includeCategory" = "true"
    And the external calls are mocked as
    | url                   | method | responseCode | responseBodyFile          | responseHeaders               | queryParams |
    | /categories/200       | GET    | 200          | category_response_200.json | Content-Type:application/json |             |
    When a "GET" request to the endpoint is sent
    Then the response has status code = 200
    And the response matches the data in the file "expected_pet_response_1001.json"
    And verify that the following requests were made
    | url             | method | queryParams |
    | /categories/200 | GET    |             |

#### Unit test Strategy

1. Generate a JUnit 5 + Mockito unit test class.
2. Follow the Arrange → Act → Assert pattern.
3. Create a @BeforeEach method to initialize mocks and the service under test.
4. Use mock(), when(), verify(), and times() for mocking and verification.
5. Include helper methods to build request and response objects.
6. Verify both positive (successful) and negative (failure/no-op) scenarios.


## API Documentation & Examples

### Sample API Usage
```bash
# Start the application
mvn spring-boot:run

# Test API endpoint 
curl -X GET "http://localhost:8080/v2/pet/findByStatus?status=available"

```

## Common Development Tasks

### Adding New API Implementation
1. Override the default method from the interface
2. Add business logic and proper return values
3. Add corresponding unit tests

## Troubleshooting

### Common Issues & Solutions

#### Runtime Issues  
```bash
# Check if port 8080 is available
netstat -an | findstr :8080

# Verify Java version
java --version

# Check application logs
mvn spring-boot:run | tee application.log
```

## Additional Features & Extensions

### Development Best Practices
- Always run `mvn test` before committing changes
- Follow the existing code style and naming conventions  
- Add comprehensive tests for new features (happy path + error cases + edge cases)
- Use the OpenAPI specification as the single source of truth
- Keep business logic separate from generated code
- Document any custom configurations or workarounds

## 📋 Implementation Draft

### Task Summary
[What you're going to do in 1-2 sentences]

### Files Impact Analysis
#### Files to be CREATED:
- `path/to/new/file.java` - [purpose/description]
- `path/to/another/file.feature` - [purpose/description]

#### Files to be MODIFIED:
- `path/to/existing/file.java` - [what specific changes]
    - Lines X-Y: [specific change description]
    - New method: [method name and purpose]
- `path/to/config.yml` - [configuration changes]

#### Files to be DELETED:
- `path/to/old/file.java` - [reason for deletion]
